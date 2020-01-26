package com.cdk.global.shopping.cart.service;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.service.impl.DiscountServiceImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountSlabManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class DiscountServiceTest {

    private DiscountService discountService;
    private DiscountSlabManager slabManager;

    @Before
    public void setUp() {
        slabManager = Mockito.mock(DiscountSlabManager.class);
        discountService = new DiscountServiceImpl(slabManager);
        List<DiscountSlab> discountSlabs = new ArrayList<DiscountSlab>() {
            {
                add(new DiscountSlab(0, 5000, 0));
                add(new DiscountSlab(5000, 10000, 10));
                add(new DiscountSlab(10000, 2147483647, 20));
            }
        };
        Mockito.when(slabManager.getSlab(ArgumentMatchers.any(Customer.class))).thenReturn(
                discountSlabs
        );
    }

    @Test
    public void calculateBillTest() {

        double billAmount = discountService.
                calculateBillAmount(15000, new Customer("regular"));
        Assert.assertEquals(13500, billAmount, 0);
    }

}
