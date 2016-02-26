/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.xplanner.config;

import edu.umss.devportal.common.ParameterDescriptor;
import edu.umss.devportal.common.ToolDescriptor;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author raul
 */
public class XPlannerConfigTest {

    public XPlannerConfigTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getToolDescriptor method, of class XPlannerConfig.
     */
    @Test
    public void testGetToolDescriptorNotNull() {
        System.out.println("getToolDescriptor");        
        ToolDescriptor result = XPlannerConfig.getToolDescriptor();
        assertNotNull(result);
    }

    /**
     * Test of getToolDescriptor method, of class XPlannerConfig.
     */
    @Test
    public void testGetToolDescriptor() {
        System.out.println("getToolDescriptor");
        String version = "Version 1.0.0.0";
        ToolDescriptor result = XPlannerConfig.getToolDescriptor();
        assertEquals(XPlannerConfig.ToolName, result.getName());
        assertEquals(XPlannerConfig.ToolDescription, result.getDescription());
        assertEquals(version, result.getVersion());
    }

    /**
     * Test of getRequiredParameters method, of class XPlannerConfig.
     */
    @Test
    public void testGetRequiredParametersNotNull() {
        System.out.println("getRequiredParameters");        
        List result = XPlannerConfig.getRequiredParameters();
        assertNotNull(result);
    }

    /**
     * Test of getRequiredParameters method, of class XPlannerConfig.
     */
    @Test
    public void testGetRequiredParametersNotEmpty() {
        System.out.println("getRequiredParameters");
        List result = XPlannerConfig.getRequiredParameters();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of getRequiredParameters method, of class XPlannerConfig.
     */
    @Test
    public void testGetRequiredParametersContainsParameters() {
        System.out.println("getRequiredParameters");
        String [] requiredParams = {XPlannerConfig.Password, XPlannerConfig.ServiceUrl, XPlannerConfig.User};
        List<ParameterDescriptor> result = XPlannerConfig.getRequiredParameters();


        for(String param : requiredParams) {
            boolean found = false;
            for(ParameterDescriptor paramDesc : result) {
                if(paramDesc.getName().equals(param))
                {
                    found = true;
                    break;
                }
            }
            if(!found)
            {
                fail("Parameter:" + param + " not found");
            }
        }


    }
}