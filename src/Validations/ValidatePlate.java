package Validations;

import java.util.regex.*;

public class ValidatePlate {
    public boolean isValid(String s)
    {
        // The given argument to compile() method
        // is regular expression.
        // 1) Begins with 3 uppercase letters
        // 2) Then "-".
        // 3) Then 4 digits 0-9.
        Pattern p = Pattern.compile("[A-Z]{3}-[0-9]{4}");

        // Pattern class contains matcher() method
        // to find matching between given plate
        // and regular expression
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

}
