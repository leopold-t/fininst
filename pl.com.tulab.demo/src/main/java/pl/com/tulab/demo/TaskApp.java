package pl.com.tulab.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TaskApp {

	public static final String INSTRUMENT_1 = "INSTRUMENT1";

	public static final String INSTRUMENT_2 = "INSTRUMENT2";

	public static final String INSTRUMENT_3 = "INSTRUMENT3";

	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy", new Locale("en"));

	public static void main( String[] args ) throws FileNotFoundException
    {
        InputDataHandler idh = new InputDataHandler();
        idh.initializeData(new File(".\\src\\main\\resources\\example_input.txt"));
 
        CalculationModule cm = new CalculationModule();
        idh.passDataIntoCalculationModule(cm);

        // For INSTRUMENT1 – mean
        System.out.println("Mean value of " + INSTRUMENT_1 + ": \t\t" + cm.mean(INSTRUMENT_1));
        
        // For INSTRUMENT2 – mean for November 2014
        System.out.println("Mean value of " + INSTRUMENT_2 + " for Nov 2014: " + cm.mean(INSTRUMENT_2, "01-Nov-2014"));

        // For INSTRUMENT3 – max value
        System.out.println("Max value of " + INSTRUMENT_3 + ": \t\t" + cm.max(INSTRUMENT_3));

        // For any other instrument from the input file - sum of the newest (in terms of the date) 3 elements 
        // - for each instrument name individually.
        System.out.println("Sum of the newest 3 elements of");
        System.out.println("\t\t" + INSTRUMENT_1 + ": \t\t" + cm.sumOfNewest(INSTRUMENT_1, 3));
        System.out.println("\t\t" + INSTRUMENT_2 + ": \t\t" + cm.sumOfNewest(INSTRUMENT_2, 3));
        System.out.println("\t\t" + INSTRUMENT_3 + ": \t\t" + cm.sumOfNewest(INSTRUMENT_3, 3));
    }
}
