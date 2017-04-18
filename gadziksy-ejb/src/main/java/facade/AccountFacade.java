package facade;

import facadeApi.AccountRepository;
import jpa.Account;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 12.04.17.
 */
@Stateless
public class AccountFacade extends AbstractFacade implements AccountRepository {

    @Override
    public Optional<Account> getAccountById(Long id) {
        return Optional.ofNullable(entityManager.find(Account.class, id));
    }

    @Override
    public Optional<Account> getAccountByEmail(String email) {
        TypedQuery<Account> query = entityManager.createQuery("SELECT X FROM account X WHERE X.email=:email", Account.class)
                .setParameter("email", email);
        return query.getResultList().stream().findFirst();
    }

    @Override
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public void updateAccount(Account account) {
        entityManager.merge(account);
    }
}
