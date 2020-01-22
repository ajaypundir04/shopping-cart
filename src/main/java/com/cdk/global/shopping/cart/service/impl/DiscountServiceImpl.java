package com.cdk.global.shopping.cart.service.impl;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.service.DiscountService;
import com.cdk.global.shopping.cart.service.SlabManager;

import java.util.Collections;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {
    private SlabManager slabManager;

    public DiscountServiceImpl(SlabManager slabManager) {
        this.slabManager = slabManager;
    }


    @Override
    public double calculateBillAmount(double purchaseAmount, Customer customerType) {
        List<DiscountSlab> slabs = slabManager.getSlab(customerType);

        return calculateDiscount(purchaseAmount, slabs);
    }

    private double calculateDiscount(double purchaseAmount, List<DiscountSlab> slabs) {
        double remaining = purchaseAmount;
        double discount = 0.0d;
        //DiscountRange[] ranges = DiscountRange.values();
        for (int i = 1; i < slabs.size() && remaining > 0; i++) {
            double df = Math.min((slabs.get(i).getLow() - slabs.get(i-1).getLow()), remaining);
            discount += df * ((slabs.get(i).getDiscount()) / 100);
            //this.discountRange = ranges[i];
            remaining -= df;
        }
        return purchaseAmount-discount;
    }
}
