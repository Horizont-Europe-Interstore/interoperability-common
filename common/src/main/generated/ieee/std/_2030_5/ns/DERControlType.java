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
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Control modes supported by the DER.  Bit positions SHALL be defined as follows:
 *  0 - Charge mode
 *  1 - Discharge mode
 *  2 - opModConnect (Connect / Disconnect - implies galvanic isolation)
 *  3 - opModEnergize (Energize / De-Energize)
 *  4 - opModFixedPFAbsorbW (Fixed Power Factor Setpoint when absorbing active power)
 *  5 - opModFixedPFInjectW (Fixed Power Factor Setpoint when injecting active power)
 *  6 - opModFixedVar (Reactive Power Setpoint)
 *  7 - opModFixedW (Charge / Discharge Setpoint)
 *  8 - opModFreqDroop (Frequency-Watt Parameterized Mode)
 *  9 - opModFreqWatt (Frequency-Watt Curve Mode)
 *  10 - opModHFRTMayTrip (High Frequency Ride Through, May Trip Mode)
 *  11 - opModHFRTMustTrip (High Frequency Ride Through, Must Trip Mode)
 *  12 - opModHVRTMayTrip (High Voltage Ride Through, May Trip Mode)
 *  13 - opModHVRTMomentaryCessation (High Voltage Ride Through, Momentary Cessation Mode)
 *  14 - opModHVRTMustTrip (High Voltage Ride Through, Must Trip Mode)
 *  15 - opModLFRTMayTrip (Low Frequency Ride Through, May Trip Mode)
 *  16 - opModLFRTMustTrip (Low Frequency Ride Through, Must Trip Mode)
 *  17 - opModLVRTMayTrip (Low Voltage Ride Through, May Trip Mode)
 *  18 - opModLVRTMomentaryCessation (Low Voltage Ride Through, Momentary Cessation Mode)
 *  19 - opModLVRTMustTrip (Low Voltage Ride Through, Must Trip Mode)
 *  20 - opModMaxLimW (Maximum Active Power)
 *  21 - opModTargetVar (Target Reactive Power)
 *  22 - opModTargetW (Target Active Power)
 *  23 - opModVoltVar (Volt-Var Mode)
 *  24 - opModVoltWatt (Volt-Watt Mode)
 *  25 - opModWattPF (Watt-PowerFactor Mode)
 *  26 - opModWattVar (Watt-Var Mode)
 * All other values reserved.
 * 
 * <p>Java class for DERControlType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DERControlType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;urn:ieee:std:2030.5:ns&gt;HexBinary32"&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DERControlType", propOrder = {
    "value"
})
public class DERControlType {

    @XmlValue
    @XmlJavaTypeAdapter(HexBinaryAdapter.class)
    protected byte[] value;

    /**
     * A 32-bit field encoded as a hex string (8 hex characters max). Where applicable, bit 0, or the least significant bit, goes on the right. Note that hexBinary requires pairs of hex characters, so an odd number of characters requires a leading "0".
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(byte[] value) {
        this.value = value;
    }

}