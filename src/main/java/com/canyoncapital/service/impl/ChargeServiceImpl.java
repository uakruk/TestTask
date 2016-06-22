package com.canyoncapital.service.impl;

import com.canyoncapital.service.ChargeService;
import com.canyoncapital.util.Currency;
import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

import java.util.Map;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Charge Service Implementation
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public class ChargeServiceImpl implements ChargeService {

    // logger for this class
    private static Logger logger = Logger.getLogger(ChargeService.class.getName());

    /**
     *
     * @param amount amount of charge
     * @param currency currency
     * @param cardToken card token
     * @param description desription of charge
     * @param requestOptions request charge
     * @return performed charge
     */
    @Override
    public Charge makeCharge(long amount, Currency currency, Token cardToken, String description,
                                                                           RequestOptions requestOptions) {
        Map<String, Object> chargeParams = getChargeParams(amount, currency, description);
        chargeParams.put("source", cardToken.getId());

        return charge(chargeParams, requestOptions);
    }

    /**
     *
     * @param amount amount of charge
     * @param currency currency
     * @param customer customer
     * @param description description of charge
     * @param requestOptions request options
     * @return performed charge
     */
    @Override
    public Charge makeCharge(long amount, Currency currency, Customer customer, String description,
                             RequestOptions requestOptions) {
        Map<String, Object> chargeParams = getChargeParams(amount, currency, description);
        chargeParams.put("customer", customer.getId());
        return charge(chargeParams, requestOptions);
    }

    // creating map with charge parameters
    private Map<String, Object> getChargeParams(long amount, Currency currency, String description) {
        Map<String, Object> chargeParams = new HashMap<>();

        chargeParams.put("amount", amount);
        chargeParams.put("currency", currency.toString());
        chargeParams.put("description", description);
        return chargeParams;
    }

    // main charge method
    private Charge charge(Map<String, Object> chargeParams, RequestOptions requestOptions) {
        Charge resp = null;

        try {
            resp = Charge.create(chargeParams, requestOptions);
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
