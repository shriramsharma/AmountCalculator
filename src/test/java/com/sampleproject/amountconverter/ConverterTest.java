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

}
