package com.inf.agenda;



public interface IFieldsAgenda {
     

    
    //calendar
    public final String CALENDA_ID= "CALENDA_ID";
    public final String CALENDA_WHAT= "WHATS";
    public final String CALENDA_WHERE= "WHERES";
    public final String CALENDA_TIMEEVENT= "TIMEEVENT";
    public final String CALENDA_TIMECREATE= "TIMECREATE";
    public final String CALENDA_FROM_TIME= "FROM_TIME";
    public final String CALENDA_TO_TIME= "TO_TIME";
    public final String CALENDA_USER_ID= "USER_ID";
    public final String CALENDA_TYPE= "TYPE";
    public final String CALENDA_DEPARTMENT_ID= "DEPARTMENT_ID";
    public final String[] CALENDAR_ALL_FIELDS ={CALENDA_WHAT,CALENDA_WHERE,CALENDA_TIMEEVENT,CALENDA_TIMECREATE,CALENDA_FROM_TIME,CALENDA_TO_TIME,CALENDA_USER_ID,CALENDA_TYPE,CALENDA_DEPARTMENT_ID}; 
    
    //calendarMEETING
       public final String CALENDAR_MEETING_MEETING_ID= "CALENDAR_MEETING_ID";
       public final String CALENDAR_MEETING_BASE_EMP= "BASE_EMP";
       public final String CALENDAR_MEETING_CONTENT= "CONTENT";
       public final String CALENDAR_MEETING_DEVICE= "DEVICE";
       public final String CALENDAR_MEETING_TIMEEVENT= "TIMEEVENT";
       public final String CALENDAR_MEETING_TIMECREATE= "TIMECREATE";
       public final String CALENDAR_MEETING_FROM_TIME= "FROM_TIME";
       public final String CALENDAR_MEETING_TO_TIME= "TO_TIME";
       public final String CALENDAR_MEETING_USER_ID= "USER_ID";
       public final String[] CALENDAR_MEETINGR_ALL_FIELDS ={CALENDAR_MEETING_BASE_EMP,CALENDAR_MEETING_CONTENT,CALENDAR_MEETING_DEVICE,CALENDAR_MEETING_TIMEEVENT,CALENDAR_MEETING_TIMECREATE,CALENDAR_MEETING_FROM_TIME,CALENDAR_MEETING_TO_TIME,CALENDAR_MEETING_USER_ID}; 
       
    //CALENDAR XE
     public final String CALENDAR_XE_MEETING_ID= "CALENDAR_XE_ID";
     public final String CALENDAR_XE_BASE_EMP= "BASE_EMP";
     public final String CALENDAR_XE_CONTENT= "CONTENT";
     public final String CALENDAR_XE_DEVICE= "DEVICE";
     public final String CALENDAR_XE_TIMEEVENT= "TIMEEVENT";
     public final String CALENDAR_XE_TIMECREATE= "TIMECREATE";
     public final String CALENDAR_XE_FROM_TIME= "FROM_TIME";
     public final String CALENDAR_XE_TO_TIME= "TO_TIME";
     public final String CALENDAR_XE_USER_ID= "USER_ID";
     public final String[] CALENDAR_XER_ALL_FIELDS ={CALENDAR_XE_BASE_EMP,CALENDAR_XE_CONTENT,CALENDAR_XE_DEVICE,CALENDAR_XE_TIMEEVENT,CALENDAR_XE_TIMECREATE,CALENDAR_XE_FROM_TIME,CALENDAR_XE_TO_TIME,CALENDAR_XE_USER_ID}; 

}