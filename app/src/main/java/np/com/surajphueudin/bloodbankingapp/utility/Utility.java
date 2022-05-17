package np.com.surajphueudin.bloodbankingapp.utility;

public class Utility {
    private static String[] BLOOD_GROUPS
            = {
            "A+",
            "A-",
            "B+",
            "B-",
            "AB+",
            "AB-",
            "O+",
            "O-"};


    private static String[] NEPAL_DISTRICTS
            = {
            "achham",
            "arghakhanchi",
            "baglung",
            "baitadi",
            "bajhang",
            "bajura",
            "banke",
            "bara",
            "bardiya",
            "bhaktapur",
            "bhojpur",
            "chitwan",
            "dadeldhura",
            "dailekh",
            "dang deukhuri",
            "darchula",
            "dhading",
            "dhankuta",
            "dhanusa",
            "dholkha",
            "dolpa",
            "doti",
            "gorkha",
            "gulmi",
            "humla",
            "ilam",
            "jajarkot",
            "jhapa",
            "jumla",
            "kailali",
            "kalikot",
            "kanchanpur",
            "kapilvastu",
            "kaski",
            "kathmandu",
            "kavrepalanchok",
            "khotang",
            "lalitpur",
            "lamjung",
            "mahottari",
            "makwanpur",
            "manang",
            "morang",
            "mugu",
            "mustang",
            "myagdi",
            "nawalparasi",
            "nuwakot",
            "okhaldhunga",
            "palpa",
            "panchthar",
            "parbat",
            "parsa",
            "pyuthan",
            "ramechhap",
            "rasuwa",
            "rautahat",
            "rolpa",
            "rukum",
            "rupandehi",
            "salyan",
            "sankhuwasabha",
            "saptari",
            "sarlahi",
            "sindhuli",
            "sindhupalchok",
            "siraha",
            "solukhumbu",
            "sunsari",
            "surkhet",
            "syangja",
            "tanahu",
            "taplejung",
            "terhathum",
            "udayapur"
    };

    public static String[] getNepalDistricts() {
        return NEPAL_DISTRICTS;
    }

    ;

    public static String[] getBloodGroups() {
        return BLOOD_GROUPS;
    }

    ;

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

    public static String toReverseBloodGroupShortcut(String value) {
        String output = "";

        switch (value) {
            case "A+":
                output = "aPositive";
                break;
            case "B+":
                output = "bPositive";
                break;
            case "AB+":
                output = "abPositive";
                break;
            case "O+":
                output = "oPositive";
                break;
            case "A-":
                output = "aNegative";
                break;
            case "B-":
                output = "bNegative";
                break;
            case "AB-":
                output = "abNegative";
                break;
            case "O-":
                output = "oNegative";
                break;
            default:
                break;

        }
        return output;
    }



}
