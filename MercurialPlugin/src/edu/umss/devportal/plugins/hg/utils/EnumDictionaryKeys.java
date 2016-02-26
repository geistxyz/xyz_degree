/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.plugins.hg.utils;

/**
 *
 * @author Administrator
 */
public enum EnumDictionaryKeys {

    KEY("auth");
    private String data;

    private EnumDictionaryKeys(String data) {
        this.data = data;
    }

    public void setValue(String value) {
        data = value;
    }

    public String getValue() {
        return data;
    }
}
