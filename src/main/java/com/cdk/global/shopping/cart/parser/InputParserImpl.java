package com.cdk.global.shopping.cart.parser;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.model.Invoice;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * Used for parsing the input strings.
 */
public class InputParserImpl implements InputParser {

    /**
     *
     * @param inputList
     * @return
     */
    @Override
    public Map<Customer, List<DiscountSlab>> slabParser(List<String> inputList) {
        return inputList.stream()
                .map(line -> line.split(" "))
                .collect(
                        Collectors.toMap(
                                this::createCustomer,
                                array -> new ArrayList<>(Collections.singletonList(createDiscountSlab(array))),
                                (l, r) -> {
                                    l.addAll(r);
                                    return l;
                                }
                ));
    }

    @Override
    public List<Invoice> invoiceParser(List<String> inputList) {
        return inputList.stream().map(
                line->{
                    String [] input = line.split(" ");
                    return new Invoice(
                            new Customer(input[0]),
                            Double.parseDouble(input[1])
                    );

                }
        ).collect(Collectors.toList());

    }

    private Customer createCustomer(String []array) {
        return  new Customer(array[0]);
    }

    private DiscountSlab createDiscountSlab(String []array) {
        return new DiscountSlab(Float.parseFloat(array[1]), Float.parseFloat(array[2]), Float.parseFloat(array[3]));
    }
}
