package com.cdk.global.shopping.cart.util;



import com.cdk.global.shopping.cart.constants.ApplicationConstants;
import com.cdk.global.shopping.cart.exception.ShoppingCartException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Used to handle the file level operation.
 *
 * @author Ajay Singh Pundir
 */
public class InputFileReader {

    private InputFileReader() {
    }

    /**
     * Read the file and generates the list of string as output.
     *
     * @param filePath location of the file.
     * @param type     extension type of file
     * @return all the strings passed in the file
     * @throws FileNotFoundException if file path is invalid
     */
    public static List<String> parseFile(String filePath, String type) throws FileNotFoundException {
        List<String> stringList = null;
        switch (type) {
            case ApplicationConstants.TXT_EXTENSION:
                stringList = readTextFile(filePath);
                break;

            default:
                throw new ShoppingCartException("Wrong Extension");
        }
        return stringList;
    }

    private static List<String> readTextFile(String filePath) throws FileNotFoundException {
        List<String> stringList = new ArrayList<>();
        File file = new File(filePath);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            stringList.add(sc.nextLine());
        return stringList;
    }
}
