package com.cdk.global.shopping.cart.parser;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.util.InputFileReader;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.hamcrest.beans.HasPropertyWithValue;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class InputParserTest {

    private InputParser parser;

    @Before
    public void setUp() {
        parser = new InputParserImpl();
    }

    @Test
    public void parseInputTest() throws IOException {
        List<String> inputList = InputFileReader
                .parseFile("src/test/resources/slab_details.txt");
        Map<Customer, List<DiscountSlab>> slabs = parser.slabParser(inputList);

        Assert.assertThat(slabs.get(new Customer("regular")),
                Matchers.contains(
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(0.0)),
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(10.0)),
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(20.0))

                )

        );
        Assert.assertThat(slabs.get(new Customer("premium")),
                Matchers.contains(
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(10.0)),
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(15.0)),
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(20.0)),
                        HasPropertyWithValue.
                                hasProperty("discount", CoreMatchers.is(30.0))

                )

        );
    }

}
