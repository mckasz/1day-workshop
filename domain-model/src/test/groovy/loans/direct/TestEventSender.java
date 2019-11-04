package loans.direct;

import loans.direct.dto.LoanOfferEvent;

import java.util.LinkedList;

class TestEventSender implements EventSender {

    private LinkedList<Event> events = new LinkedList<>();

    @Override
    public void send(LoanOfferEvent event) {
        events.add(event);
    }

    public Event getLastEvent() {
        return events.getLast();
    }
}
