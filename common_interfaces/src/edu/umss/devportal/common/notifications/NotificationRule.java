/*
 * @(#)NotificationRule.java
 */

package edu.umss.devportal.common.notifications;

import java.util.List;

/**
 * Interface that represents a notification rule saw sinse the application side.
 *
 * @author Alex Arenas
 */
public interface NotificationRule {

    /**
     * @return the notification rule identifier
     */
    public String getIdentifier();

    /**
     * @return the notification rule name
     */
    public String getName();

    /**
     * @return the notification rule description
     */
    public String getDescription();

    /**
     * @return a list of notification parameters needed to execute the rule.
     */
    public List<NotificationParameterDescriptor> getParameterDescriptors();
}
