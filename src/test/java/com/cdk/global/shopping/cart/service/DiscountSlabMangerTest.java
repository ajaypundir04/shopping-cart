package com.cdk.global.shopping.cart.service;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.service.impl.DiscountSlabManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountSlabMangerTest {
    private DiscountSlabManager slabManager;

    @Before
    public void setUp() {
        Map<Customer, List<DiscountSlab>> slabMap = new HashMap<Customer, List<DiscountSlab>>() {
            {
                put(new Customer("regular"),
                        new ArrayList<DiscountSlab>() {
                            {
                                add(new DiscountSlab(0, 5000, 0));
                                add(new DiscountSlab(5000, 10000, 10));
                            }
                        });
            }
        };
        slabManager = new DiscountSlabManager(slabMap);
    }

    @Test
    public void getSlabTest() {

        List<DiscountSlab> slabs = slabManager.getSlab(new Customer("regular"));
        Assert.assertEquals(new ArrayList<DiscountSlab>() {
            {
                add(new DiscountSlab(0, 5000, 0));
                add(new DiscountSlab(5000, 10000, 10));
            }
        }, slabs);
    }

    @Test
    public void getAddSlabTest() {

        slabManager.addSlab(new Customer("regular"),
                new ArrayList<DiscountSlab>() {
                    {
                        add(new DiscountSlab(10000, 2147483647, 20));
                    }
                }
        );
        List<DiscountSlab> slabs = slabManager.getSlab(new Customer("regular"));
        Assert.assertEquals(new ArrayList<DiscountSlab>() {
            {
                add(new DiscountSlab(0, 5000, 0));
                add(new DiscountSlab(5000, 10000, 10));
                add(new DiscountSlab(10000, 2147483647, 20));
            }
        }, slabs);
    }

    @Test
    public void updateSlabTest() {

        slabManager.updateSlab(
                new Customer("regular"),
                new DiscountSlab(5000, 10000, 10),
                new DiscountSlab(5000, 10000, 5)
        );
        List<DiscountSlab> slabs = slabManager.getSlab(new Customer("regular"));
        Assert.assertEquals(new ArrayList<DiscountSlab>() {
            {
                add(new DiscountSlab(0, 5000, 0));
                add(new DiscountSlab(5000, 10000, 5));
            }
        }, slabs);
    }

    @Test
    public void removeSlabTest() {

        slabManager.removeSlab(
                new Customer("regular"),
                new DiscountSlab(5000, 10000, 10)
        );
        List<DiscountSlab> slabs = slabManager.getSlab(new Customer("regular"));
        Assert.assertEquals(new ArrayList<DiscountSlab>() {
            {
                add(new DiscountSlab(0, 5000, 0));
            }
        }, slabs);
    }

    @Test
    public void removeCustomerTest() {

        slabManager.removeCustomer(
                new Customer("regular")
        );
        List<DiscountSlab> slabs = slabManager.getSlab(new Customer("regular"));
        Assert.assertNull(slabs);
    }
}
