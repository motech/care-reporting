package org.motechproject.care.reporting.mapper;


import org.motechproject.care.reporting.enums.FormType;

import java.util.HashMap;
import java.util.Map;

public class NamespaceMapper {

    static Map<String, FormType> formMapper = new HashMap<String, FormType>() {{
        String prefix = "http://bihar.commcarehq.org/pregnancy/";
        put(prefix + "new", FormType.New);
        put(prefix + "registration", FormType.Registration);
        put(prefix + "bp", FormType.Bp);
        put(prefix + "ebf", FormType.Ebf);
        put(prefix + "cf", FormType.Cf);
        put(prefix + "pnc", FormType.Pnc);
        put(prefix + "refer", FormType.Refer);
        put(prefix + "death", FormType.Death);
        put(prefix + "del", FormType.Delivery);
        put(prefix + "close", FormType.Close);
        put(prefix + "migrate_out", FormType.Mo);
        put(prefix + "migrate_in", FormType.Mi);
        put(prefix + "mtp_abort", FormType.Abort);
        put(prefix + "update_vaccinations", FormType.Ui);
    }};

    public static FormType getFormType(String namespace){
        return formMapper.get(namespace);
    }
}
