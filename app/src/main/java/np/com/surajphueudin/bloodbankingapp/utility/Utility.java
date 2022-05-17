package np.com.surajphueudin.bloodbankingapp.utility;

public class Utility {

    public static String formatDate(String date) {
        return date.substring(0, 10);
    }

    ;

    public static String toBloodGroupShortcut(String value) {
        String output = "";

        switch (value) {
            case "aPositive":
                output = "A+";
                break;
            case "bPositive":
                output = "B+";
                break;
            case "abPositive":
                output = "AB+";
                break;
            case "oPositive":
                output = "O+";
                break;
            case "aNegative":
                output = "A-";
                break;
            case "bNegative":
                output = "B-";
                break;
            case "abNegative":
                output = "AB-";
                break;
            case "oNegative":
                output = "O-";
                break;
            default:
                break;

        }
        return output;
    }


}
