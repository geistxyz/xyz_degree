package edu.umss.devportal.plugins.xplanner.reports.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtility {

    public static java.util.LinkedList searchBetweenDates(java.util.Date startDate, java.util.Date endDate) {
        java.util.Date begin = new Date(startDate.getTime());
        java.util.LinkedList list = new java.util.LinkedList();
        list.add(new Date(begin.getTime()));
        while(begin.compareTo(endDate)<0){
            begin = new Date(begin.getTime() + 86400000);
            list.add(new Date(begin.getTime()));
        }
        return list;
    }
     /**
     * <p>Checks if two calendars represent the same day ignoring time.</p>
     * @param cal1  the first calendar, not altered, not null
     * @param cal2  the second calendar, not altered, not null
     * @return true if they represent the same day
     * @throws IllegalArgumentException if either calendar is <code>null</code>
     */
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }

//    public static void main(String[] args) throws Exception   {
//        java.util.LinkedList hitList = searchBetweenDates(
//            new java.text.SimpleDateFormat("dd/MM/yyyy").parse("10/10/2004"),
//            new java.text.SimpleDateFormat("dd/MM/yyyy").parse("14/10/2004"));
//        String[] comboDates = new String[hitList.size()];
//        for(int i=0; i<hitList.size(); i++)
//            comboDates[i] = new java.text.SimpleDateFormat("dd/MM/yyyy - E").format(((java.util.Date)hitList.get(i)));
//        for(int i=0; i<comboDates.length; i++)
//            System.out.println(comboDates[i]);
//    }

}