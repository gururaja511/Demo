package com.incture.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.testng.xml.Parser;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import org.xml.sax.SAXException;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TestNgCreation {
	public static void main(String[] args) {
		//createTestNgFile("172.16.30.83","5555");
		/*Map<String, String> parameters=new HashMap();
		parameters.put("key123", "value123");
		parameters.put("key456", "value456");
		addSuiteLevelParameters("/opt/workspace/BPM2/testng.xml", parameters);*/
		TestNgCreation testng=new TestNgCreation();
		String[] arr={"module","Module2"};
		
		testng.xmlDomModifierCustom("D:\\GridImplementationRandD\\testng.xml","172.16.30.83","5555",arr);


	}




	public  void xmlDomModifierCustom(String filepath,String nodeIp,String port,String module[]){
		try{
			System.out.println("filepath    "+filepath);
			System.out.println("nodeIp   "+nodeIp);
			System.out.println("port   "+port);
			System.out.println("module  "+module);



			try {
				//	String filepath = "D:\\GridImplementationRandD\\testng.xml";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);


				// Modifying parameters code will starts from here 
				int noofParameters=doc.getElementsByTagName("parameter").getLength();

				for(int i=0;i<noofParameters;i++){
					System.out.println(doc.getElementsByTagName("parameter").item(i).getAttributes().item(1).getNodeValue());

					String type=doc.getElementsByTagName("parameter").item(i).getAttributes().item(0).getNodeValue();

					if(type.equals("port")){
						doc.getElementsByTagName("parameter").item(i).getAttributes().item(1).setNodeValue(port);
					}else if(type.equals("nodeIp")){
						doc.getElementsByTagName("parameter").item(i).getAttributes().item(1).setNodeValue(nodeIp);
					}
					System.out.println(doc.getElementsByTagName("parameter").item(i).getAttributes().item(0).getNodeValue());
					System.out.println(doc.getElementsByTagName("parameter").item(i).getAttributes().item(1).getNodeValue());

				}
				// Modifying parameters code will starts from here -- ends

				// Modifying class name to triger perticular TestNG class on only first occerences in testng.xml file
				int noofClass=doc.getElementsByTagName("class").getLength();
				for(int i=0;i<noofClass;i++)
					doc.getElementsByTagName("classes").item(0).removeChild(doc.getElementsByTagName("class").item(0));


				System.out.println(doc.getElementsByTagName("class").getLength());

				//	doc.getElementsByTagName("class").item(0).getAttributes().item(0).setNodeValue(module);


				for(int i=0;i<module.length;i++){
				Element nodetitle = doc.createElement("class");
				nodetitle.setAttribute("name", module[i]);
				doc.getElementsByTagName("classes").item(0).appendChild(nodetitle);
				}
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath));
				transformer.transform(source, result);

				System.out.println("Done");

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (SAXException sae) {
				sae.printStackTrace();
			}}catch (Exception e) {
				System.out.println(e.getMessage());
			}
	}


	/**
	 * This method is working only if the class is present in the current working project
	 * @param testNglocation
	 * @param parameters
	 */
	public static void addSuiteLevelParameters(String testNglocation,Map<String,String> parameters){



		FileWriter fos=null;
		List<XmlSuite> suite = null;
		try {


			suite = (List <XmlSuite>)(new Parser(testNglocation).parse());
			fos=new FileWriter(new File(testNglocation)); 


			for(XmlSuite suiteSin:suite){

				suiteSin.setParameters(parameters);
				fos.write(suiteSin.toXml());
				fos.flush();
				//System.out.println(suiteSin.toXml());
			}	


			fos.close();

		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}


	public static String createTestNgFile(String nodeIp,String port){

		List<XmlSuite> list_suit_XmlSuit=new ArrayList<XmlSuite>();
		XmlSuite suit=new XmlSuite();
		suit.setName("GridDemoSuti");

		ArrayList<XmlTest> list_test_XmlTest = new ArrayList<XmlTest>();// to add all tests to suit
		XmlTest test=new XmlTest();
		test.setName("GridDemoTC");
		test.setVerbose(2);
		test.setSuite(suit);


		ArrayList<XmlClass> list_classes_XmlClass = new ArrayList<XmlClass>();
		XmlClass classes=new XmlClass();
		classes.setName("TestNG.Framework_001");

		ArrayList<XmlInclude> list_method_XmlInclude = new ArrayList<XmlInclude>();//  to add all methods for classes
		XmlInclude includeMethod=	new XmlInclude("main");
		includeMethod.addParameter("nodeIp", nodeIp);
		includeMethod.addParameter("port", port);
		list_method_XmlInclude.add(includeMethod);

		classes.setIncludedMethods(list_method_XmlInclude);	
		list_classes_XmlClass.add(classes);

		test.setClasses(list_classes_XmlClass);

		list_test_XmlTest.add(test);

		suit.setTests(list_test_XmlTest);
		list_suit_XmlSuit.add(suit);

		/*TestNG testNG = new TestNG();
	testNG.setXmlSuites(list_suit_XmlSuit);
	testNG.setVerbose(2);
	testNG.run();*/
		System.out.println(suit.toXml());

		try{
			FileWriter fos=new FileWriter(new File("/opt/workspace/BPM2/testng.xml")); 
			fos.write(suit.toXml());
			fos.flush();
			fos.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		//System.out.println(suit.toXml().toString());
		/*TestNG testNG = new TestNG();

	testNG.setXmlSuites(list_suit_XmlSuit);
	testNG.setVerbose(2);
	testNG.run();*/
		return suit.toXml().toString();

	}

}

