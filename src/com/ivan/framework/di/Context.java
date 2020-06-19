package com.ivan.framework.di;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class Context {
    public static final String TAG_BEAN = "bean";
    public static final String TAG_PROPERTY = "property";
    public Map<String, Object> mapObject;
    public List<Bean> beans;

    public Context(String xmlPath) {
        mapObject = new HashMap<>();
        beans = new ArrayList<>();
        try {
            parseOurXml(xmlPath);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

        try {
            instantiateBeans();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void parseOurXml(String xmlPath) throws ParserConfigurationException, IOException, SAXException, InvalidConfigurationException {
        Document document;
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(new File(xmlPath));
        Element root = document.getDocumentElement();
        NodeList rootChildNodes = root.getChildNodes();
        for (int i = 0; i < rootChildNodes.getLength(); i++) {
            Node beanNode = rootChildNodes.item(i);
            if (TAG_BEAN.equals(beanNode.getNodeName())) {
                parseBean(beanNode);
            }
        }
    }

    private void parseBean(Node beanNode) throws InvalidConfigurationException {
        NamedNodeMap attributesOfBean = beanNode.getAttributes();
        Node id = attributesOfBean.getNamedItem("id");
        String beanIdValue = id.getNodeValue();
        Node beanClass = attributesOfBean.getNamedItem("class");
        String beanClassValue = beanClass.getNodeValue();
        NodeList beanChildNodes = beanNode.getChildNodes();
        Map<String, Property> properties = new HashMap<>();
        for (int i = 0; i < beanChildNodes.getLength(); i++) {
            Node propertyNode = beanChildNodes.item(i);
            if (TAG_PROPERTY == propertyNode.getNodeName()) {
                Property property = parseProperty(propertyNode);
                properties.put(property.getName(), property);
            }
        }
        beans.add(new Bean(beanIdValue, beanClassValue, properties));
    }

    private Property parseProperty(Node propertyNode) throws InvalidConfigurationException {
        NamedNodeMap attributesOfProperty = propertyNode.getAttributes();
        Node nameProperty = attributesOfProperty.getNamedItem("name");
        String propertyNameValue = nameProperty.getNodeValue();
        Node propertySetting = attributesOfProperty.getNamedItem("val");
        if (propertySetting != null) {
            return new Property(propertyNameValue, propertySetting.getNodeValue(), ValueType.VALUE);
        } else {
            propertySetting = attributesOfProperty.getNamedItem("ref");
            if (propertySetting == null) {
                throw new InvalidConfigurationException("Failed to find attribute ref of val: " + propertyNameValue);
            } else {
                return new Property(propertyNameValue, propertySetting.getNodeValue(), ValueType.REF);
            }
        }
    }

    private void instantiateBeans() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (Bean bean : beans) {
            Class<?> aClass = Class.forName(bean.getClassName());
            Object ob = aClass.newInstance();
            mapObject.put(bean.getId(), ob);
        }
        for (Object ob :
                mapObject.values()) {
            System.out.println(ob);
        }

    }

    public Object getById(String object) {
        return mapObject.get(object);
    }
}
