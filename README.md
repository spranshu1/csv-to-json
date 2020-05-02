# csv-to-json

Simple CSV to JSON converter 

## Target Version

`1.0.0`

Getting started:

* [Prerequisites](#markdown-header-prerequisites)
* [Setup](#markdown-header-setup)
* [Run](#markdown-header-run)
* [Release Log](#markdown-header-releaselog)
* [Contact](#markdown-header-authors)

## Prerequisites

Ensure local installation of following softwares/tools:

* JDK - 1.8
    ```markdown
    $ java -version
    java version "1.8.0_121"
    ```
* Apache Maven - if using maven dependency 
    ```https://maven.apache.org/install.html```

## Setup

1. Clone latest version from repo 

2. Go to root location and build the project - `mvn clean install`

3. Make sure `csv-to-json-xxx.jar` is created inside target folder

## Run

* Command to convert csv to json - `java -jar csv-to-json-0.0.1-SNAPSHOT.jar [source] [destination] [sheet-name/sheet-index]`
    ```markdown
    java -jar csv-to-json-0.0.1-SNAPSHOT.jar "D:\test.xlsx" "D:\\test.json" "Sheet1"
    ```  

## Release Log
	
`1.0.0`

- First version	

### Support or Contact
```
spranshu1
```