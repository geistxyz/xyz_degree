/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.umss.devportal.common.reports;

import edu.umss.devportal.common.reports.MagicNumbersStructure;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edson Alvarez
 */
public class MagicNumbersStructureImplementation implements MagicNumbersStructure{

    private List<MagicNumberData> criteria;
    private List<MagicNumberData> status;
    private List<MagicNumberData> magicNumbers;
    private List<MagicNumberData> devMagics;
    private List<MagicNumberData> fulfilledSeverity;

    public List<MagicNumberData> getCriteriaList() {
        return criteria;
    }

    public List<MagicNumberData> getStatusList() {
        return status;
    }

    public List<MagicNumberData> getMagicNumbersList() {
        return magicNumbers;
    }

    public List<MagicNumberData> getDevMagicList() {
        return devMagics;
    }

    public void setCriteriaList(List<MagicNumberData> criteria) {
        this.criteria = criteria;
    }

    public void setStatusList(List<MagicNumberData> status) {
        this.status = status;
    }

    public void setMagicNumbersList(List<MagicNumberData> magicNumbers) {
        this.magicNumbers = magicNumbers;
    }

    public void setDevMagicList(List<MagicNumberData> devMagics) {
        this.devMagics = devMagics;
    }

     public List<MagicNumberData> getFulfilledSeverity() {
        return fulfilledSeverity;
    }

    public void setFulfilledSeverityList(List<MagicNumberData> fulfilledSeverity) {
        this.fulfilledSeverity = fulfilledSeverity;
    }

    public int getTotalMagicNumbers() {
        int totalMagicNumbers = 0;
        for(MagicNumberData magicNumber : magicNumbers){
            if((Integer)magicNumber.getValue() > 0)
                totalMagicNumbers += (Integer)magicNumber.getValue();
        }
        return totalMagicNumbers;
    }

    public int getTotalDevMagic() {
        int totalDevMagic = 0;
        for(MagicNumberData devMagicNumer : devMagics){
            if((Integer)devMagicNumer.getValue() > 0)
                totalDevMagic += (Integer)devMagicNumer.getValue();
        }
        return totalDevMagic;
    }

    public int getSeverityCount() {
        return criteria.size();
    }
}
