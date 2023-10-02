
# VAGRANT

Este directorio contiene el archivo `vagrantfile` y las **unidades de trabajo**.

Guardo aquí las **unidades de trabajo** debido a que, gracias al `vagrantfile`, tengo un entorno de desarrollo portable, lo único que tengo que hacer el clonar el repositorio:
```
git clone https://github.com/AlejandroBA2023/alejandroba.git
```

Y montar el vagrant usando:
```
vagrant up
```


El archivo vagrant lo he configurado para que instale automaticamente el **jdk 17** y el **jre 17**

```sh
   config.vm.provision "shell", inline: <<-SHELL
     apt-get update
     apt-get install -y openjdk-17-jdk openjdk-17-jre
   SHELL
```