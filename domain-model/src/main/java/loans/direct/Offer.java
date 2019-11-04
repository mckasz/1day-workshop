package loans.direct;

import loans.direct.dto.OfferId;
import loans.direct.dto.UserId;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
class Offer {
    @NonFinal
    private OfferId offerId;
    @NonFinal
    private OfferStatus status;
    private final UserId user1;
    private final UserId user2;
    private final int amount;

    Offer(UserId user1, UserId user2, int amount) {
        this.status = OfferStatus.INITIALIZED;
        this.user1 = user1;
        this.user2 = user2;
        this.amount = amount;
    }

    void accept() {
        status = OfferStatus.ACCEPTED;
    }

    void reject() {
        status = OfferStatus.REJECTED;
    }
}
