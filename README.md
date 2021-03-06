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
    compile 'com.cookingfox:logging-adapter-java:0.4.0'
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
    <version>0.4.0</version>
</dependency>
```

## Usage

Example using Android adapter:

```java
Logger.init().setLoggerAdapter(new AndroidLoggerAdapter());

Logger.debug("Created a user: %s", user);
```

## Configuration

The following example is used to explain the configuration options:

```java
package com.example;

import com.cookingfox.logging.Logger;

public class MyClass {
   public void myMethod() {
       Logger.debug("Hello!");
   }
}
```

When one of the Logger methods is called, a reference to the 'caller' is generated by analyzing the
stack trace. By default, the way this caller reference is formatted is its fully-qualified class 
name. For the above example, this would be: `com.example.MyClass`.

To configure the Logger, you call `Logger.init()`, which returns a Settings object. This class
provides the following options:

### setLoggerAdapter()

Sets the `LoggerAdapter` instance, which typically wraps a third party logging library. Of course, 
you can always create your own implementation. It is not possible to set multiple logger adapters.

### setEnabled()

Use this method to enable or disable logging. For example, you can disable logging for your release
build.

### callerAddLineNumber()

Set to `true` to add the caller line number, for example: `com.example.MyClass:7`.

### callerAddMethodName()

Set to `true` to add the caller method name, for example: `com.example.MyClass#myMethod`.

### callerUseSimpleName()

Set to `true` to use a simple class name for the caller, for example: `MyClass`.

### Example output when all caller options are set to 'true'

`MyClass#myMethod:7`
