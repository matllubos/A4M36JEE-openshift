package cz.cvut.fel.rest;

import cz.cvut.fel.dto.DestinationDTO;
import cz.cvut.fel.service.DestinationService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

/**
 * <p>RESP API provider for destination data source</p>
 *
 * @author Karel Cemus
 */
@Path( "/destination" )
@Produces( MediaType.APPLICATION_JSON )
public class DestinationRest {

    @Inject
    private DestinationService service;

    @Context
    private UriInfo uri;

    @GET
    @Path( "/" )
    public List<DestinationDTO> findAll() {
        return DestinationDTO.convert( uri, service.findAllDestinations() );
    }

    @GET
    @Path( "/{code}" )
    public Response getInstance( @PathParam( "code" ) String code ) {
        try {
            return Response.ok( new DestinationDTO( uri, service.findByCode( code ) ) ).build();
        } catch ( Exception ignored ) {
            return Response.status( Response.Status.NOT_FOUND ).build();
        }
    }
}
