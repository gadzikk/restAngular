package jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import converter.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by gadzik on 24.05.17.
 */
@Entity(name = "message")
@Table(name = "message")
public class Message {

    @Id
    @SequenceGenerator(name = "MESSAGE_SEQ" , sequenceName = "message_id_seq" , allocationSize = 1)
    @GeneratedValue(generator = "MESSAGE_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "sender")
    private String sender;

    @Column(name = "subject")
    private String subject;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id" , referencedColumnName = "id")
    @JsonBackReference
    private Account account;

    public Message() {
    }

    public Message(String sender, String subject, String content, Account account) {
        this.sender = sender;
        this.subject = subject;
        this.content = content;
        this.account = account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @PrePersist
    public void prepersist() {
        creationDate = LocalDateTime.now();
    }
}
