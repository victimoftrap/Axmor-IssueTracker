# Тестовое задания для Axmor - Web Issue Tracker

## Запуск приложения
1. Склонировать этот репозиторий `https://github.com/victimoftrap/Axmor-IssueTracker`
2. Собрать .jar с помощью команды `mvn package` или `mvn install`
3. Запустить собранный .jar

## Интерфейс
Главная страница приложения: `localhost:8080/issues`.

В пользовательской части приложения сделаны 3 страницы:
* Страница со всеми имеющимися ишью `/issues`;
* Страница созданния нового ишью `/issues/create`;
* Страница ишью `/issues/id`.

Была оставлена дефолтная страница логина Spring Security. 
Вход под пользователем:
```
username: admin
password: admin
```
## Результаты
Потраченное время на изучение материала: 5

Полное время выполнения задания: 20

### Использованный стек:
* Java 11
* Maven 4
* Spring
  * Boot 2.6.1
  * Security 5.6
  * MVC
  * Test
* H2 Database 1.4.200
* MyBatis 3.5.7
* Thymeleaf 3.0.12
* Mockito 4
* Hamcrest 2.2
