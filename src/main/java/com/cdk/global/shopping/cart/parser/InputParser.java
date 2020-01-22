package com.cdk.global.shopping.cart.parser;

import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.model.Invoice;

import java.util.List;
import java.util.Map;

/**
 * @author Ajay Singh Pundir
 * It will parse the input file
 */
public interface InputParser {

    Map<Customer, List<DiscountSlab>> slabParser(List<String> inputList);
    List<Invoice> invoiceParser(List<String> inputList);

}
