package com.canyoncapital.service;

import com.canyoncapital.util.Currency;
import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

/**
 * Charge Service
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public interface ChargeService {

    Charge makeCharge(long amount, Currency currency, Token cardToken, String description,
                                                                RequestOptions requestOptions);

    Charge makeCharge(long amount, Currency currency, Card card, String description,
                                                                RequestOptions requestOptions);
}
