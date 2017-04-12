package facadeApi;

import jpa.Account;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 12.04.17.
 */
@Local
public interface AccountRepository {

    Optional<Account> getAccountByEmail(String email);
    void createAccount(Account account);
    void updateAccount(Account account);
}
