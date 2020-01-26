package com.cdk.global.shopping.cart.service.impl;

import com.cdk.global.shopping.cart.exception.SlabManagerException;
import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.service.SlabManager;

import java.util.*;

/**
 * @author Ajay Singh Pundir
 * It will handle all the slab reated information
 */
public class DiscountSlabManager implements SlabManager {

    private Map<Customer, List<DiscountSlab>> slabMap;

    public DiscountSlabManager() {
        this.slabMap = new HashMap<>();
    }

    public DiscountSlabManager(Map<Customer, List<DiscountSlab>> slabMap) {
        this.slabMap = slabMap;
    }

    /**
     * It will used to add the new slab to the customer.
     *
     * @param customer @{@link Customer} type of the customer
     * @param slabs    @{@link List<DiscountSlab>} new slabs for the customer
     */
    @Override
    public void addSlab(Customer customer, List<DiscountSlab> slabs) {
        if (slabMap.containsKey(customer)) {
            slabMap.get(customer).addAll(slabs);
        } else {
            slabMap.put(customer, slabs);
        }
    }

    /**
     * It will be used to get the slab on the basis of customer type
     *
     * @param customer @{@link Customer} type of customer
     * @return @{@link List<DiscountSlab>}  slabs for the customer
     */
    @Override
    public List<DiscountSlab> getSlab(Customer customer) {
        return slabMap.get(customer);
    }

    /**
     * It will be used to update the existing slab for the customer
     *
     * @param customer @{@link Customer} type of customer
     * @param oldSlab  @{@link DiscountSlab} previous slab
     * @param newSlab  @{@link DiscountSlab} new slab
     */
    @Override
    public void updateSlab(Customer customer, DiscountSlab oldSlab, DiscountSlab newSlab) {
        if (!slabMap.containsKey(customer)) {
            throw new SlabManagerException("Customer Not Exists");
        }
        List<DiscountSlab> slabs = slabMap.get(customer);
        if (Objects.isNull(slabs)) {
            slabs = new ArrayList<>();
            slabs.add(newSlab);
        } else {
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

    /**
     * It will be used to remove the customer form the slab.
     *
     * @param customer @{@link Customer} to be removed
     */
    @Override
    public void removeCustomer(Customer customer) {
        slabMap.remove(customer);
    }

    /**
     * It is used to remove the slab for the customer.
     *
     * @param customer @{@link Customer} type of customer
     * @param slab     @{@link DiscountSlab} to be removed
     */
    @Override
    public void removeSlab(Customer customer, DiscountSlab slab) {
        slabMap.get(customer).remove(slab);
    }

}
