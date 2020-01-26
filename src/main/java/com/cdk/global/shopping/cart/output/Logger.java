package com.cdk.global.shopping.cart.output;

import com.cdk.global.shopping.cart.model.Invoice;

/**
 * @author Ajay Singh Pundir
 * It will be used to log the output.
 */
public interface Logger {

    void printInvoice(Invoice invoice);

    void printHeader();
}
