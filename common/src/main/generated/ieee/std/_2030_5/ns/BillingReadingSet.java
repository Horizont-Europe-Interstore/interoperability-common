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
import jakarta.xml.bind.annotation.XmlType;


/**
 * Time sequence of readings of the same reading type.
 * 
 * <p>Java class for BillingReadingSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingReadingSet"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}ReadingSetBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BillingReadingListLink" type="{urn:ieee:std:2030.5:ns}BillingReadingListLink" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingReadingSet", propOrder = {
    "billingReadingListLink"
})
public class BillingReadingSet
    extends ReadingSetBase
{

    @XmlElement(name = "BillingReadingListLink")
    protected BillingReadingListLink billingReadingListLink;

    /**
     * Gets the value of the billingReadingListLink property.
     * 
     * @return
     *     possible object is
     *     {@link BillingReadingListLink }
     *     
     */
    public BillingReadingListLink getBillingReadingListLink() {
        return billingReadingListLink;
    }

    /**
     * Sets the value of the billingReadingListLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillingReadingListLink }
     *     
     */
    public void setBillingReadingListLink(BillingReadingListLink value) {
        this.billingReadingListLink = value;
    }

}