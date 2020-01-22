package com.cdk.global.shopping.cart;

import com.cdk.global.shopping.cart.constants.ApplicationConstants;
import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.model.Invoice;
import com.cdk.global.shopping.cart.parser.InputParser;
import com.cdk.global.shopping.cart.parser.InputParserImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountServiceImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountSlabManager;
import com.cdk.global.shopping.cart.service.impl.ShoppingCart;
import com.cdk.global.shopping.cart.util.InputFileReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApplicationLauncher {

    private static ShoppingCart cart;
     private static InputParser parser;
    public static void main(String[] args) {
        try {
            List<String> slabList = InputFileReader.parseFile("src/main/resources/slab_details.txt", ApplicationConstants.TXT_EXTENSION);
            List<String> invoiceList = InputFileReader.parseFile("src/main/resources/invoice_details.txt", ApplicationConstants.TXT_EXTENSION);
            parser = new InputParserImpl();
            Map<Customer, List<DiscountSlab>> slabMap= parser.slabParser(slabList);
            List<Invoice> invoices = parser.invoiceParser(invoiceList);
            invoices.stream()
                    .map(invoice -> {
                        cart = new ShoppingCart
                                (invoice, new DiscountServiceImpl(new DiscountSlabManager(slabMap)));
                        return  cart.getInvoice();
                    }).collect(Collectors.toList())
            .stream().forEach(System.out::println);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
