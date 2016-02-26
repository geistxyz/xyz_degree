/*
 *  @(#)PluginNotificationRule.java   28-nov-2010
 */

package edu.umss.devportal.plugins.dummy.notifications;

import edu.umss.devportal.common.notifications.DefaultNotificationResponse;
import edu.umss.devportal.common.notifications.NotificationParameterDescriptor;
import edu.umss.devportal.common.notifications.NotificationResponse;
import edu.umss.devportal.common.notifications.PluginNotificationRule;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

/**
 * A dummy rule.
 *
 * @author Alex Arenas
 */
public class RandomRule  implements PluginNotificationRule{

    private final static String DESCRIPTION = "A rule that generates results in a random way.";
    private final static String ID = "RandomRule";
    private final static String NAME = "Random issue tracker rule";

    private final static String FAIL_PARAM_NAME = "Fail percent";

    private Random random = new Random();


    public NotificationResponse execute(Properties prop) {
        DefaultNotificationResponse response = new DefaultNotificationResponse();
        response.setRuleIdentifier(ID);
        float failValue = 0;
        try{
            failValue = Float.parseFloat(prop.getProperty(FAIL_PARAM_NAME));
        }catch(Exception e){
            failValue = 0.5f;
        }
        float r = random.nextFloat();
        if(r < failValue){
            response.setResult(NotificationResponse.ResponseResult.FAIL);
            response.setMessage("The rule failed.");
        }else{
            response.setResult(NotificationResponse.ResponseResult.SUCCESS);
            response.setMessage("The rule passed.");
        }

        return response;
    }

    public String getDescription() {
        return DESCRIPTION;
    }

    public String getIdentifier() {
        return ID;
    }

    public String getName() {
        return NAME;
    }

    public List<NotificationParameterDescriptor> getParameterDescriptors() {
        List<NotificationParameterDescriptor> parameters = new ArrayList<NotificationParameterDescriptor>();

        parameters.add(new NotificationParameterDescriptor(FAIL_PARAM_NAME, "0.5",
                "The probability to fail.  From 0.0 to 1.0", "", true));
        parameters.add(new NotificationParameterDescriptor("Sample parameter",
                "Default", "A sample parameter", "", false));

        return parameters;
    }



}
