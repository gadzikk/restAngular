package request;

import java.math.BigDecimal;

/**
 * Created by gadzik on 17.04.17.
 */
public class TransferMoneyRequest {

    private Long receiverId;
    private BigDecimal transferedAmount;

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getTransferedAmount() {
        return transferedAmount;
    }

    public void setTransferedAmount(BigDecimal transferedAmount) {
        this.transferedAmount = transferedAmount;
    }
}
