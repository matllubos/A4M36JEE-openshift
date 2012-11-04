package cz.cvut.fel.aos.service.facade;

import cz.cvut.fel.aos.utils.DateAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;


/**
 * <p>Java class for findFlights complex type.
 * <p/>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p/>
 * <pre>
 * &lt;complexType name="findFlights">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="intervalFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="intervalTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="codeFrom" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="codeTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType( XmlAccessType.FIELD )
@XmlType( name = "findFlights", propOrder = {
        "intervalFrom",
        "intervalTo",
        "codeFrom",
        "codeTo"
} )
public class FindFlights
        implements Serializable {

    private final static long serialVersionUID = 1L;

    @XmlElement( type = String.class )
    @XmlJavaTypeAdapter( DateAdapter.class )
    @XmlSchemaType( name = "dateTime" )
    protected Date intervalFrom;

    @XmlElement( type = String.class )
    @XmlJavaTypeAdapter( DateAdapter.class )
    @XmlSchemaType( name = "dateTime" )
    protected Date intervalTo;

    protected String codeFrom;

    protected String codeTo;

    /**
     * Gets the value of the intervalFrom property.
     *
     * @return possible object is {@link String }
     */
    public Date getIntervalFrom() {
        return intervalFrom;
    }

    /**
     * Sets the value of the intervalFrom property.
     *
     * @param value allowed object is {@link String }
     */
    public void setIntervalFrom( Date value ) {
        this.intervalFrom = value;
    }

    /**
     * Gets the value of the intervalTo property.
     *
     * @return possible object is {@link String }
     */
    public Date getIntervalTo() {
        return intervalTo;
    }

    /**
     * Sets the value of the intervalTo property.
     *
     * @param value allowed object is {@link String }
     */
    public void setIntervalTo( Date value ) {
        this.intervalTo = value;
    }

    /**
     * Gets the value of the codeFrom property.
     *
     * @return possible object is {@link String }
     */
    public String getCodeFrom() {
        return codeFrom;
    }

    /**
     * Sets the value of the codeFrom property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodeFrom( String value ) {
        this.codeFrom = value;
    }

    /**
     * Gets the value of the codeTo property.
     *
     * @return possible object is {@link String }
     */
    public String getCodeTo() {
        return codeTo;
    }

    /**
     * Sets the value of the codeTo property.
     *
     * @param value allowed object is {@link String }
     */
    public void setCodeTo( String value ) {
        this.codeTo = value;
    }

}
