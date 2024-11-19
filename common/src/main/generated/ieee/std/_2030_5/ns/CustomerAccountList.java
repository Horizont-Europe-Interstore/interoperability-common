//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.2 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.11.19 at 12:20:19 PM CET 
//


package ieee.std._2030_5.ns;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * A List element to hold CustomerAccount objects.
 * 
 * <p>Java class for CustomerAccountList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerAccountList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}SubscribableList"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CustomerAccount" type="{urn:ieee:std:2030.5:ns}CustomerAccount" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="pollRate" type="{urn:ieee:std:2030.5:ns}UInt32" default="900" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerAccountList", propOrder = {
    "customerAccount"
})
public class CustomerAccountList
    extends SubscribableList
{

    @XmlElement(name = "CustomerAccount")
    protected List<CustomerAccount> customerAccount;
    @XmlAttribute(name = "pollRate")
    protected Long pollRate;

    /**
     * Gets the value of the customerAccount property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the customerAccount property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomerAccount().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CustomerAccount }
     * 
     * 
     */
    public List<CustomerAccount> getCustomerAccount() {
        if (customerAccount == null) {
            customerAccount = new ArrayList<CustomerAccount>();
        }
        return this.customerAccount;
    }

    /**
     * Gets the value of the pollRate property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public long getPollRate() {
        if (pollRate == null) {
            return  900L;
        } else {
            return pollRate;
        }
    }

    /**
     * Sets the value of the pollRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPollRate(Long value) {
        this.pollRate = value;
    }

}
