package resource;

import facadeApi.OperationRepository;
import jpa.Operation;
import session.UserSession;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 19.04.17.
 */
@Path("/operation")
@RequestScoped
public class OperationResource {

    @EJB
    private OperationRepository operationRepository;

    @Inject
    private UserSession session;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getAllOperations() {
        List<Operation> result = operationRepository.getAllOperations(session.getId());
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/last")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLastOperation() {
        Optional<Operation> opOperation = operationRepository.getLastOperation(session.getId());
        if(!opOperation.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Operation operation = opOperation.get();
        return Response.ok().entity(operation).build();
    }
}
