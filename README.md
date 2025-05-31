
# NoSenseGenerator
A simple tool that generates random sentences. Ideal for testing, writing prompts, or language learning.

---

## Table of Contents

1. [Project Overview](#1-project-overview)  
2. [Installation and Setup](#2-installation-and-setup)  
   - [Requirements](#requirements)  
   - [Installation Steps](#installation-steps)  
   - [Running the Application](#running-the-application)  
3. [Execution Environment](#3-execution-environment)  
   - [Supported Platforms](#supported-platforms)  
   - [Configuration Notes](#configuration-notes)  
4. [Core Features and External Libraries](#4-core-features-and-external-libraries)  
   - [Main Functionalities](#main-functionalities)  
   - [External Libraries Used](#external-libraries-used)  
5. [External APIs Used](#5-external-apis-used)  
   - [Authentication and Access](#authentication-and-access)  
   - [API Rate Limits and Constraints](#api-rate-limits-and-constraints)  
6. [Known Issues and Limitations](#6-known-issues-and-limitations)  
7. [Contributing](#7-contributing)  
8. [License](#8-license)  
9. [Acknowledgments](#9-acknowledgments)

---

## 1. Project Overview

NoSenseGenerator is a Java-based command-line application that generates grammatically varied, yet semantically nonsensical sentences.  
Its main purpose is to assist in **testing NLP systems**, creating **creative writing prompts**, and exploring **language generation logic**.  
It is intended for **developers, testers, writers**, and anyone curious about language and randomness.

---

## 2. Installation and Setup

### Requirements

To run this project, you will need:

- Java 17 or higher  
- Maven must be installed
- Internet connection (for dependency resolution)  
- Operating system: Linux / Windows / macOS  
- Git (to clone the repository)
- A valid Google Cloud API Key

### Installation Steps 
Check if Maven is installed (optional)

  ```bash
  mvn -v
  ```
Clone the repository and build the project
```bash
git clone https://github.com/FedeRio-Biscotto/nosensegenerator.git
cd nosensegenerator
mvn clean install
```

#### Build the Project
To compile the project, run:
  ```bash
  mvn complie
  ```
### Running the Application
#### 1. From the Command Line
You can start the Spring Boot application directly with:
```bash
mvn spring-boot:run
```
This will automatically start the embedded application server (e.g., Tomcat).

#### 2. Build and Run the Executable JAR
To build and run the executable JAR file:

  ```bash
  mvn package
  ```
The JAR file will be located in the `target` directory. 
Run it using:
  ```bash
  mvn java -jar target/NoSenseGenerator-1.0-SNAPSHOT.jar

  ```



---

### Alternatively, from IntelliJ
- Open IntelliJ IDEA and import the project as a Maven project.
- Once the project is loaded, navigate to the `Main` class, typically located in:  
  `src/main/java/NoSenGen/generator/Main.java`.
- Right-click on the `Main` class and select **Run 'Main'** to start the application.

---

### Notes
- By default, Spring Boot will start the application on port **8080**. You can access the application via `http://localhost:8080`.
- Modify the `application.properties` file (located in `src/main/resources`) to configure the application as needed.
- Logs and server activity will be output to the terminal while the application is running.

---

## 3. Execution Environment

### Supported Platforms

- Windows (tested)
- macOS
- Linux (Debian-based and Arch-based distributions)

### Configuration Notes

- Ensure Java 17 is set as your project SDK
- If using IntelliJ, validate project structure and language level (`File → Project Structure`)

---

## 4. Core Features and External Libraries

### Main Functionalities

- Random sentence generation
- Choice of verb tenses: **past**, **present**, **future**
- Detection and debug flag for potentially sensitive content
- Interactive CLI interface

### External Libraries Used

- **Gson**: JSON parsing  
  (`com.google.code.gson:gson:2.8.9`)
- **JUnit Jupiter (JUnit 5)**: unit testing framework  
  (`org.junit.jupiter:junit-jupiter:5.10.0`)

---

## 5. External APIs Used

### Authentication and Access

The project uses the following Google APIs:

- [Syntax Analysis API](https://language.googleapis.com/v1/documents:analyzeSyntax?key=)
- [Content Moderation API](https://language.googleapis.com/v1/documents:moderateText?key=)

These require a valid **Google Cloud API Key**.  
Ensure your key has access to the API.

### API Rate Limits and Constraints

Google enforces quota limits on API usage. Refer to the [Cloud Natural Language Quotas](https://cloud.google.com/natural-language/quotas) documentation for up-to-date details.  
Using these APIs may incur costs depending on usage volume.

---

## 6. Known Issues and Limitations

- Limited vocabulary size (subject to JSON word lists)
- Token analysis and sensitive content detection is rudimentary
- Tests will not compile unless JUnit 5 is correctly included in the classpath

---

## 7. Contributing

1. Fork the repository  
2. Create a new branch (`git checkout -b feature-name`)  
3. Commit your changes (`git commit -m 'Add new feature'`)  
4. Push to your fork (`git push origin feature-name`)  
5. Create a pull request

---

## 8. License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).  
See `LICENSE` file for full details.

---

## 9. Acknowledgments

- Thanks to [Federica Bernardinello](https://github.com/FedeRio-Biscotto), [Mattia Zenere](https://github.com/melodjin), [Matteo Pauletto](https://github.com/Pauletot), [Tommaso Maretto](https://github.com/Tommy160804)

