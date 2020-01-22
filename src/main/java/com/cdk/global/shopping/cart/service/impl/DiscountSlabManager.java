package com.cdk.global.shopping.cart.service.impl;

import com.cdk.global.shopping.cart.exception.SlabManagerException;
import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.service.SlabManager;

import java.util.*;

public class DiscountSlabManager implements SlabManager {

    private Map<Customer, List<DiscountSlab>> slabMap;

    public DiscountSlabManager() {
        this.slabMap = new HashMap<>();
    }

    public DiscountSlabManager( Map<Customer, List<DiscountSlab>> slabMap) {
        this.slabMap = slabMap;
    }

    @Override
    public void addSlab(Customer customer, List<DiscountSlab> slabs) {
        slabMap.put(customer, slabs);
    }

    @Override
    public List<DiscountSlab> getSlab(Customer customer) {
        return slabMap.get(customer);
    }

    @Override
    public void updateSlab(Customer customer, DiscountSlab slab) {
        if(!slabMap.containsKey(customer))
        {
           throw  new SlabManagerException("Customer Not Exists");
        }
        List<DiscountSlab> slabs = slabMap.get(customer);
        if(Objects.isNull(slabs))
            slabs = new ArrayList<>();
        slabs.add(slab);
    }

    @Override
    public void removeCustomer(Customer customer) {
        slabMap.remove(customer);
    }

    @Override
    public void removeSlab(Customer customer, DiscountSlab slab) {
            slabMap.get(customer).remove(slab);
    }

}
