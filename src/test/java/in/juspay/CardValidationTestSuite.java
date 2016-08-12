package in.juspay;

import org.junit.BeforeClass;
import org.junit.Test;


import static junit.framework.TestCase.*;

/**
 * Created by Arijit Banerjee on 12/8/16.
 * @author Arijit Banerjee
 * @since 1.0
 * Test Suite for isCardValid method of CardValidation {@link in.juspay.CardValidation}
 */


public class CardValidationTestSuite {


    @Test(expected = IllegalArgumentException.class)
    public void testIllegalArgumentException(){

        CardValidation.isCardValid(""); //For empty String

        CardValidation.isCardValid(" "); //For space in the input

        CardValidation.isCardValid("kshfskfhsf433265723    *&^$$^"); //For invalid input
    }

    @Test
    public void testForVisaCard(){

        assertTrue(CardValidation.isCardValid("4242424242424242")); //For valid VISA Card Number

        assertFalse(CardValidation.isCardValid("12121212121212121")); //For invalid Card Number

    }

    @Test
    public void testForMasterCard(){

        assertTrue(CardValidation.isCardValid("5151515151515151")); // For valid Master Card Number

        assertTrue(CardValidation.isCardValid("5251515151515151")); // For valid Master Card Number

        assertTrue(CardValidation.isCardValid("5351515151515151")); // For valid Master Card Number

        assertTrue(CardValidation.isCardValid("5451515151515151")); // For valid Master Card Number

        assertTrue(CardValidation.isCardValid("5551515151515151")); // For valid Master Card Number

        assertFalse(CardValidation.isCardValid("5050505050505005")); //For invalid Card Number

        assertFalse(CardValidation.isCardValid("51")); // For invalid length

    }

    @Test
    public void testForDiscoverCard(){

        assertTrue(CardValidation.isCardValid("6011601160116011")); // For valid Discover Card Number

        assertTrue(CardValidation.isCardValid("6565656565656565")); // For valid Discover Card Number

        assertTrue(CardValidation.isCardValid("6445656565656565")); // For valid Discover Card Number

        assertTrue(CardValidation.isCardValid("6495656565656565")); // For valid Discover Card Number

        assertTrue(CardValidation.isCardValid("6225656565656565")); // For valid Discover Card Number

        assertFalse(CardValidation.isCardValid("6363636363636363")); // For invalid Card Number

        assertFalse(CardValidation.isCardValid("6363636363636363676767676")); // For invalid length

        assertFalse(CardValidation.isCardValid("656")); // For invalid length
    }

    @Test(expected = IllegalArgumentException.class)
    public void testForRuPayCard(){

        assertTrue(CardValidation.isCardValid("5085015085015085")); // For valid RuPay Card Number

        assertFalse(CardValidation.isCardValid("1234123412341234")); // For invalid Card Number

        assertFalse(CardValidation.isCardValid("508501539738637563875635")); //For invalid length

        CardValidation.isCardValid("50850"); // For length < 6
    }

    @Test
    public void testForAMEXCard(){

        assertTrue(CardValidation.isCardValid("343434343434341")); // For valid American Express Card Number

        assertTrue(CardValidation.isCardValid("373434343434341")); // For valid American Express Card Number

        assertFalse(CardValidation.isCardValid("383434343434341")); // For invalid Card Number

        assertFalse(CardValidation.isCardValid("343434")); // For invalid length
    }

    @Test
    public void testForMaestroCard(){

        assertTrue(CardValidation.isCardValid("5046811111111111")); //For valid Maestro Card Number of 16 digit-length

        assertTrue(CardValidation.isCardValid("5046811111111111111")); //For valid Maestro Card Number of 19 digit-length

        assertFalse(CardValidation.isCardValid("1111111111111111111")); //For invalid Card Number and invalid length

        assertFalse(CardValidation.isCardValid("6304")); //For invalid length
    }

    @Test
    public void testForDinersCard(){

        assertTrue(CardValidation.isCardValid("38111111111111")); //For valid Diners Card Number

        assertTrue(CardValidation.isCardValid("30111111111111")); //For valid Diners Card Number

        assertFalse(CardValidation.isCardValid("23123123123121")); //For invalid Card Number

        assertFalse(CardValidation.isCardValid("121212121212121212121212")); //For invalid length
    }

    @Test
    public void testForJCBCard(){

        assertTrue(CardValidation.isCardValid("356565656565656")); //For valid JCB Card Number of 15-digit length

        assertTrue(CardValidation.isCardValid("1800656565656565")); //For valid JCB Card Number of 16-digit length

        assertFalse(CardValidation.isCardValid("213199999999999999")); //For invalid length
    }

    @Test
    public void getCardBrandTest(){

        assertNull(CardValidation.getCardBrand("111111111111")); // Invalid Card

        assertEquals(CardValidation.getCardBrand("4242424242424242"),"VISA"); // VISA

        assertEquals(CardValidation.getCardBrand("5551515151515151"),"MASTERCARD"); //MASTERCARD

        assertEquals(CardValidation.getCardBrand("6225656565656565"),"DISCOVER"); //DISCOVER

        assertEquals(CardValidation.getCardBrand("5085015085015085"),"RUPAY"); //RUPAY

        assertEquals(CardValidation.getCardBrand("37343434343434"),"AMEX"); //AMEX

        assertEquals(CardValidation.getCardBrand("5046811111111111111"),"MAESTRO"); //MAESTRO

        assertEquals(CardValidation.getCardBrand("30111111111111"),"DINERS"); //DINERS

        assertEquals(CardValidation.getCardBrand("1800656565656565"),"JCB"); //JCB
    }

}
