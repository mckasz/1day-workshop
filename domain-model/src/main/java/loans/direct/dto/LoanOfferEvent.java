package loans.direct.dto;

import loans.direct.Event;
import lombok.Value;

@Value
public class LoanOfferEvent implements Event {
    private final OfferId offerId;
    private final UserId loaner;
    private final UserId accepter;
    private final int amount;

}
