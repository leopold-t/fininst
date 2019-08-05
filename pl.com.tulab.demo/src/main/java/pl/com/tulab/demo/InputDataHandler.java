package pl.com.tulab.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class InputDataHandler {

	private Map<Instrument, BigDecimal> instrumentData = new HashMap<Instrument, BigDecimal>();

	private Map<String, BigDecimal> maxValuesCache = new HashMap<String, BigDecimal>();

	private Map<String, LocalDate> newestDatesCache = new HashMap<String, LocalDate>();

	public void initializeData(final File file) throws FileNotFoundException{
		System.out.println("File: " + file.getAbsolutePath());

		if(!file.exists()){
			throw new FileNotFoundException("Invalid file path");
		} else{
			this.process(file);
		}

		// TODO: mock data to be finally removed
		initMockData();
	}

	private void process(final File file){
		String date = "";

		/* Parse a file line-by-line */
		// while not EOF
		if(isDateValid(date)){
			// load data into instrumentData
			// determine and set maximum value while loading data
			// setInstrumentMaxValue(...);
			// setInstrumentLatestDate(...);
		}
	}

	/* Validate date values. Discard entries with non-business date */
	private boolean isDateValid(final String date){
		boolean result = false;

		// compare dates against business calendar

		return result;
	}

	protected void setInstrumentMaxValue(final Instrument instrument, final BigDecimal price){
		BigDecimal currentMaxValue = maxValuesCache.get(instrument.getName());

		if(currentMaxValue == null || currentMaxValue.compareTo(price) < 1){
			maxValuesCache.put(instrument.getName(), price);
		}
	}


	protected void setNewestDateCache(final Instrument instrument){
		LocalDate currentDate = newestDatesCache.get(instrument.getName());

		if(currentDate == null || currentDate.getYear() < instrument.getDate().getYear()){
			newestDatesCache.put(instrument.getName(), instrument.getDate());
		}
		else if(currentDate.getYear() == instrument.getDate().getYear() && //
				currentDate.getDayOfYear() < instrument.getDate().getDayOfYear()){
				newestDatesCache.put(instrument.getName(), instrument.getDate());
			}
	}

	protected void setInstrumentData(final Instrument instrument, final BigDecimal price) {
		this.instrumentData.put(instrument, price);
	}

	/* Pass all of the time series to the "calculation module" */
	public void passDataIntoCalculationModule(final CalculationModule cm){
		for(Instrument instrument : instrumentData.keySet()){
			cm.setInstrumentData(instrument, instrumentData.get(instrument));
		}

		cm.setMaxValuesCache(maxValuesCache);
		cm.setNewestDatesCache(newestDatesCache);
	}

	public BigDecimal getInstrumentPrice(final Instrument instrument){
		return instrumentData.get(instrument);
	}

	/* TODO: to be finally removed */
	private void initMockData(){
		// INSTRUMENT1 - sample data
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("01-Jan-1996", TaskApp.formatter)),//
				BigDecimal.valueOf(2.4655));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("01-Jan-1996", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("22-Feb-1996", TaskApp.formatter)),//
				BigDecimal.valueOf(2.54));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("22-Feb-1996", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("07-Mar-1996", TaskApp.formatter)),//
				BigDecimal.valueOf(2.577));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_1, LocalDate.parse("07-Mar-1996", TaskApp.formatter)));
		
		// INSTRUMENT2 - sample data
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("18-May-2001", TaskApp.formatter)),//
				BigDecimal.valueOf(9.899876508));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("18-May-2001", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("04-Jun-2001", TaskApp.formatter)),//
				BigDecimal.valueOf(9.968310206));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("04-Jun-2001", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("18-Jun-2001", TaskApp.formatter)),//
				BigDecimal.valueOf(9.914058762));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("18-Jun-2001", TaskApp.formatter)));
		// November 2014
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("03-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.248039722));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("03-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("04-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.246394589));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("04-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("05-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.265365342));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("05-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("06-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.264890209));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("06-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("07-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.259554731));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("07-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("10-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.257005669));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("10-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("11-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.25693874));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("11-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("12-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.244623133));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("12-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("13-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.24416476));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("13-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("14-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.249227145));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("14-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("17-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.248171537));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("17-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("18-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.246131805));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("18-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("19-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.259958242));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("19-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("20-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.259554731));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("20-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("21-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.249623498));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("21-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("24-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.259218687));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("24-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("25-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.273020528));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("25-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("26-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.273984503));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("26-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("27-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.269659044));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("27-Nov-2014", TaskApp.formatter)));
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("28-Nov-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(9.275640724));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_2, LocalDate.parse("28-Nov-2014", TaskApp.formatter)));
		
		// INSTRUMENT3 - sample data
		// Line 1907: INSTRUMENT3,31-May-2012,78.5325
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("31-May-2012", TaskApp.formatter)),//
				BigDecimal.valueOf(78.5325));
		setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("31-May-2012", TaskApp.formatter)),//
				BigDecimal.valueOf(78.5325));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("31-May-2012", TaskApp.formatter)));

		// Line 1927: INSTRUMENT3,28-Jun-2012,79.2865
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("28-Jun-2012", TaskApp.formatter)),//
				BigDecimal.valueOf(79.2865));
		setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("28-Jun-2012", TaskApp.formatter)),//
				BigDecimal.valueOf(79.2865));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("28-Jun-2012", TaskApp.formatter)));

		// Line 1957: INSTRUMENT3,09-Aug-2012,78.765
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("09-Aug-2012", TaskApp.formatter)),//
				BigDecimal.valueOf(78.765));
		setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("09-Aug-2012", TaskApp.formatter)),//
				BigDecimal.valueOf(78.765));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("09-Aug-2012", TaskApp.formatter)));

		// Line 14519: INSTRUMENT3,16-Oct-2013,98.7625
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("16-Oct-2013", TaskApp.formatter)),//
				BigDecimal.valueOf(98.7625));
		setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("16-Oct-2013", TaskApp.formatter)),//
				BigDecimal.valueOf(98.7625));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("16-Oct-2013", TaskApp.formatter)));

		// Line 14787: INSTRUMENT3,27-Oct-2014,107.785
		instrumentData.put(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("27-Oct-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(107.785));
		setInstrumentMaxValue(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("27-Oct-2014", TaskApp.formatter)),//
				BigDecimal.valueOf(107.785));
		setNewestDateCache(new Instrument(TaskApp.INSTRUMENT_3, LocalDate.parse("27-Oct-2014", TaskApp.formatter)));
	}
}
