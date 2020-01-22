package com.cdk.global.shopping.cart.model;

import java.util.Objects;

public class Customer {

    private String customerType;

    public Customer(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return Objects.equals(getCustomerType(), customer.getCustomerType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCustomerType());
    }
}
