package org.motechproject.care.reporting.builder;


import org.motechproject.commcare.domain.CommcareForm;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.HashMap;
import java.util.Map;

public class CommcareFormBuilder {

    FormValueElementBuilder formBuilder = new FormValueElementBuilder();
    CommcareForm form = new CommcareForm();
    Map<String, String> metadata = new HashMap<>();


    public CommcareFormBuilder addMetadata(String key, String value){
        metadata.put(key, value);
        return this;
    }

    public CommcareFormBuilder addAttribute(String key, String value){
        formBuilder.addAttribute(key, value);
        return this;
    }

    public CommcareFormBuilder addSubElement(String key, String value){
        formBuilder.addSubElement(key, value);
        return this;
    }

    public CommcareFormBuilder addSubElement(String key, FormValueElement value){
        formBuilder.addSubElement(key, value);
        return this;
    }


    public CommcareForm build(){

        form.setForm(formBuilder.build());
        form.setMetadata(metadata);

        return form;
    }

}
