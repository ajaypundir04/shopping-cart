package com.cdk.global.shopping.cart.output;

import com.cdk.global.shopping.cart.model.Invoice;

public interface Logger {

    void printInvoice(Invoice invoice);
    void printHeader();
}
