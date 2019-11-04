package loans.direct.dto;

import lombok.Value;

@Value
public class LoanOfferCommand {
    private final UserId user1;
    private final UserId user2;
    private final int amount;
}
