package org.motechproject.care.reporting.parser;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class AwwChildCaseParser extends AwwFormInfoParser {
    public AwwChildCaseParser(InfoParser infoParser) {
        super(infoParser, FormSegment.CHILD);
    }

    public List<Map<String, String>> parse(CommcareForm commcareForm) {
        List<Map<String, String>> childrenMap = new ArrayList<>();
        String namespace = commcareForm.getForm().getAttributes().get(NAMESPACE_ATTRIBUTE_NAME);
        AwwCaseType caseType = this.getCaseTypeFromNamespace(namespace);
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

    private List<FormValueElement> getChildCaseElements(CommcareForm commcareForm, AwwCaseType caseType) {
        List<FormValueElement> childCaseElements = new ArrayList<>();
        String nodeName = caseType.getChildCaseRootNode();

        switch (caseType) {
            case CHILD_ONLY:
                childCaseElements.add(commcareForm.getForm().getChildElement(CASE));
                break;
            case MOTHER_ONLY:
                break;
            case MOTHER_AND_CHILD:
                for (Map.Entry<String, FormValueElement> element : commcareForm.getForm().getElements().entrySet()) {
                    if (element.getKey().contains(nodeName)) {
                        FormValueElement childCase = element.getValue().getChildElement(CASE);
                        if (childCase != null) {
                            childCaseElements.add(childCase);
                        }
                    }
                }
                break;
            default:
                break;
        }

        return childCaseElements;
    }
}
