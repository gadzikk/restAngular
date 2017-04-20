package jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import converter.LocalDateAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gadzik on 11.03.17.
 */
@Entity(name = "account")
@Table(name = "account")
public class Account {

    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "account_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "ACCOUNT_SEQ", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @OneToMany(targetEntity = Transfer.class , fetch = FetchType.LAZY , mappedBy = "senderAccount" , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<Transfer> transfers = new HashSet<>();

    @OneToMany(targetEntity = Operation.class , fetch = FetchType.LAZY , mappedBy = "account" , cascade = CascadeType.REMOVE)
    @JsonManagedReference
    private Set<Operation> operations = new HashSet<>();

    public Account() {
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(String email, String password, BigDecimal money) {
        this.email = email;
        this.password = password;
        this.money = money;
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Set<Transfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<Transfer> transfers) {
        this.transfers = transfers;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Set<Operation> getOperations() {
        return operations;
    }

    public void setOperations(Set<Operation> operations) {
        this.operations = operations;
    }

    @PrePersist
    public void prepersist() {
        creationDate = LocalDate.now();
    }
}
