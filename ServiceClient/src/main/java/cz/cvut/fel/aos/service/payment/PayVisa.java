package cz.cvut.fel.aos.service.payment;

import cz.cvut.fel.aos.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>Java class for payVisa complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="payVisa">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservationId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cardName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCard" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="validUntil" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="verificationCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "payVisa", propOrder = {
        "reservationId",
        "password",
        "cardName",
        "creditCard",
        "validUntil",
        "verificationCode"
} )
public class PayVisa
        implements Serializable {

    private final static long serialVersionUID = 1L;

    protected long reservationId;

    protected String password;

    protected String cardName;

    protected long creditCard;

    @XmlElement( type = String.class )
    @XmlJavaTypeAdapter( DateAdapter.class )
    @XmlSchemaType( name = "dateTime" )
    protected Date validUntil;

    protected int verificationCode;

    /** Gets the value of the reservationId property. */
    public long getReservationId() {
        return reservationId;
    }

    /** Sets the value of the reservationId property. */
    public void setReservationId( long value ) {
        this.reservationId = value;
    }

    /**
     * Gets the value of the password property.
     *
     * @return possible object is {@link String }
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     *
     * @param value allowed object is {@link String }
     */
    public void setPassword( String value ) {
        this.password = value;
    }

    /**
     * Gets the value of the cardName property.
     *
     * @return possible object is {@link String }
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * Sets the value of the cardName property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCardName( String value ) {
        this.cardName = value;
    }

    /** Gets the value of the creditCard property. */
    public long getCreditCard() {
        return creditCard;
    }

    /** Sets the value of the creditCard property. */
    public void setCreditCard( long value ) {
        this.creditCard = value;
    }

    /**
     * Gets the value of the validUntil property.
     *
     * @return possible object is {@link String }
     */
    public Date getValidUntil() {
        return validUntil;
    }

    /**
     * Sets the value of the validUntil property.
     *
     * @param value allowed object is {@link String }
     */
    public void setValidUntil( Date value ) {
        this.validUntil = value;
    }

    /** Gets the value of the verificationCode property. */
    public int getVerificationCode() {
        return verificationCode;
    }

    /** Sets the value of the verificationCode property. */
    public void setVerificationCode( int value ) {
        this.verificationCode = value;
    }

}
