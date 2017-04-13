package serviceApi;

import jpa.Account;

import javax.ejb.Local;

/**
 * Created by gadzik on 14.04.17.
 */
@Local
public interface AuthenticationService {
    void assignSession(Account account);
    void clearSession();
}
