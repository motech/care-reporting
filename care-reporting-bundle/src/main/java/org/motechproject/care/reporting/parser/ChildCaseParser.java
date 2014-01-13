package org.motechproject.care.reporting.parser;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class ChildCaseParser extends FormInfoParser {

    public ChildCaseParser(InfoParser infoParser) {
        super(infoParser, FormSegment.CHILD);
    }

    public List<Map<String, String>> parse(CommcareForm commcareForm) {
        List<Map<String, String>> childrenMap = new ArrayList<>();
        if (!commcareForm.getForm().getAttributes().containsKey(NAMESPACE_ATTRIBUTE_NAME)) {
            return childrenMap;
        }
        String namespace = commcareForm.getForm().getAttributes().get(NAMESPACE_ATTRIBUTE_NAME);
        FormCaseType caseType = ChildCaseParser.getCaseTypeFromNamespace(namespace);
        if (caseType == null) {
            caseType = FormCaseType.CCS_MOTHER_AND_CHILD;
        }

        List<FormValueElement> childCaseElements = getChildCaseElements(commcareForm, caseType);
        if (childCaseElements == null) {
            return childrenMap;
        }

        for (FormValueElement childCaseElement : childCaseElements) {
            Map<String, String> childMap = parse(childCaseElement, commcareForm);
            if (childMap == null) {
                logger.warn(format("Skipping processing of child form with instance id %s", commcareForm.getId()));
                continue;
            }
            childrenMap.add(childMap);
        }
        return childrenMap;
    }

    private List<FormValueElement> getChildCaseElements(CommcareForm commcareForm, FormCaseType caseType) {
        List<FormValueElement> childCaseElements = new ArrayList<>();

        if (caseType.equals(FormCaseType.CHILD_ONLY)) {
            FormValueElement childCase = infoParser.getCaseElement(commcareForm.getForm());
            if (childCase != null) {
                childCaseElements.add(childCase);
            }
        } else if (caseType.equals(FormCaseType.AWW_MOTHER_AND_CHILD)
                || caseType.equals(FormCaseType.CCS_MOTHER_AND_CHILD)) {
            String nodeName = caseType.getChildCaseRootNode();
            List<FormValueElement> elements = commcareForm.getForm().getAllElements(nodeName);

            if (elements == null) {
                return childCaseElements;
            }

            for (FormValueElement element : elements) {
                if (element.getElementName().contains(nodeName)) {
                    childCaseElements.add(element);
                }
            }
        }

        return childCaseElements;
    }
}
