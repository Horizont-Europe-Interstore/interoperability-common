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
 *  0 - opModFreqWatt (Frequency-Watt Curve Mode)
 *  1 - opModHFRTMayTrip (High Frequency Ride Through, May Trip Mode)
 *  2 - opModHFRTMustTrip (High Frequency Ride Through, Must Trip Mode)
 *  3 - opModHVRTMayTrip (High Voltage Ride Through, May Trip Mode)
 *  4 - opModHVRTMomentaryCessation (High Voltage Ride Through, Momentary Cessation Mode)
 *  5 - opModHVRTMustTrip (High Voltage Ride Through, Must Trip Mode)
 *  6 - opModLFRTMayTrip (Low Frequency Ride Through, May Trip Mode)
 *  7 - opModLFRTMustTrip (Low Frequency Ride Through, Must Trip Mode)
 *  8 - opModLVRTMayTrip (Low Voltage Ride Through, May Trip Mode)
 *  9 - opModLVRTMomentaryCessation (Low Voltage Ride Through, Momentary Cessation Mode)
 *  10 - opModLVRTMustTrip (Low Voltage Ride Through, Must Trip Mode)
 *  11 - opModVoltVar (Volt-Var Mode)
 *  12 - opModVoltWatt (Volt-Watt Mode)
 *  13 - opModWattPF (Watt-PowerFactor Mode)
 *  14 - opModWattVar (Watt-Var Mode)
 * All other values reserved.
 * 
 * <p>Java class for DERCurveType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DERCurveType"&gt;
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
@XmlType(name = "DERCurveType", propOrder = {
    "value"
})
public class DERCurveType {

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