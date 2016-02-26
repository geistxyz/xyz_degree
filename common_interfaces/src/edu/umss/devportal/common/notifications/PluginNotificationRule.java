/*
 * @(#)PluginNotificationRule.java
 */

package edu.umss.devportal.common.notifications;

import java.util.Properties;

/**
 * Interface that represents a notification rule saw sinse the plugin side.
 *
 * @author Alex Arenas
 */
public interface PluginNotificationRule extends NotificationRule {

    /**
     * Permits execute the rule and get a notification response.
     *
     * @param prop notification parameters configured by the client.
     * @return a notification response resulting of execute the rule.
     */
    NotificationResponse execute(Properties prop);
    
}
