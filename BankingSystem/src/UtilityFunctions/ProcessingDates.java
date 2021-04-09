package UtilityFunctions;

abstract public class ProcessingDates {
    public static Integer StringIntMonth(String monthS) {
        Integer monthI = 0;
        switch (monthS) {
            case "Jan":
                monthI = 1;
                break;
            case "Feb":
                monthI = 2;
                break;
            case "Mar":
                monthI = 3;
                break;
            case "Apr":
                monthI = 4;
                break;
            case "May":
                monthI = 5;
                break;
            case "Jun":
                monthI = 6;
                break;
            case "Jul":
                monthI = 7;
                break;
            case "Aug":
                monthI = 8;
                break;
            case "Sep":
                monthI = 9;
                break;
            case "Oct":
                monthI = 10;
                break;
            case "Nov":
                monthI = 11;
                break;
            case "Dec":
                monthI = 12;
                break;
        }
        return monthI;
    }

    public static String IntStringMonth(Integer monthI) {
        String monthS = "";
        switch (monthI) {
            case 1:
                monthS = "Jan";
                break;
            case 2:
                monthS = "Feb";
                break;
            case 3:
                monthS = "Mar";
                break;
            case 4:
                monthS = "Apr";
                break;
            case 5:
                monthS = "May";
                break;
            case 6:
                monthS = "Jun";
                break;
            case 7:
                monthS = "Jul";
                break;
            case 8:
                monthS = "Aug";
                break;
            case 9:
                monthS = "Sep";
                break;
            case 10:
                monthS = "Oct";
                break;
            case 11:
                monthS = "Nov";
                break;
            case 12:
                monthS = "Dec";
                break;
        }
        return monthS;
    }

    public static String computeFinalDay(String currentDate, String period) {
        String [] periodSplit = period.split(" ");  // 3 years, 6 months etc.
        String [] dateSplit = currentDate.split("-"); // 24-Nov-2020

        if(periodSplit[1] == "years") {
            Integer year = Integer.parseInt(dateSplit[2]);
            Integer periodYear = Integer.parseInt(periodSplit[0]);
            year += periodYear;
            return dateSplit[0] + "-" + dateSplit[1] + "-" + year.toString();
        }

        else {
            Integer periodMonth = Integer.parseInt(periodSplit[1]);
            Integer month = StringIntMonth(dateSplit[1]);
            month += periodMonth;
            String monthS = "";
            if(month > 12) {
                monthS = IntStringMonth(month - 12);
                Integer year = Integer.parseInt(dateSplit[2]);
                year++;
                return dateSplit[0] + "-" + monthS + "-" + year.toString();
            }
            else {
                monthS = IntStringMonth(month);
                return dateSplit[0] + "-" + monthS + "-" + dateSplit[2];
            }
        }
    }
}
