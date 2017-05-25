package facadeApi;

import jpa.Message;

import javax.ejb.Local;
import java.util.List;
import java.util.Optional;

/**
 * Created by gadzik on 24.05.17.
 */
@Local
public interface MailMessageRepository {
    List<Message> getAllMessages(Long id);
    void writeMessage(Message message);
    void removeMessage(Long id);
    Optional<Message> readMessage(Long accountId , Long messageId);
}
