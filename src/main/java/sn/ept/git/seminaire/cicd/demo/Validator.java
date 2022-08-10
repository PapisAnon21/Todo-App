package sn.ept.git.seminaire.cicd.demo;

import sn.ept.git.seminaire.cicd.demo.exception.BadPhoneException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Validator {

    public static final String ORANGE = "ORANGE";
    public static final String FREE = "FREE";
    public static final String EXPRESSO = "EXPRESSO";
    public static final String PROMOBILE = "PROMOBILE";

    public static final String REGEX="^(\\+221|00221)?(70|75|76|77|78)[0-9]{7}$";

    private Validator(){
        super();
    }

    public static boolean validateSnMobilePhone(String phone) {
       return false;
    }


    public static String getSnMobileOperator(String phone) throws BadPhoneException {
        String result;
        Pattern r = Pattern.compile(REGEX);
        Matcher matcher = r.matcher(phone);
        if (!matcher.matches()) {
            throw new BadPhoneException("Bad phone " + phone);
        }
        String operator = matcher.group(2);

        switch (operator) {
            case "77":
            case "78":
                result = ORANGE;
                break;
            case "76":
                result = FREE;
                break;
            case "70":
                result = EXPRESSO;
                break;
            default:
                result = PROMOBILE;
        }
        return result;
    }

}
