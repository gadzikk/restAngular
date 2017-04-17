package resource;

import facadeApi.AccountRepository;
import jpa.Account;
import model.User;
import request.LoginRequest;
import serviceApi.AuthenticationService;
import session.UserSession;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

/**
 * Created by gadzik on 14.04.17.
 */
@Path("/authentication")
@RequestScoped
public class AuthenticationResource {

    @EJB
    private AccountRepository accountRepository;

    @EJB
    private AuthenticationService authenticationService;

    @Inject
    private UserSession session;

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest request) {
        Optional<Account> opAccount = accountRepository.getAccountByEmail(request.getEmail());
        if (!opAccount.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Account account = opAccount.get();
        if (!account.getPassword().equals(request.getPassword())) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        authenticationService.assignSession(account);
        User user = new User(session.getEmail() , session.getMoney() , session.getCreationDate());
        return Response.ok().entity(user).build();
    }


}
