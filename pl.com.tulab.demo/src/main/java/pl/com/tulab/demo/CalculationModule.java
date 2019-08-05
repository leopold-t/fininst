package pl.com.tulab.demo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CalculationModule 
{

	private Map<Instrument, BigDecimal> instrumentData = new HashMap<Instrument, BigDecimal>();

	private Map<String, BigDecimal> maxValuesCache = new HashMap<String, BigDecimal>();

	private Map<String, LocalDate> newestDatesCache = new HashMap<String, LocalDate>();

	public BigDecimal max(final String instrumentName) {
		return maxValuesCache.get(instrumentName);
	}

	public LocalDate newest(final String instrumentName) {
		return newestDatesCache.get(instrumentName);
	}

	public BigDecimal mean(final String instrumentName){
		BigDecimal tmpSum = BigDecimal.valueOf(0);
		int recordsNumber = 0;

	for(Instrument instrument : instrumentData.keySet()){
		if(instrumentName.equals(instrument.getName())){
			tmpSum = tmpSum.add(instrumentData.get(instrument));
			recordsNumber++;
		}
	}

		return tmpSum.divide(BigDecimal.valueOf(recordsNumber));
    }

	public BigDecimal mean(final String instrumentName, final String date){
		BigDecimal tmpSum = BigDecimal.valueOf(0);
		LocalDate localDate = LocalDate.parse(date, TaskApp.formatter);
		int recordsNumber = 0;

		for(Instrument instrument : instrumentData.keySet()){
			if(instrumentName.equals(instrument.getName()) && // 
					instrument.getDate().getMonth().equals(localDate.getMonth()) && 
					instrument.getDate().getYear() == localDate.getYear()){
				tmpSum = tmpSum.add(instrumentData.get(instrument));
				recordsNumber++;
			}
		}

		return tmpSum.divide(BigDecimal.valueOf(recordsNumber));
    }

	public BigDecimal sumOfNewest(final String instrumentName, final int numberOfRecords){
		BigDecimal result = BigDecimal.valueOf(0);
		int recordsFound = 0;

		if(numberOfRecords <= this.instrumentData.size()){
			LocalDate startingDate = newestDatesCache.get(instrumentName);
			
			if(startingDate != null){
				Instrument instrumentPattern = new Instrument(instrumentName, startingDate);
				
				while(recordsFound < numberOfRecords){
					if(instrumentData.get(instrumentPattern) != null){
						result = result.add(instrumentData.get(instrumentPattern));
						recordsFound++;
					}
					
					instrumentPattern.setDate(instrumentPattern.getDate().minusDays(1));
				}
			}
		}
		else{
			System.out.println("Number of elements exceeds the size of the dataset.");
			return null;
		}

		return result;
    }

	public BigDecimal getInstrumentPrice(final Instrument instrument){
		return instrumentData.get(instrument);
	}

	public void setInstrumentData(final Instrument instrument, final BigDecimal price) {
		this.instrumentData.put(instrument, price);
	}
	
	public void setInstrumentData(Map<Instrument, BigDecimal> instrumentDataMap) {
		this.instrumentData.putAll(instrumentDataMap);
	}

	public void setMaxValuesCache(final Map<String, BigDecimal> maxValuesCache) {
		this.maxValuesCache.putAll(maxValuesCache);
	}

	public void setNewestDatesCache(Map<String, LocalDate> newestDates) {
		this.newestDatesCache = newestDates;
	}
}
