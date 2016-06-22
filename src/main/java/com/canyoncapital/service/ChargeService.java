package com.canyoncapital.service;

import com.canyoncapital.util.Currency;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
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

    /**
     * make charge by card token
     * @param amount amount of charge
     * @param currency currency
     * @param cardToken card token
     * @param description desription of charge
     * @param requestOptions request charge
     * @return performed charge
     */
    Charge makeCharge(long amount, Currency currency, Token cardToken, String description,
                                                                RequestOptions requestOptions);

    Charge makeCharge(long amount, Currency currency, Customer customer, String description,
                      RequestOptions requestOptions);
}
