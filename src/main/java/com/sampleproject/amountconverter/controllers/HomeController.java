package com.sampleproject.amountconverter.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sampleproject.amountconverter.service.IConverter;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	@Autowired
	private IConverter converter;
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/convert/{num:.+}", method = RequestMethod.GET)
	@ResponseBody
	public String convert(@PathVariable String num) {
		String result = "";
		if (num == null)
			return "Invalid param";
		try {
			result = converter.toWords(Double.valueOf(num));
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			result = "Error occured.";
		}
		return result;
	}

}
