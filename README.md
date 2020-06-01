# Search app.

## Tools:

- Eclipse
- Maven
- Junit
- REST Assured
- Cucumber

## Maven dependencies: 
- REST Assured
- Cucumber
- JUnit.

>API(Source: https://www.goodreads.com/api/index#author.show)

## API used: URL: 
### https://www.goodreads.com/search/index.xml (sample URL) 
#### HTTP method: GET Parameters: q, page, key, search[field]

Development BDD Style:

- Feature File - src.test.java.feature.SearchBook.feature 
- Test Runner - src.test.java.runner.TestRunner.java 
- Glute Code - src.test.java.gluecode.SearchBook.java 
- Static data File - configs.Configurations.property

###  To Run: Navigate to Feature File - src.test.java.feature.SearchBook.feature, Right-click on editor and Run "Cucumber Feature"

### Explanation: 
For now, this application works on static data, API receive its data from the property file. Once endpoints are hit, XML response is received, then XML responses are manupulated uisng XMLpath to get desired O/P.

