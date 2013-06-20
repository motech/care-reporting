package org.motechproject.care.reporting.migration.util;

import com.google.gson.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class CommcareDataConverter {

    private static Document createNewDocument(String rootElementName) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();
            Element rootElement = document.createElement(rootElementName);
            document.appendChild(rootElement);
            return document;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toFormXml(String formJson) {
        Document document = createNewDocument("data");
        Element rootElement = document.getDocumentElement();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(formJson);
        recursivelyParse(rootElement, (JsonObject) jsonObject.get("form"));
        return toString(document);
    }

    public static List<String> toCaseXml(String caseJson) {
        List<String> caseXmls = new ArrayList<>();

        JsonParser parser = new JsonParser();
        JsonObject jsonObject = (JsonObject) parser.parse(caseJson);

        Document createUpdateCase = processForCreateAndUpdate(jsonObject);
        caseXmls.add(toString(createUpdateCase));

        if (jsonObject.get("closed").getAsBoolean()) {
            Document closeCase = processForClosedCase(jsonObject);
            caseXmls.add(toString(closeCase));
        }
        return caseXmls;
    }

    private static Document processForCreateAndUpdate(JsonObject jsonObject) {
        List<String> createPropertyNames = asList("case_type", "case_name", "owner_id");
        JsonObject properties = jsonObject.get("properties").getAsJsonObject();
        JsonObject indices = jsonObject.get("indices").getAsJsonObject();
        Document caseDocument = createCaseAndPopulateAttributes(jsonObject);

        processCreate(createPropertyNames, properties, caseDocument);
        processUpdate(createPropertyNames, properties, caseDocument);
        if (indices.has("mother_id")) {
            processIndex(indices, caseDocument);
        }

        return caseDocument;
    }

    private static void processCreate(List<String> createPropertyNames, JsonObject properties, Document caseDocument) {
        Element createElement = caseDocument.createElement("create");
        for (String property : createPropertyNames) {
            populateProperty(createElement, properties, property);
        }
        caseDocument.getDocumentElement().appendChild(createElement);
    }

    private static void processUpdate(List<String> createPropertyNames, JsonObject properties, Document caseDocument) {
        Element updateElement = caseDocument.createElement("update");
        for (Map.Entry<String, JsonElement> entry : properties.entrySet()) {
            if (createPropertyNames.contains(entry.getKey())) {
                continue;
            }
            JsonElement value = entry.getValue();
            if (!value.isJsonNull()) {
                populateProperty(updateElement, value.getAsString(), entry.getKey());
            }
        }
        caseDocument.getDocumentElement().appendChild(updateElement);
    }

    private static void processIndex(JsonObject indices, Document caseDocument) {
        Element indexElement = caseDocument.createElement("index");
        JsonObject mother = indices.get("mother_id").getAsJsonObject();
        Element motherElement = populateProperty(indexElement, mother.get("case_id").getAsString(), "mother_id");
        motherElement.setAttribute("case_type", mother.get("case_type").getAsString());
        caseDocument.getDocumentElement().appendChild(indexElement);
    }

    private static Element populateProperty(Element element, String propertyValue, String propertyName) {
        Element propertyElement = element.getOwnerDocument().createElement(propertyName);
        propertyElement.setTextContent(propertyValue);
        element.appendChild(propertyElement);
        return propertyElement;
    }

    private static Element populateProperty(Element element, JsonObject jsonObject, String propertyName) {
        String propertyValue = jsonObject.get(propertyName).getAsString();
        return populateProperty(element, propertyValue, propertyName);
    }

    private static Document processForClosedCase(JsonObject jsonObject) {
        Document caseDocument = createCaseAndPopulateAttributes(jsonObject);
        Element closeElement = caseDocument.createElement("close");
        caseDocument.getDocumentElement().appendChild(closeElement);
        return caseDocument;
    }

    private static Document createCaseAndPopulateAttributes(JsonObject jsonObject) {
        Document closedCaseDocument = createNewDocument("case");
        Element caseElement = closedCaseDocument.getDocumentElement();
        populateCaseAttributes(caseElement, jsonObject);
        return closedCaseDocument;
    }

    private static void populateCaseAttributes(Element caseElement, JsonObject jsonObject) {
        String caseId = jsonObject.get("case_id").getAsString();
        caseElement.setAttribute("case_id", caseId);
        String dateModified = jsonObject.get("date_modified").getAsString();
        caseElement.setAttribute("date_modified", dateModified);
        String userId = jsonObject.get("user_id").getAsString();
        caseElement.setAttribute("user_id", userId);
        caseElement.setAttribute("xmlns", "http://commcarehq.org/case/transaction/v2");
    }

    static String toString(Node node) {
        try {
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            StringWriter sw = new StringWriter();
            transformer.transform(new DOMSource(node), new StreamResult(sw));
            return sw.toString();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static void recursivelyParse(Element parentElement, JsonPrimitive jsonPrimitive, String nodeName) {
        String value = jsonPrimitive.getAsString();
        if (isValue(nodeName)) {
            parentElement.setTextContent(value);
            return;
        }
        if (isAttribute(nodeName)) {
            parentElement.setAttribute(nodeName.substring(1), value);
            return;
        }
        Element element = parentElement.getOwnerDocument().createElement(nodeName);
        element.setTextContent(value);
        parentElement.appendChild(element);
    }

    private static void recursivelyParse(Element parentElement, JsonArray jsonArray, String nodeName) {
        for (int i = 0; i < jsonArray.size(); i++) {
            recursivelyParse(parentElement, jsonArray.get(i), nodeName);
        }
    }

    private static void recursivelyParse(Element parentElement, JsonObject jsonObject, String nodeName) {
        Element element = parentElement.getOwnerDocument().createElement(nodeName);
        parentElement.appendChild(element);
        recursivelyParse(element, jsonObject);
    }

    private static void recursivelyParse(Element thisElement, JsonObject jsonObject) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            recursivelyParse(thisElement, entry.getValue(), entry.getKey());
        }
    }

    private static void recursivelyParse(Element parentElement, JsonElement jsonElement, String nodeName) {
        if (jsonElement.isJsonPrimitive()) {
            recursivelyParse(parentElement, jsonElement.getAsJsonPrimitive(), nodeName);
            return;
        }

        if (jsonElement.isJsonArray()) {
            recursivelyParse(parentElement, jsonElement.getAsJsonArray(), nodeName);
            return;
        }

        if (jsonElement.isJsonObject()) {
            recursivelyParse(parentElement, jsonElement.getAsJsonObject(), nodeName);
        }
    }

    private static boolean isAttribute(String key) {
        if (key.startsWith("@")) {
            return true;
        }
        return false;
    }

    private static boolean isValue(String key) {
        if (key.startsWith("#")) {
            return true;
        }
        return false;
    }
}
