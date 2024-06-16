@tag
Feature: Purchase the order from Ecommerce Website

Background:
  Given I landed on Ecommerce page

@Regression
Scenario Outline: Positive test to submit the order

  Given Login with username "<username>" and password "<password>"
  When I add product "<productName>" from cart
  And checkout "<productName>" and submit the order 
  Then "THANKYOU FOR THE ORDER." message is displayed on confirmation page

  Examples:
    | username            | password | productName |
    | patillms@gmail.com  | P@til555 | ZARA COAT 3 |