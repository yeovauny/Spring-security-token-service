## Sepring Security token service
this project has a serveral application for know how works spring securty.

those prject was builed with:
- Springboot 2.5
- java 11
- Spring Security


##ls-example-resource
this application is only for kwon how works the Oauth2 Resources. for autentication only needs the JWT token, given by token-validator-service

##token-generator
this application is only for how build an application with gradle packages, and also only give a token JWT .

##token-validator-service
this application give the token jwt with expiration time and validate the token agains the resource service, in this example the
ls-example-resource


There is a postman project to check how works!