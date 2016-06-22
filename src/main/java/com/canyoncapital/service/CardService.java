package com.canyoncapital.service;

import com.stripe.exception.*;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

/**
 * Card Service
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public interface CardService {

    /**
     * create token for a card
     * @param cardNumber card number
     * @param expMonth  month expires
     * @param expYear   year expires
     * @param cvc       CVC
     * @param requestOptions    default request options
     * @return a token for a newly created card
     */
    Token createCard(long cardNumber, int expMonth, int expYear, int cvc, RequestOptions requestOptions);
}
