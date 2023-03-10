 # url-shortener
---
### Project Description
1. Java spring project that takes url at any length and returns shortened version.
2. It takes API json url and optional expiration date and stores it in the inner h2 database
3. has statistics for shortened url usages

---

### Requirements:
Copy this project: `git@github.com:resetcat/url-shortener.git`

---
### Launch using docker
1. Have docker installed on your system. You can download it here https://www.docker.com/
2. Locate the root folder, open console and create docker file by entering in your console:
```text
    docker build -t url-shortener .
```
3. And run the app with command:
```text
    docker run -p 8080:8080 -t url-shortener
```
---
### Launch from console
1. Have java installed on your system. You can download it here https://www.java.com/download/ 
2. Has Apache Maven installed on your system. You can download it from the official website at https://maven.apache. 
   org/download.cgi <br />
3. Navigate to the project directory in the console <br />
4. Run the project using the command:
```
    mvn spring-boot:run
```
---

### Launch from your IDE
You can also run the Spring Boot application from an IDE that supports Apache Maven, such as IntelliJ IDEA,
Eclipse, or NetBeans. To do this, you can import your Maven-based Spring Boot project into the IDE and use the IDE's built-in support for Maven to run the application.

---
### Endpoint description
1. `http://localhost:8080/api/shortened-urls/shorten` send a post request with your `json` body <br /> example:
```json
{
  "url": "https://www.example.com",
  "expiration": {
    "unit": "days",
    "amount": 5
  }
}
 ``` 
`expiration` is optional (unit could be `days` or `hours`) <br />
return should be like this:
```json
{
    "url": "http://localhost:8080/1",
    "expires": "2023-02-16 14:09"
}
```
2. `http://localhost:8080/api/statistics/shortened-urls` `get request` returns statistics:
* total shortened url count
* count of all consumed urls 
```json
{
    "totalShortenedUrlCount": 1,
    "shortenedUrlsConsumedCount": 0
}
```
3. `http://localhost:8080/api/statistics/shortened-urls/{id}` `get request` replace {id} with number of  your 
   shortened url id. It returns how many times shortened url was used <br />
 example: `http://localhost:8080/1` id is `1`
```json
{
"timesConsumed": 0
}
```
4. `http://localhost:8080/{id}` your short url that redirect to web page that you entered at first point

---
