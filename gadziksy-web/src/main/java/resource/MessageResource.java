package resource;

import facadeApi.AccountRepository;
import facadeApi.MailMessageRepository;
import jpa.Account;
import jpa.Message;
import request.SendMessageRequest;
import session.UserSession;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 25.05.17.
 */
@Path("/message")
@RequestScoped
public class MessageResource {

    @Inject
    private UserSession session;

    @EJB
    private MailMessageRepository messageRepository;

    @EJB
    private AccountRepository accountRepository;

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMessages() {
        List<Message> result = messageRepository.getAllMessages(session.getId());
        if (result.isEmpty()) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok().entity(result).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(@PathParam("id") Long id) {
        Optional<Message> opMessage = messageRepository.readMessage(session.getId(),id);
        if (!opMessage.isPresent()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Message message = opMessage.get();
        return Response.ok().entity(message).build();
    }

    @POST
    @Path("/send")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendMessage(SendMessageRequest request) {
        Optional<Account> opReciver = accountRepository.getAccountByEmail(request.getReceiverEmail());
        if (!opReciver.isPresent()) {
            Response.status(Response.Status.BAD_REQUEST).build();
        }
        Account receiver = opReciver.get();

        Message message = new Message(session.getEmail(), request.getSubject(), request.getContent(), receiver);
        messageRepository.writeMessage(message);

        return Response.ok().build();
    }

    @DELETE
    @Path("/remove/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeMessage(@PathParam("id") Long id){
        Optional<Message> opMessage = messageRepository.readMessage(session.getId(),id);
        if (!opMessage.isPresent()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        messageRepository.removeMessage(id);
        return Response.ok().build();
    }

}
