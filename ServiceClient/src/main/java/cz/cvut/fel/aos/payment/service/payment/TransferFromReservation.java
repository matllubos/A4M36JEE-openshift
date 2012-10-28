package cz.cvut.fel.aos.payment.service.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for transferFromReservation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="transferFromReservation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservationIdFrom" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="passwordFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="reservationIdTo" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="passwordTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "transferFromReservation", propOrder = {
        "reservationIdFrom",
        "passwordFrom",
        "reservationIdTo",
        "passwordTo"
} )
public class TransferFromReservation
        implements Serializable {

    private final static long serialVersionUID = 1L;

    protected long reservationIdFrom;

    protected String passwordFrom;

    protected long reservationIdTo;

    protected String passwordTo;

    /** Gets the value of the reservationIdFrom property. */
    public long getReservationIdFrom() {
        return reservationIdFrom;
    }

    /** Sets the value of the reservationIdFrom property. */
    public void setReservationIdFrom( long value ) {
        this.reservationIdFrom = value;
    }

    /**
     * Gets the value of the passwordFrom property.
     *
     * @return possible object is {@link String }
     */
    public String getPasswordFrom() {
        return passwordFrom;
    }

    /**
     * Sets the value of the passwordFrom property.
     *
     * @param value allowed object is {@link String }
     */
    public void setPasswordFrom( String value ) {
        this.passwordFrom = value;
    }

    /** Gets the value of the reservationIdTo property. */
    public long getReservationIdTo() {
        return reservationIdTo;
    }

    /** Sets the value of the reservationIdTo property. */
    public void setReservationIdTo( long value ) {
        this.reservationIdTo = value;
    }

    /**
     * Gets the value of the passwordTo property.
     *
     * @return possible object is {@link String }
     */
    public String getPasswordTo() {
        return passwordTo;
    }

    /**
     * Sets the value of the passwordTo property.
     *
     * @param value allowed object is {@link String }
     */
    public void setPasswordTo( String value ) {
        this.passwordTo = value;
    }

}
