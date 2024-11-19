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


/**
 * Data point values for defining a curve or schedule
 * 
 * <p>Java class for CurveData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CurveData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="excitation" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="xvalue" type="{urn:ieee:std:2030.5:ns}Int32"/&gt;
 *         &lt;element name="yvalue" type="{urn:ieee:std:2030.5:ns}Int32"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CurveData", propOrder = {
    "excitation",
    "xvalue",
    "yvalue"
})
public class CurveData {

    protected Boolean excitation;
    protected int xvalue;
    protected int yvalue;

    /**
     * Gets the value of the excitation property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExcitation() {
        return excitation;
    }

    /**
     * Sets the value of the excitation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExcitation(Boolean value) {
        this.excitation = value;
    }

    /**
     * Gets the value of the xvalue property.
     * 
     */
    public int getXvalue() {
        return xvalue;
    }

    /**
     * Sets the value of the xvalue property.
     * 
     */
    public void setXvalue(int value) {
        this.xvalue = value;
    }

    /**
     * Gets the value of the yvalue property.
     * 
     */
    public int getYvalue() {
        return yvalue;
    }

    /**
     * Sets the value of the yvalue property.
     * 
     */
    public void setYvalue(int value) {
        this.yvalue = value;
    }

}
