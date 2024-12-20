//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Contains 802.15.4 link layer specific attributes.
 * 
 * <p>Java class for IEEE_802_15_4 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IEEE_802_15_4"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="capabilityInfo" type="{urn:ieee:std:2030.5:ns}HexBinary8"/&gt;
 *         &lt;element name="NeighborListLink" type="{urn:ieee:std:2030.5:ns}NeighborListLink" minOccurs="0"/&gt;
 *         &lt;element name="shortAddress" type="{urn:ieee:std:2030.5:ns}UInt16"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IEEE_802_15_4", propOrder = {
    "capabilityInfo",
    "neighborListLink",
    "shortAddress"
})
public class IEEE802154 {

    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    @XmlSchemaType(name = "hexBinary")
    protected byte[] capabilityInfo;
    @XmlElement(name = "NeighborListLink")
    protected NeighborListLink neighborListLink;
    @XmlSchemaType(name = "unsignedShort")
    protected int shortAddress;

    /**
     * Gets the value of the capabilityInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getCapabilityInfo() {
        return capabilityInfo;
    }

    /**
     * Sets the value of the capabilityInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapabilityInfo(byte[] value) {
        this.capabilityInfo = value;
    }

    /**
     * Gets the value of the neighborListLink property.
     * 
     * @return
     *     possible object is
     *     {@link NeighborListLink }
     *     
     */
    public NeighborListLink getNeighborListLink() {
        return neighborListLink;
    }

    /**
     * Sets the value of the neighborListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link NeighborListLink }
     *     
     */
    public void setNeighborListLink(NeighborListLink value) {
        this.neighborListLink = value;
    }

    /**
     * Gets the value of the shortAddress property.
     * 
     */
    public int getShortAddress() {
        return shortAddress;
    }

    /**
     * Sets the value of the shortAddress property.
     * 
     */
    public void setShortAddress(int value) {
        this.shortAddress = value;
    }

}
