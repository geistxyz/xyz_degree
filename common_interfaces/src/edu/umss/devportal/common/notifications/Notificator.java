/*
 * @(#)PluginNotificationRule.java
 */

package edu.umss.devportal.common.notifications;

import java.util.Collection;
import java.util.Properties;

/**
 *
 * @author Alex Arenas
 */
public interface Notificator {

    /**
     * Permits to get all available rules for this notificator.
     *
     * @return a list of all available notification rules.
     */
    Collection<NotificationRule> getAvailableNotificationRules();

    /**
     * Permits execute a rule and get a NotificationResponse resulted of execution.
     *
     * @param identifier rule identifier.
     * @param prop list of parameters needed to execute the rule
     * @return a notification response resulting of execute the rule.
     */
    NotificationResponse executeNotificationRule(String identifier, Properties prop);
}
