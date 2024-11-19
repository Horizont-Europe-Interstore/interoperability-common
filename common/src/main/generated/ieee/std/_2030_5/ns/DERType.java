//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlValue;


/**
 *  0 - Not applicable / Unknown
 *  1 - Virtual or mixed DER
 *  2 - Reciprocating engine
 *  3 - Fuel cell
 *  4 - Photovoltaic system
 *  5 - Combined heat and power
 *  6 - Other generation system
 *  80 - Other storage system
 *  81 - Electric vehicle
 *  82 - EVSE
 *  83 - Combined PV and storage
 * All other values reserved.
 * 
 * <p>Java class for DERType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DERType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:ieee:std:2030.5:ns&gt;UInt8"&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DERType", propOrder = {
    "value"
})
public class DERType {

    @XmlValue
    protected short value;

    /**
     * Unsigned integer, max inclusive 255 (2^8-1)
     * 
     */
    public short getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     */
    public void setValue(short value) {
        this.value = value;
    }

}
