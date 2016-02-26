/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

/**
 *
 * @author Edson Alvarez
 */
public class MagicNumberDataImplementation implements MagicNumberData{

    private String key;
    private Object value;

    @Override
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

}
