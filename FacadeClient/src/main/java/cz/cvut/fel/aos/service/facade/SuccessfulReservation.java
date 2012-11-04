package cz.cvut.fel.aos.service.facade;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for successfulReservation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="successfulReservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="confirmation" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="reservation" type="{http://facade.service.aos.fel.cvut.cz/}reservation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "successfulReservation", propOrder = {
        "confirmation",
        "reservation"
} )
public class SuccessfulReservation
        implements Serializable {

    private final static long serialVersionUID = 1L;

    protected byte[]  confirmation;

    protected Reservation reservation;

    /**
     * Gets the value of the confirmation property.
     *
     * @return possible object is byte[]
     */
    public byte[]  getConfirmation() {
        return confirmation;
    }

    /**
     * Sets the value of the confirmation property.
     *
     * @param value allowed object is byte[]
     */
    public void setConfirmation( byte[]  value ) {
        this.confirmation = ( value );
    }

    /**
     * Gets the value of the reservation property.
     *
     * @return possible object is {@link Reservation }
     */
    public Reservation getReservation() {
        return reservation;
    }

    /**
     * Sets the value of the reservation property.
     *
     * @param value allowed object is {@link Reservation }
     */
    public void setReservation( Reservation value ) {
        this.reservation = value;
    }

}
