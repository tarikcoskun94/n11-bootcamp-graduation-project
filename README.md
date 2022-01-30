# Loan Application System

<!-- TABLE OF CONTENTS -->
<details><summary>CONTENT</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
        <li><a href="#short-description">Short Description</a></li>
        <li><a href="#main-story">Main Story</a></li>
        <li><a href="#extended-story">Extended Story</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#screenshots">Screenshots</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project
### Built With
* [Java 11](https://www.oracle.com/tr/java/technologies/javase/jdk11-archive-downloads.html)
* [IntelliJ Idea](https://www.jetbrains.com/idea)
* [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
* [Validation](https://spring.io/guides/gs/validating-form-input)
* [Developer Tools](https://docs.spring.io/spring-boot/docs/1.5.16.RELEASE/reference/html/using-boot-devtools.html)
* [Postgre SQL](https://www.postgresql.org/)
* [Open API](https://springdoc.org)
* [Lombok](https://projectlombok.org)

### Short Description
This is a Spring Boot project that meet loan applications and response its result of application. This project has 4 entity: Customer, Loan Customer, Loan Application and Collateral. In following sections, there is a main story from Patika.dev and there is a extended story that has was wrote by me.

Before you run and use this project, please read the readme file, main story and extended story by clicking below links.

### Main Story
<a href="https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-tarikcoskun94/blob/main/2022_01_18_n11_TalentHub_Java_Spring_Bootcamp_Bitirme_Projesi.pdf" target="_blank">Click me to read main story.</a>

### Extended Story
<a href="https://docs.google.com/document/d/1UneIvUKJznG_jL0BjbVmCG7XBi2SH-YILzPYB5wIfTM/edit?usp=sharing" target="_blank">Click me to read extended story.</a>

<!-- GETTING STARTED -->
## Getting Started
### Installation
1. Clone the repo
   ```sh
   git clone https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-tarikcoskun94.git
   ```
2. Install JDK-11 and switch to it.
3. Import the project to your IDE.
4. Change the project structure (File > Project Structure).
  ![intellij-project-structure](https://user-images.githubusercontent.com/10232721/151702557-7e2be504-2aa0-4cea-907d-69ceef636a27.png)
  ![intellij-project-structure-2](https://user-images.githubusercontent.com/10232721/151702559-78d5fcb0-80ff-4ef7-bd53-ef1105857b90.png)
5. Create a new database by using PgAdmin on PostgreSQL.
6. Go to project file structure and find the application.properties file under resources.
7. You must the edit database name, username and password on application.properties file.
 ![application-prop](https://user-images.githubusercontent.com/10232721/151702685-7dc517ec-d947-4e8a-a7a5-86b4aac8481d.png)
8. Run the project
9. Open the swagger ui on your browser. Default link -> http://localhost:8080/swagger-ui.html
10. Send request to endpoints (The project is running on the 8080 port by defalt).

### Screenshots
##### WARNING! All of dates format for request must be as "dd-mm-yyyy".
---
![customer-all-endpoints](https://user-images.githubusercontent.com/10232721/151703098-cefc6fe0-a623-4cac-bf9b-64c50ee56c3f.png)
- Use these end-points to create, update, list all, list by id and delete customer.
- Observe the given constraints through request schemas.
</br>

![loanapp-all-endpoints](https://user-images.githubusercontent.com/10232721/151703104-108d3276-3ee2-402b-80b9-9c41c237da89.png)
- Use these end-points to create, cancel and query loan application.
- You must create a customer first and must give detail for loan customer second by using above end-points to create a loan application.
- Observe the given constraints through request schemas.
</br>

![request-structures](https://user-images.githubusercontent.com/10232721/151703107-bbd46651-a33a-40c0-893b-19d37b56614a.png)
- You can observe the given constraints, here.
</br>

![response-structure](https://user-images.githubusercontent.com/10232721/151703771-319ce234-6e32-4143-bbce-edeecdafd6fb.png)
- Structure of all responses are like that.
- Content includes always response of request details.
</br>

![get-by-id](https://user-images.githubusercontent.com/10232721/151703736-d74d8e75-43ea-4829-afdc-f648a46ba0d1.png)
- An example of response for getting customer by id.

![not-found-response](https://user-images.githubusercontent.com/10232721/151704241-c3fcf979-7abf-4314-8323-e746d60da0af.png)
- An example of response for not getting customer by id.

<!-- CONTRIBUTING -->
## Contributing
Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request
<h4 align="center"> OR </h4>
<p align="center"><a href="https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-tarikcoskun94/issues">Create an issue</a> </p>

<!-- LICENSE -->
## License
Distributed under the MIT License. See `LICENSE.txt` for more information.

<!-- CONTACT -->
## Contact
Tarık COŞKUN: https://www.linkedin.com/in/tarıkcoskun

Project Link: https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-tarikcoskun94
