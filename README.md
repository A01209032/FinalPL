## Programming Languages

# Final Project

# Samuel Ivan Ramirez Navarro



## Context of the problem:


Abandoned animals are a big problem in Mexico, there are many animals living

in the streets or in refuges waiting for people to adopt them. Because of this there are

many associations which promote adopting pets. They create campaigns to fertilize pets

for a low cost and promote the adoption of pets they take care of.

According to the Mexican Senate there are 28 million pets of which 70% are

abandoned. Associations like “Segunda Oportunidad Queretaro A.C” make campaigns

and share information regarding pets that are in adoption.


The development of an application to improve the awareness of the adoptable

animals and adoptions campaigns might improve the probability of those animals to find

a new home.


There are many applications in social media like Facebook or Instagram where

applications using image processing match the user's face with other people or fantasy

characters. These applications like Google Arts & Culture which matches people's faces

to paintings have gone viral and created a lot of attention. An app with the same idea,

matching people's faces to adoptable pets might create a similar reaction.


## Solution:


The possible solution is a web app designed to help animal adoption campaigns

and events. It has one main purpose: Match an adoptable dog with a possible adopter

using image processing. This app has an HTML5 interface and a backend running in

Java. The users will be able to upload a picture of a possible adopter and get a match

for an adoptable dog in the campaign.


The Java app will work as a server, communicating with hosts through GET

messages. Since it will have to communicate with multiple hosts at the same time it will

need to create a new thread for every request. A thread pool was implemented so the

host can handle up to 100 requests at the same time, any extra threads will be added to

a queue.

The user of the web app will be able to add adoptable animals to the animal

database in the server, delete adoptable animals in the server, configure the number of

threads used on the processing of images, get a list of saved animals in the server and

upload the image of a possible adopter to the server to get a match from the server.


The Java server has an Animal database, which saves the path to the picture of

the animal, the name, the description and the value given to the image by the Image

Processing. This database is implemented using the Singleton pattern so every new

thread connection access the same ​ _FaceDataBase_ object. The methods implemented

in the database are synchronized, because many threads may access the objects at the

same time.



The server processes the images using threads. It saves the pixels in a matrix

and assigns the configurable number of threads (default 4) to process the matrix. Every

thread is assigned to a row in the matrix and for every pixel it gets the Red, Green and

Blue value and divides it by 3. The server then adds up the value of every pixel and

divides it by the number of pixels in the image. The value is divided by the number of

pixels so any image, regardless of the size has a consistent value.





The interface will be running on HTML5 and it will need apache for php to run

properly. This web app will handle the requests for the server. It will upload the images

to the server and give the paths of the images to the Java app using GET messages.

This interface can be further developed by the associations using the application, so it

can be customized for each event.



A deployment option with further development is to run the PHP configuration

and the java app on a server, and have multiple computers in a local network on the

event running the app. This way many of the possible adopters will be able to use the

application and see which animals they match with. Currently the app is only made to

run on one computer which can be carried to an event and used to match the event

goers with animals on the database.


## Results:


The result of the project is a running web application working with the java

server. It is deployed in apache and can run all the use cases. The use cases were

tested using the interface and the server was tested using a Linux script.




The project is allocated in GitHub and has a GNU license, it was also given to an

association which works on dog adoptions. The program will be used when there is an

in-person campaign after Covid-19 restrictions stop. The GitHub releases can be found

on the evidence section.



## Conclusion:


The development of this project made me investigate the current image and face

processing applications in the market. My main idea was to implement face recognition

in order to more accurately match the faces of the adopter to the dogs. This idea was

soon discarded due to the lack of time for the development of this project.


The final solution which only matches pictures with the value of the colors is a

simple form of image processing, but it successfully accomplished the main purpose of

the project: to raise awareness of the animals in adoption. This app can be used to

spark the curiosity of possible animal adopters, maybe they do not adopt the animal

they match with, but they will be attracted to the event.


The success of this project when it is tested on a real-life event after the covid-19

restrictions end may spark other developers with the inspiration to create open-source

projects which help with the problem of animals in adoption. I hope this project can be

used to save the lives of many dogs and get them forever homes where they can be

happy and loved.


## Setup instructions:

### Requirements:

1) Have Java Installed

https://java.com/en/download/manual.jsp

2) Have Apache running with PHP

https://www.apachefriends.org/es/index.html

3) Have PHP file upload enabled

### Steps:

1) Copy ​ _www_ ​ folder to apache

2) Run Jar in a terminal with java -jar Final.jar

3) Open localhost (apache) and the webapp should be running


## Evidence.

The following message was sent to ​ **Segunda Oportunidad Querétaro A.C:**

Buenas tardes,

Soy Samuel Ramírez, un estudiante de ingeniería en sistemas computacionales del

Tecnológico de Monterrey. Este semestre desarrollé un programa con la idea de apoyar

campañas de adopción de animales. Este sistema sirve para emparejar la foto de

alguna persona con un animal en adopción. Te da un porcentaje de “Parecido” para

fomentar que se adopten los animalitos.

A continuación, le mando un video de cómo funciona el programa.

Este programa tiene una licencia de libre uso, pero me ofrezco a ayudarle a instalarlo y

montarlo voluntariamente si es que lo quisiera usar en una campaña de adopción. Me

encantaría ayudar a los animalitos y también ver mi proyecto en acción.

Le mando el sitio web donde está alojado el programa, ahí van a estar las instrucciones

para descargarlo e instalarlo en caso de que lo quieran probar.

Name Link
GitHub https://github.com/A01209032/FinalPL
Release v0.1 https://github.com/A01209032/FinalPL/rele
ases
Picture matching demonstration https://youtu.be/z6wufjWP6QU
Picture upload demonstration https://youtu.be/aYyJA21MVQE


## References:

Held, A. (2018, January 15). Google App Goes Viral Making An Art Out Of Matching
Faces To Paintings. Retrieved November 19, 2020, from
https://www.npr.org/sections/thetwo-way/2018/01/15/578151195/google-app-goes-
viral-making-an-art-out-of-matching-faces-to-paintings

República, S. (2018, November 27). Senado de la República. Retrieved November 19,
2020, from ​https://www.senado.gob.mx/64/gaceta_del_senado/documento/
