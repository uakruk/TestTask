package com.canyoncapital;

import com.canyoncapital.service.CardService;
import com.canyoncapital.service.ChargeService;
import com.canyoncapital.service.CustomerService;
import com.canyoncapital.service.ServiceFactory;
import com.canyoncapital.util.Currency;
import com.stripe.model.Card;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import com.stripe.net.RequestOptions;

import java.util.logging.Logger;

/**
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public class PaymentWorker {

    private final String API_KEY;

    private static Logger logger = Logger.getLogger(PaymentWorker.class.getName());

    private RequestOptions requestOptions;

    private CardService cardService;
    private ChargeService chargeService;
    private CustomerService customerService;

    // Input data //
    private String email = "test@test.com";
    private String fullName = "Yaroslav Kruk";
    private long cardNumber = 4242_4242_4242_4242L;
    private int expMonth = 3;
    private int expYear = 2020;
    private Currency currency = Currency.EUR;
    private int cvc = 333;
    private long amount = 400;
    private String description = "Test charge";


    public PaymentWorker(String API_KEY) {
        this.API_KEY = API_KEY;
        requestOptions = new RequestOptions.RequestOptionsBuilder().setApiKey(API_KEY).build();
        cardService = ServiceFactory.getCardService();
        chargeService = ServiceFactory.getChargeService();
        customerService = ServiceFactory.getCustomerService();
    }

    public void doTask() {
        Customer customer = customerService.createCustomer(email, fullName, requestOptions);
        System.out.println(customer);
        System.err.println("Successfully created the customer");
        Token cardToken = cardService.createCard(cardNumber, expMonth, expYear, cvc, requestOptions);
        System.out.println(cardToken);
        System.err.println("Successfully created the card token");
   //     Card card = customerService.addCard(customer, cardToken);
  //      System.out.println(card);
        System.err.println("Successfully added the card to the user");
        Charge charge = chargeService.makeCharge(amount, currency, cardToken, description, requestOptions);
        System.out.println(charge);
        System.err.println("Successfully performed the charge");
    }

}
