/*
 *  Copyright (c) 2023-2024 Sunesis and/or its affiliates
 *  and other contributors as indicated by the @author tags and
 *  the contributor list.
 *
 *  Licensed under the MIT License (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  https://opensource.org/licenses/MIT
 *
 *  The software is provided "AS IS", WITHOUT WARRANTY OF ANY KIND, express or
 *  implied, including but not limited to the warranties of merchantability,
 *  fitness for a particular purpose and noninfringement. in no event shall the
 *  authors or copyright holders be liable for any claim, damages or other
 *  liability, whether in an action of contract, tort or otherwise, arising from,
 *  out of or in connection with the software or the use or other dealings in the
 *  software. See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package si.sunesis.interoperability.common.ieee2030dot5;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import ieee.std._2030_5.ns.ObjectFactory;
import ieee.std._2030_5.ns.TimeType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlValue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Trafela, Sunesis
 * @since 1.0.1
 */
@Slf4j
public class IEEEObjectFactory {

    private static ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes field) {
            if (field.getAnnotation(XmlAttribute.class) != null) {
                return !field.getAnnotation(XmlAttribute.class).required();
            }

            if (field.getAnnotation(XmlElement.class) != null) {
                return !field.getAnnotation(XmlElement.class).required();
            }

            return field.getAnnotation(XmlValue.class) == null;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };

    private IEEEObjectFactory() {
        throw new IllegalStateException("Utility class");
    }

    @SneakyThrows
    public static <T> T fromXMLToIEEE(String xml, Class<T> type) {
        if (!xml.contains("xmlns")) {
            xml = xml.replace("<" + type.getSimpleName() + ">", "<"
                    + type.getSimpleName() + " xmlns=\"urn:ieee:std:2030.5:ns\">");
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        return ((JAXBElement<T>) jaxbContext.createUnmarshaller()
                .unmarshal(new StringReader(xml)))
                .getValue();
    }

    @SneakyThrows
    public static <T> String fromIEEEToXML(T element, Class<T> objectClass) {
        StringWriter stringWriter = new StringWriter();

        JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

        // format the XML output
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        QName qName = new QName("urn:ieee:std:2030.5:ns", objectClass.getSimpleName());
        JAXBElement<T> root = new JAXBElement<>(qName, objectClass, element);

        jaxbMarshaller.marshal(root, stringWriter);

        return stringWriter.toString();
    }

    @SneakyThrows
    public static <T> T fromJSONToIEEE(String json, Class<T> objectClass) {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(TimeType.class, new TimeTypeAdapter())
                .create();

        Type type = new TypeToken<Map<String, Object>>() {
        }.getType();
        Map<String, Object> originalMap = gson.fromJson(json, type);

        if (originalMap.containsKey(objectClass.getSimpleName().toLowerCase())) {
            originalMap = (Map<String, Object>) originalMap.get(objectClass.getSimpleName().toLowerCase());
        }

        Map<String, Object> newMap = new HashMap<>();
        for (Map.Entry<String, Object> entry : originalMap.entrySet()) {
            newMap.put(entry.getKey().substring(0, 1).toLowerCase() + entry.getKey().substring(1), entry.getValue());
        }

        String newJsonInput = gson.toJson(newMap);

        return gson.fromJson(newJsonInput, objectClass);
    }

    public static String fromIEEEToJSON(Object object) {
        return fromIEEEToJSON(object, false);
    }

    public static String fromIEEEToJSON(Object object, boolean excludeOptionals) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeHierarchyAdapter(TimeType.class, new TimeTypeAdapter())
                .setPrettyPrinting();

        if (excludeOptionals) {
            gsonBuilder.addSerializationExclusionStrategy(excludeOptionals ? exclusionStrategy : null);
        }

        Gson gson = gsonBuilder.create();

        // Serialize the object to a JSON string
        String json = gson.toJson(object);

        // Wrap the serialized JSON in an "event" key
        Map<String, Object> wrappedMap = new HashMap<>();
        wrappedMap.put(object.getClass().getSimpleName().toLowerCase(), gson.fromJson(json, Object.class));

        // Convert the wrapped map back to a JSON string
        return gson.toJson(wrappedMap);
    }

    public static Class getClass(String className, String packageName) {
        try {
            if (className.contains(".")) {
                return Class.forName(packageName + "."
                        + className.substring(0, className.lastIndexOf('.')));
            }

            return Class.forName(packageName + "."
                    + className);
        } catch (ClassNotFoundException e) {
            // handle the exception
            log.error("Error: {}", e.getMessage());
        }
        return null;
    }

    public static Boolean validateIEEE2030dot5(String input) throws IOException, SAXException {
        JsonNode jsonNode = isValidJson(input);

        if (jsonNode != null) {
            return validateJSONForIEEE2030dot5(input, jsonNode);
        }

        Document document = isValidXml(input);

        if (document != null) {
            return validateXMLForIEEE2030dot5(input, document);
        }

        return false;
    }

    private static Boolean validateXMLForIEEE2030dot5(String xml, Document document) throws IOException, SAXException {
        // Get root element
        String rootName = document.getDocumentElement().getNodeName();
        rootName = rootName.substring(0, 1).toUpperCase() + rootName.substring(1);

        Class clazz = IEEEObjectFactory.getClass(rootName, "ieee.std._2030_5.ns");

        if (clazz == null) {
            throw new IllegalArgumentException("Invalid root element name");
        }

        String output = IEEEObjectFactory.fromIEEEToXML(IEEEObjectFactory.fromXMLToIEEE(xml, clazz), clazz);

        try {
            validateAgainstSchema(output);
        } catch (SAXException e) {
            log.error("Exception: {}", e.getMessage());

            String path = "/xml/" + rootName + ".xml";

            InputStream fileStream = IEEEObjectFactory.class.getResourceAsStream(path);

            InputStreamReader inputStreamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Join the lines to a single string
            String lines = String.join("\n", bufferedReader.lines().toList());

            log.info("Correct XML example:\n{}", lines);

            throw new IllegalArgumentException("Invalid IEEE2030.5 XML structure");
        }

        return true;
    }

    private static Boolean validateJSONForIEEE2030dot5(String json, JsonNode jsonNode) throws IOException, SAXException {
        //Get name of root element
        String rootName = jsonNode.fieldNames().next();
        rootName = rootName.substring(0, 1).toUpperCase() + rootName.substring(1);

        Class clazz = IEEEObjectFactory.getClass(rootName, "ieee.std._2030_5.ns");

        if (clazz == null) {
            throw new IllegalArgumentException("Invalid root element name");
        }

        String output = IEEEObjectFactory.fromIEEEToXML(IEEEObjectFactory.fromJSONToIEEE(json, clazz), clazz);

        try {
            validateAgainstSchema(output);
        } catch (SAXException e) {
            log.error("Exception: {}", e.getMessage());

            String path = "/xml/" + rootName + ".xml";

            InputStream fileStream = IEEEObjectFactory.class.getResourceAsStream(path);

            InputStreamReader inputStreamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Join the lines to a single string
            String lines = String.join("\n", bufferedReader.lines().toList());

            clazz = getClass(rootName, "ieee.std._2030_5.ns");

            json = IEEEObjectFactory.fromIEEEToJSON(IEEEObjectFactory.fromXMLToIEEE(lines, clazz), true);

            log.error("Correct JSON example:\n{}", json);

            throw new IllegalArgumentException("Invalid IEEE2030.5 JSON structure");
        }

        return true;
    }

    private static void validateAgainstSchema(String xml) throws SAXException, IOException {
        String xsdFile = "/xml/sep.xsd";

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        // Load the XSD from resources
        InputStream xsdStream = IEEEObjectFactory.class.getResourceAsStream(xsdFile);
        Schema schema = factory.newSchema(new StreamSource(xsdStream));

        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(xml)));
    }

    private static class TimeTypeAdapter extends TypeAdapter<TimeType> {

        @Override
        public void write(JsonWriter jsonWriter, TimeType timeType) throws IOException {
            if (timeType == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(timeType.getValue());
            }
        }

        @Override
        public TimeType read(JsonReader jsonReader) throws IOException {
            if (jsonReader != null) {
                TimeType timeType = new TimeType();
                timeType.setValue(jsonReader.nextLong());
                return timeType;
            } else {
                return null;
            }
        }
    }

    private static JsonNode isValidJson(String jsonString) {
        try {
            return new ObjectMapper().readTree(jsonString);
        } catch (Exception e) {
            return null;
        }
    }

    private static Document isValidXml(String xmlString) {
        try {
            if (!xmlString.startsWith("<?xml")) {
                xmlString = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + xmlString;
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            factory.setXIncludeAware(false);
            factory.setNamespaceAware(false);

            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource inputSource = new InputSource(new StringReader(xmlString));

            return builder.parse(inputSource);
        } catch (Exception e) {
            return null;
        }
    }
}
