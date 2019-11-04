package loans.direct;


import loans.direct.dto.LoanOfferEvent;

interface EventSender {
    void send(LoanOfferEvent event);
}
