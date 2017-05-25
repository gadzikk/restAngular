package facade;

import facadeApi.MailMessageRepository;
import jpa.Message;


import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 24.05.17.
 */
@Stateless
public class MailMessageFacade extends AbstractFacade implements MailMessageRepository {

    @Override
    public List<Message> getAllMessages(Long id) {
        TypedQuery<Message> query = entityManager.createQuery("SELECT M FROM message M where M.account.id=:id", Message.class)
                .setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public void writeMessage(Message message) {
        entityManager.persist(message);
    }

    @Override
    public void removeMessage(Long id) {
        Message message = entityManager.find(Message.class,id);
        entityManager.remove(message);
    }

    @Override
    public Optional<Message> readMessage(Long accountId, Long messageId) {
        TypedQuery<Message> query = entityManager.
                createQuery("SELECT M FROM message M WHERE M.account.id=:accountId and M.id=:messageId",Message.class)
                .setParameter("accountId",accountId)
                .setParameter("messageId",messageId);

        return query.getResultList().stream().findFirst();
    }
}
