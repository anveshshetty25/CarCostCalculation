package com.dynatrace.carcostcalculation.service;

import java.util.HashMap;
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
 */
@Path("")
public class CarCostCalculation {

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     * @throws Exception 
     */
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
	
	private static Logger logger = Logger.getLogger(CarCostCalculation.class);
	private static final Map<String,Integer> carlookup = new HashMap<String,Integer>()
	{{
		put(COUPE,15000);
		put(TRUCK,25000);
		put(SUV,30000);
		put(LUXARY_SEDAN,35000);
	}};
 	private static final Map<String, Map<String,Integer>> OptionLookup = new HashMap<String, Map<String,Integer>>()
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
    
    
    @GET
    @Path("{carType}{options:(/options/[^/]+?)?}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getCarCost(@PathParam("carType") String carType,@PathParam("options") String options) throws Exception {
    	double carCost = 0.0;
    		String[] carOptions={};
    	if(options!=null){
    	 carOptions = options.split(",");
    	 logger.info("User selected of car type "+carType+"with"+carOptions);
    	}
        
        if(carlookup.containsKey(carType)){
        	carCost += carlookup.get(carType);
        	for(String option:carOptions){
        	if(OptionLookup.get(carType).containsKey(option)){
        		carCost += OptionLookup.get(carType).get(option);
        	}else if(option.length()!=0){
        		Set<String> allowedCarTypes = OptionLookup.get(carType).keySet();
        		logger.error(carType+" doesn't support the options you selected please select options from these "+allowedCarTypes);
        		return carType+" doesn't support the options you selected please select options from these "+allowedCarTypes;
        	}
        	}
        	if (carCost > 30000 && carCost <= 60000) {
                // luxury tax
                carCost += 850;
            } else if (carCost > 60000) {
                // extra luxury tax
                carCost += 1000;
            }
            double tax = 0;
            //looping two times
            tax = slowTaxCalculationMethod();
            String[] gasGuzzlers = { "truck", "suv" };
            for (String gasGuzzler : gasGuzzlers) {
                if (gasGuzzler.equals(carType)) {
                    // adding gas guzzler tax
                    tax += 1000;
                    break;
                }
            }
            carCost += tax;
        }else{
        	logger.error("The Car Type "+carType+" is not available please select car types from the following "+carlookup.keySet());
        	return "The Car Type "+carType+" is not available please select car types from the following "+carlookup.keySet();
        }
    	return "The car value of "+carType+" is "+carCost; 
    }
    
    private static double slowTaxCalculationMethod() {
        // the Thread.sleep cannot be removed
        try {
            Thread.sleep((long) 0.1);
        } catch (Exception e) {
            // Do nothing
        }
        return 500;
    }



}
