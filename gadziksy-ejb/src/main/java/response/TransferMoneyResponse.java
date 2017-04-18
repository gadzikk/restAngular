package response;

import model.GuestInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by gadzik on 17.04.17.
 */
public class TransferMoneyResponse implements Serializable {
    private GuestInfo sender;
    private GuestInfo receiver;
    private BigDecimal transferedMoney;

    public TransferMoneyResponse() {
    }

    private TransferMoneyResponse(Builder builder) {
        setSender(builder.sender);
        setReceiver(builder.receiver);
        setTransferedMoney(builder.transferedMoney);
    }

    public GuestInfo getSender() {
        return sender;
    }

    public void setSender(GuestInfo sender) {
        this.sender = sender;
    }

    public BigDecimal getTransferedMoney() {
        return transferedMoney;
    }

    public void setTransferedMoney(BigDecimal transferedMoney) {
        this.transferedMoney = transferedMoney;
    }

    public GuestInfo getReceiver() {
        return receiver;
    }

    public void setReceiver(GuestInfo receiver) {
        this.receiver = receiver;
    }


    public static final class Builder {
        private GuestInfo sender;
        private GuestInfo receiver;
        private BigDecimal transferedMoney;

        public Builder() {
        }

        public Builder sender(GuestInfo val) {
            sender = val;
            return this;
        }

        public Builder receiver(GuestInfo val) {
            receiver = val;
            return this;
        }

        public Builder transferedMoney(BigDecimal val) {
            transferedMoney = val;
            return this;
        }

        public TransferMoneyResponse build() {
            return new TransferMoneyResponse(this);
        }
    }
}
