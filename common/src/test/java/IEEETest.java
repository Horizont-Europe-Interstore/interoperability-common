import ieee.std._2030_5.ns.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;
import si.sunesis.interoperability.common.exceptions.HandlerException;
import si.sunesis.interoperability.common.ieee2030dot5.IEEEObjectFactory;

import java.io.IOException;

@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IEEETest {

    private static final String serverXMLInput = """
            <Event>
                <mRID>10</mRID>
            	<creationTime>1702909917932</creationTime>
            	<EventStatus>
            		<currentStatus>1</currentStatus>
            		<dateTime>1693216835000</dateTime>
            		<potentiallySuperseded>false</potentiallySuperseded>
            	</EventStatus>
            	<interval>
            		<duration>900</duration>
            		<start>1693216835000</start>
            	</interval>
            </Event>
            """;

    private static final String serverXMLInput2 = """
            <EventStatus>
            		<currentStatus>1</currentStatus>
            		<dateTime>1693216835000</dateTime>
            		<potentiallySuperseded>false</potentiallySuperseded>
            	</EventStatus>
            """;

    private static final String serverJsonInput = """
            {
              "event": {
                "creationTime": 1.702909917932E12,
                "eventStatus": {
                  "currentStatus": 1.0,
                  "dateTime": 1.693216835E12,
                  "potentiallySuperseded": false
                },
                "interval": {
                  "duration": 900.0,
                  "start": 1.693216835E12
                },
                "mrid": {
                  "value": [
                    16.0
                  ]
                }
              }
            }""";

    @Test
    public void testA() {
        EventStatus eventStatus = new ObjectFactory().createEventStatus();

        log.info("eventStatus JSON: {}", IEEEObjectFactory.fromIEEEToJSON(eventStatus));
        log.info("eventStatus XML: {}", IEEEObjectFactory.fromIEEEToXML(eventStatus, EventStatus.class));

        // XML to IEEE
        Event event = IEEEObjectFactory.fromXMLToIEEE(serverXMLInput, Event.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(1702909917932L, event.getCreationTime().getValue());
    }

    @Test
    public void testB() {
        // XML to IEEE to JSON
        Event event = IEEEObjectFactory.fromXMLToIEEE(serverXMLInput, Event.class);

        String json = IEEEObjectFactory.fromIEEEToJSON(event);

        Assert.assertEquals(serverJsonInput, json);
    }

    @Test
    public void testC() {
        // JSON to IEEE
        Event event = IEEEObjectFactory.fromJSONToIEEE(serverJsonInput, Event.class);

        Assert.assertNotNull(event);

        Assert.assertEquals(1702909917932L, event.getCreationTime().getValue());
    }

    @Test
    public void testD() {
        TimeType type = new ObjectFactory().createTimeType();
        type.setValue(1999L);

        DeviceStatus endDevice = new ObjectFactory().createDeviceStatus();
        endDevice.setChangedTime(type);
        endDevice.setOnCount(3);
        endDevice.setOpState(Integer.valueOf(2).shortValue());
        endDevice.setPollRate(900L);
        endDevice.setOpTime(992932L);

        String xml = IEEEObjectFactory.fromIEEEToXML(endDevice, DeviceStatus.class);
        log.info("XML: \n{}", xml);

        PowerConfiguration powerConfiguration = new ObjectFactory().createPowerConfiguration();
        powerConfiguration.setLowChargeThreshold(100L);
        powerConfiguration.setBatteryInstallTime(type);
        String json = IEEEObjectFactory.fromIEEEToJSON(powerConfiguration);
        log.info("JSON: \n{}", json);
    }

    @Test
    public void testE() {
        String xml = """
                <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
                <DeviceStatus xmlns="urn:ieee:std:2030.5:ns">
                    <changedTime>1999</changedTime>
                    <onCount>3</onCount>
                    <opState>2</opState>
                    <opTime>992932</opTime>
                </DeviceStatus>
                """;

        DeviceStatus status = IEEEObjectFactory.fromXMLToIEEE(xml, DeviceStatus.class);

        log.info("DeviceStatus: {}", IEEEObjectFactory.fromIEEEToJSON(status));
    }

    @Test
    public void testF() {
        String input = """
                <ActivePower>
                	<value>1702909917932</value>
                	<multiplier>
                		<value>1</value>
                	</multiplier>
                </ActivePower>
                """;

        ActivePower status = IEEEObjectFactory.fromXMLToIEEE(input, ActivePower.class);

        log.info("ActivePower: {}", IEEEObjectFactory.fromIEEEToJSON(status));
    }

    @Test
    public void testG() {
        try {
            IEEEObjectFactory.validateIEEE2030dot5(serverJsonInput);
        } catch (IOException e) {
            log.error("JSON is not valid: {}", e.getMessage());
            Assert.fail(e.getMessage());
        } catch (SAXException e) {
            e.printStackTrace();
            log.error("XML is not valid: {}", e.getMessage());
            Assert.fail(e.getMessage());
        } catch (HandlerException e) {
            log.error("Handler exception: {}", e.getMessage());
            Assert.fail(e.getMessage());
        }

        log.info("Validation successful");
    }

    @Test
    public void testH() {
        try {
            IEEEObjectFactory.validateIEEE2030dot5(serverXMLInput);
        } catch (IOException e) {
            log.error("JSON is not valid: {}", e.getMessage());
            Assert.fail(e.getMessage());
        } catch (SAXException e) {
            log.error("XML is not valid: {}", e.getMessage());
            Assert.fail(e.getMessage());
        } catch (HandlerException e) {
            log.error("Handler exception: {}", e.getMessage());
            Assert.fail(e.getMessage());
        }

        log.info("Validation successful");
    }
}
