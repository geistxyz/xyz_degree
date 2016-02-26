/*
 * @(#)NotificationParameterDescriptor.java
 */

package edu.umss.devportal.common.notifications;


/**
 * Class that represents a parameter information needed by a notification rule.
 * 
 * @author Alex Arenas
 */
public class NotificationParameterDescriptor {

    /**
     * Notification parameter name.
     */
    private String name;

    /**
     * Notification parameter default value (optional).
     */
    private String defaultValue;

    /**
     * Notification parameter description.
     */
    private String description;

    /**
     * Notification paramete heck patters.  Used to validate the parameter value.
     */
    private String checkPattern;

    /**
     * Flag indicating if the attribute is mandatory or optional.
     */
    private boolean isMandatory;

    /**
     * Default constructor.
     *
     * @param name parameter's name.
     * @param defaultValue parameter's default value
     * @param description parameter's description
     * @param checkPattern paratmeter's check pattern
     * @param isMandatory paramter's is mandatory flag
     */
    public NotificationParameterDescriptor(String name, String defaultValue, 
            String description, String checkPattern, boolean isMandatory) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.description = description;
        this.checkPattern = checkPattern;
        this.isMandatory = isMandatory;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCheckPattern() {
        return checkPattern;
    }

    public void setCheckPattern(String checkPattern) {
        this.checkPattern = checkPattern;
    }

    public boolean isIsMandatory() {
        return isMandatory;
    }

    public void setIsMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

}
