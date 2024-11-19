//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * Provides an identifiable, subscribable collection of resources for a particular device to consume.
 * 
 * <p>Java class for FunctionSetAssignments complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FunctionSetAssignments"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}FunctionSetAssignmentsBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="mRID" type="{urn:ieee:std:2030.5:ns}mRIDType"/&gt;
 *         &lt;element name="description" type="{urn:ieee:std:2030.5:ns}String32" minOccurs="0"/&gt;
 *         &lt;element name="version" type="{urn:ieee:std:2030.5:ns}VersionType" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="subscribable" type="{urn:ieee:std:2030.5:ns}SubscribableType" default="0" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FunctionSetAssignments", propOrder = {
    "mrid",
    "description",
    "version"
})
public class FunctionSetAssignments
    extends FunctionSetAssignmentsBase
{

    @XmlElement(name = "mRID", required = true)
    protected MRIDType mrid;
    protected String description;
    protected VersionType version;
    @XmlAttribute(name = "subscribable")
    protected Short subscribable;

    /**
     * Gets the value of the mrid property.
     * 
     * @return
     *     possible object is
     *     {@link MRIDType }
     *     
     */
    public MRIDType getMRID() {
        return mrid;
    }

    /**
     * Sets the value of the mrid property.
     * 
     * @param value
     *     allowed object is
     *     {@link MRIDType }
     *     
     */
    public void setMRID(MRIDType value) {
        this.mrid = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link VersionType }
     *     
     */
    public VersionType getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link VersionType }
     *     
     */
    public void setVersion(VersionType value) {
        this.version = value;
    }

    /**
     * Gets the value of the subscribable property.
     * 
     * @return
     *     possible object is
     *     {@link Short }
     *     
     */
    public short getSubscribable() {
        if (subscribable == null) {
            return ((short) 0);
        } else {
            return subscribable;
        }
    }

    /**
     * Sets the value of the subscribable property.
     * 
     * @param value
     *     allowed object is
     *     {@link Short }
     *     
     */
    public void setSubscribable(Short value) {
        this.subscribable = value;
    }

}
