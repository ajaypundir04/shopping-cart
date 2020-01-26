package com.cdk.global.shopping.cart.service;

import com.cdk.global.shopping.cart.model.Customer;

/**
 * @author Ajay Singh Pundir
 */
public interface DiscountService {

    double calculateBillAmount(double purchaseAmountList, Customer customerType);


}
