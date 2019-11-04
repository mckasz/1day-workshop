package loans.direct;

import loans.direct.dto.OfferId;

import java.util.HashMap;
import java.util.Map;

class InMemoryTransactionRepository implements OfferRepository {

    private final Map<OfferId, Offer> transactions = new HashMap<>();

    @Override
    public OfferId save(Offer transaction) {
        OfferId id = new OfferId(2);
        transactions.put(id, transaction);
        return id;
    }

    @Override
    public Offer read(OfferId id) {
        return transactions.get(id);
    }
}
