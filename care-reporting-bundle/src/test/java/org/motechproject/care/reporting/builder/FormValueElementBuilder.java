package org.motechproject.care.reporting.builder;

import com.google.common.collect.HashMultimap;
import org.motechproject.commcare.domain.FormValueElement;

import java.util.HashMap;
import java.util.Map;

public class FormValueElementBuilder {

    private FormValueElement element = new FormValueElement();
    private Map<String, String> attributes = new HashMap<>();
    private HashMultimap<String, FormValueElement> subElements = new HashMultimap<>();
    private String elementName = "form";

    public FormValueElementBuilder withName(String elementName) {
        this.elementName = elementName;
        return this;
    }

    public FormValueElementBuilder addAttribute(String key, String value){
        attributes.put(key, value);
        return this;
    }

    public FormValueElementBuilder addSubElement(String key, String value){
        subElements.put(key, formWithSingleValue(key, value));
        return this;
    }

    public FormValueElementBuilder addSubElement(String key, FormValueElement value){
        value.setElementName(key);
        subElements.put(key, value);
        return this;
    }

    public FormValueElement build(){
        element.setAttributes(attributes);
        element.setSubElements(subElements);
        element.setElementName(elementName);
        return element;
    }

    private FormValueElement formWithSingleValue(final String elementName, final String value) {
        return new FormValueElement() {{
            setValue(value);
            setElementName(elementName);
        }};
    }

}
