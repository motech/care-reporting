package org.motechproject.care.reporting.factory;

import org.motechproject.care.reporting.domain.measure.*;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.enums.FormType;
import org.motechproject.care.reporting.mapper.NamespaceMapper;
import org.motechproject.care.reporting.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class FormFactory {

    private static Map<Pair<FormType, CaseType>, Class<?>> mapper = new HashMap<Pair<FormType, CaseType>, Class<?>>() {{
        put(new Pair<>(FormType.New, CaseType.Mother), NewForm.class);

        put(new Pair<>( FormType.Registration, CaseType.Mother), RegistrationMotherForm.class);
        put(new Pair<>(FormType.Registration, CaseType.Child), RegistrationChildForm.class);

        put(new Pair<>(FormType.Cf, CaseType.Mother), CfMotherForm.class);
        put(new Pair<>(FormType.Cf, CaseType.Child), CfChildForm.class);

        put(new Pair<>(FormType.Close, CaseType.Mother), CloseMotherForm.class);
        put(new Pair<>(FormType.Close, CaseType.Child), CloseChildForm.class);

        put(new Pair<>(FormType.Death, CaseType.Mother), DeathMotherForm.class);
        put(new Pair<>(FormType.Death, CaseType.Child), DeathChildForm.class);

        put(new Pair<>(FormType.Ebf, CaseType.Mother), EbfMotherForm.class);
        put(new Pair<>(FormType.Ebf, CaseType.Child), EbfChildForm.class);

        put(new Pair<>(FormType.Pnc, CaseType.Mother), PncMotherForm.class);
        put(new Pair<>(FormType.Pnc, CaseType.Child), PncChildForm.class);

        put(new Pair<>(FormType.Refer, CaseType.Mother), ReferMotherForm.class);
        put(new Pair<>(FormType.Refer, CaseType.Child), ReferChildForm.class);

        put(new Pair<>(FormType.Ui, CaseType.Mother), UiMotherForm.class);
        put(new Pair<>(FormType.Ui, CaseType.Child), UiChildForm.class);

        put(new Pair<>(FormType.Delivery, CaseType.Mother), DeliveryMotherForm.class);
        put(new Pair<>(FormType.Delivery, CaseType.Child), DeliveryChildForm.class);

        put(new Pair<>(FormType.Abort, CaseType.Mother), AbortForm.class);
        put(new Pair<>(FormType.Bp, CaseType.Mother), BpForm.class);
        put(new Pair<>(FormType.Mi, CaseType.Mother), MiForm.class);
        put(new Pair<>(FormType.Mo, CaseType.Mother), MoForm.class);
    }};

    public static Class<?> getForm(String namespace, CaseType caseType){
        FormType formType = NamespaceMapper.getFormType(namespace);
        Pair<FormType, CaseType> inputPair = new Pair<>(formType, caseType);

        return mapper.get(inputPair);
    }

}
