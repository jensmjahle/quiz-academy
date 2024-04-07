<div align="center">
  <h1 align="center">Quiz Academy</h1>
  <p align="center">
  This is the entire repository made by Ari Maman, Magnus Rindal, Ole Heggum, and Jens Martin Jahle, for the subject Full-stack application development (IDATT2105) for the spring semester 2024.
  </p>
</div>

## Info

The purpose of this assignment was to develop a quiz bank like web application that provides a comprehensive and user-friendly 
platform for creating, managing and taking quizzes for educational, training, or entertainment purposes.


## How to run the application

### Prerequisites

To run this program you need:

- JDK 17
- Maven
- Docker
- Node version 16 or higher
- npm

### Installation and execution

1. Clone the repository
   ```sh
   git@gitlab.stud.idi.ntnu.no:full-stack-a-team/quiz-academy.git
   ```

#### Running the backend

The backend can be run through a dockerfile:

1. Build the dockerfile
   ```sh
   docker build -t quizacademyapplication .
   ```

2. Execute the dockerfile on port 8080
   ```sh
   docker run -p 8080:8080 quizacademyapplication
   ```

> Note that this needs to be done within the network of NTNU, because the backend accesses the database which is hosted within a virtual machine.

#### Running the frontend

1. Navigate to the frontend folder
   ```sh
   cd quizAcademyFrontend
   ```

2. Install the necessary dependencies
   ```sh
   npm install
   ```

3. Run the frontend
   ```sh
   npm run dev
   ```

> Don't forget to have the backend running!


### Andre kommandoer

- `make database-down` - Sletter databasen
- `make test`- Kjører alle testene
- `make compile` - Kompilerer kildekoden
- `make prettier` - Formatterer kildekoden
- `make clean` - Sletter `target` mappen

### Test user

The application can be used without already having an account created

- Username: johndoe
- Password: password

## Contact us

Ari Maman - arim@stud.ntnu.no (scrum master)





.

### Running the backend through docker:
Run these two commands:

docker build -t quizacademyapplication .

docker run -p 8080:8080 quizacademyapplication

### Running swagger endpoint documentation:
Paste http://localhost:8080/swagger-ui/index.html into web browser, (8080 is the default port in which the backend runs)