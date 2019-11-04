package loans.direct

import loans.direct.dto.LoanOfferCommand
import loans.direct.dto.LoanOfferEvent
import loans.direct.dto.UserId
import spock.lang.Specification


class FirstSpecification extends Specification {
    // Associated unit test
    def "User1 offers loan to user2, user2 accepts offer"() {
        given:
        TestEventSender eventSender = new TestEventSender()
        Loans app = new Loans(eventSender, new InMemoryTransactionRepository())
        UserId loaner = new UserId(123)
        UserId accepter = new UserId(234)
        int amount = 20
        LoanOfferCommand command = new LoanOfferCommand(loaner, accepter, amount)

        when:
        app.loanOffer(command)

        then:
        def lastEvent = eventSender.getLastEvent()
        lastEvent instanceof LoanOfferEvent
        def event = (LoanOfferEvent) lastEvent
        event.getLoaner() == loaner
        event.getAccepter() == accepter
        event.getAmount() == amount
        event.getOfferId().getId() > 0

        when:
        app.acceptOffer(event.getOfferId())

        then:
        TransactionAudit transactionAudit = app.getTransactionAudit(event.getOfferId())
        transactionAudit.getStatus() == OfferStatus.ACCEPTED
        transactionAudit.getAmount() == 20
    }

    def "User1 offers loan to user2, user2 rejects offer"() {
        given:
        TestEventSender eventSender = new TestEventSender()
        Loans app = new Loans(eventSender, new InMemoryTransactionRepository())
        UserId loaner = new UserId(123)
        UserId accepter = new UserId(234)
        int amount = 20
        LoanOfferCommand command = new LoanOfferCommand(loaner, accepter, amount)

        when:
        app.loanOffer(command)

        then:
        def lastEvent = eventSender.getLastEvent()
        lastEvent instanceof LoanOfferEvent
        def event = (LoanOfferEvent) lastEvent
        event.getLoaner() == loaner
        event.getAccepter() == accepter
        event.getAmount() == amount
        event.getOfferId().getId() > 0

        when:
        app.rejectOffer(event.getOfferId())

        then:
        TransactionAudit transactionAudit = app.getTransactionAudit(event.getOfferId())
        transactionAudit.getStatus() == OfferStatus.REJECTED
        transactionAudit.getAmount() == 20
    }
}
