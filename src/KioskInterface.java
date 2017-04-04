/*
 * Created by Question on 4/4/2017
 * Copyright (c) 2017. All Rights Reserved.
 */

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

/**
 * Title        KioskInterface.java
 * Description  This class contains the kiosk interface's definition.
 */
class KioskInterface {
	private File filmXMLFile;

	KioskInterface() {
		filmXMLFile = new File("films.xml");
	}

	ArrayList<Film> getFilm() {
		ArrayList<Film> films = ReadXMLFile(filmXMLFile);
		return films;
	}

	private ArrayList<Film> ReadXMLFile(File file) {
		ArrayList<Film> list = new ArrayList<>();
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(file);
			NodeList filmList = doc.getElementsByTagName("film");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private void CreateXMLFile(ArrayList<Object> list) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
