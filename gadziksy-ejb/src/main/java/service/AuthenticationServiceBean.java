package service;

import jpa.Account;
import serviceApi.AuthenticationService;
import session.UserSession;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.math.BigDecimal;

/**
 * Created by gadzik on 14.04.17.
 */
@Stateless
public class AuthenticationServiceBean implements AuthenticationService {

    @Inject
    private UserSession session;

    @Override
    public void assignSession(Account account) {
        session.setEmail(account.getEmail());
        session.setCreationDate(account.getCreationDate());
        session.setMoney(account.getMoney());
    }

    @Override
    public void clearSession() {
        session.setEmail("");
        session.setCreationDate(null);
        session.setMoney(new BigDecimal(0.0));
    }
}
