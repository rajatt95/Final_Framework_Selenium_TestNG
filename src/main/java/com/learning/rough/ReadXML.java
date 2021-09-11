package com.learning.rough;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.learning.data.Constants;

public class ReadXML {

	public static void main(String[] args) {

		File fXmlFile = new File(Constants.TestNG_Results_XML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Document doc = null;
		try {
			doc = dBuilder.parse(fXmlFile);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.getDocumentElement().normalize();

		/* <testng-results skipped="0" failed="0" ignored="0" total="2" passed="2"> */

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

		System.out.println("total : " + doc.getDocumentElement().getAttribute("total"));
		System.out.println("Passed : " + doc.getDocumentElement().getAttribute("passed"));
		System.out.println("failed : " + doc.getDocumentElement().getAttribute("failed"));
		System.out.println("skipped : " + doc.getDocumentElement().getAttribute("skipped"));
		System.out.println("ignored : " + doc.getDocumentElement().getAttribute("ignored"));

	}
}
