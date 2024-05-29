# Distance Around the World
Java Swing application to measure the distance between you and another
place, but if you were to travel in the opposite direction (around the world)

******DISCLAIMER***

This project has not yet been fully developed but has been
put onto Github to track progress and view older versions.

Current Goals:

* Bring functionality to the map by using the search bar
to locate places (web scraping will be used for place info and autofilling)
* Allow the user to choose two places on the map
* When choosing each place, save data on longitude and latitude
* Measure the real distance between two places on the map, then
subtract it from the full distance around the Earth (Linear Algebra will play a role in this)
* Fix execution via Maven

## Setup

### Project Folder
Open your computer's terminal and cd to a directory of your choice.

Ensure git is installed on your system, then clone this repository using:

```sh
git clone https://github.com/Cortes205/Distance-Around-the-World
```

or you can download it manually as a zipfile.

### Requirements
Requirements are all located in pom.xml and are automatically installed when building the application via Maven

## Usage

### Execute
*********Disclaimer******
<br>**The following execution is currently not working and is 
better executed through an IDE (ex: eclipse)**

Open the terminal in the program's directory

Ensure you have the following:
* An internet connection
* Maven
* Java

To run this program, use the command:

```sh
mvn compile
```

then

```sh
mvn package
```

and finally,

```sh
java -jar target/Distance-Around-the-World-0.1.0.jar
```

### Runtime


## About This Project
This project was inspired by a joke I told my girlfriend. She and 
I are about 50 km away from each other, but I like being dramatic! So I subtracted
that distance by the circumference of the Earth and acted extremely sad as I told her we were 40,025 km
away from each other. However, this distance assumes that we both live on the equator. Therefore,
this distance is wrong. This inspired me to create a program to measure the real distance. The only
thing this program assumes is that the Earth is a perfect sphere with a radius of 6,371 km.

[Linkedin](https://www.linkedin.com/in/cortes205/)

## APIs Used
* OpenStreetMap - JXMapViewer2
* JSoup
