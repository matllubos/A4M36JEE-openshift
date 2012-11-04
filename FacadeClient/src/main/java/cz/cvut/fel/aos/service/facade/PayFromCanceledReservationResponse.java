package cz.cvut.fel.aos.service.facade;

import javax.activation.DataHandler;
import javax.xml.bind.annotation.*;
import java.io.Serializable;


/**
 * <p>Java class for payFromCanceledReservationResponse complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="payFromCanceledReservationResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="return" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "payFromCanceledReservationResponse", propOrder = {
        "_return"
} )
public class PayFromCanceledReservationResponse
        implements Serializable {

    private final static long serialVersionUID = 1L;

    @XmlElement( name = "return" )
    protected boolean _return;

    /**
     * Gets the value of the return property.
     *
     * @return possible object is {@link DataHandler }
     */
    public boolean getReturn() {
        return _return;
    }

    /**
     * Sets the value of the return property.
     *
     * @param value allowed object is {@link DataHandler }
     */
    public void setReturn( boolean value ) {
        this._return = value;
    }

}
