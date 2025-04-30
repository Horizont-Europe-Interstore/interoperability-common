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
import si.sunesis.interoperability.common.exceptions.HandlerException;

import javax.xml.XMLConstants;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author David Trafela, Sunesis
 * @since 1.0.1
 */
@Slf4j
public class IEEEObjectFactory {

    private final static String IEEE_NAMESPACE = "ieee.std._2030_5.ns";

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

    /**
     * Private constructor to prevent instantiation of utility class.
     */
    private IEEEObjectFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Converts XML string to an IEEE2030.5 object.
     *
     * @param xml the XML string to convert
     * @param type the class of the IEEE object to create
     * @param <T> the type of the IEEE object
     * @return the created IEEE object
     */
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

    /**
     * Converts an IEEE2030.5 object to an XML string.
     *
     * @param element the IEEE object to convert
     * @param objectClass the class of the IEEE object
     * @param <T> the type of the IEEE object
     * @return the XML string representation
     */
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

    /**
     * Converts a JSON string to an IEEE2030.5 object.
     *
     * @param json the JSON string to convert
     * @param objectClass the class of the IEEE object to create
     * @param <T> the type of the IEEE object
     * @return the created IEEE object
     */
    @SneakyThrows
    public static <T> T fromJSONToIEEE(String json, Class<T> objectClass) {
        Gson gson = new GsonBuilder()
                .registerTypeHierarchyAdapter(TimeType.class, new TimeTypeAdapter())
                .create();

        String className = objectClass.getSimpleName();
        className = className.substring(0, 1).toLowerCase() + className.substring(1);

        JsonNode jsonNode = isValidJson(json);

        if (jsonNode == null) {
            throw new IllegalArgumentException("Invalid JSON input");
        }

        // Skip root node if equal to class name
        if (jsonNode.fieldNames().hasNext() && jsonNode.fieldNames().next().equalsIgnoreCase(className)) {
            json = jsonNode.get(className).toString();
        }

        return gson.fromJson(json, objectClass);
    }

    /**
     * Converts an IEEE2030.5 object to a JSON string, including all fields.
     *
     * @param object the IEEE object to convert
     * @param <T> the type of the IEEE object
     * @return the JSON string representation
     */
    public static <T> String fromIEEEToJSON(T object) {
        return fromIEEEToJSON(object, false);
    }

    /**
     * Converts an IEEE2030.5 object to a JSON string with optional exclusion of optional fields.
     *
     * @param object the IEEE object to convert
     * @param excludeOptionals whether to exclude optional fields
     * @param <T> the type of the IEEE object
     * @return the JSON string representation
     */
    public static <T> String fromIEEEToJSON(T object, boolean excludeOptionals) {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .registerTypeHierarchyAdapter(TimeType.class, new TimeTypeAdapter())
                .setPrettyPrinting();

        if (excludeOptionals) {
            gsonBuilder.addSerializationExclusionStrategy(exclusionStrategy);
        }

        Gson gson = gsonBuilder.create();

        String className = object.getClass().getSimpleName();
        className = Character.toLowerCase(className.charAt(0)) + className.substring(1);

        Map<String, T> wrappedMap = new HashMap<>();
        wrappedMap.put(className, object);  // No need to serialize and deserialize again

        return gson.toJson(wrappedMap);
    }

    /**
     * Gets a class by name from a specified package.
     *
     * @param className the name of the class
     * @param packageName the package containing the class
     * @return the Class object or null if not found
     */
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

    /**
     * Validates if the input conforms to IEEE2030.5 standard, detecting if it's JSON or XML.
     *
     * @param input the input string to validate
     * @return true if valid, false otherwise
     * @throws IOException if an I/O error occurs
     * @throws HandlerException if validation fails
     */
    public static Boolean validateIEEE2030dot5(String input) throws IOException, HandlerException {
        JsonNode jsonNode = isValidJson(input);

        if (jsonNode != null) {
            return validateJSONForIEEE2030dot5(jsonNode);
        }

        Document document = isValidXml(input);

        if (document != null) {
            return validateXMLForIEEE2030dot5(input, document);
        }

        return false;
    }

    /**
     * Validates XML input against IEEE2030.5 schema.
     *
     * @param xml the XML string to validate
     * @param document the parsed XML document
     * @return true if valid, false otherwise
     * @throws IOException if an I/O error occurs
     * @throws HandlerException if validation fails
     */
    private static Boolean validateXMLForIEEE2030dot5(String xml, Document document) throws IOException, HandlerException {
        // Get root element
        String rootName = document.getDocumentElement().getNodeName();
        rootName = rootName.substring(0, 1).toUpperCase() + rootName.substring(1);

        Class clazz = IEEEObjectFactory.getClass(rootName, IEEE_NAMESPACE);

        if (clazz == null) {
            throw new IllegalArgumentException("Invalid root element name");
        }

        String output = IEEEObjectFactory.fromIEEEToXML(IEEEObjectFactory.fromXMLToIEEE(xml, clazz), clazz);

        try {
            validateAgainstSchema(output);
        } catch (SAXException e) {
            log.error("Exception: {}", e.getMessage());

            String path = "/xml/" + clazz.getSimpleName() + ".xml";

            InputStream fileStream = IEEEObjectFactory.class.getResourceAsStream(path);

            InputStreamReader inputStreamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Join the lines to a single string
            String lines = String.join("\n", bufferedReader.lines().toList());

            log.info("Correct XML example:\n{}", lines);

            throw new HandlerException("Invalid IEEE2030.5 XML structure");
        }

        return true;
    }

    /**
     * Validates JSON input against IEEE2030.5 schema by converting to XML and validating.
     *
     * @param jsonNode the parsed JSON node
     * @return true if valid, false otherwise
     * @throws IOException if an I/O error occurs
     * @throws HandlerException if validation fails
     */
    private static Boolean validateJSONForIEEE2030dot5(JsonNode jsonNode) throws IOException, HandlerException {
        //Get name of root element
        String orgRootName = jsonNode.fieldNames().next();
        String rootName = orgRootName.substring(0, 1).toUpperCase() + orgRootName.substring(1);

        Class clazz = IEEEObjectFactory.getClass(rootName, IEEE_NAMESPACE);

        if (clazz == null) {
            throw new IllegalArgumentException("Invalid root element name");
        }

        // Skip root element
        String json = jsonNode.get(orgRootName).toString();

        String output = IEEEObjectFactory.fromIEEEToXML(IEEEObjectFactory.fromJSONToIEEE(json, clazz), clazz);

        try {
            validateAgainstSchema(output);
        } catch (SAXException e) {
            log.error("Exception: {}", e.getMessage());

            String path = "/xml/" + clazz.getSimpleName() + ".xml";

            InputStream fileStream = IEEEObjectFactory.class.getResourceAsStream(path);

            InputStreamReader inputStreamReader = new InputStreamReader(fileStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            // Join the lines to a single string
            String lines = String.join("\n", bufferedReader.lines().toList());

            clazz = getClass(clazz.getSimpleName(), IEEE_NAMESPACE);

            Object struct = IEEEObjectFactory.fromXMLToIEEE(lines, clazz);

            log.error("Correct minimal JSON example without optional values:\n{}", IEEEObjectFactory.fromIEEEToJSON(struct, true));
            log.error("Correct full JSON example with optional values:\n{}", IEEEObjectFactory.fromIEEEToJSON(struct, false));

            throw new HandlerException("Invalid IEEE2030.5 JSON structure");
        }

        return true;
    }

    /**
     * Validates XML against the IEEE2030.5 XSD schema.
     *
     * @param xml the XML string to validate
     * @throws SAXException if the XML is not valid according to the schema
     * @throws IOException if an I/O error occurs
     */
    private static void validateAgainstSchema(String xml) throws SAXException, IOException {
        String xsdFile = "/xml/sep.xsd";

        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        factory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        // Load the XSD from resources
        InputStream xsdStream = IEEEObjectFactory.class.getResourceAsStream(xsdFile);
        Schema schema = factory.newSchema(new StreamSource(xsdStream));

        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new StringReader(xml)));
    }

    /**
     * Custom Gson TypeAdapter for handling IEEE2030.5 TimeType objects.
     */
    private static class TimeTypeAdapter extends TypeAdapter<TimeType> {

        /**
         * Writes a TimeType object as a JSON value.
         *
         * @param jsonWriter the JSON writer
         * @param timeType the TimeType object to write
         * @throws IOException if an I/O error occurs
         */
        @Override
        public void write(JsonWriter jsonWriter, TimeType timeType) throws IOException {
            if (timeType == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(timeType.getValue());
            }
        }

        /**
         * Reads a TimeType object from a JSON value.
         *
         * @param jsonReader the JSON reader
         * @return the created TimeType object
         * @throws IOException if an I/O error occurs
         */
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

    /**
     * Checks if a string is valid JSON and returns the parsed JsonNode.
     *
     * @param jsonString the string to check
     * @return the parsed JsonNode or null if not valid JSON
     */
    private static JsonNode isValidJson(String jsonString) {
        try {
            return new ObjectMapper().readTree(jsonString);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Checks if a string is valid XML and returns the parsed Document.
     *
     * @param xmlString the string to check
     * @return the parsed Document or null if not valid XML
     */
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
