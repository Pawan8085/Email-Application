# Email-Application

Tech stack :- Spring, Java, Hibernate, MySql, Spring Security

#FEATURES :-<br>
. User can create their account login into them <br>
. User can send message to other user and recieve message from other user<br>
. User can star and delete the message 

# ERD 

![Flowchart](https://user-images.githubusercontent.com/101393436/216821518-61d050a3-cb6c-45c6-806a-39cf2609000a.png)

# REST API
## USER
### Register User
`POST /mymail/register`
```
http://localhost:8080/mymail/register
```
### Login User
`POST /mymail/login`
```
http://localhost:8080/mymail/login
```
&nbsp;

## SENT
### Send Message
`POST /mymail/mail`
```
http://localhost:8080/mymail/mail
```
### Star From Sent Message
`POST /mymail/sent/{id}`
```
http://localhost:8080/mymail/sent/{id}
```
### Delete From Sent Message
`DELETE /mymail/sent/{id}`
```
http://localhost:8080/mymail/sent/{id}
```


&nbsp;

## RECIVED

### Star From Recived Message
`POST /mymail/recived/{id}`
```
http://localhost:8080/mymail/recived/{id}
```
### Delete From Recived Message
`DELETE /mymail/recived/{id}`
```
http://localhost:8080/mymail/recived/{id}
```

&nbsp;

## STARRED

### Get All Starred Message
`GET /mymail/starred`
```
http://localhost:8080/mymail/starred/
```
### Delete From Starred Message
`DELETE /mymail/starred/{id}`
```
http://localhost:8080/mymail/starred/{id}
```


    
    
