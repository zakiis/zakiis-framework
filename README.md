### Introduction

Zakiis-framework - A public java framework  based on spring boot for enhancing your project or quick start a new project. 

### Quick start

We highly recommend using spring boot 2.x and spring cloud 3.x in your project. If these requirement can't meet, It's safe not using the features provided by Netflix  like Feign, Ribbon.

We recommend using our BOM (bill of materials)

```xml
<dependencyManagement>
  <dependencies>
    <dependency>
      <groupId>io.github.zakiis</groupId>
      <artifactId>spring-cloud-zakiis-dependencies</artifactId>
      <version>2022.0.1</version>
      <scope>import</scope>
      <type>pom</type>
    </dependency>
  </dependencies>
</dependencyManagement>
```

sample pom file for spring boot web application

```xml
<project xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd" xmlns="http://maven.apache.org/POM/4.0.0"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
  <modelVersion>4.0.0</modelVersion>
  <groupId></groupId>
  <artifactId></artifactId>
  <version></version>
  
  <name></name>
  <url></url>
  <description></description>
  
  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <java.version>17</java.version>
  </properties>
  
  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.github.zakiis</groupId>
        <artifactId>spring-cloud-zakiis-dependencies</artifactId>
        <version>2022.0.1</version>
        <scope>import</scope>
        <type>pom</type>
      </dependency>
    </dependencies>
  </dependencyManagement>
    
  <dependencies>
    
  </dependencies>
    
  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <configuration>
          <source>${java.version}</source>
          <target>${java.version}</target>
          <parameters>true</parameters>
        </configuration>
      </plugin>
      <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
      </plugin>
    </plugins>
  </build>
</project>
```

### Modules

The architecture of this framework looks like below.

zakiis-framework

|-- zakiis-core

|-- zakiis-dependencies

|-- |-- spring-cloud-zakiis-dependencies

|-- zakiis-security

|-- |-- zakiis-job-starter

|-- |-- zakiis-log-starter

|-- |-- zakiis-rdb-starter

|-- |-- zakiis-security-starter

|-- |-- zakiis-web-starter

|-- zakiis-starter

### How to use it

The demostration of the method to use this framework, can see [zakiis-framework-demo](https://github.com/zakiis/zakiis-framework-demo)