package resource;

import facadeApi.OperationRepository;
import jpa.Operation;
import serviceApi.OperationService;
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
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
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

    @EJB
    private OperationService operationService;

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
    public Response getLastOperation() {
        Optional<Operation> opOperation = operationRepository.getLastOperation(session.getId());
        if (!opOperation.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Operation operation = opOperation.get();
        return Response.ok().entity(operation).build();
    }

    @GET
    @Path("/average")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countAverage() {
        List<Operation> result = operationRepository.getAllOperations(session.getId());
        if (result.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        BigDecimal average = operationService.countAverage(result);
        return Response.ok().entity(average).build();
    }

    @GET
    @Path("/median")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countMedian() {
        List<Operation> result = operationRepository.getAllOperations(session.getId());
        if (result.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        BigDecimal median = operationService.countMedian(result);
        return Response.ok().entity(median).build();
    }

    @GET
    @Path("/mode")
    @Produces(MediaType.APPLICATION_JSON)
    public Response countMode() {
        List<Operation> result = operationRepository.getAllOperations(session.getId());
        if (result.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Optional<BigDecimal> opMode = operationService.countMode(result);
        if (!opMode.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        BigDecimal mode = opMode.get();
        return Response.ok().entity(mode).build();
    }
}
