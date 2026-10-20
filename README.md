# Procesador de Contratos Públicos (XML a MySQL)

Este proyecto es una aplicación Java diseñada para la gestión y transformación de datos de contratos públicos procedentes de la Junta de Andalucía.

## Funcionalidades

1. Lectura eficiente de ficheros XML de gran tamaño utilizando **SAX Parser**.
2. Almacenamiento de datos en base de datos relacional (**MySQL**) utilizando **JDBC** y sentencias preparadas (PreparedStatement) para seguridad y rendimiento.
3. Generación de un nuevo fichero XML de salida filtrado (eliminando campos sensibles) utilizando **DOM Parser**.

## Tecnologías Utilizadas

* **Lenguaje:** Java (JDK 8+)
* **Base de Datos:** MySQL
* **Acceso a Datos:** JDBC (MySQL Connector/J)
* **Procesamiento XML:** JAXP (SAX para lectura, DOM para escritura)
* **IDE:** NetBeans

## Requisitos Previos

* Tener instalado Java y MySQL.
* Crear la base de datos ejecutando el script SQL incluido (`script.sql`).
* Configurar la conexión: copiar `db.properties.example` como `db.properties` (en la raíz del proyecto) y poner el usuario y la contraseña de MySQL. Este archivo está en `.gitignore`, así que las credenciales nunca se suben al repositorio. También se pueden usar las variables de entorno `DB_URL`, `DB_USER` y `DB_PASSWORD`.

## Ejecución

Ejecutar la clase principal `com.victoria.trabajo_final.Main` desde NetBeans. El programa lee `contratos.xml`, inserta los contratos en MySQL y genera `contratos_procesados.xml` sin los campos sensibles.
