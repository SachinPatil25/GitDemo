
Feature: Login error validation

@ErrorValidation
Scenario Outline: Verify the error message 

  Given I landed on Ecommerce page
  When Login with username "<username>" and password "<password>"
  Then "Incorrect email or password." message is displayed 

  Examples:
    | username            | password | 
    | patillms@gmail.com  | P@til55  |