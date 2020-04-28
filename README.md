# Simulation

Simple online multiplayer game developed using Java.

## Notice!

Code is written and tested using Java version 11.

## Documentation

[requirements](https://github.com/githubuser100923/simulation/blob/master/docs/requirements.md)  
[working hours](https://github.com/githubuser100923/simulation/blob/master/docs/working_hours.md)  
[architecture](https://github.com/githubuser100923/simulation/blob/master/docs/achitecture.md)

## [Releases](https://github.com/githubuser100923/simulation/releases)

## Usage

Download `.jar` file from [here](https://github.com/githubuser100923/simulation/releases). Run it on your computer. Might require some permission. Add your name to the input box and press `join`. Select one character the game starts automatically. You can move the character with arrow keys.

### Testing

Run tests

```
mvn test
```

Generate test coverage report

```
mvn jacoco:report
```

### Other

Generate JAR

```
mvn package
```

Generate JavaDoc

```
mvn javadoc:javadoc
```

Use checkstyle

```
mvn jxr:jxr checkstyle:checkstyle
```
