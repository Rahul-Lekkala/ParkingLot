
# Parking Lot


## Project Requirements

- JDK 1.8


## Description

We own a parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. We want to create an automated ticketing system that allows my customers to use my parking lot without human intervention.

When a car enters my parking lot, we want to have a ticket ssued to the driver. The ticket issuing process includes
- Taking note of the Vehicle registration number and the age of the driver of the car.
- And allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them).

The customer should be allocated a parking slot which is nearest to the entry. At the exit the customer returns the ticket which then marks the slot they were using as being available.

Example
#### Input ####
```
Create_parking_lot 6
Park KA-01-HH-1234 driver_age 21
Park PB-01-HH-1234 driver_age 21
Slot_numbers_for_driver_of_age 21
Slot_numbers_for_driver_of_age 18
Park PB-01-TG-2341 driver_age 40
Slot_number_for_car_with_number PB-01-HH-1234
Leave 2
Park HR-29-TG-3098 driver_age 39
Slot_number_for_car_with_number PB-01-HH-6789
Vehicle_registration_number_for_driver_of_age 18
```

#### Output ####
```
Created parking of 6 slots
Car with vehicle registration number "KA-01-HH-1234" has been parked at slot number 1
Car with vehicle registration number "PB-01-HH-1234" has been parked at slot number 2
1,2
No parked car matches the query
Car with vehicle registration number "PB-01-TG-2341" has been parked at slot number 3
2
Slot number 2 vacated, the car with vehicle registration number "PB-01-HH-1234" left the space, the driver of the car was of age 21
Car with vehicle registration number "HR-29-TG-3098" has been parked at slot number 2
No parked car matches the query
No parked car matches the query
```


## Installation

### MacOs ###

- Go to [Oracle's website](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) and download the latest macOs x64.
![Image](/images/java-installation.png)

- Wait for the download to complete. Double click the file from the downloads folder.
Java Installer would launch up. Click Continue.

- Click install on the next screen

- Have a coffee and wait for the installation to complete. When the installation is complete, you would see the screen below:
![Image](/images/java-installation-confirmation.png)

- Click close. We are ready to Rock and Roll. Do a Dance.

#### Check
- Launch up Terminal.
	* cmd + space -> Type terminal -> Press enter
- Type in the command “java –version” as shown in the screen. If it does not work, go to the trouble shooting section.

#### Troubleshooting
-	Check if there are any pre-existing Java installs. Uninstall them and reinstall again.
-	Temporarily turn off firewalls and antivirus software.
-	If you get file corrupt message, download the installation file again.
-	Check if you are on 32-bit OS or 64-bit OS and ensure you are making use of the right java download.


### Ubuntu

Installation instructions for installing Oracle Java JDK 8 64-Bit on Ubuntu 16.04-21.04

- Go to [Oracle's website](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html) and download the latest Linux x64 Compressed Archive.
- Open Terminal and type `sudo mkdir  -p /opt/jdk`.
- Go to your Downloads folder (or the folder that you downloaded the file to) and open Terminal from it (by right clicking and selecting "Open in Terminal").
- Type `sudo cp -rf jdk-8u(version number)-linux-x64.tar.gz /opt/jdk/`.
- Type `cd /opt/jdk/`.
- Type `sudo tar -zxf jdk-8u(version number)-linux-x64.tar.gz`.
- Type `sudo update-alternatives --install /usr/bin/java java /opt/jdk/jdk1.8.0_(version number)/bin/java 100`.
- Type `sudo update-alternatives --config java` (It will ask you to select the version of Java you downloaded from a list (0,1,2...)).
- Type `sudo  nano /etc/environment`and paste `JAVA_HOME=/opt/jdk/jdk1.8.0_(version number)` and `JRE_HOME=/opt/jdk/jdk1.8.0_(version number)/jre`
- Verify your installation by typing `java -version`.

### To install using apt

- To install this version, first update the package index:
`sudo apt update`

- Next, check if Java is already installed:
`java -version`

- If Java is not currently installed, you’ll see the following output:

```Output
Command 'java' not found, but can be installed with:

apt install default-jre
apt install openjdk-11-jre-headless
apt install openjdk-8-jre-headless
```
- Execute the following command to install the default Java Runtime Environment (JRE), which will install the JRE from OpenJDK 11:

`sudo apt install default-jre`

- The JRE will allow you to run almost all Java software.
- Verify the installation with:

`java -version`

- You’ll see the following output:

```Output
openjdk version "11.0.7" 2020-04-14
OpenJDK Runtime Environment (build 11.0.7+10-post-Ubuntu-2ubuntu218.04)
OpenJDK 64-Bit Server VM (build 11.0.7+10-post-Ubuntu-2ubuntu218.04, mixed mode, sharing)
```

- Install Java Development Kit (JDK) in addition to the JRE in order to compile and run some specific Java-based software. To install the JDK, execute the following command, which will also install the JRE:

`sudo apt install default-jdk`

- Verify that the JDK is installed by checking the version of javac, the Java compiler:

`javac -version`

You’ll see the following output:
```
Output
javac 11.0.7
```



## Running the project

Open terminal and create a directory `parkinglot`

Go to the project directory
```bash
  cd parkinglot
```
Clone the project

```bash
  git clone https://github.com/Rahul-Lekkala/ParkingLot.git
```

Compile all java files

```
javac src\com\parkinglot\exceptions\*.java -d classes
javac -cp classes src\com\parkinglot\model\*.java -d classes
javac -cp classes src\com\parkinglot\service\*.java -d classes
javac -cp classes src\com\parkinglot\command\*.java -d classes
javac -cp classes src\com\parkinglot\input\*.java -d classes
javac -cp classes src\com\parkinglot\Main.java -d classes
```

Create a text file, say input.txt in project directory, and provide commands in the file

Run the program by providing the created filename as command line argument
```
java -cp classes com.parkinglot.Main input.txt
```

Ouput will be displayed on the terminal



## Understanding the Code

### Models

Parkinglot Project uses the following models
#### Command

  It stores the commandname and arguments, which is later used to execute command
#### Parkinglot

Parkinglot consists of capacity of parking spots and a list of parking spots of provided capacity. It is used to park, unpark a vehicle and get a parking spot
#### ParkingSpot

ParkingSpot consists of a ParkingSpotNumber which is unique, status if it is occupied or not and a Vehicle if occupied
#### ParkingSpotStatus
ParkingSpotStatus is of two types - AVAILABLE & OCCUPIED
#### Vehicle
Vehicle stores the details of vehicle registration number and the age of driver

### Service

ParkingLot consists of `ParkingService` which provides the following features
- Create a Parking ParkingLot
- Park a vehicle
- Unpark/leave a vehicle from parking slot
- Get all Parkingslots based on given age of driver
- Get a Parkingslot based on given Vehicle Registration number
- Get all Vehicles based on given age of driver

### Command

For each feature provided by `ParkingLotService` there is a seperate class which handles that responsibility. We have multiple Command classes that inherits the behaviour of base class which is common to all commands.

By this, we can achieve `Single Responsibility` and in future, if new command is added, we can create new class for the new command achieving `Open-Close Principle`

### Exceptions

To handle unexpected behaviour, we have multiple Exceptions