package com.util;

import java.sql.ResultSet;

public class DaoUtil {
    public static int get_rc(ResultSet rs) throws Exception{
        rs.last();
        int  size=rs.getRow();
        rs.beforeFirst();
        return size;
    }
    
    public static String print_error(Exception ex) {
        StringBuffer sb = new StringBuffer(ex.getMessage());
        sb.append("\r\nThông tin l?i chi ti?t :");
        StackTraceElement[] ste_arr = ex.getStackTrace();
        String msg;
        for (int i = 0; i < ste_arr.length; i++) {
            msg = ste_arr[i].toString();
            if (msg.startsWith("com.action.disabilitypeople."))
                sb.append("\r\n" +
                        ste_arr[i].toString());
        }
        return sb.toString();
    }
}
