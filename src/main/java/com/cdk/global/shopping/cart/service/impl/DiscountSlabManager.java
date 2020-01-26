package com.cdk.global.shopping.cart.service.impl;

import com.cdk.global.shopping.cart.service.SlabManager;
import com.cdk.global.shopping.cart.exception.SlabManagerException;
import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;

import java.util.*;

public class DiscountSlabManager implements SlabManager {

    private Map<Customer, List<DiscountSlab>> slabMap;

    public DiscountSlabManager() {
        this.slabMap = new HashMap<>();
    }

    public DiscountSlabManager(Map<Customer, List<DiscountSlab>> slabMap) {
        this.slabMap = slabMap;
    }

    @Override
    public void addSlab(Customer customer, List<DiscountSlab> slabs) {
        if (slabMap.containsKey(customer)) {
            slabMap.get(customer).addAll(slabs);
        } else {
            slabMap.put(customer, slabs);
        }
    }

    @Override
    public List<DiscountSlab> getSlab(Customer customer) {
        return slabMap.get(customer);
    }

    @Override
    public void updateSlab(Customer customer, DiscountSlab oldSlab, DiscountSlab newSlab) {
        if (!slabMap.containsKey(customer)) {
            throw new SlabManagerException("Customer Not Exists");
        }
        List<DiscountSlab> slabs = slabMap.get(customer);
        if (Objects.isNull(slabs))
        {
            slabs = new ArrayList<>();
            slabs.add(newSlab);
        }
        else {
            Optional<DiscountSlab> existingSlab = slabs
                    .stream()
                    .filter(s -> s.equals(oldSlab))
                    .findFirst();
            if (!existingSlab.isPresent()) {
                throw new SlabManagerException("No Such Slab");
            } else {
                DiscountSlab discountSlab = existingSlab.get();
                discountSlab.setLow(newSlab.getLow());
                discountSlab.setDiscount(newSlab.getDiscount());
                discountSlab.setHigh(newSlab.getHigh());
            }
        }
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
