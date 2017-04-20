package jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import converter.LocalDateTimeAdapter;
import enums.OperationType;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Created by gadzik on 19.04.17.
 */
@Entity(name = "operation")
@Table(name = "operation")
public class Operation {

    @Id
    @SequenceGenerator(name = "OPERATION_SEQ", sequenceName = "operation_id_seq" , allocationSize = 1)
    @GeneratedValue(generator = "OPERATION_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @JsonBackReference
    private Account account;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private OperationType type;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    public Operation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @PrePersist
    public void prepersist() {
        creationDate = LocalDateTime.now();
    }
}
