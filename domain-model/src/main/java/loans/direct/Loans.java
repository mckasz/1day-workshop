package loans.direct;


import loans.direct.dto.LoanOfferCommand;
import loans.direct.dto.LoanOfferEvent;
import loans.direct.dto.OfferId;

class Loans {

    private final EventSender eventSender;
    private final OfferRepository offerRepository;

    Loans(EventSender eventSender, OfferRepository offerRepository) {
        this.eventSender = eventSender;
        this.offerRepository = offerRepository;
    }

    void loanOffer(LoanOfferCommand command) {
        OfferId id = offerRepository.save(new Offer(command.getUser1(), command.getUser2(), command.getAmount()));
        eventSender.send(new LoanOfferEvent(id, command.getUser1(), command.getUser2(), command.getAmount()));
    }

    void acceptOffer(OfferId offerId) {
        Offer offer = offerRepository.read(offerId);
        offer.accept();
        offerRepository.save(offer);
    }

    void rejectOffer(OfferId offerId) {
        Offer offer = offerRepository.read(offerId);
        offer.reject();
        offerRepository.save(offer);
    }

    public TransactionAudit getTransactionAudit(OfferId offerId) {
        Offer offer = offerRepository.read(offerId);
        return new TransactionAudit(offerId, offer.getStatus(), offer.getAmount());
    }
}
