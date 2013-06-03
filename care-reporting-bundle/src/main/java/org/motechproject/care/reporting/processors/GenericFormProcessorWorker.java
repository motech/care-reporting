package org.motechproject.care.reporting.processors;

import org.apache.commons.lang.StringUtils;
import org.motechproject.care.reporting.domain.dimension.ChildCase;
import org.motechproject.care.reporting.domain.dimension.Flw;
import org.motechproject.care.reporting.domain.dimension.MotherCase;
import org.motechproject.care.reporting.enums.CaseType;
import org.motechproject.care.reporting.factory.FormFactory;
import org.motechproject.care.reporting.mapper.GenericMapper;
import org.motechproject.care.reporting.service.Service;
import org.motechproject.care.reporting.utils.ObjectUtils;
import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.care.reporting.parser.ChildInfoParser;
import org.motechproject.care.reporting.parser.MetaInfoParser;
import org.motechproject.care.reporting.parser.MotherInfoParser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GenericFormProcessorWorker {

    private CommcareForm commcareForm;
    private Map<String, String> metadata;
    private Service service;
    Class<?> motherForm;
    Class<?> childForm;

    public GenericFormProcessorWorker(Service service){
        this.service = service;
    }

    public void process(CommcareForm commcareForm){
        initialize(commcareForm);
        Serializable serializable = parseMotherForm();
        saveForm(serializable, motherForm);

        List<Serializable> serializables = parseChildForms();
        saveForm(serializables, childForm);
    }

    void initialize(CommcareForm commcareForm){
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

    List<Serializable> parseChildForms(){
        if(null == childForm)
            return new ArrayList<>();

        List<Serializable> childForms = new ArrayList<>();
        List<Map<String, String>> childDetails = new ChildInfoParser().parse(commcareForm);

        for(Map<String, String> childDetail: childDetails){

            Map<String, String> childInfo = new HashMap<>(metadata);
            childInfo.putAll(childDetail);

            Serializable formObject = (Serializable) new GenericMapper().map(childInfo, childForm);
            setChildCase(childInfo.get("caseId"), formObject);
            setFlw(childInfo.get("userID"), formObject);
            childForms.add(formObject);
        }
        return childForms;
    }

     void saveForm(Serializable form, Class<?> type){
         service.save(type.cast(form));
     }

     void saveForm(List<Serializable> forms, Class<?> type){
        for(Serializable form: forms){
            saveForm(form, type);
        }
     }

    private void setMotherCase(String caseId, Object form){
        if(StringUtils.isEmpty(caseId)){
            return;
        }

        MotherCase motherCase = service.getMotherCase(caseId);
        ObjectUtils.set(form, "motherCase", motherCase);
    }

    private void setChildCase(String caseId, Object form){
        if(StringUtils.isEmpty(caseId)){
            return;
        }

        ChildCase childCase = service.getChildCase(caseId);
        ObjectUtils.set(form, "childCase", childCase);
    }

    private void setFlw(String flwId, Object form){
        if(StringUtils.isEmpty(flwId)){
            return;
        }

        Flw flw = service.getFlw(flwId);
        ObjectUtils.set(form, "flw", flw);
    }


    private String namespace(CommcareForm commcareForm) {
        return commcareForm.getForm().getAttributes().get("xmlns");
    }
}


