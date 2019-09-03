import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class dateTime {

    public dateTime() {
    }

    String convert(String in) throws ParseException {
        SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy HHmm");
        Date date = inputFormat.parse(in);
        SimpleDateFormat output = new SimpleDateFormat("d");
        SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM yyyy hh:mm aa");
        return output.format(date) + suffix(in) + outputFormat.format(date);
    }

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
