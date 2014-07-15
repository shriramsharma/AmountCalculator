/**
 * 
 */
package com.sampleproject.amountconverter.service.impl;

import java.text.DecimalFormat;

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
			return "WOW!! thats a lot of money. Unfortunately the conversion cannot handle such large amount.";

		long wholeNumber = (long) number;
		double fraction = number - wholeNumber;

		if (wholeNumber > 999)
			sb.append(convertLargeNums(wholeNumber).trim());
		else
			sb.append(convert(wholeNumber));

		if (fraction > 0.0d) {
			sb.append(" and ");
			sb.append(getFractionInString(fraction));
			sb.append("/");
			sb.append("100");
		}

		sb.append(" dollars");
		return sb.toString();
	}

	private String convertLargeNums(long wholeNumber) {
		StringBuffer sb = new StringBuffer();
		int i = 0;
		while (wholeNumber > 0) {
			if (wholeNumber % 1000 > 0) {
				sb.insert(0, LARGENUMS[i]);
				sb.insert(0, convert(wholeNumber % 1000));
				sb.insert(0, " ");
			}
			wholeNumber /= 1000;
			i++;
		}
		return sb.toString();
	}

	private String convertTripleDigit(long wholeNumber) {
		StringBuffer sb = new StringBuffer();
		sb.append(UNITS[(int) (wholeNumber / 100)]);
		sb.append(" hundred");
		long num = wholeNumber % 100;
		sb.append(convertDoubleDigit(num));
		return sb.toString();
	}

	private String convert(long wholeNumber) {
		StringBuffer sb = new StringBuffer();
		if (wholeNumber == 0)
			sb.append("Zero");
		else if (wholeNumber <= 9)
			sb.append(convertSingleDigit(wholeNumber).trim());
		else if (wholeNumber <= 99) {
			sb.append(convertDoubleDigit(wholeNumber).trim());
		} else if (wholeNumber <= 999) {
			sb.append(convertTripleDigit(wholeNumber).trim());
		}
		return sb.toString();
	}

	private String convertSingleDigit(long num) {
		return UNITS[(int) num];
	}

	private String convertDoubleDigit(long num) {
		StringBuffer sb = new StringBuffer();
		if (num < 20)
			sb.append(UNITS[(int) num]);
		else {
			sb.append(TENS[(int) (num / 10)]);
			sb.append(convertSingleDigit(num % 10));
		}
		return sb.toString();
	}

	private String getFractionInString(double fraction) {
		DecimalFormat nf = new DecimalFormat("0.00#");
		nf.setMaximumFractionDigits(2);
		String fracStrVal = nf.format(fraction);
		int index = fracStrVal.indexOf(".");
		if (index != -1) {
			return fracStrVal.substring(index + 1, fracStrVal.length());
		}
		return "0";
	}
}
