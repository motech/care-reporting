package org.motechproject.care.reporting.mapper;


import org.motechproject.care.reporting.enums.FormType;

import java.util.HashMap;
import java.util.Map;

public class NamespaceMapper {

    static Map<String, FormType> formMapper = new HashMap<String, FormType>() {{
        put("http://bihar.commcarehq.org/pregnancy/new", FormType.New);
        put("http://bihar.commcarehq.org/pregnancy/registration", FormType.Registration);
    }};

    public static FormType getFormType(String namespace){
        return formMapper.get(namespace);
    }
}
