package com.guigarage.uif.examples.jfx;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by hendrikebbers on 16.01.15.
 */
public class WeatherService {

    public static String getWeather(String city) throws RuntimeException {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&mode=xml");
            Element eElement = (Element) document.getElementsByTagName("weather").item(0);
            return eElement.getAttribute("value");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
