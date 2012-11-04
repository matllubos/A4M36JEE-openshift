package cz.cvut.fel.aos.service.adapter;

import java.util.Date;

/**
 * <p>Adaptér pro 3rd layer</p>
 *
 * @author Karel Cemus
 */
public class Flight {

    private final cz.cvut.fel.aos.service.flight.Flight inner;

    public Flight( final cz.cvut.fel.aos.service.flight.Flight inner ) {
        this.inner = inner;
    }

    public Flight() {
        throw new UnsupportedOperationException( "This method is not supported by adapter." );
    }

    public Destination getFrom() {
        return new Destination( inner.getFrom() );
    }

    public Destination getTo() {
        return new Destination( inner.getTo() );
    }

    public Date getArrival() {
        return inner.getArrival();
    }

    public int getCapacityLeft() {
        return inner.getCapacityLeft();
    }

    public Date getDeparture() {
        return inner.getDeparture();
    }

    public int getCost() {
        return inner.getCost();
    }

    public String getNumber() {
        return inner.getNumber();
    }

    public int getCapacity() {
        return inner.getCapacity();
    }

    public long getId() {
        return inner.getId();
    }

    public void setId( final long value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setNumber( final String value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setCapacityLeft( final int value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setTo( final Destination value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setFrom( final Destination value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setDeparture( final Date value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setCapacity( final int value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setArrival( final Date value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setCost( final int value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }
}
