package com.dynatrace.carcostcalculation;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;

import com.dynatrace.carcostcalculation.service.CarCostCalculation;

import static org.junit.Assert.assertEquals;


public class CarCostCalculationTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(CarCostCalculation.class);
	}

	/**
	 * Test to see that the message "Got it!" is sent in the response.
	 * @throws Exception 
	 */
	@Test
	public void testGetCarCostCoupe() throws Exception {
		final String responseMsg = target().path("coupe/v8,automatic,navigation,sunroof,premiumaudio").request().get(String.class);
		//CarCostCalculation car = new CarCostCalculation();
		//final String  responseMsg =  car.getIt("coupe", "v8,automatic,navigation,sunroof,premiumaudio",1l);
		assertEquals("The car value of coupe is 24500.0", responseMsg);
	}

	@Test
	public void testGetCarCostTruck() throws Exception {
		final String responseMsg = target().path("truck/v8,automatic,navigation,sunroof,premiumaudio,towpackage").request().get(String.class);
		//CarCostCalculation car = new CarCostCalculation();
		// final String  responseMsg =  car.getIt("truck", "v8,automatic,navigation,sunroof,premiumaudio,towpackage",1l);
		assertEquals("The car value of truck is 38500.0", responseMsg);
	}

	@Test
	public void testGetCarCostSuv() throws Exception {
		final String responseMsg = target().path("suv/v8,automatic,navigation,sunroof,premiumaudio,towpackage").request().get(String.class);
		// CarCostCalculation car = new CarCostCalculation();
		// final String  responseMsg =  car.getIt("suv", "v8,automatic,navigation,sunroof,premiumaudio,towpackage",1l);
		assertEquals("The car value of suv is 43050.0", responseMsg);
	}

	@Test
	public void testGetCarCostluxarySedan() throws Exception {
		final String responseMsg = target().path("luxary_sedan/v8,automatic,navigation,sunroof,premiumaudio,towpackage").request().get(String.class);
		assertEquals("The car value of luxary_sedan is 66700.0", responseMsg);
	}

	@Test
	public void testGetCarCostIncorrectCar() throws Exception {
		final String responseMsg = target().path("luxary/v8,automatic,navigation,sunroof,premiumaudio,towpackage").request().get(String.class);
		assertEquals("The Car Type luxary is not available please select car types from the following [coupe, truck, suv, luxary_sedan]", responseMsg);
	}

	@Test
	public void testGetCarCostIncorrectOption() throws Exception {
		final String responseMsg = target().path("coupe/v8,automatic,navigation,sunroof,premiumaudio,towpackage").request().get(String.class);
		assertEquals("coupe doesn't support towpackage, please select options from these [premiumaudio, v8, navigation, sunroof, automatic]", responseMsg);
	}

	@Test
	public void testGetCarCostWithdefaultOption() throws Exception {
		final String responseMsg = target().path("coupe/default").request().get(String.class);
		assertEquals("The car value of coupe is 15500.0", responseMsg);
	}
}
