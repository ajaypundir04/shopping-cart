package com.cdk.global.shopping.cart.service.impl;

import com.cdk.global.shopping.cart.model.Invoice;
import com.cdk.global.shopping.cart.service.DiscountService;

public class ShoppingCart {

    private Invoice invoice;
    private DiscountService discountService;

    public ShoppingCart(Invoice invoice, DiscountService discountService) {
        this.invoice = invoice;
        this.discountService = discountService;
    }

    public Invoice getInvoice() {
        invoice.setBillAmount((
                discountService.calculateBillAmount
                        (
                                invoice.getPurchasedAmount(),
                                invoice.getCustomer())
        ));
        return invoice;
    }


}
