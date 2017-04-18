package serviceApi;

import jpa.Account;

import javax.ejb.Local;
import java.math.BigDecimal;

/**
 * Created by gadzik on 18.04.17.
 */
@Local
public interface AccountService {

    void prepareTransfer(Account sender, Account receiver, BigDecimal transferedMoney);
}
