# EXAMEN

## Enunciado
Se pide generar un programa principal que lance 2 procesos independientes:

1er proceso: Deberá esperar 10 segundos y luego escribir en disco un archivo vacío con nombre "uno_acabo.txt"

2º proceso: Deberá recibir 2 números, sumarlos y devolverlos al proceso que lo llamó.

El proceso principal recogerá el resultado y lo imprimirá en pantalla con tu nombre completo.

Recuerda: esto va a ser ejecutado en un entorno Debian por lo que deberás tomar las debidas precauciones.

## Mi RESOLUCION

El **programa principal** prepara 2 procesos con `ProcessBuilder`: *pb1* y *pb2*.

```java
public class Main{
    public static void main(String[] args) {
        try {
            String suma;
            String nombre = "Alejandro Bermejo Álvarez";

            // Primer proceso
            ProcessBuilder pb1 = new ProcessBuilder("/bin/bash", "proceso1.sh");
            Process p1 = pb1.start();

            // Segundo proceso
            ProcessBuilder pb2 = new ProcessBuilder("java", "Proceso2", "1", "2");
            Process p2 = pb2.start();
            p2.waitFor(); // Espero a que el programa termine

            suma = p2.inputReader().readLine(); // Capturo la salida del programa

            // Imprimo el resultado
            System.out.println("El resultado es: " + suma + "\nMi nombre completo es: " + nombre);


        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
```



### Proceso 1
El primer proceso lo he hecho con un script de linux, ya que es el SO donde se va a ejecutar. Es un script muy secillo:

```sh
#!/bin/bash
sleep 10;
touch uno_acabo.txt;
exit 0;
```

Simplemente espera 10 segundos con `sleep` y crea un archi con `touch`.

---

### Proceso 2
El segundo proceso está creado en java. Es un programa muy sencillo que recoge dos argumentos enviados por parámetros a traves del **args**. Realiza una suma y realiza la salida con `System.out.println()`. 

```java
public class Proceso2 {
    public static void main(String[] args) {
        // Recoge dos numeros mediante args
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        // Realiza e imprime la suma
        int suma = num1 + num2;
        System.out.println(suma);
    }
}
```
