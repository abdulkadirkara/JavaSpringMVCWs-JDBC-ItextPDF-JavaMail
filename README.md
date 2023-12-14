# Java Spring MVC + JDBC + JAVA mail + ItextPDF 

The project is an example of a medium-level application developed using Java Spring Framework. The project includes the following features:

## Directory structure

Project src structure directories.

	src/main/java/  
	├─ com/springMvcWebService
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

## Spring MVC Framework
Learn more about logging using [MVC Framework](https://docs.spring.io/spring-framework/docs/3.2.x/spring-framework-reference/html/mvc.html).

## ITEX PDF
Request data validation using [Itex7](https://github.com/itext/itext7).

## Data source and Java mail configuration File (applicationContext.xml)
JDBC database configurasyon and java mail configuration File  : src/main/webapp/WEB-INF
/applicationContext.xml 
