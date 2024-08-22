# Project 1: Team 5 - Calorie Tracker Application

## Proposal
###  Description
The Calorie Tracker app is a Spring Boot web application that helps users manage their dietary intake by tracking calories from various food items. It features user and admin authentication, profile management, BMI calculation, and daily and weekly calorie monitoring. Users can log their calorie intake through interactions with integrated food and calorie databases. The app also offers profile management, BMI retrieval, and basic data management for calorie logs, providing an engaging experience for individuals committed to maintaining a healthy diet.
### User Stories
See [wiki page](https://github.com/Will-Java-FS/Team5-Project1-SarahAnatoli-StephenBecker-BolaGhaly-MichaelNugent/wiki/User-Stories).
### Database Tables
See [wiki page](https://github.com/Will-Java-FS/Team5-Project1-SarahAnatoli-StephenBecker-BolaGhaly-MichaelNugent/wiki/Database-Tables).

## Technologies
| Technology  | Version |
|-------------|---------|
| Typescript  |   5.5.3      |
| React       | 18.3.1       |
| Spring Boot |3.3.2         |
| Java | 17      |
## Project Execution
### Frontend 
Build the React application with the command:
```
npm run build
```
Serve this folder locally or on a designated cloud provider.  (Modify the base URL)
### Backend
Build the Spring Boot project and serve run the jar file on a JRE installed machine (local or cloud).
## Relevant Information
- Security can be a large hurdle in development.  Ensure valid JWTs are sent in every protected-route request.
- Install frontend's node modules after project clone:
```
npm install
```

## API Demonstration
After you clone this repository, visit our
[Postman Public Workspace](https://www.postman.com/nugentmichael/workspace/team-5-project-1-calorie-tracker-app)
to view a pre-assembled list of requests and easily try a few.
Make sure you're on the desktop application version or use their Desktop agent as the browser version
alone will not query a localhost domain.

## Members & Responsibilities
| Team Member | Feature Responsibilities                                                        |
| ---------|---------------------------------------------------------------------------------|
| Sarah Anatoli | BMI Calculation, Nutrition Insights, Exercise Logging, Food Database Management |    
| Stephen Becker | Calorie Logging, Daily Calorie Goal Setting, Weekly Calorie Monitoring          |     
| Bola Ghaly | Password Reset, Calorie Log History, Data Privacy & Security, User Management   |
| Michael Nugent | User Registration, Login, Privileges, Profile Management, Security              |