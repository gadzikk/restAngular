package jpa;

import converter.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;

/**
 * Created by gadzik on 11.03.17.
 */
@Entity(name = "account")
@Table(name = "account")
public class Account {

    @SequenceGenerator(name="ACCOUNT_SEQ" , sequenceName = "account_id_seq", allocationSize = 1)
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "ACCOUNT_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
    @PrePersist
    public void prepersist(){
        creationDate = LocalDate.now();
    }
}
