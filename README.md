# SQL Login and Registration Android App

## Table of Contents

1. [Description](#description)
2. [Features](#features)
3. [Technologies](#technologies)
4. [Classes](#classes)
5. [Installation](#installation)
6. [Usage](#usage)
7. [Screenshots](#screenshots)


## Description

This Android application provides a simple login and registration system using SQLite for data storage.

## Features

- User registration with username and password
- User login verification
- Multilingual support (English, German, French, Polish, Spanish, Turkish and Russian)

## Technologies

This Android application was developed using the following technologies:

- **Android Studio:** The official integrated development environment (IDE) for Android app development.
  
- **Java:** The primary programming language used for Android app development.

- **SQLite:** A software library that provides a relational database management system used for local data storage within the app.

- **XML:** Extensible Markup Language is used for designing the user interface (UI) layout of the application.

- **GitHub:** Version control system utilized for tracking changes, collaborating with others, and managing the source code.

## Classes

### 1. DatabaseHelper
The `DatabaseHelper` class manages the SQLite database creation and version management. It includes methods for adding users and checking user credentials.

### 2. HomeActivity
The `HomeActivity` class represents the main screen after successful login.

### 3. LoginActivity
The `LoginActivity` class handles user login. It includes the login form and functionality to switch languages.

### 4. RegisterActivity
The `RegisterActivity` class manages user registration. It includes the registration form and language change functionality.

## Installation
1. Clone the repository.
   ```bash
   git clone https://github.com/FuzzyWuzzy8/SQLiteLoginReg.git
   
2. Open the project in Android Studio.
3. Build and run the application on an Android emulator or device.

**To install app on your Android device, follow these steps:**
1. Download the APK file from the [Releases](release-url) page.
2. Enable installation from unknown sources in your device settings.
3. Install the APK on your Android device.
4. Open the SQLiteLoginReg app.

## Usage
1. **Login:** Enter your username and password to access the app.
2. **Registration:** Sign up with a new username and password.

## Screenshots
![Screenshot 1](https://github.com/FuzzyWuzzy8/SQLiteLoginReg/blob/master/screenshots/SQL_1.png)
![Screenshot 2](https://github.com/FuzzyWuzzy8/SQLiteLoginReg/blob/master/screenshots/SQL_2.png)
<!-- Add more screenshots later -->

