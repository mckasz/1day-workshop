package loans.direct;

import loans.direct.dto.OfferId;
import lombok.Value;

@Value
class TransactionAudit {
    private final OfferId transactionId;
    private final OfferStatus status;
    private final int amount;

}
