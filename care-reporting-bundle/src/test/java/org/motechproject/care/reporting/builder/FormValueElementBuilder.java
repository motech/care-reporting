package org.motechproject.care.reporting.builder;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.HashMap;
import java.util.Map;

public class FormValueElementBuilder {

    FormValueElement element = new FormValueElement();
    Map<String, String> attributes = new HashMap<>();
    HashMultimap<String, FormValueElement> subElements = new HashMultimap<>();

    public FormValueElementBuilder addAttribute(String key, String value){
        attributes.put(key, value);
        return this;
    }

    public FormValueElementBuilder addSubElement(String key, String value){
        subElements.put(key, formWithSingleValue(value));
        return this;
    }

    public FormValueElementBuilder addSubElement(String key, FormValueElement value){
        subElements.put(key, value);
        return this;
    }

    public FormValueElement build(){
        element.setAttributes(attributes);
        element.setSubElements(subElements);
        return element;
    }

    private FormValueElement formWithSingleValue(final String value) {
        return new FormValueElement() {{
            setValue(value);
        }};
    }

}
