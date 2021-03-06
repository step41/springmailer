# Step41's SpringMailer

## Summary

A small demo app built using Spring Boot and Vue. Fully encapsulated micro-service API with front-end app for mailing holiday greetings to a recipient of your choice. 

## Installation

This application was built using Spring Boot and Java 1.8+. It may run on earlier versions of Java but the app has only been tested on versions 1.8 and higher. The project uses Maven for package dependency so any required software packages should be downloaded automatically depending on your particular IDE configuration. Any external libraries not provided via Maven are included directly under the Resources folder. 

Because the application service makes calls to 3rd party mail providers, accounts and corresponding API keys are required to enable that functionality. When sending a message, the primary provider is called first, with a seamless failover to the second provider in the event that the primary fails for any reason. For this application, I chose [SendGrid](https://sendgrid.com/docs/API_Reference/api_v3.html) as the primary and [Amazon's SES](http://docs.aws.amazon.com/ses/latest/DeveloperGuide/Welcome.html) as the secondary, as both services offered good developer documentation and library support for their respective APIs.

## Usage

Before booting the service, make sure you add your SendGrid API key to the "sendgrid.mail.api.secret" property in the application.properties file. Amazon also requires API keys, but expects its credentials to be stored in a separate directory, outside of the project. See [this link](http://docs.aws.amazon.com/ses/latest/DeveloperGuide/create-shared-credentials-file.html) for more details. If you are utilizing Amazon's developer sandbox service, you will also need to verify recipient emails, prior to usage. If you prefer to use different mail providers, it should be fairly straightforward to swap one out for another. The application's notification service is abstracted away from the specific providers. You would simply need to create your own service that accepts the same arguments.  

Please note that this is a demo app only. For simplicity reasons, I utilized Apache's Tomcat servlet and Derby database for data storage. This means any data stored is non-persistent, and will be lost as soon as the service is shut down. Optionally, you can select a different database, however, since the code was only tested with Derby, additional code modifications may be required to retain the original functionality.

## To Do

Given the restricted timeline requirements of this project, I didn't have time to accomplish all that I would have liked. The project, as a whole, meets and exceeds all of the requirements listed in the challenge below. Additional time, however, would mean adding JavaDoc-style comments to all of the files. It also would have meant more extensive testing to ensure the application is as stable and secure as possible. Essentially, more time generally means a more highly-polished application that meets the high standards I've come to expect, both from a developer and user perspective. 

## History

Curious as to the origin of how this application came to be? This app was actually written in response to a project challenge presented by [Darius Houle](https://github.com/dariushoule/). From the perspective of a developer who's been doing this for many years now, I have to say that this challenge was very well put together. On the surface, it may seem like a simple app, but look more closely and you'll see it covers a broad spectrum of design challenges that programmers will typically encounter in their projects these days. Needless to say, I had a lot to accomplish in a very short period of time. But I did exactly that, and had a lot of fun doing it! 

## The Happy Holidays Email Application Challenge

A simple web application designed to deliver a happy holidays email to an arbitrary recipient and retain a history of messages sent.

### Technologies

- Spring Boot - http://projects.spring.io/spring-boot/
- A client side application framework such as Angular, Ember, or Vue.
- Relational Database Persistence (In-memory or filesystem based databases are fine for the purposes keeping complexity low. 
- Maven or Gradle for java package management. 

### Instructions

Develop a Spring Boot based application to host a restful API consumed by your client side application. 

The deliverable should consist of a single page web application with two tabs. The first tab should host a form that collects the information required to send a happy holidays email to an arbitrary recipient. Your API should be able to send this email to the recipient using one of two third party providers. The service should provide an abstraction between two different email service providers allowing for seamless failover. If one of the services goes down, your service should recover using a different provider without human intervention.

The second tab should show a historical view of emails sent to recipients. 

The application should be made available on public version control such as bitbucket or github. It should contain a readme with the instructions needed to run the application and configure sensitive values such as API keys. 

### Notes

This is the bare set of requirements for the project, but increasing consideration will be given to apps that go beyond the  minimum requirements. There are many improvements that can be made in the realms of testing, performance, asynchronous processing, security, new/enhanced features etc... It is up to you to decide how to demonstrate your best work. 

### Example Email Providers

- SendGrid
- Mailgun
- Mandrill
- Amazon SES

## Credits

Jeff Todnem @ Step41, LLC

## License

GNU GENERAL PUBLIC LICENSE
