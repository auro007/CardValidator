package in.juspay;


import java.util.regex.Pattern;

/**
 * Created by Arijit Banerjee on 11/8/16.
 * @author Arijit Banerjee
 * @since 1.0
 */

public class CardValidation {

    private static final int[][] rupayRange = {
                                                {508500,508999},
                                                {606985,607384},
                                                {607385,607484},
                                                {607485,607984},
                                                {608001,608100},
                                                {608101,608200},
                                                {608201,608300},
                                                {608301,608350},
                                                {608351,608500},
                                                {652150,652849},
                                                {652850,653049},
                                                {653050,653149}

                                                };
    private static final String maestroPattern = "^(5018|5081|5044|504681|5020|5038|603845|603123|6304|6759|676[1-3]|6220|504834|504817|504645)\\d*";
    private static final String diners = "^36\\d*|38\\d*|30[0-5]\\d*";
    private static final String discover = "^6011\\d*|65\\d*|64[4-9]\\d*|622\\d*";


    /**
     * Call this method the get the Card Brand. Takes in a String parameter & returns a String object
     * @param cardIsin: the parameter that contains the Card number
     * @return brand: The brand name of the Card as a String object
     */
    public static String getCardBrand(final String cardIsin){

        String brand = null;

        /**
         * Will throw an IllegalArgumentException in case of input containing
         * letters, special characters, blank space & empty String
         */

        if (!cardIsin.matches("[0-9]+")){
            throw new IllegalArgumentException("INVALID ARGUMENT! Should contain a valid Card Number");
        }


        if (cardIsin.substring(0,2).matches("51|52|53|54|55"))
            brand = "MasterCard".toUpperCase();
        else if (cardIsin.startsWith("4"))
            brand = "Visa".toUpperCase();
        else if (cardIsin.startsWith("34") || cardIsin.startsWith("37"))
            brand = "Amex".toUpperCase();
        else if (Pattern.matches(maestroPattern,cardIsin))
            brand = "Maestro".toUpperCase();
        else if (Pattern.matches(diners, cardIsin))
            brand = "Diners".toUpperCase();
        else if (Pattern.matches(discover,cardIsin))
            brand = "Discover".toUpperCase();
        else if (inRupayRange(cardIsin))
            brand = "Rupay".toUpperCase();
        else if (cardIsin.startsWith("35")|| cardIsin.startsWith("2131") || cardIsin.startsWith("1800"))
            brand = "JCB";
        return brand;
    }

    /**
     * Call this method to check the validity of the Card. Takes in a String parameter & returns a boolean value
     * @param cardNumber: A String object containing the number of the card
     * @return boolean value
     */
    public static boolean isCardValid(final String cardNumber) {

        String cardBrand = getCardBrand(cardNumber);

        if(cardBrand != null) {
            if (cardBrand.equalsIgnoreCase("VISA") || cardBrand.equalsIgnoreCase("MASTERCARD") ||
                    cardBrand.equalsIgnoreCase("DISCOVER") || cardBrand.equalsIgnoreCase("RUPAY")) {
                return cardNumber.length() == 16;
            } else if (cardBrand.equalsIgnoreCase("AMEX")) {
                return cardNumber.length() == 15;
            } else if (cardBrand.equalsIgnoreCase("MAESTRO")) {
                return cardNumber.length() == 16 || cardNumber.length() == 19;
            } else if (cardBrand.equalsIgnoreCase("DINERS")) {
                return cardNumber.length() == 14;
            } else if (cardBrand.equalsIgnoreCase("JCB")) {
                return cardNumber.length() == 15 || cardNumber.length() == 16;
            }
        }
        return false;
    }


    /**
     * Invoked by the method getCardBrand to check if the given card number is of RuPay brand
     * Takes in a String parameter & returns a boolean object
     * @param isin: Contains the Card ISIN / BIN
     * @return boolean value
     */
    public static boolean inRupayRange(final String isin){

        if(!isin.matches("[0-9]+") || isin.length()<6)
            throw new IllegalArgumentException("INVALID ARGUMENT! Should contain a valid Card Number");

       long intIsin = Long.parseLong(isin.substring(0,6));

        for (int row = 0; row < CardValidation.rupayRange.length; row++)
        {
            try {
                if (intIsin >= CardValidation.rupayRange[row][0] && intIsin <= CardValidation.rupayRange[row][1])
                    return true;
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public static void main(String... args) {

        System.out.println(CardValidation.inRupayRange("50850"));

    }
}
