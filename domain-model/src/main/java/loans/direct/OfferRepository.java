package loans.direct;

import loans.direct.dto.OfferId;

interface OfferRepository {
    OfferId save(Offer transaction);
    Offer read(OfferId id);
}
