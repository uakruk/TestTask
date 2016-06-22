package com.canyoncapital.service.impl;

import com.canyoncapital.service.CardService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of Card Service
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public class CardServiceImpl implements CardService {

    // Logger
    private static Logger logger = Logger.getLogger(CardService.class.getName());

    /**
     *
     * @param cardNumber card number
     * @param expMonth  month expires
     * @param expYear   year expires
     * @param cvc       CVC
     * @param requestOptions    default request options
     * @return
     */
    @Override
    public Token createCard(long cardNumber, int expMonth, int expYear, int cvc, RequestOptions requestOptions) {

        Map<String, Object> tokenParams = new HashMap<>();
        Map<String, Object> cardParams = new HashMap<>();

        cardParams.put("number", cardNumber);
        cardParams.put("exp_month", expMonth);
        cardParams.put("exp_year", expYear);
        cardParams.put("cvc", cvc);

        tokenParams.put("card", cardParams);

        Token resp = null;
        try {
            resp =  Token.create(tokenParams, requestOptions);
        } catch (CardException e) {
            logger.log(Level.SEVERE, "Card exception", e);
        } catch (RateLimitException e) {
            logger.log(Level.WARNING, "Too many requests made to the API too quickly", e);
        } catch (InvalidRequestException e) {
            logger.log(Level.SEVERE, "Invalid parameters were supplied to Stripe's API", e);
        } catch (AuthenticationException e) {
            logger.log(Level.SEVERE, "Authentication with Stripe's API failed " +
                    "(maybe you changed API keys recently)", e);
        } catch (APIConnectionException e) {
            logger.log(Level.SEVERE, "Network communication with Stripe failed", e);
        } catch (StripeException e) {
            logger.log(Level.SEVERE, "Something wrong. Connect with support", e);
        }
        return resp;
    }
}