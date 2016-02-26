/*
 * @(#)MagicNumbersStructure.java
 */

package edu.umss.devportal.common.reports;

import java.util.List;

/**
 *
 * @author Edson
 * @author July Camacho
 * @version 1.0
 *
 * Represents a Magic Numbers structure
 */
public interface MagicNumbersStructure extends DataStructure{

    /**
     * Gets the List of criteria values per each severity
     * @return criteria
     */
    public List<MagicNumberData> getCriteriaList();

    /**
     * Gets the List of status values per each severity
     * @return status
     */
    public List<MagicNumberData> getStatusList();

    /**
     * Gets the List of Magic Numbers values per each severity
     * @return magicNumbers
     */
    public List<MagicNumberData> getMagicNumbersList();


    /**
     * Gets the List of Dev Magic values per each severity
     * @return devMagic
     */
    public List<MagicNumberData> getDevMagicList();

     /**
     * Gets the List of values per each severity that represents if a severity
     * has been fulfilled.
     * @return fulfilledSeverity
     */
    public List<MagicNumberData> getFulfilledSeverity();

    /**
     * Sets the list of criteria per each severity
     * @param criteria
     */
    public void setCriteriaList(List<MagicNumberData> criteria);

     /**
     * Sets the list of status per each severity
     * @param status
     */
    public void setStatusList(List<MagicNumberData> status);

    /**
     * Sets the list of magicNumbers per each severity
     * @param magicNumbers
     */
    public void setMagicNumbersList(List<MagicNumberData> magicNumbers);

    /**
     * Sets the list of devMagic per each severity
     * @param devMagic
     */
    public void setDevMagicList(List<MagicNumberData> devMagic);

    /**
     * Sets the list of fulfilledSeverity per each severity
     * @param fulfilledSeverity
     */
    public void setFulfilledSeverityList(List<MagicNumberData> fulfilledSeverity);

    /**
     * Gets the sum of Magic Numbers
     * @return the total magic numbers
     */
    public int getTotalMagicNumbers();

    /**
     * Gets the sum of Dev Magic
     * @return the total dev magic
     */
    public int getTotalDevMagic();


    /**
     * Gets the count of Severities
     * @return the severities size
     */
    public int getSeverityCount();
}
