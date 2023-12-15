# Codigo ~~70~~ ~~95%~~ Completado

~~Esta documentacion esta enfocada al futuro del programa ya que el servidor aun no esta terminado y falta agregar la logica a las distintas peticiones del cliente.~~

~~A falta de pequeñas refactorizaciones para terminar con el programa en este momento el codigo funciona correctamente pero aun hay perqueñas cosas que se iran mejorando poco a poco~~

El codigo a dia de hoy esta completamente terminado, se podrian hacer algunas mejoras que tenemos contempladas pero por ahora es una version estable que funciona bastante bien

# Manual de usuario
Para ejecutar el servidor (vease [Cambios antes de ejecutar](#cambios-antes-de-ejecutar) antes de ejecutar) se necesita introducir a la maquina que va a ejecutar el servidor en una red en la cual no haya ningun servidor dhcp para que no haya conflicto entre ellos y una maquina windows/ubuntu. Utilizaremos el siguiente comando para ejecutarlo:

```java
javac Sdhcp.java 
java Sdhcp.java
```

Al conectarse a la red desde otra maquina esta se conectara a nuestro servidor para que le de una IP entre el rango preselecionado siempre y cuando en la interfaz de red del cliente este habilitado la IP automatica por servidor DHCP que viene de fabrica en todas las maquinas Windows y en practicamente todas las maquinas Linux igualmente seria bueno comprobarlo por si no estuviese habilitado

## Cambios antes de ejecutar

El usuario puede y debe cambiar la ip del servidor antes de ejecutarlo que debera ser la misma ip que tiene la maquina donde se ejecuta esto debera cambiarse en la linea 15 que seria la siguiente:
```java 
private static final String IPSERVER = "Tu ip";
```
Tambien puede y debe cambiar el broadcast al de su red en la linea 180 que seria la sigueinte:
```java 
InetAddress BROADCAST = InetAddress.getByName("Tu broadcast");
```
Por ultimo usted puede cambiar tanto la puerta de enlace y dns que se va a ofrecer al cliente asi como la mascara que debera cambiar en funcion de su direccion ip todo esto lo podra cambiar en las lineas 17, 18 y 19 que son las siguientes:
```java 
private static final String ROUTER = "Tu puerta de enlace";
private static final String MASK = "Tu mascara";
private static final String DNS = "Tu dns";
```
Mas adelante en la parte de [TODO](#TODO) se especifan mejores a futuro en estas partes del codigo, aparte varias mas

## Errores
Los errores/limitaciones del codigo son: 
- El servidor esta configurado para atender como maximo a 10 clientes o mejor dicho para antender 10 peticiones ya que las ips una vez dejan de usuarse el servidor no las vuelve a poner "disponibles" y llega un momento que las ips se terminan dando un error en el servidor

## TODO
En un futuro se solucionaran los errores añadiendo una refactorizacion a la logica que carga las ips y las asignan para que compruebe si una ip esta en uso por ejemplo eviando un `ping` y si no lo esta la ponga en disponible y tambien se añadiran todos los [Cambios antes de ejecutar](#cambios-antes-de-ejecutar) para que antes de ejecutar el codigo el usuario ya sea mediante una entrada estandar o por parametros se introduzcan. A largo plazo quizas hagamos un pequeño formulario en el cual introducir estos datos de manera grafica

## Realizacion de pruebas
Utilizando la clase cliente se podra hacer varias pruebas en el caso de querer modificar el servidor se podra utilizar esta clase para simular la conexion de un cliente cambiando en las lineas 15 donde especifica el comentario en funcion necesite el usuario:
```java
byte[] mensaje = {
                0x01,  // OpCode: Boot Request
                0x01,  // Hardware Type: Ethernet
                0x06,  // Hardware Address Length: 6 (para direcciones MAC)
                0x00,  // Hops: 0
                
                // Transaction ID (4 bytes): Puedes generar un ID único para la transacción
                (byte) 0xDE, (byte) 0xAD, (byte) 0xBE, (byte) 0xEF,  
                
                0x00,  // Seconds Elapsed: 0
                0x00,  // Bootp Flags: 0 (no se especifica ninguna bandera)
                
                // Cliente IP Address (4 bytes): 0.0.0.0 (ya que es una prueba para DISCOVERY/REQUEST)
                0x00, 0x00, 0x00, 0x00,  
                
                // Tu dirección MAC (6 bytes): Reemplaza con la dirección MAC real del cliente
                0x00, 0x11, 0x22, 0x33, 0x44, 0x55,  
                
                // Padding (10 bytes): Para que el mensaje alcance el tamaño mínimo
                0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,  
                
                // Magic Cookie (4 bytes): Identifica el inicio de las opciones DHCP
                (byte) 0x63, (byte) 0x82, (byte) 0x53, (byte) 0x63,  
                
                0x35,  // Código de opción
                0x01,  // Longitud de la opción
                0x01   // Valor de la opción cambiar a 0x01 para DISCOVERY y 0x03 para REQUEST
            };
```
Y lo ejecutaremos de la siguiente forma:

```java
javac Cliente.java 
java Cliente.java
```

## Autores

@AlejandroBA2023 @AntonioMartinSosa

