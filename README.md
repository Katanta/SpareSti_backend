<p align="center">
    <a href="https://gitlab.stud.idi.ntnu.no/idatt2106-gruppe-2/idatt2106_2024_02_backend" target="_blank">
        <img width="30%" src="https://m.media-amazon.com/images/I/51ckv2myWXL.jpg" alt="Sparesti logo">
    </a>
    </p>
<h1 align="center">
                Sparesti backend 
</h1>

## 🚀 Getting started
### Requirements
To run the application, you need the following installed: 
- Java 17
- Maven >= **3.8**
- Make
- Docker
- Git

### ⚙ Setup
1. Clone the repository
```
git clone https://gitlab.stud.idi.ntnu.no/idatt2106-gruppe-2/idatt2106_2024_02_backend
```
2. Navigate to the project root folder
```
cd idatt2106_2024_02_backend
```
3. Create an ```.env``` file in the project root directory by copying over the ```.env.example``` file: 
```
cp .env.example .env
```
If you are on Windows Command Prompt use: 
```
copy .env.example .env
```

NOTE: We are fully aware that this file should not be public and that the user should 
use their own configuration parameters, but 
for the ease of the examiners we have chosen to do it this way.


### 🚗 Running the application

1. Run the application (OBS: Make sure docker is running). It will take a few minutes the first time you run the application. 
```
make run
```
The application is now running on port 8080.

NOTE: If you don't have Make installed, use the following command instead: 
```
docker compose up
```

#### 🛑 Exiting the application

To exit the application, use CTRL+C. You can rerun the application by using 
```make run``` again.

### 🧪 Test data
The database is populated with a pre-configured user at runtime which 
can be used to experiment with the application. You can log in with the following credentials:
- Username: ```username```
- Password: ```Test123!```

### 📋 Running tests
You can run unit and integration tests with: 
```
make test
```
This will generate a ```target``` folder with test coverage data. You can now find the report 
by navigating the folder structure: ```target -> site -> jacoco -> index.html``` or by using [this link](http://localhost:63342/SpareStiBackend/target/site/jacoco/index.html).

### 🗎 Documentation
#### REST endpoints
The REST endpoints are documented using [Swagger UI](https://swagger.io/tools/swagger-ui/). The documentation is generated at runtime and can be found [here](http://localhost:8080/swagger-ui/index.html).

#### Source code
The entire backend source code has been documented with Javadoc. The entire documentation is deployed with Gitlab Pages and can be found [here](https://idatt2106-2024-02-backend-idatt2106-gruppe-2-bae057cdb765bc09e0.pages.stud.idi.ntnu.no).

### ⌨️ Other commands
Run ```make help``` to get a list of all possible commands, along with a short explanation for their use case. Alternatively, take a look at the [Makefile](Makefile) for what each command does programmatically. Some of the commands are also mentioned below: 
- ```make test```: Test the application
- ```make destroy```: Tear down the application
- ```make format```: Format the source code