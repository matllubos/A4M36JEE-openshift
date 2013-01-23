package cz.cvut.fel.dto;

import cz.cvut.fel.model.Destination;
import lombok.Data;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** @author Karel Cemus */
@Data
public class DestinationDTO {

    /** destination code */
    private String code;

    /** url of current resource */
    private String resourceUrl;

    /** destination name */
    private String name;

    /** instance is valid until deletion date */
    private boolean deleted;

    public DestinationDTO( UriInfo uri, Destination destination ) {
        this.code = destination.getCode();
        this.resourceUrl = getURL( uri, code );
        this.name = destination.getName();
        this.deleted = destination.getValidUntil().getTime() <= new Date().getTime();
    }

    public static String getURL( final UriInfo uri, final String code ) {
        UriBuilder builder = uri.getBaseUriBuilder();
        builder.path( "destination" );
        builder.path( code );
        return builder.build().toString();
    }

    public static List<DestinationDTO> convert( UriInfo uri, List<Destination> destinations ) {
        List<DestinationDTO> converted = new ArrayList<DestinationDTO>( destinations.size() );

        for ( Destination destination : destinations ) {
            converted.add( new DestinationDTO( uri, destination ) );
        }

        return converted;
    }
}
