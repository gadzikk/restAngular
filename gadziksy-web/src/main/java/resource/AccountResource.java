package resource;

import facadeApi.AccountRepository;
import jpa.Account;
import request.SaveAccountRequest;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.Optional;

/**
 * Created by gadzik on 12.04.17.
 */
@Path("/account")
@RequestScoped
public class AccountResource {

    @EJB
    private AccountRepository accountRepository;

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(SaveAccountRequest request) {
        Account account = new Account(request.getEmail(), request.getPassword() , request.getMoney());
        accountRepository.createAccount(account);
        return Response.ok().build();
    }

    @GET
    @Path("/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAccountByEmail(@PathParam("email") String email) {
        Optional<Account> opAccount = accountRepository.getAccountByEmail(email);
        if (!opAccount.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Account account = opAccount.get();
        return Response.ok().entity(account).build();
    }

}
