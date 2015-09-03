# Logging Adapters for Java

Adapters for various Java and Android logging implementations.

[![Build Status](https://travis-ci.org/cookingfox/logging-adapter-java.svg?branch=master)](https://travis-ci.org/cookingfox/logging-adapter-java)

## Features

- Contains adapters for popular logging libraries. (i.e. Android `Log`, SLF4J)
- Easy to implement your own adapter through a simple interface.
- Simple log method calls: no need to define a `TAG` (Android) or create a logger per class (SLF4J).

## Download

[![Download](https://api.bintray.com/packages/cookingfox/maven/logging-adapter-java/images/download.svg)](https://bintray.com/cookingfox/maven/logging-adapter-java/_latestVersion)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.cookingfox/logging-adapter-java/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.cookingfox/logging-adapter-java)

The distribution is hosted on [Bintray](https://bintray.com/cookingfox/maven/logging-adapter-java/view).
To include the package in your projects, you can add the jCenter repository.

### Gradle

Add jCenter to your `repositories` block:

```groovy
repositories {
    jcenter()
}
```

and add the project to the `dependencies` block in your `build.gradle`:

```groovy
dependencies {
    compile 'com.cookingfox:logging-adapter-java:0.2.2'
}
```

### Maven

Add jCenter to your repositories in `pom.xml` or `settings.xml`:

```xml
<repositories>
    <repository>
        <id>jcenter</id>
        <url>http://jcenter.bintray.com</url>
    </repository>
</repositories>
```

and add the project declaration to your `pom.xml`:

```xml
<dependency>
    <groupId>com.cookingfox</groupId>
    <artifactId>logging-adapter-java</artifactId>
    <version>0.2.2</version>
</dependency>
```

## Usage

Example using Android adapter:

```java
Logger.init().addLoggerAdapter(new AndroidLoggerAdapter());

Logger.debug("Created a user: %s", user);
```
