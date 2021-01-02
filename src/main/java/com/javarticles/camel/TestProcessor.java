package com.javarticles.camel;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestProcessor implements Processor {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public void process(Exchange exchange) throws Exception {
       String row = exchange.getIn().getBody(String.class);
        String databaseInfo ="";
        ProducerTemplate template = exchange.getContext().createProducerTemplate();


        System.out.println("=========================>\n"+row);
        Document document = convertStringToDocument(row);
        // System.out.println( evaluateXPath(document, "/employees/@dateOfBirth") );
        XPathFactory xpf = XPathFactory.newInstance();
        XPath xpath = xpf.newXPath();
        Element userElement = (Element) xpath.evaluate("/employees", document,
                XPathConstants.NODE);
        System.out.println(userElement.getAttribute("dateOfBirth").toString());
        System.out.println(userElement.getAttribute("idOfXml").toString());
        System.out.println(userElement.getAttribute("databaseInfo").toString());

        databaseInfo= userElement.getAttribute("databaseInfo").toString();
        System.out.println("THE DATABASE IS "+databaseInfo);
        if (!databaseInfo.equals("ORACLE")){
            System.out.println("NOT EQUAL TO ORACLE ");
        }else{
            template.sendBody("file:C:/app/OUT?fileName=employ.xml", "<hello>world!</hello>");

        }
    }






    private static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
            return doc;
        } catch (Exception ex) {
            System.err.println("unable to load XML: " + ex);
        }
        return null;
    }
    private static List<String> evaluateXPath(Document document, String xpathExpression) throws Exception
    {
        // Create XPathFactory object
        XPathFactory xpathFactory = XPathFactory.newInstance();

        // Create XPath object
        XPath xpath = xpathFactory.newXPath();

        List<String> values = new ArrayList<>();
        try
        {
            // Create XPathExpression object
            XPathExpression expr = xpath.compile(xpathExpression);

            // Evaluate expression result on XML document
            NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);

            for (int i = 0; i < nodes.getLength(); i++) {
                values.add(nodes.item(i).getNodeValue());
            }

        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }
        return values;
    }
}
