package com.gorenko.pavel.dao;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLConf {

    private final static File XML_PATH = new File("c:\\Users\\user\\IdeaProjects\\Practice_2_3\\dataSource.xml");

    public static Datasources parseXMLConfiguration () throws ParserConfigurationException, IOException, SAXException {

        Datasources connectionFromXML = null;

        try {
            File file = XML_PATH;
            JAXBContext jaxbContext = JAXBContext.newInstance(Datasources.class, Datasources.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            connectionFromXML = (Datasources) jaxbUnmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return connectionFromXML;
    }

}
