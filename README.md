### RESTful ad service

## How to start
1 . clone https://github.com/vadikpkin/web_app.git
```
git clone https://github.com/vadikpkin/web_app.git
```
2 . create PostgreSQL database called ad_service with user: admin , pass: password (TBA)

3 . to migrate table type this from root folder
```
mvn flyway:migrate
```
4 . deploy ad_service.war from root folder to Tomcat or other servlet container