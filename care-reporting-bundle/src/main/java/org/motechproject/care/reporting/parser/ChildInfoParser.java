package org.motechproject.care.reporting.parser;

import com.google.common.collect.Multimap;
import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class ChildInfoParser extends FormInfoParser {

    public ChildInfoParser(InfoParser infoParser) {
        super(infoParser, FormSegment.CHILD);
    }

    public List<Map<String, String>> parse(CommcareForm commcareForm) {
        List<Map<String, String>> childrenMap = new ArrayList<>();
        if (!commcareForm.getForm().getAttributes().containsKey(NAMESPACE_ATTRIBUTE_NAME)) {
            return childrenMap;
        }
        String namespace = commcareForm.getForm().getAttributes().get(NAMESPACE_ATTRIBUTE_NAME);
        FormCaseType caseType = ChildInfoParser.getCaseTypeFromNamespace(namespace);
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
            childCaseElements.add(commcareForm.getForm());
        } else if (caseType.equals(FormCaseType.AWW_MOTHER_AND_CHILD)
                || caseType.equals(FormCaseType.CCS_MOTHER_AND_CHILD)) {
            childCaseElements.addAll(findChildCaseElements(caseType, commcareForm, true));
        } else if (caseType.equals(FormCaseType.CHILD_MANY_TO_MANY)) {
            childCaseElements.addAll(findChildCaseElements(caseType, commcareForm, false));
        }

        return childCaseElements;
    }

    private List<FormValueElement> findChildCaseElements(FormCaseType caseType, CommcareForm commcareForm,
                                                         boolean useStringContains) {
        String nodeName = caseType.getChildCaseRootNode();
        Multimap<String, FormValueElement> elements = commcareForm.getForm().getSubElements();
        List<FormValueElement> childCaseElements = new ArrayList<>();

        if (elements == null) {
            return childCaseElements;
        }

        for (Map.Entry<String, FormValueElement> element : elements.entries()) {
            if (useStringContains && element.getKey().contains(nodeName)) {
                childCaseElements.add(element.getValue());
            } else if (!useStringContains && element.getKey().equals(nodeName)) {
                childCaseElements.add(element.getValue());
            }
        }

        return childCaseElements;
    }

    @Override
    protected Map<String, String> parseCaseInfo(FormCaseType caseType, FormValueElement caseElement,
                                                CommcareForm commcareForm) {
        if (caseType == null || !caseType.equals(FormCaseType.CHILD_MANY_TO_MANY)) {
            return super.parseCaseInfo(caseType, caseElement, commcareForm);
        }

        return new HashMap<>();
    }
}
