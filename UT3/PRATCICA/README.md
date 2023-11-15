# PRACTICA SERVIDOR CON 2 CLIENTES


## ARCHIVOS
- [Cliente.java](Cliente.java)
- [Servidor.java](Servidor.java)
- [Mensaje.java](Mensaje.java)
- [Persona.java](Persona.java)
- [config.properties](config.properties)

## COMO FUNCIONA
Primero, se debe iniciar el **servidor** con el comando `java /UT3/PRATCICA/Servidor.java`.

Una vez iniciado el **servidor**, se puede poner en marcha el inicio de los **clientes**. Puedes hacerlo mediante el comando `java /UT3/PRATCICA/Cliente.java` (recuerda que para su funcionamiento, se deben conectar dos **clientes**).

En los **clientes**, puedes poner los siguientes comandos:
- sms: Envia una cadena de texto
- persona: Te pide crear la persona y enviarla
- quit: Finaliza las conexiones

El **cliente emisor** le envia al servidor un mensaje de tipo **Mensaje** y el **servidor** lo analizará y le enviará al **cliente receptor** el mensaje.


## RECURSOS
He utilizado los siguientes recursos:

- https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html
    - Para controlar las excepciones del servidor

- https://es.stackoverflow.com/questions/33215/como-leer-un-properties-externo
    - Para investigar acerca de los .properties
    