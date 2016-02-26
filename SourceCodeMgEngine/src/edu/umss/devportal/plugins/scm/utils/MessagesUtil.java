/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.scm.utils;

/**
 *An util that manages the fails, errors messages for returns to user application
 * @author Arminda Yovana Soto
 */
public enum MessagesUtil {

    FILE_CREATED("The file is created"),
    DIRECTORY_NOT_FOUND("The directory is not found"),
    DIRECTORY_DELETED("The directory is deleted successfully"),
    DIRECTORY_NOT_DELETED("The directory name is incorrect,the directory is not deleted"),
    TEMP_MESSAGE("the value is incorrect"),
    SCMPLUGIN_NAME_INVALID("the SCM value is incorrect, please revise"),;
    private String message;

    /**
     * Constructor initialize a message variable
     * @param message
     */
    private MessagesUtil(String message) {
        this.message = message;
    }

    /**
     *Sets a message
     * @param message
     */
    public void setNewMessage(String message) {
        this.message = message;
    }

    /**
     * Returns a message
     * @return message represent a literal message
     */
    public String getMessage() {
        return this.message;
    }
}
