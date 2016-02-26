/*
 * @(#)BaseNotificator.java
 */

package edu.umss.devportal.common.notifications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Class that represents a notifications manager.  Each plugin that supports
 * notifications should be extens this class to manage all implementations
 * of notification for this plugin.
 *
 * @author Alex Arenas
 */
public class BaseNotificator implements Notificator{

    /**
     * Map that storage all available rules.
     */
    private Map<String, PluginNotificationRule> rules;

    /**
     * Default constructor.
     */
    public BaseNotificator() {
        rules = new HashMap<String, PluginNotificationRule>();
    }

    /**
     * Adds a new notification rule to the notificator.
     *
     * @param rule the new rule to add the notificator rules.
     */
    protected void add(PluginNotificationRule rule){
        rules.put(rule.getIdentifier(), rule);
    }

    /**
     * Remove the notification rule from notificator.
     * 
     * @param rule rule to remove
     */
    protected void remove(PluginNotificationRule rule){
        remove(rule.getIdentifier());
    }

    /**
     * Remove the notification rule from notificator.
     *
     * @param key identifier of the notification rule to be removed.
     */
    protected void remove(String key){
        rules.remove(key);
    }

    /**
     * @see Notificator#executeNotificationRule(java.lang.String, java.util.Properties)
     */
    public NotificationResponse executeNotificationRule(String identifier, Properties prop) {
        if(!rules.containsKey(identifier)){
            return new DefaultNotificationResponse(identifier,
                    NotificationResponse.ResponseResult.EXCEPTION,
                    "The rule identifier does not exist");
        }

        PluginNotificationRule rule = rules.get(identifier);
        return rule.execute(prop);
    }

    /**
     * @see Notificator#getAvailableNotificationRules()
     */
    public Collection<NotificationRule> getAvailableNotificationRules() {
        Collection<NotificationRule> allRules = new ArrayList<NotificationRule>();

        for (PluginNotificationRule pluginNotificationRule : rules.values()) {
            allRules.add(pluginNotificationRule);
        }

        return allRules;
    }

}
