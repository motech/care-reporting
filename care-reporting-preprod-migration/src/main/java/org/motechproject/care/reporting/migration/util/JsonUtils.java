package org.motechproject.care.reporting.migration.util;

import com.google.gson.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.Map;

public class JsonUtils {

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
        if(isValue(nodeName)) {
            parentElement.setTextContent(value);
            return;
        }
        if(isAttribute(nodeName)) {
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
