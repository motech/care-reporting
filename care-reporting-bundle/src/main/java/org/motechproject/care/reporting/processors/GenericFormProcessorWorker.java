package org.motechproject.care.reporting.processors;

import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.factory.FormFactory;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.parser.ChildInfoParser;
import org.motechproject.care.reporting.parser.MetaInfoParser;
import org.motechproject.care.reporting.parser.MotherInfoParser;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.commcare.domain.CommcareForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericFormProcessorWorker extends ProcessorWorker {
    private static final Logger logger = LoggerFactory.getLogger("commcare-reporting-mapper");

    Class<?> motherForm;
    Class<?> childForm;
    private CommcareForm commcareForm;
    private Map<String, String> metadata;

    public GenericFormProcessorWorker(Service service) {
        super(service);
    }

    public void process(CommcareForm commcareForm) {
        initialize(commcareForm);
        Serializable serializable = parseMotherForm();
        saveForm(serializable, motherForm);

        List<Serializable> serializables = parseChildForms();
        saveForm(serializables, childForm);
    }

    void initialize(CommcareForm commcareForm) {
        this.commcareForm = commcareForm;
        metadata = new MetaInfoParser().parse(commcareForm);
        String namespace = namespace(commcareForm);
        motherForm = FormFactory.getForm(namespace, CaseType.Mother);
        childForm = FormFactory.getForm(namespace, CaseType.Child);
    }

    Serializable parseMotherForm() {
        Map<String, String> motherInfo = new HashMap<>(metadata);
        motherInfo.putAll(new MotherInfoParser().parse(commcareForm));

        Object formObject = new GenericMapper().map(motherInfo, motherForm);

        setMotherCase(motherInfo.get("caseId"), formObject);
        setFlw(motherInfo.get("userID"), formObject);

        return (Serializable) formObject;
    }

    List<Serializable> parseChildForms() {
        if (null == childForm)
            return new ArrayList<>();

        List<Serializable> childForms = new ArrayList<>();
        List<Map<String, String>> childDetails = new ChildInfoParser().parse(commcareForm);

        for (Map<String, String> childDetail : childDetails) {

            Map<String, String> childInfo = new HashMap<>(metadata);
            childInfo.putAll(childDetail);

            Serializable formObject = (Serializable) new GenericMapper().map(childInfo, childForm);
            setChildCase(childInfo.get("caseId"), formObject);
            setFlw(childInfo.get("userID"), formObject);
            childForms.add(formObject);
        }
        return childForms;
    }

    void saveForm(Serializable form, Class<?> type) {
        logger.info(String.format("Started processing form %s", form));
        service.save(type.cast(form));
        logger.info(String.format("Finished processing form %s", form));
    }

    void saveForm(List<Serializable> forms, Class<?> type) {
        for (Serializable form : forms) {
            saveForm(form, type);
        }
    }

    private String namespace(CommcareForm commcareForm) {
        return commcareForm.getForm().getAttributes().get("xmlns");
    }
}


