/**
 * 
 */
package com.sampleproject.amountconverter.service;

/**
 * @author shriramsharma
 *
 */
public interface IConverter {

	public static final double LIMIT = 100000000000.0d;

	public static final String[] TENS = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty", " seventy",
			" eighty", " ninety" };

	public static final String[] UNITS = { "", " one", " two", " three", " four", " five", " six", " seven", " eight",
			" nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen", " seventeen",
			" eighteen", " nineteen" };

	public static final String[] LARGENUMS = { "", " thousand", " million", " billion" };

	public String toWords(double number);

}
