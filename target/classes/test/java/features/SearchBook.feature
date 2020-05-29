#Author: mhaskar

#Find books by title, author, or ISBN

#Get an xml response with the most popular books for the given query. 
#This will search all books in the title/author/ISBN fields and show matches, sorted by popularity on Goodreads. 
#There will be cases where a result is shown on the Goodreads site, but not through the API. 
#This happens when the result is an Amazon-only edition and we have to honor Amazon's terms of service.

#URL: https://www.goodreads.com/search/index.xml    (sample url)

#HTTP method: GET

#Parameters:
#q: The query text to match against book title, author, and ISBN fields. Supports boolean operators and phrase searching.
#page: Which page to return (defau1lt 1, optional)
#key: Developer key (required).
#search[field]: Field to search, one of 'title', 'author', or 'all' (default is 'all')


@tag
Feature: Search book API operation using REST-Assured

Scenario: Verify GET operation with user key and query paramter

    Given I perform GET operation for "https://www.goodreads.com/search" with query parameters
    
    Then I see API response in XML format and use XML class path for result verification
    
   	Then I should see the status as 200 