
# NoSenseGenerator
A simple tool that generates random sentences. Ideal for testing, writing prompts, or language learning.

**DEBUG:** se usate Windows scaricate: [Git for Windows](https://git-scm.com/downloads/win)

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
- Maven (recommended)  
- Internet connection (for dependency resolution)  
- Operating system: Linux / Windows / macOS  
- Git (to clone the repository)

### Installation Steps

```bash
git clone https://github.com/FedeRio-Biscotto/nosensegenerator.git
cd nosensegenerator
mvn clean install
```

### Running the Application

```bash
cd target
java -jar NoSenseGenerator-1.0-SNAPSHOT.jar
```

Alternatively, from IntelliJ:
- Import as a Maven project
- Run the `Main` class from the `main/java/NoSenGen/generator/Main.java`

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

- No GUI interface (CLI only)
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

- Thanks to [FedeRio-Biscotto](https://github.com/FedeRio-Biscotto),

