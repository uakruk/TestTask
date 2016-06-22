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

    Customer createCustomer(String email, String fullName, RequestOptions requestOptions);

    Card addCard(Customer customer, Token cardToken, String apiKey);
}
