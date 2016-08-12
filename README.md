Card Validator
===================
Generic helper for Card number validation.

[![Build Status](https://travis-ci.org/auro007/CardValidator.svg?branch=master)](https://travis-ci.org/auro007/CardValidator)


Usage:
-------------

Add & import the library to your project.

Use **isCardValid** method to run a validation on the Card & the **getCardBrand** method to get the brand of the Card

Both the methods are static methods, so simply invoke them with their Class name.


Validate the Card:
-------------------------

Pass the card number as `String` to the method & it returns a `Boolean` value on the basis of the input

`CardValidation.isCardValid("4242424242424242");`


Get the Card brand
-------------------------
Pass the card number as `String` to the method. The method returns the name of the Card.
In case of Card brand not found, the method returns `null`

