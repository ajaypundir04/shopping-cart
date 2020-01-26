package com.cdk.global.shopping.cart.service;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;

import java.util.List;

public interface SlabManager {

    void addSlab(Customer customer, List<DiscountSlab> slabs);

    List<DiscountSlab> getSlab(Customer customer);

    void updateSlab(Customer customer, DiscountSlab oldSlab, DiscountSlab newSlab);

    void removeCustomer(Customer customer);

    void removeSlab(Customer customer, DiscountSlab slab);
}
