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
    private TransferRepository transferRepository;

    @Override
    public void prepareTransfer(Account sender, Account receiver, BigDecimal transferedMoney) {

        Transfer transfer = new Transfer(sender, receiver, transferedMoney);
        transferRepository.writeTransfer(transfer);

//        CREATE FUNCTION doTransfer() RETURNS trigger AS $$
//        BEGIN
//        UPDATE ACCOUNT SET MONEY = MONEY - NEW.transfered_money WHERE id = NEW.SENDER_ID;
//        UPDATE ACCOUNT SET MONEY = MONEY + NEW.transfered_money WHERE id = NEW.RECEIVER_ID;
//        RETURN NULL;
//        END;
//        $$ language plpgsql;
//
//        CREATE TRIGGER transfertrg
//        AFTER INSERT
//        ON transfer
//        FOR EACH ROW
//        EXECUTE PROCEDURE doTransfer();
    }
}
