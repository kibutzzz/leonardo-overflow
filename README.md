# Leonardo Overflow
Attempt to recreate a simplified version of the StackOverflow Backend

## Database

Before running the application, run the database container

- run `docker run -d -p 3306:3306 kibutzzz/leonardo_overflow_database`

## Run

Steps to run the application using the terminal

- Navigate to the root directory 
- Run `./gradlew bootRun` 

**OR**

Using the JAR:

- `./gradlew assemble` to build the JAR
- `java -jar build/libs/leonardo-overflow-0.0.1-SNAPSHOT.jar`

## Test

- Navigate to the root directory
- Run `./gradlew test` 

## Code Quality

- Before any commit, the app should successfully `clean build`
- Run `./gradlew clean build` 
- If you want to check code quality during development in a faster way, there is 
also the `codeQuality` task, which runs `pmdMain`, `pmdTest`, `checkstyleMain`, 
`checkstyleTest` and `jacocoTestCoverageVerification`.


#### TODO

 - [ ] Add git hooks to check if the project is building before commit 
