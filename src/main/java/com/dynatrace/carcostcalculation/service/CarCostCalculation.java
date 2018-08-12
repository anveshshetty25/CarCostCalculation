package com.dynatrace.carcostcalculation.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
	Map<String, Map<String,Integer>> car = new HashMap<String, Map<String,Integer>>()
    {{
    	put("coupe",new HashMap<String,Integer>(){{
    		put(CAR_COST,15000);
    		put(ENGINE_TYPE,5000);
    		put(TRANSMISSION_TYPE,1000);
    		put(MUSIC_TYPE,1000);
    		put(SUNROOF,1000);
    		put(NAVIGATION,1000);
    		
    	}});
    	put("truck",new HashMap<String,Integer>(){{
    		put(CAR_COST,25000);
    		put(ENGINE_TYPE,6000);
    		put(TRANSMISSION_TYPE,1500);
    		put(MUSIC_TYPE,1100);
    		put(SUNROOF,1000);
    		put(NAVIGATION,1000);
    		put(TOW_PACKAGE,550);
    		
    	}});
    	put("suv",new HashMap<String,Integer>(){{
    		put(CAR_COST,30000);
    		put(ENGINE_TYPE,5500);
    		put(TRANSMISSION_TYPE,1200);
    		put(MUSIC_TYPE,1500);
    		put(SUNROOF,1000);
    		put(NAVIGATION,1000);
    		put(TOW_PACKAGE,500);
    		
    	}});
    	put("luxary_sedan",new HashMap<String,Integer>(){{
    		put(CAR_COST,35000);
    		put(ENGINE_TYPE,25000);
    		put(TRANSMISSION_TYPE,1200);
    		put(MUSIC_TYPE,1500);
    		put(SUNROOF,1000);
    		put(NAVIGATION,1000);
    		put(TOW_PACKAGE,500);
    	}});
    }};
    @GET
    @Path("{carType}/{options}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt(@PathParam("carType") String carType,@PathParam("options") String options) throws Exception {
    	double carCost = 0.0;
    	if(carType!= null){
    		String[] carOptions={};
    	if(options!=null){
    	 carOptions = options.split(",");
    	}
        
        if(car.containsKey(carType)){
        	carCost += car.get(carType).get(CAR_COST);
        	for(String option:carOptions){
        	if(car.get(carType).containsKey(option)){
        		carCost += car.get(carType).get(option);
        	}else{
        		return carType+" doesn't support "+option+" please select options from these "+car.get(carType).keySet();
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
        	return "The Car Type "+carType+" is not available please select car types from the following "+car.keySet();
        }
    	}
    	return "The car value of "+carType+" is "+carCost; 
    }
    
    private static double slowTaxCalculationMethod() {
        // the Thread.sleep cannot be removed
        try {
            Thread.sleep(1500);
        } catch (Exception e) {
            // Do nothing
        }
        return 500;
    }



}
