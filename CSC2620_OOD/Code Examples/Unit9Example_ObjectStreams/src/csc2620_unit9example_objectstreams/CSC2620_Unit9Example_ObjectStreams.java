/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package csc2620_unit9example_objectstreams;

import java.io.*;
import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author
 */
public class CSC2620_Unit9Example_ObjectStreams {

    // The file is not human readable
    static final String dataFile = "invoicedata.txt";

    static final BigDecimal[] prices = {
        new BigDecimal("19.99"),
        new BigDecimal("9.99"),
        new BigDecimal("15.99"),
        new BigDecimal("3.99"),
        new BigDecimal("4.99")};
    static final int[] units = {12, 8, 13, 29, 50};
    static final String[] descs = {"Java T-shirt",
        "Java Mug",
        "Duke Juggling Dolls",
        "Java Pin",
        "Java Key Chain"};

    public static void main(String[] args)
            throws IOException, ClassNotFoundException {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));

            // Write out a Calendar object
            out.writeObject(Calendar.getInstance());
            for (int i = 0; i < prices.length; i++) {
                // Write out the BigDecimal object
                out.writeObject(prices[i]);
                // Write out an 'int'
                out.writeInt(units[i]);
                // Write out a String object
                out.writeUTF(descs[i]);
            }
        } finally {
            out.close();
        }

        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)));

            Calendar date = null;
            BigDecimal price;
            int unit;
            String desc;
            BigDecimal total = new BigDecimal(0);

            // Read in the Calendar object
            date = (Calendar) in.readObject();

            System.out.format("On %tA, %<tB %<te, %<tY:%n", date);

            try {
                while (true) {
                    // Read in the BigDecimal object
                    price = (BigDecimal) in.readObject();
                    // Now read in an 'int'
                    unit = in.readInt();
                    // Now read in a String object
                    desc = in.readUTF();
                    System.out.format("You ordered %d units of %s at $%.2f%n",
                            unit, desc, price);
                    total = total.add(price.multiply(new BigDecimal(unit)));
                }
            } catch (EOFException e) {
            }
            System.out.format("For a TOTAL of: $%.2f%n", total);
        } finally {
            in.close();
        }
    }
}
