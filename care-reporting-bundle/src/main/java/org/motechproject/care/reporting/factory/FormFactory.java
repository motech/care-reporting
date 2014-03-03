package org.motechproject.care.reporting.factory;

import org.motechproject.care.reporting.domain.measure.MoveBeneficiaryForm;
import org.motechproject.care.reporting.domain.measure.*;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.enums.FormType;
import org.motechproject.care.reporting.mapper.NamespaceMapper;
import org.motechproject.care.reporting.utils.Pair;

import java.util.HashMap;
import java.util.Map;

public class FormFactory {

    private static Map<Pair<FormType, CaseType>, Class<?>> mapper = new HashMap<Pair<FormType, CaseType>, Class<?>>() {{
        put(new Pair<>(FormType.New, CaseType.MOTHER), NewForm.class);

        put(new Pair<>(FormType.Registration, CaseType.MOTHER), RegistrationMotherForm.class);
        put(new Pair<>(FormType.Registration, CaseType.CHILD), RegistrationChildForm.class);

        put(new Pair<>(FormType.Cf, CaseType.MOTHER), CfMotherForm.class);
        put(new Pair<>(FormType.Cf, CaseType.CHILD), CfChildForm.class);

        put(new Pair<>(FormType.Close, CaseType.MOTHER), CloseMotherForm.class);
        put(new Pair<>(FormType.Close, CaseType.CHILD), CloseChildForm.class);

        put(new Pair<>(FormType.Death, CaseType.MOTHER), DeathMotherForm.class);
        put(new Pair<>(FormType.Death, CaseType.CHILD), DeathChildForm.class);

        put(new Pair<>(FormType.Ebf, CaseType.MOTHER), EbfMotherForm.class);
        put(new Pair<>(FormType.Ebf, CaseType.CHILD), EbfChildForm.class);

        put(new Pair<>(FormType.Pnc, CaseType.MOTHER), PncMotherForm.class);
        put(new Pair<>(FormType.Pnc, CaseType.CHILD), PncChildForm.class);

        put(new Pair<>(FormType.Refer, CaseType.MOTHER), ReferMotherForm.class);
        put(new Pair<>(FormType.Refer, CaseType.CHILD), ReferChildForm.class);

        put(new Pair<>(FormType.Ui, CaseType.MOTHER), UiMotherForm.class);
        put(new Pair<>(FormType.Ui, CaseType.CHILD), UiChildForm.class);

        put(new Pair<>(FormType.Delivery, CaseType.MOTHER), DeliveryMotherForm.class);
        put(new Pair<>(FormType.Delivery, CaseType.CHILD), DeliveryChildForm.class);

        put(new Pair<>(FormType.Abort, CaseType.MOTHER), AbortForm.class);
        put(new Pair<>(FormType.Bp, CaseType.MOTHER), BpForm.class);
        put(new Pair<>(FormType.Mi, CaseType.MOTHER), MiForm.class);
        put(new Pair<>(FormType.Mo, CaseType.MOTHER), MoForm.class);
        put(new Pair<>(FormType.MoveBeneficiary, CaseType.MOTHER), MoveBeneficiaryForm.class);
        put(new Pair<>(FormType.MotherEdit, CaseType.MOTHER), MotherEditForm.class);

        put(new Pair<>(FormType.AwwRegisterChild, CaseType.MOTHER), AwwRegisterMotherForm.class);
        put(new Pair<>(FormType.AwwRegisterChild, CaseType.CHILD), AwwRegisterChildForm.class);
        put(new Pair<>(FormType.AwwGrowthMonitoring1, CaseType.CHILD), AwwGrowthMonitoringChildForm1.class);
        put(new Pair<>(FormType.AwwGrowthMonitoring2, CaseType.CHILD), AwwGrowthMonitoringChildForm2.class);
        put(new Pair<>(FormType.AwwThrMother, CaseType.MOTHER), AwwThrMotherForm.class);
        put(new Pair<>(FormType.AwwClose, CaseType.CHILD), AwwCloseChildForm.class);
        put(new Pair<>(FormType.AwwEditChild, CaseType.CHILD), AwwEditChildForm.class);
        put(new Pair<>(FormType.AwwUpdateVaccinations, CaseType.CHILD), AwwUpdateVaccinationsChildForm.class);
        put(new Pair<>(FormType.AwwThrChild, CaseType.CHILD), AwwThrChildForm.class);
        put(new Pair<>(FormType.AwwPreschoolActivities, CaseType.MOTHER), AwwPreschoolActivitiesForm.class);
        put(new Pair<>(FormType.AwwPreschoolActivities, CaseType.CHILD), AwwPreschoolActivitiesChildForm.class);

        put(new Pair<>(FormType.CcsGrowthMonitoring, CaseType.CHILD), GrowthMonitoringChildForm.class);
    }};

    public static Class<?> getForm(String namespace, CaseType caseType) {
        FormType formType = NamespaceMapper.getFormType(namespace);
        Pair<FormType, CaseType> inputPair = new Pair<>(formType, caseType);

        return mapper.get(inputPair);
    }

}
