package service;

import facadeApi.AccountRepository;
import facadeApi.TransferRepository;
import jpa.Account;
import jpa.Transfer;
import serviceApi.AccountService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.math.BigDecimal;

/**
 * Created by gadzik on 18.04.17.
 */
@Stateless
public class AccountServiceBean implements AccountService {

    @EJB
    private AccountRepository accountRepository;

    @EJB
    private TransferRepository transferRepository;

    @Override
    public void prepareTransfer(Account sender, Account receiver, BigDecimal transferedMoney) {

        BigDecimal senderMoney = sender.getMoney().subtract(transferedMoney);
        sender.setMoney(senderMoney);
        accountRepository.updateAccount(sender);

        BigDecimal receiverMoney = receiver.getMoney().add(transferedMoney);
        receiver.setMoney(receiverMoney);
        accountRepository.updateAccount(receiver);

        Transfer transfer = new Transfer(sender, receiver, transferedMoney);
        transferRepository.writeTransfer(transfer);
    }
}
