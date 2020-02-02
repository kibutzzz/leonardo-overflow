# Leonardo Overflow
Attempt to recreate a simplified version of the StackOverflow Backend


## Run

Steps to run the application using the terminal

- Navigate to the root directory 
- Run `./gradlew bootRun` 

## Test

- Navigate to the root directory
- Run `./gradlew test` 

## Code Quality

- Before any commit, the app should successfully `clean build`
- Run `./gradlew clean build` 
- If you want to check code quality during development in a faster way, there is 
also the `codeQuality` task, which runs `pmdMain`, `pmdTest`, `checkstyleMain`, 
`checkstyleTest` and `jacocoTestCoverageVerification`.


####TODO

 - [ ] Add git hooks to check if the project is building before commit 
