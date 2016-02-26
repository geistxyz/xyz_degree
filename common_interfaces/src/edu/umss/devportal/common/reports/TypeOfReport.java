/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.common.reports;

/**
 *
 * @author Edson
 */
public interface TypeOfReport {

    AvailableType toAvailableReport();

    boolean sameType(TypeOfReport other);
}
