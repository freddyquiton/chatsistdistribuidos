chatsistdistribuidos
====================

Proyecto de un chat para la materia de sistemas distribuidos

Implementamos un servidor y un cliente

Autores:
====================
Sandra Isabel Lara Navia
Pablo Sanabria Quispe

Requisitos
====================
Java 1.7.0 o superior
ant
git
Una computadora que ejecute java

Compilaci�n
====================
obtener la ultima versi�n del c�digo usando:
git clone https://github.com/Tailmon/chatsistdistribuidos.git
cd chatsistemasdistribuidos
ant

Dentro de la carpeta dist se encontr�r los ejecutables

Distribuci�n
====================

El cliente necesita de los siguientes archivos
dist/Client.jar
dist/common.jar

El servidor necesita de los siguientes archivos
dist/Server.jar
dist/common.jar

Ejecui�n:
====================
Servidor
java -jar Server.jar

Cliente
java -jar Client.jar (ipservidor)
ejemplo:
java -jar Client.jar 192.168.1.192

Problemas conocidos
====================
No esta implementado correctamente el cierre de pesta�as cuando una conversaci�n termina, asi que solo se puede chatear con una persona
hasta que cierren ambos los clientes.
Existen problemas cuando un cliente se intenta conectar mediante el uso de localhost, usar la IP real de la aplicaci�n

FAQ
====================
Puertos necesarios?
12345
12346



