package pl.com.tulab.demo;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

public class AppTest 
{
	/**
     * Newest Date Test
     */
    @Test
    public void newestDateTest()
    {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", new Locale("en"));
    	InputDataHandler idh = new InputDataHandler();

    	idh.setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("06-Jan-2003", TaskApp.formatter)));
    	idh.setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("01-Oct-1994", TaskApp.formatter)));
    	idh.setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("23-Jun-1998", TaskApp.formatter)));

    	CalculationModule cm = new CalculationModule();
    	idh.passDataIntoCalculationModule(cm);

    	assertTrue( cm.newest(TaskApp.INSTRUMENT_1).format(formatter).endsWith("06-Jan-2003"));
    }

	/**
     * Sum Of Newest Date Elements Test
     */
    @Test
    public void sumOfNewestDateElementsTest()
    {
    	Instrument element1 = new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("01-Jun-1998", TaskApp.formatter));
    	BigDecimal price1 = BigDecimal.valueOf(2);

    	Instrument element2 = new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("13-Jul-2002", TaskApp.formatter));
    	BigDecimal price2 = BigDecimal.valueOf(4);

    	Instrument element3 = new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("27-Oct-2014", TaskApp.formatter));
    	BigDecimal price3 = BigDecimal.valueOf(8);

    	InputDataHandler idh = new InputDataHandler();
    	idh.setInstrumentData(element1, price1);
    	idh.setNewestDateCache(element1);

    	idh.setInstrumentData(element2, price2);
    	idh.setNewestDateCache(element2);

    	idh.setInstrumentData(element3, price3);
    	idh.setNewestDateCache(element3);


    	CalculationModule cm = new CalculationModule();
    	idh.passDataIntoCalculationModule(cm);

    	assertTrue( cm.sumOfNewest(TaskApp.INSTRUMENT_1, 1).compareTo(BigDecimal.valueOf(8)) == 0);
    	assertTrue( cm.sumOfNewest(TaskApp.INSTRUMENT_1, 2).compareTo(BigDecimal.valueOf(12)) == 0);
    	assertTrue( cm.sumOfNewest(TaskApp.INSTRUMENT_1, 3).compareTo(BigDecimal.valueOf(14)) == 0);
    	assertTrue( cm.sumOfNewest(TaskApp.INSTRUMENT_1, 100) == null);
    }

	/**
     * Data Pass Test
     */
    @Test
    public void dataPassTest()
    {
    	Instrument record1 = new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("01-Jun-2001", TaskApp.formatter));
    	BigDecimal price1 = BigDecimal.valueOf(32);

    	Instrument record2 = new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("13-Jul-2002", TaskApp.formatter));
    	BigDecimal price2 = BigDecimal.valueOf(64);

    	Instrument record3 = new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("27-Oct-2014", TaskApp.formatter));
    	BigDecimal price3 = BigDecimal.valueOf(128);

    	InputDataHandler idh = new InputDataHandler();
    	idh.setInstrumentData(record1, price1);
    	idh.setInstrumentData(record2, price2);
    	idh.setInstrumentData(record3, price3);

    	CalculationModule cm = new CalculationModule();
    	idh.passDataIntoCalculationModule(cm);

    	assertTrue( cm.getInstrumentPrice(record1).compareTo(BigDecimal.valueOf(32)) == 0);
    	assertTrue( cm.getInstrumentPrice(record2).compareTo(BigDecimal.valueOf(64)) == 0);
    	assertTrue( cm.getInstrumentPrice(record3).compareTo(BigDecimal.valueOf(128)) == 0);
    }	

    /**
     * Max Value Test
     */
    @Test
    public void maxValueTest()
    {
    	InputDataHandler idh = new InputDataHandler();

    	idh.setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("01-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(-1));
    	idh.setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("02-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(0));
    	idh.setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("03-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(1));

    	idh.setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_2,//
    			LocalDate.parse("03-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(128));

    	CalculationModule cm = new CalculationModule();
    	idh.passDataIntoCalculationModule(cm);

    	assertTrue( cm.max(TaskApp.INSTRUMENT_1).compareTo(BigDecimal.valueOf(1)) == 0);
    }

    /**
     * Mean Test
     */
    @Test
    public void meanTest()
    {
    	CalculationModule cm = new CalculationModule();
    	
    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("01-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(1));
    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("02-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(2));
    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("03-Jan-1996", TaskApp.formatter)), BigDecimal.valueOf(3));

    	assertTrue( cm.mean(TaskApp.INSTRUMENT_1).compareTo(BigDecimal.valueOf(2)) == 0);
    }
    
    /**
     * Month Mean Test
     */
    @Test
    public void monthMeanTest()
    {
    	CalculationModule cm = new CalculationModule();

    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_1,//
    			LocalDate.parse("10-Nov-2014", TaskApp.formatter)), BigDecimal.valueOf(5));

    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_2,//
    			LocalDate.parse("10-Nov-2014", TaskApp.formatter)), BigDecimal.valueOf(1));
    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_2,//
    			LocalDate.parse("11-Nov-2014", TaskApp.formatter)), BigDecimal.valueOf(2));
    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_2,//
    			LocalDate.parse("12-Nov-2014", TaskApp.formatter)), BigDecimal.valueOf(3));

    	cm.setInstrumentData(new Instrument(TaskApp.INSTRUMENT_2,//
    			LocalDate.parse("12-Dec-2014", TaskApp.formatter)), BigDecimal.valueOf(2));

    	assertTrue( cm.mean(TaskApp.INSTRUMENT_2, "01-Nov-2014").compareTo(BigDecimal.valueOf(2)) == 0);
    }
}
