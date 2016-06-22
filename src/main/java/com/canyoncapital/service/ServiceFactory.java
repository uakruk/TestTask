package com.canyoncapital.service;

import com.canyoncapital.service.impl.CardServiceImpl;
import com.canyoncapital.service.impl.ChargeServiceImpl;
import com.canyoncapital.service.impl.CustomerServiceImpl;

/**
 * A factory for the services
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public class ServiceFactory {

    private static CardService cardService;
    private static ChargeService chargeService;
    private static CustomerService customerService;

    private ServiceFactory() {}

    public static CardService getCardService() {
        if (cardService == null) {
            cardService = new CardServiceImpl();
        }
        return cardService;
    }

    public static ChargeService getChargeService() {
        if (chargeService == null) {
            chargeService = new ChargeServiceImpl();
        }
        return chargeService;
    }

    public static CustomerService getCustomerService() {
        if (customerService == null) {
            customerService = new CustomerServiceImpl();
        }
        return customerService;
    }
}
