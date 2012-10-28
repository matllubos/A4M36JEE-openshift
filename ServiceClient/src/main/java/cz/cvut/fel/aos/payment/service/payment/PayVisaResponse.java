package cz.cvut.fel.aos.payment.service.payment;

import cz.cvut.fel.aos.booking.service.reservation.Reservation;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for payVisaResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="payVisaResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://payment.service.payment.aos.fel.cvut.cz/}reservation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "payVisaResponse", propOrder = {
        "_return"
} )
public class PayVisaResponse
        implements Serializable {

    private final static long serialVersionUID = 1L;

    @XmlElement( name = "return" )
    protected Reservation _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link Reservation }
     */
    public Reservation getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is {@link Reservation }
     */
    public void setReturn( Reservation value ) {
        this._return = value;
    }

}