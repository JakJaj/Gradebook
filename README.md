# Grade Book Project

This project is a comprehensive online grade book system developed as part of a final year project. It provides a web-based platform writen using React with a REST API created using Spring Boot. System is created for schools to manage student grades, facilitate communication between students, teachers, and parents, and generate reports on student performance. The system utilizes various technologies to achieve its functionality. 

## Technologies Used

### Backend

- **Java 17**: The primary programming language used for the backend logic.
- **Project Lombok**: A library to reduce boilerplate code in Java.
- **Spring Boot**: A powerful framework for building Java-based applications.
    - **Spring Web**: Provides HTTP request handling.
    - **Spring Data JPA**: For data access.
    - **Spring Boot Dev Tools**: Tools for rapid application development.
    - **MySQL Driver**: JDBC driver for MySQL database connectivity.
    - **MySQL**: Relational database management system.
    - **Hibernate**: An object-relational mapping framework.

### Frontend

- **HTML/TilewindCSS/TS**: Standard web technologies for building user interfaces.
- **React**: A JavaScript library for building user interfaces.
    - **React Router**: Library for managing and navigating the routing of a React application
    - **Axios**: Promise-based HTTP client for making asynchronous requests to APIs

## Testing

- **JUnit 5**: A unit testing framework for Java.
- **Mockito**: A mocking framework for unit tests in Java.

## Features

- **Grade Management**: Allows teachers to input, update, and manage student grades.
- **Communication System**: Provides messaging capabilities between students, teachers, and parents.
- **Report Generation**: Enables the generation of student reports with detailed performance analysis.
- **User Authentication and Authorization**: Secure login system for different user roles (students, teachers, parents).
- **Real-time Updates**: Utilizes Websockets for real-time updates and notifications.
- **Responsive Design**: Ensures usability across various devices with responsive design principles.
- **Intuitive User Interface**: Designed with usability in mind to facilitate easy navigation and usage.

## Getting Started

To run the project locally, follow these steps:

1. Clone the repository: `git clone git@github.com:JakJaj/Gradebook.git`
2. Navigate to the project directory: `cd Gradebook`
3. Configure the backend:
     - Set up the MySQL database and update application.properties with the database configuration.
     - Ensure Java 17 is installed and set up on your system.
4. Run the API: `./mvnw spring-boot:run`
5. Run the frontend: `npm run dev`
5. Access the application in your web browser at a web url provided after running `npm run dev`.

## Contributing

I shouldn't be seeking help from anyone because this project is for my Engineering thesis. Nevertheless, if you find this project interesting, you are more than welcome to contribute! Please fork the repository and create a pull request with your proposed changes.
