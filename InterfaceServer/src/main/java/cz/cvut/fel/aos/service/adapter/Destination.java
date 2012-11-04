package cz.cvut.fel.aos.service.adapter;

/**
 * <p>Adaptér pro 3rd layer</p>
 *
 * @author Karel Cemus
 */
public class Destination {

    private final cz.cvut.fel.aos.service.destination.Destination inner;

    public Destination( final cz.cvut.fel.aos.service.destination.Destination inner ) {
        this.inner = inner;
    }

    public Destination() {
        throw new UnsupportedOperationException( "Model is not designed to use no-argument constructor." );
    }

    public String getCode() {
        return inner.getCode();
    }

    public void setId( final long value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public void setName( final String value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public String getName() {
        return inner.getName();
    }

    public void setCode( final String value ) {
        throw new UnsupportedOperationException( "Method is not supported by adapter." );
    }

    public long getId() {
        return inner.getId();
    }
}
