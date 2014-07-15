/**
 * 
 */
package com.sampleproject.amountconverter.service.impl;

import java.text.NumberFormat;

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

		StringBuffer sb = new StringBuffer();

		if (number > LIMIT)
			return "Unsupported number length. Currently the application only supports numbers less 10000";

		int wholeNumber = (int) number;
		double fraction = number - wholeNumber;

		if (wholeNumber == 0)
			sb.append("Zero");
		else if (wholeNumber <= 9)
			sb.append(convertSingleDigit(wholeNumber).trim());
		else if (wholeNumber <= 99) {
			sb.append(convertDoubleDigit(wholeNumber).trim());
		} else if (wholeNumber <= 999) {
			sb.append(convertTripleDigit(wholeNumber).trim());
		}

		if (fraction > 0.0d) {
			sb.append(" and ");
			sb.append(getFractionInString(fraction));
			sb.append("/");
			sb.append("100");
		}

		sb.append(" dollars");
		return sb.toString();
	}

	private String convertTripleDigit(int wholeNumber) {
		StringBuffer sb = new StringBuffer();
		sb.append(UNITS[wholeNumber / 100]);
		sb.append(" hundred");
		int num = wholeNumber % 100;
		sb.append(convertDoubleDigit(num));
		return sb.toString();
	}

	private String convertSingleDigit(int num) {
		return UNITS[num];
	}

	private String convertDoubleDigit(int num) {
		StringBuffer sb = new StringBuffer();
		if (num < 20)
			sb.append(UNITS[num]);
		else {
			sb.append(TENS[num / 10]);
			sb.append(convertSingleDigit(num % 10));
		}
		return sb.toString();
	}

	private String getFractionInString(double fraction) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setMaximumFractionDigits(2);
		String fracStrVal = nf.format(fraction);
		int index = fracStrVal.indexOf(".");
		if (index != -1) {
			return fracStrVal.substring(index + 1, fracStrVal.length());
		}
		return "0";
	}
}
