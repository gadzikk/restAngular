package jpa;

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

    @Column
    private BigDecimal money;

    public Transfer() {
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

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }
}
