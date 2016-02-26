/*
 * @(#)MagicNumbersBean.java 11/04/21
 *
 * Copyright (c) 2011 Universidad Mayor de San SimÃƒÂ³n
 * Cochabamba - Bolivia
 * All Rights Reserved.
 */
package edu.umss.devportal.reports.backbeans;

import edu.umss.devportal.common.IssueTracker;
import edu.umss.devportal.common.ProjectRole;
import edu.umss.devportal.common.reports.DynamicStructure;
import edu.umss.devportal.common.reports.MagicNumberData;
import edu.umss.devportal.common.reports.MagicNumberDataImplementation;
import edu.umss.devportal.common.reports.MagicNumbers;
import edu.umss.devportal.reports.utilities.CellContent;
import edu.umss.devportal.common.reports.MagicNumbersStructure;
import edu.umss.devportal.ejb.session.LoginSessionBean;
import edu.umss.devportal.common.reports.MagicNumbersStructureImplementation;
import edu.umss.devportal.common.reports.Report;
import edu.umss.devportal.entity.ProjectMembershipEntity;
import edu.umss.devportal.reports.utilities.DataTransformer;
import edu.umss.devportal.reports.utilities.Severity;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 * @author Raul Garvizu
 * @author Edson Alvarez
 * @see DynamicGridBean
 */
@ManagedBean
@RequestScoped
public class MagicNumbersBean extends DynamicGridBean {

    @EJB
    private LoginSessionBean loginSessionBean;
    private String issueTrackerProjectId;
    private int personId;
    private MagicNumbersStructure magicNumbersStructure;
    private boolean editable;
    private int magicNumberColumns;
    private IssueTracker issueTracker;
    private Properties prop = new Properties();
    private int severityCount;
    private List<Severity> severityList;
    private List<MagicNumberData> data = new ArrayList<MagicNumberData>();
    private boolean magicNumbrersFromIssues;

    @PostConstruct
    public void postConstruct() {
        isRetrievedFromIssues();
        getIssueTracker();
    }

    private boolean getIssueTracker() {
        try {
            issueTrackerProjectId =
                    loginSessionBean.getIdProjectIssueTracker();

            issueTracker = loginSessionBean.getCurrentIssueTracker();
            if (issueTracker != null) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error trying to retrieve the "
                    + "issue tracker id:" + e);
            FacesContext.getCurrentInstance().
                    addMessage(
                    null,
                    new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Error trying to retrieve the issue tracker id",
                    "Description: " + e));
            return false;
        }
    }

    /**
     * Generates the list of headers
     * @see DynamicGridBean.getColumns()
     * @return List<String>, list of column titles
     */
    @Override
    public List<String> getColumns() {

        List<String> columns = new ArrayList<String>();

        if (magicNumbrersFromIssues) {
            try {
                //issueTracker = loginSessionBean.getCurrentIssueTracker();
                MagicNumbersStructure magicNumber = getMagicNumbers();
                List<MagicNumberData> severitiesInitialList = magicNumber.getCriteriaList();

                columns.add(null);

                for (int i = 0; i <= severitiesInitialList.size() - 1; i++) {
                    MagicNumberData magicNumberData = severitiesInitialList.get(i);
                    columns.add(magicNumberData.getKey());
                }

            } catch (Exception ex) {
                System.out.println("Error trying to retrieve the "
                        + "issue tracker id:" + ex);
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error trying to retrieve the issue tracker id",
                        "Description: " + ex));
            }
        } else {
            MagicNumbersStructure structure = new MagicNumbersStructureImplementation();
            structure = getMagicNumbers();

            severityCount = structure.getSeverityCount();

            columns.add(null);
            for (int level = 0; level < severityCount; level++) {
                columns.add(structure.getCriteriaList().get(level).getKey());
            }
        }

        columns.add("Total");
        return columns;
    }

    public int getMagicNumberColumns() {

        if (magicNumbrersFromIssues) {
            magicNumberColumns = getMagicNumbers().getSeverityCount();
        } else {
            try {
                //issueTracker = loginSessionBean.getCurrentIssueTracker();
                MagicNumbers magicNumber = issueTracker.getMagicNumbers();
                List<MagicNumberData> severitiesInitialList = magicNumber.getSeverities();
                magicNumberColumns = severitiesInitialList.size();
            } catch (Exception e) {
                if (issueTracker == null) {
                    System.out.println("Issue Tracker is null Magic Numbers could't be generated !!! \nCaused by: " + e);
                } else {
                    System.out.println("There are no columns for magic numbers");
                }

                magicNumberColumns = 0;
            }
        }

        return magicNumberColumns;
    }

    public void setMagicNumberColumns(int magicNumberColumns) {
        this.magicNumberColumns = magicNumberColumns;
    }

    /**
     * Generates the Magic Numbers Report
     * @return a DynamicStructure that contains representation of the Magic Numbers
     */
    public MagicNumbersStructure getMagicNumbers() {

        //IssueTracker issueTracker = null;

        if (magicNumbrersFromIssues) {

            List<MagicNumberData> severitiesFullList = new ArrayList<MagicNumberData>();
            magicNumbersStructure = new MagicNumbersStructureImplementation();
            boolean isNotEmpty = true;

            try {
                List reportsList = new ArrayList();
                List<Report> gridReports = new ArrayList<Report>();
                reportsList.add("2");
                gridReports = issueTracker.getGridReports("31", reportsList);
                Report allIssues = gridReports.get(1);

                DynamicStructure structure = (DynamicStructure) allIssues.getDataStructure();

                List<List<String>> dynamicList = structure.getDynamicList();

                List<String[]> magicNumberArrayList = new ArrayList<String[]>();

                for (int i = 0; i <= dynamicList.size() - 1; i++) {
                    boolean existentSeverity = false;
                    int index = 0;
                    List<String> row = dynamicList.get(i);

                    if (!magicNumberArrayList.isEmpty()) {
                        for (int p = 0; p <= magicNumberArrayList.size() - 1; p++) {
                            String extSeverity = magicNumberArrayList.get(p)[0];
                            String curSeverity = row.get(6);
                            if (extSeverity.equals(curSeverity)) {
                                existentSeverity = true;
                                index = p;
                                break;
                            }
                        }

                        if (existentSeverity) {
                            if (row.get(0).toString().equals("Closed")) {
                                int currentClosed = Integer.parseInt(magicNumberArrayList.get(index)[2]);
                                magicNumberArrayList.get(index)[2] = "" + (currentClosed + 1);
                            } else {
                                int currentOpen = Integer.parseInt(magicNumberArrayList.get(index)[1]);
                                magicNumberArrayList.get(index)[1] = "" + (currentOpen + 1);
                            }
                        } else {
                            String[] newSeverity = new String[3];
                            newSeverity[0] = row.get(5).toString();
                            if (row.get(0).toString() == "Closed") {
                                newSeverity[1] = "0";
                                newSeverity[2] = "1";
                            } else {
                                newSeverity[1] = "1";
                                newSeverity[2] = "0";
                            }
                            magicNumberArrayList.add(newSeverity);
                        }
                    } else {
                        String[] newSeverity = new String[3];
                        newSeverity[0] = row.get(5).toString();
                        if (row.get(0).toString() == "Closed") {
                            newSeverity[1] = "0";
                            newSeverity[2] = "1";
                        } else {
                            newSeverity[1] = "1";
                            newSeverity[2] = "0";
                        }
                        magicNumberArrayList.add(newSeverity);
                    }
                }

                List<MagicNumberData> sevList = new ArrayList();

                for (int k = 0; k < magicNumberArrayList.size(); k++) {
                    MagicNumberData sev = new MagicNumberDataImplementation();
                    sev.setKey(magicNumberArrayList.get(k)[0]);
                    sev.setValue(50);
                    sevList.add(sev);
                }

                List<MagicNumberData> severitiesInitialList = sevList;
                severitiesFullList = readCriteria(severitiesInitialList);

                List<MagicNumberData> criteria = new ArrayList<MagicNumberData>();
                List<MagicNumberData> status = new ArrayList<MagicNumberData>();
                List<MagicNumberData> magicNumber = new ArrayList<MagicNumberData>();
                List<MagicNumberData> devMagic = new ArrayList<MagicNumberData>();
                List<MagicNumberData> fulfilledSeverity = new ArrayList<MagicNumberData>();

                for (int c = 0; c <= sevList.size() - 1; c++) {
                    criteria.add(sevList.get(c));
                }

                for (int s = 0; s <= magicNumberArrayList.size() - 1; s++) {
                    MagicNumberData statusValue = new MagicNumberDataImplementation();
                    statusValue.setKey(magicNumberArrayList.get(s)[0]);
                    int open = Integer.parseInt(magicNumberArrayList.get(s)[1]);
                    int closed = Integer.parseInt(magicNumberArrayList.get(s)[2]);
                    int total = open + closed;

                    statusValue.setValue((open * 100) / total);
                    status.add(statusValue);
                }

                for (int s = 0; s <= magicNumberArrayList.size() - 1; s++) {
                    MagicNumberData magicNumberValue = new MagicNumberDataImplementation();
                    magicNumberValue.setKey(magicNumberArrayList.get(s)[0]);
                    magicNumberValue.setValue(Integer.parseInt(magicNumberArrayList.get(s)[1]));
                    magicNumber.add(magicNumberValue);
                }

                for (int s = 0; s <= magicNumberArrayList.size() - 1; s++) {
                    MagicNumberData devMagicValue = new MagicNumberDataImplementation();
                    devMagicValue.setKey(magicNumberArrayList.get(s)[0]);
                    devMagicValue.setValue(Integer.parseInt(magicNumberArrayList.get(s)[1]));
                    devMagic.add(devMagicValue);
                }

                for (int s = 0; s <= magicNumberArrayList.size() - 1; s++) {
                    MagicNumberData fulfilledSev = new MagicNumberDataImplementation();
                    fulfilledSev.setKey(magicNumberArrayList.get(s)[0]);
                    fulfilledSev.setValue(Integer.parseInt(criteria.get(0).getValue().toString()) <= Integer.parseInt(status.get(0).getValue().toString()));
                    fulfilledSeverity.add(fulfilledSev);
                }

                magicNumbersStructure.setCriteriaList(criteria);
                magicNumbersStructure.setStatusList(status);
                magicNumbersStructure.setMagicNumbersList(magicNumber);
                magicNumbersStructure.setDevMagicList(devMagic);
                magicNumbersStructure.setFulfilledSeverityList(fulfilledSeverity);

            } catch (Exception ex) {
                if (issueTracker == null) {
                    System.out.println("Issue Tracker is null Magic Numbers could't be generated !!! \nCaused by: " + ex);
                }
                System.out.println("Error trying to retrieve the "
                        + "magic number structure:" + ex);
                FacesContext.getCurrentInstance().
                        addMessage(
                        null,
                        new FacesMessage(
                        FacesMessage.SEVERITY_ERROR,
                        "Error trying to retrieve the magic number structure",
                        "Description: " + ex));
            }
        } else {
            List<MagicNumberData> severitiesFullList = new ArrayList<MagicNumberData>();

            try {
                //issueTrackerProjectId = loginSessionBean.getIdProjectIssueTracker();
                //issueTracker = loginSessionBean.getCurrentIssueTracker();
                MagicNumbers magicNumber = issueTracker.getMagicNumbers();
                List<MagicNumberData> severitiesInitialList = magicNumber.getSeverities();
                severitiesFullList = readCriteria(severitiesInitialList);
                magicNumbersStructure = magicNumber.getMagicNumbers(issueTrackerProjectId, severitiesFullList);
            } catch (Exception e) {
                if (issueTracker == null) {
                    System.out.println("Issue Tracker is null Magic Numbers could't be generated !!! \nCaused by: " + e);
                }
            }
        }

        return magicNumbersStructure;
    }

    /**
     * Generates the Magic Numbers table
     * @see DynamicGridBean.getData()
     * @return List<CellContent[]>, content of the table
     */
    @Override
    public List<CellContent[]> getData() {

        List<CellContent[]> dataContent = new ArrayList<CellContent[]>();

        try {
            MagicNumbersStructure magicNumbers = getMagicNumbers();
            dataContent.add(calculateRowData(
                    DataTransformer.transformToIntegers(
                    magicNumbers.getCriteriaList()),
                    "Criteria"));

            List<MagicNumberData> list = magicNumbers.getCriteriaList();
            for (int i = 0; i < list.size() - 1; i++) {
                String key = list.get(i).getKey();
                int value = Integer.parseInt(list.get(i).getValue().toString());

                //System.out.println(i + " ) Key: " + key + " - Value: " + value);
            }

            dataContent.add(calculateRowDataWithSeverity(
                    DataTransformer.transformToIntegers(
                    magicNumbers.getStatusList()),
                    "Status"));

            dataContent.add(calculateRowDataWithTotal(
                    DataTransformer.transformToIntegers(
                    magicNumbers.getMagicNumbersList()),
                    "Magic Numbers",
                    magicNumbers.getTotalMagicNumbers()));

            dataContent.add(calculateRowDataWithTotal(
                    DataTransformer.transformToIntegers(
                    magicNumbers.getDevMagicList()),
                    "Dev Magic",
                    magicNumbers.getTotalDevMagic()));

            return dataContent;
        } catch (IOException ex) {
            Logger.getLogger(MagicNumbersBean.class.getName()).
                    log(Level.SEVERE, null, ex);
            return dataContent;
        }
    }

    /**
     * Calculates the style of the text
     * @param criteriaData
     * @param title
     * @return the array of the content of the cell
     */
    private CellContent[] calculateRowData(List<Integer> criteriaData,
            final String title) {

        CellContent[] rowData = new CellContent[criteriaData.size() + 2];
        CellContent leftTitle = new CellContent(title);
        leftTitle.setStyle("font-weight: bold;");
        rowData[0] = leftTitle;

        for (int index = 0; index < criteriaData.size(); index++) {
            rowData[index + 1] = new CellContent(
                    criteriaData.get(index).toString());
        }

        return rowData;
    }

    /**
     * Calculates the Total of the magic numbers
     * @param magicNumbersList
     * @param string
     * @param totalMagicNumbers
     * @return the array of the content of the cell
     */
    private CellContent[] calculateRowDataWithTotal(
            List<Integer> magicNumbersList,
            String string,
            Integer totalMagicNumbers) {

        CellContent[] rowData = calculateRowData(magicNumbersList, string);
        rowData[rowData.length - 1] = new CellContent(
                totalMagicNumbers.toString());

        return rowData;
    }

    /**
     * Calculates the color according the severity
     * @param statusList
     * @param string
     * @return the array of the content of the cell
     * @throws IOException
     */
    private CellContent[] calculateRowDataWithSeverity(
            List<Integer> statusList,
            String string) throws IOException {

        CellContent[] rowData = calculateRowData(statusList, string);

        for (int index = 1; index < rowData.length - 1; index++) {
            if ((Boolean) getMagicNumbers().getFulfilledSeverity().get(index - 1).getValue()) {
                rowData[index].setStyle("font-weight: bold; color: green;");
            } else {
                rowData[index].setStyle("font-weight: bold; color: red;");
            }
        }

        return rowData;
    }

    /**
     * Returns a boolean that indicates whether the Report is editable for the
     * logged user
     * @return True if the user has edit privileges, otherwise returns False
     */
    public boolean isEditable() {

        ProjectRole role = null;
        // Set the Role to display the proper reports
        List<ProjectMembershipEntity> memberAccounts = loginSessionBean.getCurrentProject().getMemberAccounts();
        for (ProjectMembershipEntity projectMembershipEntity : memberAccounts) {
            if (projectMembershipEntity.getProjectId() == loginSessionBean.getCurrentProject().getId()
                    && projectMembershipEntity.getUserId() == loginSessionBean.getUser().getId()) {
                String currentRoleName = projectMembershipEntity.getRolEntity().getName();
                if (currentRoleName != null && ProjectRole.Manager.toString().equals(currentRoleName)) {
                    role = ProjectRole.Manager;
                    break;
                } else {
                    role = ProjectRole.TeamMember;
                    break;
                }
            }
        }

        // If role is null, set Team member by default
        if (role == null) {
            role = ProjectRole.TeamMember;
        }

        List<ProjectRole> editors = new ArrayList<ProjectRole>();
        editors.add(ProjectRole.Manager);

        if (editors.contains(role)) {
            editable = true;
        } else {
            editable = false;
        }

        return editable;
    }

    /**
     * Sets the editable property
     * @param editable
     */
    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public String getReportName() {
        // TODO: Retrieve the name form the plugin
        return "Magic Numbers";
    }

    ///////////////////////////////////////////////////////////////
    /**
     * Message when a spinner value is set
     * @param actionEvent
     */
    public void updateSeverities(ActionEvent actionEvent) {

        List<MagicNumberData> severities = new ArrayList<MagicNumberData>();
        List<Severity> spinners = getSeverityList();
        String bodyMessageInfo = "The severity criteria has been updated successfully";
        String bodyMessageError = "The severity criteria has not been updated successfully";

        System.out.println("Severities size " + issueTracker.getMagicNumbers().getSeverities().size() + "...");

        severities = issueTracker.getMagicNumbers().getSeverities();

        try {
            for (int i = 0; i < severities.size(); i++) {
                prop.setProperty(
                        severities.get(i).getKey(),
                        Integer.toString(spinners.get(i).getValue()));
            }

            prop.store(new FileOutputStream("criteria.properties"), null);

        } catch (IOException ex) {
            FacesMessage message =
                    new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    bodyMessageError + ": " + ex, "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

        FacesMessage message =
                new FacesMessage(
                FacesMessage.SEVERITY_INFO,
                bodyMessageInfo, "");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    /**
     * Gets the Severity count
     * @return the number of severities
     */
    public int getSeverityCount() {
        //IssueTracker issueTracker = null;
        try {
            //issueTracker = loginSessionBean.getCurrentIssueTracker();
            data = issueTracker.getMagicNumbers().getSeverities();

        } catch (Exception e) {
            if (issueTracker == null) {
                System.out.println("Issue Tracker is null");
            }
        }

        severityCount = data.size();
        return severityCount;
    }

    /**
     * Sets the number of severities
     * @param severityCount
     */
    public void setSeverityCount(int severityCount) {
        this.severityCount = severityCount;
    }

    /**
     * Gest the the severity Spinners
     * @return a list of spinners
     */
    public List<Severity> getSeverityList() {

        //IssueTracker issueTracker = null;
        List<MagicNumberData> list = new ArrayList<MagicNumberData>();

        try {
            //issueTracker = loginSessionBean.getCurrentIssueTracker();
            list = issueTracker.getMagicNumbers().getSeverities();

        } catch (Exception e) {
            if (issueTracker == null) {
                System.out.println("Issue Tracker is null, Description: " + e);
            }
        }

        if (severityList == null) {

            severityList = new ArrayList<Severity>();
            for (int i = 0; i < list.size(); i++) {
                severityList.add(
                        new Severity(list.get(i).getKey(),
                        (Integer) list.get(i).getValue()));
            }
        }

        return severityList;
    }

    /**
     * Sets the Severities
     * @param list of severities
     */
    public void setSeveritySpinners(List<Severity> list) {
        severityList = list;
    }

    /**
     * Retrieves the criteria list from the Data Base
     * @param severitiesInitialList
     * @return List of Magic Numbers Data that represents the Criteria
     * @throws IOException
     */
    private List<MagicNumberData> readCriteria(
            List<MagicNumberData> severitiesInitialList) throws IOException {

        boolean dataIsSet = false;
        boolean isUpdate = true;
        Properties properties = new Properties();
        List<MagicNumberData> severitiesFullList = new ArrayList<MagicNumberData>();

        File file = new File("criteria.properties");

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        // Read the file
        try {
            fis = new FileInputStream(file);

            // Here BufferedInputStream is added for fast reading.
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            // dis.available() returns 0 if the file does not have more lines.
            while (dis.available() != 0) {

                // this statement reads the line from the file and print it to
                // the console.
                // System.out.println(dis.readLine());
                dis.readLine();
            }

            // dispose all the resources after using them.
            fis.close();
            bis.close();
            dis.close();

        } catch (FileNotFoundException e) {
            System.out.print(e);
        } catch (IOException e) {
            System.out.print(e);
        }

        if (file.exists()) {
            dataIsSet = true;
            properties.load(new FileInputStream("criteria.properties"));

            for (int i = 0; i < severitiesInitialList.size(); i++) {

                if (!properties.containsKey(severitiesInitialList.get(i).getKey())) {
                    isUpdate = false;
                }
            }

            if (dataIsSet && isUpdate) {

                for (int i = 0; i < severitiesInitialList.size(); i++) {

                    MagicNumberDataImplementation magic = new MagicNumberDataImplementation();

                    magic.setKey(severitiesInitialList.get(i).getKey());
                    magic.setValue((Object) Integer.parseInt(properties.getProperty(severitiesInitialList.get(i).getKey())));
                    //                System.out.println("KEY: " + severitiesInitialList.get(i).getKey());
                    //                System.out.println("VALUE: " + (Object) Integer.parseInt(properties.getProperty(severitiesInitialList.get(i).getKey())));

                    severitiesFullList.add(magic);
                    properties.setProperty(severitiesFullList.get(i).getKey(), severitiesFullList.get(i).getValue().toString());
                }
            }

            return severitiesFullList;

        } else {

            if (dataIsSet) {
                properties.clear();
            }
            for (int i = 0; i < severitiesInitialList.size(); i++) {

                MagicNumberDataImplementation magic = new MagicNumberDataImplementation();
                magic.setKey(severitiesInitialList.get(i).getKey());
                magic.setValue(Integer.parseInt(severitiesInitialList.get(i).getValue().toString()));
                severitiesFullList.add(magic);

                properties.setProperty(severitiesFullList.get(i).getKey(), severitiesFullList.get(i).getValue().toString());
            }

            properties.store(new FileOutputStream("criteria.properties"), null);

            // Read the file

            System.out.println("File was created successfuly");
            try {
                fis = new FileInputStream(file);

                // Here BufferedInputStream is added for fast reading.
                bis = new BufferedInputStream(fis);
                dis = new DataInputStream(bis);

                // dis.available() returns 0 if the file does not have more lines.
                while (dis.available() != 0) {

                    // this statement reads the line from the file and print it to
                    // the console.
                    System.out.println(dis.readLine());
                }

                // dispose all the resources after using them.
                fis.close();
                bis.close();
                dis.close();

            } catch (FileNotFoundException e) {
                System.out.print(e);
            } catch (IOException e) {
                System.out.print(e);
            }

            return severitiesFullList;
        }
    }

    private void isRetrievedFromIssues() {

        issueTracker = loginSessionBean.getCurrentIssueTracker();

        if (issueTracker.getMagicNumbers() == null) {
            magicNumbrersFromIssues = true;
        } else {
            magicNumbrersFromIssues = false;
        }
    }
}
