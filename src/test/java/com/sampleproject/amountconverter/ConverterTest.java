/**
 * 
 */
package com.sampleproject.amountconverter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sampleproject.amountconverter.service.IConverter;

/**
 * @author shriramsharma
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-servlet-context.xml" })
public class ConverterTest {

	@Autowired
	private IConverter converter;

	/**
	 * Test method for
	 * {@link com.sampleproject.amountconverter.service.impl.Converter#toWords(java.lang.Integer)}
	 * .
	 */
	@Test
	public void testToWordsSingleDigit() {
		String singleDigit = converter.toWords(9);
		assertEquals("nine dollars", singleDigit);
	}

	@Test
	public void testToWordsSingleDigitWithFraction() {
		assertEquals("nine and 04/100 dollars", converter.toWords(9.04d));
		assertEquals("one and 99/100 dollars", converter.toWords(1.99d));
		assertEquals("three and 43/100 dollars", converter.toWords(3.43d));
	}

	@Test
	public void testToWordsDoubleDigit() {
		assertEquals("ten dollars", converter.toWords(10.0d));
		assertEquals("nineteen dollars", converter.toWords(19.0d));
		assertEquals("twenty dollars", converter.toWords(20.0d));
		assertEquals("ninety one dollars", converter.toWords(91.0d));
		assertEquals("eighty five dollars", converter.toWords(85.0d));
	}

	@Test
	public void testToWordsDoubleDigitWithFractions() {
		assertEquals("eleven and 41/100 dollars", converter.toWords(11.41d));
		assertEquals("thirty one and 01/100 dollars", converter.toWords(31.01d));
		assertEquals("fifty four and 99/100 dollars", converter.toWords(54.99d));
	}

	@Test
	public void testToWordsTripleDigit() {
		assertEquals("one hundred eleven dollars", converter.toWords(111.0d));
		assertEquals("five hundred thirty one dollars", converter.toWords(531.0d));
		assertEquals("nine hundred fifty four dollars", converter.toWords(954.0d));
	}

	@Test
	public void testToWordsTripleDigitWithFractions() {
		assertEquals("two hundred seventy six and 21/100 dollars", converter.toWords(276.21d));
		assertEquals("six hundred thirty eight and 09/100 dollars", converter.toWords(638.09d));
		assertEquals("four hundred forty four and 49/100 dollars", converter.toWords(444.49d));
		assertEquals("seven hundred four and 30/100 dollars", converter.toWords(704.30d));
	}

	@Test
	public void testToWordsLargeDigit() {
		assertEquals("nine thousand two hundred seventy six dollars", converter.toWords(9276.0d));
		assertEquals("eleven thousand six hundred thirty eight dollars", converter.toWords(11638.0d));
		assertEquals("one hundred forty four thousand four hundred forty eight dollars", converter.toWords(144448.0d));
		assertEquals("one hundred thousand dollars", converter.toWords(100000.0d));
		assertEquals("one million one hundred forty four thousand four hundred forty eight dollars",
				converter.toWords(1144448.0d));
		assertEquals("twenty million one hundred forty four thousand four hundred forty eight dollars",
				converter.toWords(20144448.0d));
		assertEquals("one hundred seventy million one hundred forty four thousand four hundred forty eight dollars",
				converter.toWords(170144448.0d));
	}

	@Test
	public void testToWordsLargeDigitWithFractions() {
		assertEquals("nine thousand two hundred seventy six and 55/100 dollars", converter.toWords(9276.55d));
		assertEquals("eleven thousand six hundred thirty eight and 83/100 dollars", converter.toWords(11638.83d));
		assertEquals("one hundred forty four thousand four hundred forty eight and 22/100 dollars",
				converter.toWords(144448.22d));
		assertEquals(
				"one hundred seventy million one hundred forty four thousand four hundred forty eight and 93/100 dollars",
				converter.toWords(170144448.93d));
		assertEquals("nine billion and 93/100 dollars", converter.toWords(9000000000.93d));
		assertEquals("ninety billion and 93/100 dollars", converter.toWords(90000000000.93d));
		assertEquals("ninety nine billion nine hundred million and 93/100 dollars", converter.toWords(99900000000.93d));
	}

	@Test
	public void testToWordsExceedLimit() {
		assertEquals("WOW!! thats a lot of money. Unfortunately the conversion cannot handle such large amount.",
				converter.toWords(1000000000000.0d));
	}

}
