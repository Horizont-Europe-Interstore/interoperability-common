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
 *  0 = Not Applicable (default, if not specified)
 *  1 = Block 1
 *  2 = Block 2
 *  3 = Block 3
 *  4 = Block 4
 *  5 = Block 5
 *  6 = Block 6
 *  7 = Block 7
 *  8 = Block 8
 *  9 = Block 9
 *  10 = Block 10
 *  11 = Block 11
 *  12 = Block 12
 *  13 = Block 13
 *  14 = Block 14
 *  15 = Block 15
 *  16 = Block 16
 * All other values reserved.
 * 
 * <p>Java class for ConsumptionBlockType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConsumptionBlockType"&gt;
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
@XmlType(name = "ConsumptionBlockType", propOrder = {
    "value"
})
public class ConsumptionBlockType {

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
