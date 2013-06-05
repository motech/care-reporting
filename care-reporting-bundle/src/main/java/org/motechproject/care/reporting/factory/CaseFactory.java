package org.motechproject.care.reporting.factory;

import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.enums.CaseType;

import java.util.HashMap;
import java.util.Map;

public class CaseFactory {

    private static Map<CaseType, Class<?>> caseMapper = new HashMap<CaseType, Class<?>>() {{
        put(CaseType.Mother, MotherCase.class);
        put(CaseType.Child, ChildCase.class);
    }};

    public static Class<?> getCase(String type) {
        final CaseType caseType = CaseType.getType(type);
        return caseMapper.get(caseType);
    }

}
