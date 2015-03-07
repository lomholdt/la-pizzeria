# LaPizzeria - Web Shop
Interactive Web Services with Java and XML 2015
<img src="http://i.imgur.com/IhEnpDL.png">

## Usage
We have created one test user for administrative purposes with the following credentials:
```
user: admin@admin.com
pass: abcdEFG123#
```
The application is tested on Apache Tomcat 8.0. All external dependencies have been added locally to the lib archive. Please note that the email containing the pin for user activation often get's caught by spam filters. 

## Use Cases
* **Register:** The customer should be able to register at the La Pizzeria web
shop and create a profile with valid email (as userid), password, name,
and delivery info: address, zip code, and phone number.
* **Browse:** The customer should be able to log in and browse a list of
pizzas with description and prices for each. If a lot of pizzas are available,
the page should be paginated (with, say, 10 pizzas per page, browseable
page-by-page). The list should be sorted by either name or price.
* **Basket:** The customer should be able to add/remove pizzas to/from a
shopping basket. It should be able to see the quantities and price per
product along with the total price of the pizzas in the basket.
* **Checkout:** The customer should be able to check out; i.e., verify the
contents of the basket and confirm the purchase.
* **Admin:** The administrator should be able to log in, add and remove
pizzas kinds from the list of pizzas available in the web shop. 

## Input Validation
* The customer info entered during registration should be validated
(i.e., it should be checked that):
* **email:** is a valid email address which is done by sending a 4-digit
number to the email address along with a link to a page where the key
has to be entered before registration is completed (if you are unable to
send emails from your Web server, you can "simulate" sending out an
email by just printing out the contents of the email on the screen inside a
`<pre/>`-element);
* **password:** should contain at least six characters, at least one digit, at
least one letter character, and at least one non-digit/letter-character;
* **zip code:** should be a 4-digit number;
* **phone number:** should be a 8-digit number.

