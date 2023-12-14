# Medium Level NestJS Project

The project is an example of a medium-level application developed using NestJS. The project includes the following features:

## Directory structure

### Overview

Project src structure directories.
src/main/java
├─ com/adoptez1plumbier
│  ├─ bean/
|  |  ├─ entity.java
|  |  ├─ PdfData.java 
|  |  ├─ Section.java
|  |  ├─ SectionData.java
|  |  ├─ User.java
│  ├─ dao/
|  |  ├─ PlumbierDAO.java
|  |  ├─ PlumbierDaoImpl.java
│  ├─ dto/
|  |  ├─ Feeling.java
|  |  ├─ Palace.java
|  |  ├─ UserData.java
|  |  ├─ WorkProp.java
│  ├─ mapper/
|  |  ├─ FileRowMapper.java
|  |  ├─ PlumbierRowMapper.java
|  |  ├─ SectionDataRowMapper.java
|  |  ├─ SectionRowMapper.java
|  |  ├─ UserDataExtractor.java
|  |  ├─ UserRowMapper.java
│  ├─ rest/
|  |  ├─ PlumbierController.java
│  ├─ utils/
│  |  ├─CreatePdfUtil.java
│  |  ├─DBUtil.java
│  |  ├─ErrorType.java
│  |  ├─FileUtils.java
│  |  ├─OuiNon.java
│  |  ├─Preferences.java
│  |  ├─ServiceResponse.java
│  |  ├─Type.java
│─ resources/
│  ├─img/ 
│─ webapp/
│  ├─application.properties
│  ├─applicationContext.xml
│  ├─dispatcher-servlet.xml
│  ├─web.xml


└─## Installation and Setup

After successfully starting the project, you can access the API at [http://localhost:3000](http://localhost:3000).

## Spring MVC Framework
Learn more about logging using [MVC Framework](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html).

## ITEX PDF
Request data validation using [Itex7](https://github.com/itext/itext7).

## Data source and Java mail configuration File (JSON Web Token)
JDBC database configurasyon and java mail configuration File  : src/main/webapp/WEB-INF
/applicationContext.xml 