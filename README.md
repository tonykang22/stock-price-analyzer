# stock-price-analyzer

<br>

## 🛣 Preview

<br>

### What is it?
- It's a program which gets the maximum profit over the last 6 months for the US stock symbol you type in.
- Just simply type in the ticker, and press "Search".

<br>

### Purpose of such project
- To practice wisely choosing adequate open API for the requirements.
    * [Issue1 - Choosing the right Open API](https://github.com/tonykang22/stock-price-analyzer/issues/1)
- Keep in mind that tests are indispensable.

<br>

## 🚀 Feature Requirements
- Algorithm
    * Efficiently and correctly return the max profit.
- Design
    * Provide clear abstractions.
    * The layer which uses open API should be easily replaceable.
    * Reusable and testable.
- Testing
    * Profit calculation should be thoroughly tested.
    * As well as all other components.

<br>

## 🏗 Project Architecture
![StockArchi drawio](https://user-images.githubusercontent.com/86760744/162616579-2a24016b-1a14-443a-82ab-e47da9915cab.png)

<br>

### Details
- Since the third-party source should be changed for some reasons(due to the price plan, or source API itself being deprecated.), used the interface to easily change the source.
- Once you search for the stock profit, the data won't change due to its character. By having a repository will save lots of time and resources. 
    * StockRepository is an interface since at the time the type of repository was not decided.
    * Whenever the decision is made, just implement the interface, and it's good to go. (E.g. MySQL, Redis) 

## 💡 Feature List
- [X] Create an object which contains the data provided from the third-party data source.
- [X] Calculate the maximum profit with the object just created.
    * [X] For the algorithm, the greedy algorithm was implied with the O(N) Big-O notation.
- [X] Return the calculated value to the client.

<br>

## 📡 Technologies Used
- SpringBoot 2.6.6
- Gradle 7.4.1
- Java 11
- Lombok
- JUnit 5

<br>

## ⌛️ Retrospect

<br>

### Test

![test](https://user-images.githubusercontent.com/86760744/162616563-75d89862-708b-44c3-93e9-ec3f80ae4f42.png)
- The tests were thoroughly done since tests are that important.
- Since every method and line were tested, now I know that the codes in the project works in the way that I intended.

<br>

### Environment

![2](https://user-images.githubusercontent.com/86760744/162616569-2edf65a2-77df-4495-b39b-5f42a16f3330.png)
- In the project, there are some critical information which shouldn't be revealed. (third-party API-key...ect)
- For this project, just used the dev-environment properties file for such information.
- But when I have to release this project in other server such as AWS, then I might need to consider using encryption library such as **Jasypt**.
