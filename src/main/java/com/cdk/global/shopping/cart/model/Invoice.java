package com.cdk.global.shopping.cart.model;

/**
 * @author Ajay Singh Pundir
 * All the invoices related information
 */
public class Invoice {

    private Customer customer;
    private double purchasedAmount;
    private double billAmount;


    public Invoice(Customer customer, double purchasedAmount) {
        this.customer = customer;
        this.purchasedAmount = purchasedAmount;
        this.billAmount = 0.0d;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(double purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(double billAmount) {
        this.billAmount = billAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "customer=" + customer +
                ", purchasedAmount=" + purchasedAmount +
                ", billAmount=" + billAmount +
                '}';
    }
}
