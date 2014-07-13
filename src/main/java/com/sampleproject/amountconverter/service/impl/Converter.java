/**
 * 
 */
package com.sampleproject.amountconverter.service.impl;

import org.springframework.stereotype.Service;

import com.sampleproject.amountconverter.service.IConverter;

/**
 * @author shriramsharma
 *
 */
@Service
public class Converter implements IConverter {

	@Override
	public String toWords(double number) {

		if (number > LIMIT)
			return "Unsupported number length. Currently the application only supports numbers less 10000";

		int wholeNumber = (int) number;

		if (wholeNumber == 0)
			return "Zero";

		if (wholeNumber <= 9)
			return UNITS[wholeNumber].trim();

		return null;
	}

}
