package com.dynatrace.carcostcalculation.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

/**
 * Root resource (exposed at "" path)
 * 
 * @author Anwesh.Valleshetti
 */
@Path("")
public class CarCostCalculation {

	public static final String CAR_COST = "cost";
	public static final String ENGINE_TYPE = "v8";
	public static final String TRANSMISSION_TYPE = "automatic";
	public static final String MUSIC_TYPE = "premiumaudio";
	public static final String SUNROOF = "sunroof";
	public static final String NAVIGATION = "navigation";
	public static final String TOW_PACKAGE = "towpackage";
	private static final String COUPE = "coupe";
	private static final String TRUCK = "truck";
	private static final String SUV="suv";
	private static final String LUXARY_SEDAN = "luxary_sedan";
	private static final String[] gasGuzzlers = { "truck", "suv" };
	private static Logger logger = Logger.getLogger(CarCostCalculation.class);
	//static lookup for cartypes and their price 
	private static final LinkedHashMap<String,Integer> carslookup = new LinkedHashMap<String,Integer>()
	{{
		put(COUPE,15000);
		put(TRUCK,25000);
		put(SUV,30000);
		put(LUXARY_SEDAN,35000);
	}};
	//static lookup for options and their price
	private static final Map<String, Map<String,Integer>> OptionsLookup = new HashMap<String, Map<String,Integer>>()
	{{
		put(COUPE,new HashMap<String,Integer>(){{
			put(ENGINE_TYPE,5000);
			put(TRANSMISSION_TYPE,1000);
			put(MUSIC_TYPE,1000);
			put(SUNROOF,1000);
			put(NAVIGATION,1000);

		}});
		put(TRUCK,new HashMap<String,Integer>(){{
			put(ENGINE_TYPE,6000);
			put(TRANSMISSION_TYPE,1500);
			put(MUSIC_TYPE,1100);
			put(SUNROOF,1000);
			put(NAVIGATION,1000);
			put(TOW_PACKAGE,550);

		}});
		put(SUV,new HashMap<String,Integer>(){{
			put(ENGINE_TYPE,5500);
			put(TRANSMISSION_TYPE,1200);
			put(MUSIC_TYPE,1500);
			put(SUNROOF,1000);
			put(NAVIGATION,1000);
			put(TOW_PACKAGE,500);

		}});
		put(LUXARY_SEDAN,new HashMap<String,Integer>(){{
			put(ENGINE_TYPE,25000);
			put(TRANSMISSION_TYPE,1200);
			put(MUSIC_TYPE,1500);
			put(SUNROOF,1000);
			put(NAVIGATION,1000);
			put(TOW_PACKAGE,500);
		}});
	}};

	/**
	 * Method processes the car cost based on user selected carType and options.
	 *  Returns output to the client as "text/plain" media type.
	 *
	 * @param carType-type{@link String}
	 * @param carType-type{@link String}
	 * @return carCost-The car cost value for user selected carType and options.
	 * @throws Exception 
	 */
	@GET
	@Path("{carType}/{options}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCarCost(@PathParam("carType") String carType,@PathParam("options") String options) throws Exception {
		double carCost = 0.0;
		String[] carOptions={};
		//If user doesn't select any options then the value will be default and carOptions array is empty
		if(!options.equals("default")){
			carOptions = options.split(",");
			logger.info("User selected of car type "+carType+" with options "+options);
		}

		//checks the car type and options selected by user and adds the respective cost
		if(carslookup.containsKey(carType)){
			carCost += carslookup.get(carType);
			for(String option:carOptions){
				if(OptionsLookup.get(carType).containsKey(option)){
					carCost += OptionsLookup.get(carType).get(option);
				}else {
					Set<String> allowedCarTypes = OptionsLookup.get(carType).keySet();
					logger.error(carType+" doesn't support "+option+", please select options from these "+allowedCarTypes);
					return carType+" doesn't support "+option+", please select options from these "+allowedCarTypes;
				}
			}
			logger.debug("The car value before adding tax"+carCost);
			if (carCost > 30000 && carCost <= 60000) {
				// luxury tax
				carCost += 850;
			} else if (carCost > 60000) {
				// extra luxury tax
				carCost += 1000;
			}
			double tax = 0;
			tax = slowTaxCalculationMethod();
			for (String gasGuzzler : gasGuzzlers) {
				if (gasGuzzler.equals(carType)) {
					// adding gas guzzler tax
					tax += 1000;
					break;
				}
			}
			carCost += tax;
			logger.debug("The car value after adding tax"+carCost);
		}else{
			logger.error("The Car Type "+carType+" is not available please select car types from the following "+carslookup.keySet());
			return "The Car Type "+carType+" is not available please select car types from the following "+carslookup.keySet();
		}
		logger.info("The car value of "+carType+" is "+carCost);
		return "The car value of "+carType+" is "+carCost; 
	}

	private static double slowTaxCalculationMethod() {
		try {
			Thread.sleep((long) 0.1);
		} catch (Exception e) {
			logger.error("Exception due to"+e);
		}
		return 500;
	}



}
