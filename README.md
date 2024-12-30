# Pet-Care

A web app for pet owners and veterinarians.

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Running the Application Locally](#running-the-application-locally)
    - [Step 1: Clone the Repository](#step-1-clone-the-repository)
    - [Step 2: Create the `.env` File](#step-2-create-the-env-file)
    - [Step 3: Build and Run the Application](#step-3-build-and-run-the-application)
- [License](#license)

## Introduction

Pet-Care is a web application designed for pet owners and veterinarians to manage pet care efficiently.

## Prerequisites

Before running the application locally, ensure you have the following installed:

- Java Development Kit (JDK) 17 or higher
- Apache Maven
- MySQL database
- Redis

## Running the Application Locally

### Step 1: Clone the Repository

Clone the repository to your local machine using the following command:

```sh
git clone https://github.com/saumitra403/Pet-Care.git
cd Pet-Care
```

### Step 2: Create the .env File

Create a new .env file in the root directory of the project. You can use the .env.example file as a reference. Copy the contents of .env.example to your new .env file and fill in the appropriate values.

```sh
cp .env.example .env
```

Edit the .env file to provide the necessary environment variables:

### Step 3: Build and Run the Application

Build the application using Maven:

```sh
mvn clean install
```

Run the application:

```sh
mvn spring-boot:run
```

Your application should now be running locally on http://localhost:9192

## License
This project is licensed under the MIT License. See the LICENSE file for details.

```This README file provides clear and concise instructions on how to set up and run the Pet-Care application locally. If you need any further modifications or additional information, please let me know!```