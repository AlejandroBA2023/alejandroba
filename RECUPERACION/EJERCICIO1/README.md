# Ejercicio multiproceso

## Enunciado: 
Partiendo de "El quijote" deberás crear un programa que:

Cada pulsación de tecla deberá lanzar un subproceso que cuente el número de caracteres pulsados que tiene el quijote. Esto es, si alguien pulsa la "a", se lanzará un subproceso que abrirá el quijote y contará todas las letras "a" que hay. Al finalizar mostrará por pantalla de forma asíncrona el caracter pulsado y las repeticiones que tiene en formato "carácter a => 4320".

Para finalizar el programa se escribirá el comando:
+ ":q" para salir matando todos los subprocesos pendientes.
+ ":wq" para esperar a todos los subprocesos, guardar resultados en "resultados.txt" y salir.

## Resolucion:

He creado 2 archivos, **Main.java** y **LeerQuijote.java**

### Main.java

Contiene la estructura del programa principal y una clase adicional llamada **HiloGestionador** que implementa **Runable**

### LeerQuijote.java

Es el programa que se encarga de buscar un caracter en el archivo **el_qujote.txt** y mostrar la cantidad total de ese caracter que hay en el archivo

## Como usar el programa:

Para usar el programa, primero se debe compilar:

`javac *.java`

Despues, se debe ejecutar el programa Main:

`java Main`

Una vez ejecutado, puedes poner un caracter (si pones dos caracteres juntos, solo contará el primer caracter) para que lo cuente y lo muestre por la terminal.

Si introduces `:q`: El programa parará todos los procesos anteriores y se cerrará

Si introduces `:wq`: El programa esperará a que todos los procesos finalicen, pero la salida no se mostrará; en cambio, se guardará la salida en **resultados.txt**