package resource;

import facadeApi.AccountRepository;
import facadeApi.TransferRepository;
import jpa.Account;
import jpa.Transfer;
import model.GuestInfo;
import request.SaveAccountRequest;
import request.TransferMoneyRequest;
import response.TransferMoneyResponse;
import serviceApi.AccountService;
import session.UserSession;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Created by gadzik on 12.04.17.
 */
@Path("/account")
@RequestScoped
public class AccountResource {

    @EJB
    private AccountRepository accountRepository;

    @Inject
    private UserSession session;

    @EJB
    private AccountService accountService;

    private TransferRepository transferRepository;


    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createAccount(SaveAccountRequest request) {
        Account account = new Account(request.getEmail(), request.getPassword(), request.getMoney());
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

    @POST
    @Path("/transfer")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response transferMoney(TransferMoneyRequest request) {
        Optional<Account> opAccount = accountRepository.getAccountById(request.getReceiverId());
        if (!opAccount.isPresent()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        if (request.getTransferedAmount().compareTo(BigDecimal.ZERO) < 1 || session.getMoney().compareTo(request.getTransferedAmount()) < 0) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        Account sender = accountRepository.getAccountByEmail(session.getEmail()).get();
        Account receiver = opAccount.get();

        accountService.prepareTransfer(sender, receiver, request.getTransferedAmount());

        Transfer transfer = new Transfer();
        transfer.setMoney(request.getTransferedAmount());
        transfer.setReceiverAccount(receiver);
        transfer.setSenderAccount(sender);

        transferRepository.writeTransfer(transfer);

        TransferMoneyResponse response = new TransferMoneyResponse.Builder()
                .sender(new GuestInfo(sender.getId(), sender.getEmail()))
                .receiver(new GuestInfo(receiver.getId(), receiver.getEmail()))
                .transferedMoney(request.getTransferedAmount())
                .build();

        return Response.ok().entity(response).build();
    }


}
