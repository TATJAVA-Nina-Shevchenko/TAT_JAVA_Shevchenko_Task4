package com.epam.shevchenko.controller.xmlparser;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class CommandSaxHandler extends DefaultHandler implements XmlParser {
	private List<String> commandList = new ArrayList<String>();
	private StringBuilder text;

	public List<String> getCommandList() {
		return commandList;
	}

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Parsing started");
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Parsing ended");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		text = new StringBuilder();
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case "command":
			commandList.add(text.toString());
			break;
		default:
			break;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		text.append(ch, start, length);
	}

}
