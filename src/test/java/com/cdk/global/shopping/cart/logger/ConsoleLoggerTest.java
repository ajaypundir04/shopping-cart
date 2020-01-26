package com.cdk.global.shopping.cart.logger;


import com.cdk.global.shopping.cart.model.Customer;
import com.cdk.global.shopping.cart.model.Invoice;
import com.cdk.global.shopping.cart.output.ConsoleLogger;
import com.cdk.global.shopping.cart.output.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

public class ConsoleLoggerTest {

    private Logger logger;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp(){
        logger = new ConsoleLogger();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void printHeaderTest(){
        logger.printHeader();
        Assert.assertEquals(String.join("",
                String.format("%-20s", "Customer "),
                String.format("%-20s", "Purchased Amount"),
                String.format("%-20s", "Bill Amount"),"\n"),outContent.toString());
    }

    @Test
    public void printInvoiceTest(){
        logger.printInvoice(new Invoice( new Customer("premium"), 45.0d));
        Assert.assertEquals(String.join("",
                String.format("%-20s", "premium" ),
                String.format("%-20d", 45),
                String.format("%-20d", 0),"\n"),outContent.toString());
    }

}
