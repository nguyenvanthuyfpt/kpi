package com.dao.mail;


import com.dao.DCore;

import com.inf.IFields;
import com.inf.ITables;

public class DSqlMail extends DCore implements ITables,IFields 
{
    //MAIL_FILTER category  in here
      public final String SQL_MAIL_FILTER_TYPE_SELECT_ALL = SELECT + STAR + FROM + TABLE_MAIL_FILTER + WHERE + MAIL_FILTER_USER_ID + EQUAL + QUESTION + ORDER_BY + MAIL_FILTER_ID;
      public final String SQL_MAIL_FILTER_TYPE_SELECT_SINGLE_ROW = SELECT + STAR + FROM + TABLE_MAIL_FILTER + WHERE + MAIL_FILTER_ID + EQUAL + QUESTION;
      public final String SQL_MAIL_FILTER_TYPE_ADD_NEW = INSERT_INTO + TABLE_MAIL_FILTER + FIELDS(MAIL_FILTER_ALL_FIELDS,true)+ VALUES(MAIL_FILTER_ALL_FIELDS.length);
      public final String SQL_MAIL_FILTER_TYPE_UPDATE = UPDATE + TABLE_MAIL_FILTER + SET + SETS(MAIL_FILTER_ALL_FIELDS)+ WHERE + MAIL_FILTER_ID + EQUAL + QUESTION;
      public final String SQL_MAIL_FILTER_TYPE_CHECK_NAME = SELECT + STAR + FROM + TABLE_MAIL_FILTER + WHERE + MAIL_FILTER_FROM + EQUAL + QUESTION;
      public final String SQL_MAIL_FILTER_TYPE_SELECT_BY_ID = SELECT + STAR + FROM + TABLE_MAIL_FILTER + WHERE + MAIL_FILTER_ID + EQUAL + QUESTION;
}