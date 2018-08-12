package com.dynatrace.carcostcalculation;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Ignore;
import org.junit.Test;

import com.dynatrace.carcostcalculation.service.CarCostCalculation;

import static org.junit.Assert.assertEquals;
@Ignore
public class CarCostCalculationTest extends JerseyTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(CarCostCalculation.class);
    }

    /**
     * Test to see that the message "Got it!" is sent in the response.
     */
    @Test
    public void testGetIt() {
        final String responseMsg = target().path("").request().get(String.class);

        assertEquals("Hello, Heroku!", responseMsg);
    }
}
