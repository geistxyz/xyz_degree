/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umss.devportal.reports.backbeans;

import java.util.List;
import javax.ejb.Stateless;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;

/**
 * Class that Generates an HTML table dynamically from a dynamic list
 * @author Edson
 */

@Stateless
@ManagedBean (name="dynamicBackingBean")
public class DynamicBackingBean extends ReportBackingBean{

    private static List<List<String>> dynamicList;
    private static String[] dynamicHeaders;
    private HtmlPanelGroup dynamicDataTableGroup;

    public DynamicBackingBean() {
    }

    /**
     * Populates the HtmlDataTable iterating over the list
     */
    private void populateDynamicDataTable() {

        /**
         * Create <h:dataTable value="#{dynamicBackingBean.dynamicList}" var="dynamicItem">.
         * The equivalent process of generate a table using a backing bean
         * is the following
         */
        HtmlDataTable dynamicDataTable = new HtmlDataTable();
        dynamicDataTable.setValueExpression("value", createValueExpression("#{dynamicBackingBean.dynamicList}", List.class));
        dynamicDataTable.setVar("dynamicItem");
        dynamicDataTable.setBorder(1);

        // Iterate over columns.
        for (int i = 0; i < dynamicList.get(0).size(); i++) {

            // Create <h:column>.
            HtmlColumn column = new HtmlColumn();
            dynamicDataTable.getChildren().add(column);

            // Create <h:outputText value="dynamicHeaders[i]"> for <f:facet name="header"> of column.
            HtmlOutputText header = new HtmlOutputText();
            header.setValue(dynamicHeaders[i]);
            column.setHeader(header);

            // Create <h:outputText value="#{dynamicItem[" + i + "]}"> for the body of column.
            HtmlOutputText output = new HtmlOutputText();
            output.setValueExpression("value", createValueExpression("#{dynamicItem[" + i + "]}", String.class));
            column.getChildren().add(output);
        }

        // Add the datatable to <h:panelGroup binding="#{dynamicBackingBean.dynamicDataTableGroup}">.
        dynamicDataTableGroup = new HtmlPanelGroup();
        dynamicDataTableGroup.getChildren().add(dynamicDataTable);
    }

    /**
     * Gets the generated panel
     * @return a created HtmlPanelGroup
     */
    public HtmlPanelGroup getDynamicDataTableGroup() {

        if (dynamicDataTableGroup == null) {
            populateDynamicDataTable();
        }

        return dynamicDataTableGroup;
    }

    /**
     * Gets the dynamic list to populate in the table
     * @return List<List<String>>
     */
    public List<List<String>> getDynamicList() {
        return dynamicList;
    }

    /**
     * Sets the Table Group
     * @param dynamicDataTableGroup
     */
    public void setDynamicDataTableGroup(HtmlPanelGroup dynamicDataTableGroup) {
        this.dynamicDataTableGroup = dynamicDataTableGroup;
    }

    /**
     * Sets the dynamic list
     * @param generatedDynamicList
     */
    public void setDynamicList(List<List<String>> generatedDynamicList) {
        dynamicList = generatedDynamicList;
    }

    /**
     * Sets the Headers of the table
     * @param generatedDynamicHeaders
     */
    public void setDynamicHeaders(String[] generatedDynamicHeaders) {
        dynamicHeaders = generatedDynamicHeaders;
    }

    /**
     * Evaluates the expression in order to populate each row of a the given column
     * @param valueExpression
     * @param valueType
     * @return
     */
    private ValueExpression createValueExpression(
            String valueExpression,
            Class<?> valueType) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getApplication().getExpressionFactory().
                createValueExpression(
                facesContext.getELContext(),
                valueExpression,
                valueType);
    }
}
