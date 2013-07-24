package org.motechproject.care.reporting.ft.utils;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class XFormAnalyzer {

    private Document document;
    private Map<String, String> datatypes = new HashMap<String, String>();

    public XFormAnalyzer(Document document) {
        this.document = document;
        processDatatypes();
    }

    private void processDatatypes() {
        NodeList bindElements = document.getElementsByTagName("bind");
        for(int i=0, len = bindElements.getLength(); i<len; i++) {
            Element bindElement = (Element) bindElements.item(i);
            String nodeset = bindElement.getAttribute("nodeset");
            if(StringUtils.isBlank(nodeset)) {
                continue;
            }
            addToDatatypes(nodeset, findDatatype(bindElement));
        }
        processSelect();
    }

    private void processSelect() {
        NodeList selectElements = document.getElementsByTagName("select1");
        for(int i=0, len = selectElements.getLength(); i<len; i++) {
            Element bindElement = (Element) selectElements.item(i);
            String ref = bindElement.getAttribute("ref");
            if(StringUtils.isBlank(ref)) {
                continue;
            }
            addToDatatypes(ref, "SELECT");
        }
    }

    private void addToDatatypes(String ref, String datatype) {
        String existingDatatype = datatypes.get(ref);
        if(existingDatatype != null && existingDatatype.startsWith("xsd:")) {   //dont override
            return;
        }
        datatypes.put(ref, datatype);
    }

    private String findDatatype(Element bindElement) {
        if(bindElement.hasAttribute("type")) {
            return bindElement.getAttribute("type");
        }
        if(bindElement.hasAttribute("calculate")) {
            return "CALCULATED";
        }
        if(bindElement.hasAttribute("relevant")) {
            return "HAS_RELEVANT";
        }
        return "HAS_NOTHING";
    }

    public static void main(String[] args) throws IOException, SAXException {
        System.out.println(DateTime.parse("1970-01-01T00:00:00Z").plusDays(15592).toString());
        InputStream s = XFormAnalyzer.class.getClassLoader().getResourceAsStream("bp.xml");
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(s);
            new XFormAnalyzer(document).analyze();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private void analyze() {
        analyze((Element) document.getDocumentElement().getElementsByTagName("data").item(0), "");
    }



    private void analyze(Element element, String parentXPath) {
        String nodeName = element.getNodeName();
        if(nodeName.contains(":")) {
            return;
        }
        NodeList childNodes = element.getChildNodes();
        boolean foundChildren = false;

        for(int i=0, len = childNodes.getLength(); i<len; i++) {
            Node child = childNodes.item(i);
            if(child.getNodeType() == Node.ELEMENT_NODE) {
                foundChildren = true;
                analyze((Element) child, parentXPath + "/" + nodeName);
            }
        }

        if(!foundChildren) {
            processElement(element, parentXPath);
        }
    }

    private void processElement(Element element, String parentXPath) {
        String xpath = parentXPath + "/" + element.getTagName();
        String datatype = datatypes.containsKey(xpath) ? datatypes.get(xpath) : "NOT_PRESENT";
        if(datatype.equals("NOT_PRESENT") || datatype.equals("HAS_NOTHING") || datatype.equals("HAS_RELEVANT"))

        System.out.println(xpath + "::" + datatype);
    }
}
