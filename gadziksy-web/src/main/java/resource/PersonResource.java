package resource;

import facadeApi.PersonRepository;
import jpa.Person;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by gadzik on 09.03.17.
 */
@Path("/person")
@RequestScoped
public class PersonResource {

    @EJB
    private PersonRepository personRepository;

    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addPerson(Person person) {
        personRepository.addPerson(person);
        return Response.ok().build();
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePerson(Person person) {
        Person p1 = personRepository.getParticularPerson(person.getId());
        if (p1 == null) {
            Response.status(Response.Status.BAD_REQUEST);
        }
        personRepository.updatePerson(person);
        return Response.ok().build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPersons() {
        return Response.ok().entity(personRepository.getAllPersons()).build();
    }

    @DELETE
    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
    public Response deletePerson(@PathParam("id") long id) {
        Person p1 = personRepository.getParticularPerson(id);
        if (p1 == null) {
            Response.status(Response.Status.BAD_REQUEST);
        }
        personRepository.deletePerson(id);
        return Response.ok().build();
    }
}
