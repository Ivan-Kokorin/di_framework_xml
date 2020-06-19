package com.ivan.framework.di;

import com.ivan.framework.Program;
import com.ivan.framework.X32or64;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.naming.ConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
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

    private void instantiateBeans() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException, ConfigurationException, InvalidConfigurationException {
        for (Bean bean : beans) {
            Class<?> aClass = Class.forName(bean.getClassName());
            mapObject.put(bean.getId(), aClass.newInstance());
        }

        for (Bean bean : beans) {
            if(bean.getClassName().equals("java.util.ArrayList")) {
                ArrayList<Program> list = (ArrayList<Program>) getById(bean.getId());
                list.add((Program) getById("msOffice"));
                list.add((Program) getById("googleChrome"));

                continue;
            }
            for (String id : bean.getProperties().keySet()) {
                Class<?> aClass = Class.forName(bean.getClassName());
                Object ob = getById(bean.getId());
                Field field = getField(aClass, id);
                if (field == null) {
                    throw new ConfigurationException("Failed to set field: " + id + " for class: " + aClass.getName());
                }
                field.setAccessible(true);
                Property property = bean.getProperties().get(id);

                switch (property.getType()) {
                    case VALUE:
                        field.set(ob, convert(field.getType().getSimpleName(), property.getValue()));
                        break;
                    case REF:
                        String refName = property.getValue();
                        if (mapObject.containsKey(refName)) {
                            field.set(ob, mapObject.get(refName));
                        } else {
                            throw new InvalidConfigurationException("Failed instantiate bean, ref :" + id);
                        }
                        break;
                    default:
                        throw new InvalidConfigurationException("Type error");
                }
            }
        }
    }

    private Field getField(Class<?> aClass, String fieldName) throws NoSuchFieldException {
        try {
            return aClass.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            Class<?> superclass = aClass.getSuperclass();
            if (superclass == null) {
                throw e;
            } else {
                return getField(superclass, fieldName);
            }
        }
    }

    private Object convert(String typeName, String value) throws ConfigurationException {
        switch (typeName) {
            case "int":
            case "Integer":
                return Integer.valueOf(value);
            case "double":
            case "Double":
                return Double.valueOf(value);
            case "float":
            case "Float":
                return Float.valueOf(value);
            case "boolean":
            case "Boolean":
                return Boolean.valueOf(value);
            case "string":
            case "String":
                return value;
            case "X32or64":
                if (value.equals("X64")){
                    return X32or64.X64;
                }else if(value.equals("X32")){
                    return X32or64.X32;
                }
            default:
                throw new ConfigurationException("Type error");
        }
    }

    public Object getById(String object) {
        return mapObject.get(object);
    }
}
