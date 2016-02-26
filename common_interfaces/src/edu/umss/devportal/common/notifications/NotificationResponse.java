/*
 * @(#)NotificationResponse.java
 */

package edu.umss.devportal.common.notifications;

/**
 *
 * @author Alex Arenas
 * @see NotificationRule
 */
public interface NotificationResponse {

    /**
     * Enumeration that permits to represent the result of execute a
     * notification rule.
     */
    enum ResponseResult{SUCCESS, FAIL, EXCEPTION};

    /**
     * @return the rule identifier associated to this notification response.
     */
    String getRuleIdentifier();

    /**
     * @return the result of execute the notification rule.
     */
    ResponseResult getResponseResult();

    /**
     * @return the message to describe the notification result
     */
    String getMessage();

}
