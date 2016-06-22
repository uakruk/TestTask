package com.canyoncapital.dto;

/**
 *
 * This interface used for retrieving user's data from a web page
 * actually, it's not finished & implemented & used yet.
 *
 * @author Yaroslav Kruk on 6/22/16.
 *         e-mail: yakruck@gmail.com
 *         GitHub: https://github.com/uakruk
 * @version 1.0
 * @since 1.7
 */
public interface PaymentDetails {

    String getEmail();

    void setEmail(String email);

    long getCardNumber();

    void setCardNumber(long cardNumber);

    int getMonth();

    void setMonth(int month);

    int getYear();

    void setYear(int year);

    int getCVC();

    void setCVC(int CVC);

}
