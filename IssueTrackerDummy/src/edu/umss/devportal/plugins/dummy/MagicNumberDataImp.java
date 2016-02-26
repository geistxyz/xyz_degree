/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.plugins.dummy;

import edu.umss.devportal.common.reports.MagicNumberData;

/**
 *
 * @author July Camacho
 */
public class MagicNumberDataImp implements MagicNumberData{

    private String key;
    private Object value;
    public void setKey(String key) {
        this.key = key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

}