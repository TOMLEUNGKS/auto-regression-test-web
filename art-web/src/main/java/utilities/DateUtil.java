package utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    
    public static String getFormattedDate(Date targetDate, String formatPattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatPattern);
        return dateFormat.format(targetDate);
    }

    public static Date getTodayDate() {
        return Calendar.getInstance().getTime();
    }

    public static Date getCalculatedDate(Date starDate, int calValue) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(starDate);
        calendar.add(Calendar.DATE, calValue);
        return calendar.getTime();
    }

    public static String getTransDate(String dob){
        StringBuffer newDob = new StringBuffer();
        String[] transDob = dob.split("-");
        String tmp = transDob[2].trim();
        newDob.append(tmp+"-");
        switch(transDob[1].trim()){
            case "01":
                newDob.append("Jan" + "-" + transDob[0].trim());
                break;
            case "02":
                newDob.append("Feb" + "-" + transDob[0].trim());
                break;
            case "03":
                newDob.append("Mar" + "-" + transDob[0].trim());
                break;
            case "04":
                newDob.append("Apr" + "-" + transDob[0].trim());
                break;            
            case "05":
                newDob.append("May" + "-" + transDob[0].trim());
                break;            
            case "06":
                newDob.append("Jun" + "-" + transDob[0].trim());
                break;            
            case "07":
                newDob.append("Jul" + "-" + transDob[0].trim());
                break;            
            case "08":
                newDob.append("Aug" + "-" + transDob[0].trim());
                break;            
            case "09":
                newDob.append("Sep" + "-" + transDob[0].trim());
                break;            
            case "10":
                newDob.append("Oct" + "-" + transDob[0].trim());
                break;            
            case "11":
                newDob.append("Nov" + "-" + transDob[0].trim());
                break;            
            case "12":
                newDob.append("Dec" + "-" + transDob[0].trim());
                break;
            }
        return (newDob.toString());
    }
}
