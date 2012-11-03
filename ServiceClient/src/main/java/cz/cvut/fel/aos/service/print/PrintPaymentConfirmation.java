package cz.cvut.fel.aos.service.print;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;


/**
 * <p>Java class for printPaymentConfirmation complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="printPaymentConfirmation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="reservation" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="cardNumber" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "printPaymentConfirmation", propOrder = {
        "reservation",
        "cardNumber",
        "amount"
} )
public class PrintPaymentConfirmation
        implements Serializable {

    private final static long serialVersionUID = 1L;

    protected long reservation;

    protected long cardNumber;

    protected int amount;

    /** Gets the value of the reservation property. */
    public long getReservation() {
        return reservation;
    }

    /** Sets the value of the reservation property. */
    public void setReservation( long value ) {
        this.reservation = value;
    }

    /** Gets the value of the cardNumber property. */
    public long getCardNumber() {
        return cardNumber;
    }

    /** Sets the value of the cardNumber property. */
    public void setCardNumber( long value ) {
        this.cardNumber = value;
    }

    /** Gets the value of the amount property. */
    public int getAmount() {
        return amount;
    }

    /** Sets the value of the amount property. */
    public void setAmount( int value ) {
        this.amount = value;
    }

}
