package com.cdk.global.shopping.cart;

import com.cdk.global.shopping.cart.exception.ShoppingCartException;
import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.DiscountSlab;
import com.cdk.global.shopping.cart.model.Invoice;
import com.cdk.global.shopping.cart.output.ConsoleLogger;
import com.cdk.global.shopping.cart.output.Logger;
import com.cdk.global.shopping.cart.parser.InputParser;
import com.cdk.global.shopping.cart.parser.InputParserImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountServiceImpl;
import com.cdk.global.shopping.cart.service.impl.DiscountSlabManager;
import com.cdk.global.shopping.cart.service.impl.ShoppingCart;
import com.cdk.global.shopping.cart.util.InputFileReader;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Ajay Singh Pundir
 * This is the entry point of the application
 */
public class ApplicationLauncher {

    private ShoppingCart cart;
    private InputParser parser;
    private Logger logger;

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.err.println("File Paths not provided.");
        } else {
            new ApplicationLauncher().launchApplication(args[0], args[1]);
        }
    }

    private void launchApplication(String slabDetailPath, String invoiceDetailPath) {
        try {
            parser = loadParser();
            logger = loadConsoleLogger();
            Map<Customer, List<DiscountSlab>> slabMap = parseSlabs(slabDetailPath);
            List<Invoice> invoices = parseInvoice(invoiceDetailPath);
            if (Objects.nonNull(invoices)) {
                logger.printHeader();
            }
            invoices.stream()
                    .map(invoice -> {
                        cart = new ShoppingCart
                                (invoice, new DiscountServiceImpl(new DiscountSlabManager(slabMap)));
                        return cart.getInvoice();
                    }).collect(Collectors.toList())
                    .stream().forEach(i -> logger.printInvoice(i));
        } catch (Exception e) {
            throw new ShoppingCartException(e);
        }
    }

    private InputParser loadParser() {
        return new InputParserImpl();
    }

    private Logger loadConsoleLogger() {
        return new ConsoleLogger();
    }

    private Map<Customer, List<DiscountSlab>> parseSlabs(String filePath) throws FileNotFoundException {
        List<String> slabList = InputFileReader.parseFile(filePath);
        return parser.slabParser(slabList);

    }

    private List<Invoice> parseInvoice(String filePath) throws FileNotFoundException {
        List<String> invoiceList = InputFileReader.parseFile(filePath);
        return parser.invoiceParser(invoiceList);

    }

}

