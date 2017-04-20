package jpa;


import converter.LocalDateAdapter;
import converter.LocalDateTimeAdapter;

import javax.persistence.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;
import java.math.BigDecimal;


/**
 * Created by gadzik on 18.04.17.
 */
@Entity(name = "transfer")
@Table(name = "transfer")
public class Transfer {

    @Id
    @SequenceGenerator(name = "TRANSFER_SEQ" , sequenceName = "transfer_id_seq" , allocationSize = 1)
    @GeneratedValue(generator = "TRANSFER_SEQ", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "sender_id", referencedColumnName = "id")
    private Account senderAccount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "receiver_id", referencedColumnName = "id")
    private Account receiverAccount;

    @Column(name = "transfered_money")
    private BigDecimal transferedMoney;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column
    private BigDecimal money;


    public Transfer() {
    }


    public Transfer(Account senderAccount, Account receiverAccount, BigDecimal transferedMoney) {
        this.senderAccount = senderAccount;
        this.receiverAccount = receiverAccount;
        this.transferedMoney = transferedMoney;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getSenderAccount() {
        return senderAccount;
    }

    public void setSenderAccount(Account senderAccount) {
        this.senderAccount = senderAccount;
    }

    public Account getReceiverAccount() {
        return receiverAccount;
    }

    public void setReceiverAccount(Account receiverAccount) {
        this.receiverAccount = receiverAccount;
    }


    public BigDecimal getTransferedMoney() {
        return transferedMoney;
    }

    public void setTransferedMoney(BigDecimal transferedMoney) {
        this.transferedMoney = transferedMoney;
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
    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
