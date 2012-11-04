package cz.cvut.fel.aos.service.adapter;

/**
 * <p>Adaptér pro 3rd layer</p>
 *
 * @author Karel Cemus
 */
public class Reservation {

    private final cz.cvut.fel.aos.service.reservation.Reservation inner;

    public Reservation( final cz.cvut.fel.aos.service.reservation.Reservation inner ) {
        this.inner = inner;
    }

    public Reservation() {
        throw new UnsupportedOperationException( "Model is not designed to use no-argument constructor." );
    }

    public int getCount() {
        return inner.getCount();
    }

    public int getCost() {
        return inner.getCost();
    }

    public String getPassword() {
        return inner.getPassword();
    }

    public int getPaid() {
        return inner.getPaid();
    }

    public Flight getFlight() {
        return new Flight( inner.getFlight() );
    }

    public long getId() {
        return inner.getId();
    }

    public boolean isCanceled() {
        return inner.isCanceled();
    }

    public void setPaid( final int value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setFlight( final Flight value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setCount( final int value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setCost( final int value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setPassword( final String value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setCanceled( final boolean value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setId( final long value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }
}
