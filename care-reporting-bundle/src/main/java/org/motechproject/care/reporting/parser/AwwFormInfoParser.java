package org.motechproject.care.reporting.parser;

import org.motechproject.care.reporting.enums.FormSegment;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

public class AwwFormInfoParser extends FormInfoParser {
    protected static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");
    protected static final String NAMESPACE_ATTRIBUTE_NAME = "xmlns";
    protected static final String CASE = "case";
    private static final Map<String, AwwCaseType> namespaceToCaseType = new HashMap<String, AwwCaseType>() {{
        put("http://bihar.commcarehq.org/pregnancy/aww_reg_child", AwwCaseType.MOTHER_AND_CHILD);
        put("http://bihar.commcarehq.org/pregnancy/aww_child_edit", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_preschool_activities", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_migrate_out", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_migrate_in", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_growth_monitoring_1", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_growth_monitoring_2", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_child_thr", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_update_vaccinations", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_close", AwwCaseType.CHILD_ONLY);
        put("http://bihar.commcarehq.org/pregnancy/aww_mother_thr", AwwCaseType.MOTHER_ONLY);
    }};

    public AwwFormInfoParser(InfoParser infoParser, FormSegment formSegment) {
        super(infoParser, formSegment);
    }

    public static AwwCaseType getCaseTypeFromNamespace(String namespace) {
        if (namespaceToCaseType.containsKey(namespace)) {
            return namespaceToCaseType.get(namespace);
        }

        return null;
    }
}
