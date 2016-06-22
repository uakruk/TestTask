package com.canyoncapital.service;

import com.stripe.exception.*;
import com.stripe.model.Card;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

/**
 * Customer service
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public interface CustomerService {

    /**
     * create customer without a card
     * @param email customer's email
     * @param fullName customer's full name will be used as descrition
     * @param requestOptions request options
     * @return customer has being created
     */
    Customer createCustomer(String email, String fullName, RequestOptions requestOptions);

    /**
     * attach a card by it's token to the customer. Some bicycles with apiKey (Request options
     * are not suitable here because of such library implementation
     * @param customer  customer to be charged
     * @param cardToken card token
     * @param apiKey    api key
     * @return a card was attached to the customer
     */
    Card addCard(Customer customer, Token cardToken, String apiKey);
}
