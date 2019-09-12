import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class dateTime {

    public dateTime() {
    }

    /*
     * To convert date and time of one format to another
     */
    String convert(String in) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = inputFormat.parse(in);
        SimpleDateFormat output = new SimpleDateFormat("d");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM yyyy hh:mm aa");
        return output.format(date) + suffix(in) + outputFormat.format(date);
    }

    /*
     * To get the suffix according to the date
     */
    private String suffix(String d) {
        String[] arr = d.split(Pattern.quote("/"));
        int n = Integer.parseInt(arr[0]);
        if (n >= 11 && n <= 13) {
            return "th ";
        }
        switch (n % 10) {
            case 1: return "st ";
            case 2: return "nd ";
            case 3: return "rd ";
            default: return "th ";
        }
    }

    /*
     * Check if input date format is valid
     */
    static boolean isValidFormat(String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date != null;
    }
}