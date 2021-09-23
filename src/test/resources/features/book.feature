Feature: Books Feature

  Scenario: Get all books
When I get the books
Then it should return all books
And status code should be 200