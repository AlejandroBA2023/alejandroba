# PRÁCTICA 1

Crear un programa que:
1. Lanza el proceso independiente `esperarNsegundos.sh`
2. Escriba por consola [letras random](https://www.baeldung.com/java-random-string) hasta que el proceso independiente haya finalizado.

El script `esperarNsegundos.sh` recibe como argumento un entero, el número de segundos a esperar antes de morir.

---
Me he dado cuenta de un error proveniente del archivo `esperarNsegundos.sh`: 

El error consiste en que si se hace alguna modificacion y se guarda desde un SO Widows, es muy probable que deje de funcionar.

Investigando, me he dado cuenta de que se puede arreglar mediante el siguiente comando:
```sh
sed -i -e 's/\r$//' esperarNsegundos.sh
```