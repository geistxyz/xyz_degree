/*
 * @(#)DefaultNotificationResponse.java
 */

package edu.umss.devportal.common.notifications;

/**
 *
 * @author Alex Arenas
 */
public class DefaultNotificationResponse implements NotificationResponse{
    
    private String message;
    private String ruleIdentifier;
    private ResponseResult result;

    /**
     * default constructor.
     */
    public DefaultNotificationResponse() {
    }

    public DefaultNotificationResponse(String ruleIdentifier, ResponseResult result, String message) {
        this.message = message;
        this.ruleIdentifier = ruleIdentifier;
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setResult(ResponseResult result) {
        this.result = result;
    }

    public void setRuleIdentifier(String ruleIdentifier) {
        this.ruleIdentifier = ruleIdentifier;
    }

    public String getMessage() {
        return message;
    }

    public ResponseResult getResponseResult() {
        return result;
    }

    public String getRuleIdentifier() {
        return ruleIdentifier;
    }

}
