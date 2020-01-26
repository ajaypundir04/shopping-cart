package com.cdk.global.shopping.cart.output;

import com.cdk.global.shopping.cart.model.Invoice;

public class ConsoleLogger implements Logger {
    @Override
    public void printInvoice(Invoice invoice) {
        System.out.println(
                String.join("",
                        String.format("%-20s",  invoice.getCustomer().getCustomerType()),
                        String.format("%-20d", Double.valueOf(invoice.getPurchasedAmount()).intValue()),
                        String.format("%-20s", Double.valueOf(invoice.getBillAmount()).intValue())));

    }

    @Override
    public void printHeader() {
        System.out.println(
                String.join("",
                        String.format("%-20s", "Customer "),
                        String.format("%-20s", "Purchased Amount"),
                        String.format("%-20s", "Bill Amount")));
    }
}
