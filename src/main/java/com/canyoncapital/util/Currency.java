package com.canyoncapital.util;

/**
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public enum Currency {
    USD("usd"),
    EUR("eur"),
    UAH("uah"),
    RUB("rub");

    private final String currency;

    Currency(final String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return currency;
    }
}
