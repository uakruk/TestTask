package com.canyoncapital.service.impl;

import com.canyoncapital.service.CustomerService;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public class CustomerServiceImpl implements CustomerService {

    private static Logger logger = Logger.getLogger(CustomerService.class.getName());

    @Override
    public Customer createCustomer(String email, String fullName, RequestOptions requestOptions) {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("email", email);
        customerParams.put("description", fullName);

        Customer resp = null;

        try {
            resp = Customer.create(customerParams, requestOptions);
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

    @Override
    public Card addCard(Customer customer, Token cardToken) {
        Map<String, Object> params = new HashMap<>();
        params.put("source", cardToken.getId());

        Card resp = null;

        try {
            resp = customer.createCard(cardToken.getId());
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
