//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Duty cycle control is a device specific issue and is managed by the device.  The duty cycle of the device under control should span the shortest practical time period in accordance with the nature of the device under control and the intent of the request for demand reduction.  The default factory setting SHOULD be three minutes for each 10% of duty cycle.  This indicates that the default time period over which a duty cycle is applied is 30 minutes, meaning a 10% duty cycle would cause a device to be ON for 3 minutes.   The “off state” SHALL precede the “on state”.
 * 
 * <p>Java class for DutyCycle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DutyCycle"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="normalValue" type="{urn:ieee:std:2030.5:ns}UInt8"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DutyCycle", propOrder = {
    "normalValue"
})
public class DutyCycle {

    @XmlSchemaType(name = "unsignedByte")
    protected short normalValue;

    /**
     * Gets the value of the normalValue property.
     * 
     */
    public short getNormalValue() {
        return normalValue;
    }

    /**
     * Sets the value of the normalValue property.
     * 
     */
    public void setNormalValue(short value) {
        this.normalValue = value;
    }

}
