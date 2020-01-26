package com.cdk.global.shopping.cart.integration;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.model.Invoice;
import com.cdk.global.shopping.cart.parser.InputParser;
import com.cdk.global.shopping.cart.parser.InputParserImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountServiceImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountSlabManager;
import com.cdk.global.shopping.cart.service.impl.ShoppingCart;
import com.cdk.global.shopping.cart.util.InputFileReader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ShoppingCartTest {
    private ShoppingCart cart;
    private Map<Customer, List<DiscountSlab>> slabMap;
    private List<Invoice> invoices;

    @Before
    public void setUp() throws Exception {
        InputParser parser = new InputParserImpl();
        slabMap = parser.slabParser(InputFileReader.parseFile("src/test/resources/slab_details.txt"));
        invoices = parser.invoiceParser(InputFileReader.parseFile("src/test/resources/invoice_details.txt"));

    }

    @Test
    public void getInvoiceTest() {

        invoices.stream()
                .map(invoice -> {
                    cart = new ShoppingCart
                            (invoice, new DiscountServiceImpl(new DiscountSlabManager(slabMap)));
                    return cart.getInvoice();
                }).collect(Collectors.toList())
                .stream();

        Assert.assertThat(invoices,
                Matchers.contains(
                HasPropertyWithValue.
                        hasProperty("billAmount", CoreMatchers.is(13500.0)),
                HasPropertyWithValue.
                        hasProperty("billAmount", CoreMatchers.is(15800.0))
                        )
        );
    }
}
