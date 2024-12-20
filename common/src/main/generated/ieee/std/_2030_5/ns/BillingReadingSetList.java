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
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * A List element to hold BillingReadingSet objects.
 * 
 * <p>Java class for BillingReadingSetList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingReadingSetList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{urn:ieee:std:2030.5:ns}SubscribableList"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="BillingReadingSet" type="{urn:ieee:std:2030.5:ns}BillingReadingSet" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingReadingSetList", propOrder = {
    "billingReadingSet"
})
public class BillingReadingSetList
    extends SubscribableList
{

    @XmlElement(name = "BillingReadingSet")
    protected List<BillingReadingSet> billingReadingSet;

    /**
     * Gets the value of the billingReadingSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the billingReadingSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingReadingSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillingReadingSet }
     * 
     * 
     */
    public List<BillingReadingSet> getBillingReadingSet() {
        if (billingReadingSet == null) {
            billingReadingSet = new ArrayList<BillingReadingSet>();
        }
        return this.billingReadingSet;
    }

}
