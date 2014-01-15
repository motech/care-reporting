package org.motechproject.care.reporting.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class FormFieldSplitter {

    private static final String MOTHER_FORM = "mother";
    private static final String CHILD_FORM = "child";
    private static final Map<String, Map<String, Set<String>>> NAMESPACE_TO_FORM_FIELDS_MAPPING =
            new HashMap<String, Map<String, Set<String>>>() {{
                put("http://bihar.commcarehq.org/pregnancy/aww_reg_child", new HashMap<String, Set<String>>() {{
                    put(MOTHER_FORM, new HashSet<String>() {{
                        add("motherName");
                        add("fatherName");
                        add("wardNumber");
                        add("hhNumber");
                        add("familyNumber");
                        add("mctsId");
                        add("aadharNumber");
                        add("mobileNumber");
                        add("mobileNumberWhose");
                        add("eatsMeat");
                        add("dob");
                        add("fullMctsId");
                        add("ownerIdCalc");
                        add("invalidOwner");
                        add("dobKnown");
                        add("dobEntered");
                        add("showAge");
                        add("ageCalc");
                        add("ageEstMonths");
                        add("ageEstYears");
                        add("updateMctsId");
                        add("updateAadharNumber");
                        add("caste");
                        add("resident");
                        add("success");
                    }});

                    put(CHILD_FORM, new HashSet<String>() {{
                        add("childName");
                        add("gender");
                        add("childMctsId");
                        add("childAlive");
                        add("dob");
                        add("fullChildMctsId");
                        add("ownerIdCalc");
                        add("invalidOwner");
                        add("success");
                    }});
                }});
            }};

    private FormFieldSplitter() {

    }

    public static boolean isNamespaceSupported(String namespace) {
        return NAMESPACE_TO_FORM_FIELDS_MAPPING.containsKey(namespace);
    }

    public static Map<String, List<Map<String, String>>> splitMotherAndChildrenFields(
            final String namespace,
            final Map<String, String> motherFields,
            final List<Map<String, String>> childFields) {
        if (!NAMESPACE_TO_FORM_FIELDS_MAPPING.containsKey(namespace)) {
            throw new IllegalArgumentException(String.format("Unsupported namespace: '%s'", namespace));
        }
        if (motherFields == null || childFields == null) {
            throw new IllegalArgumentException("Neither 'motherFields' nor 'childFields' can be NULL.");
        }
        final Map<String, Set<String>> fieldSplitLookup = NAMESPACE_TO_FORM_FIELDS_MAPPING.get(namespace);
        final Map<String, List<Map<String, String>>> formFields = new HashMap<>();

        Iterator<String> motherIterator = motherFields.keySet().iterator();
        while (motherIterator.hasNext()) {
            String motherField = motherIterator.next();

            if (fieldSplitLookup.get(CHILD_FORM).contains(motherField)) {
                boolean remove = false;

                for (Map<String, String> childFieldMap : childFields) {
                    if (childFieldMap.containsKey(motherField)) {
                        continue;
                    }

                    childFieldMap.put(motherField, motherFields.get(motherField));
                    if (!fieldSplitLookup.get(MOTHER_FORM).contains(motherField)) {
                        remove = true;
                    }
                }

                if (remove) {
                    motherIterator.remove();
                }
            }
        }

        formFields.put(MOTHER_FORM, new ArrayList<Map<String, String>>() {{ add(motherFields); }});
        formFields.put(CHILD_FORM, childFields);

        return formFields;
    }
}
