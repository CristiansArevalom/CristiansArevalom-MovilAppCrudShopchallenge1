# PAPERMANIA
## RETO 1 – APLICACIONES MOVILES
 Este proyecto es una aplicación Android para la gestión de una papelería ficticia llamada PaperMania. La aplicación permite gestionar productos y usuarios, utilizando Firebase para la autenticación y Firestore para el almacenamiento de datos, complementado con SQLite para almacenamiento local. Las funcionalidades clave incluyen registro de usuario, inicio de sesión, añadir, actualizar, eliminar y mostrar productos. La aplicación está desarrollada en Java usando Android Studio.

## Stack Tecnologico 💻
- [Java](https://www.java.com/es/)
- [Firebase ](https://spring.io/projects/spring-data-jpa)
- [SqlLite](https://www.sqlite.org/)
- [Maven](https://maven.apache.org/)
- [Android Studio](https://developer.android.com/studio?hl=es-419)

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

## Funcionalidades Clave.
- Registro y Autenticación de Usuarios: Permite la creación de cuentas de 
usuario y el inicio de sesión seguro.
- Gestión de Productos: Incluye funcionalidades para añadir, actualizar, 
eliminar y visualizar productos en el inventario.

![Autenticación](/Assets/login error.gif)
![Registro](/Assets/menu-registro.gif)
![Menu Producto](/Assets/menu-producto.gif)

## Diseño del Sistema
- a. Arquitectura de la Aplicación.
    La aplicación opera en una arquitectura cliente-servidor, donde el cliente es 
    la aplicación de Android y el servidor es Firebase. La aplicación funciona con Firebase para autenticación y almacenamiento de datos además la aplicación se maneja con un modelo MVP.
### Componentes Principales:
        • Firebase Authentication: Maneja el registro e inicio de sesión de 
        usuarios.
        • Firestore: Almacena y gestiona datos de productos y pedidos.
        • SQLite: Almacenamiento local para datos offline


## Instalación
1. Abre una terminal y navega al directorio donde quieres clonar el proyecto.
2. Ejecuta el siguiente comando:

    ```bash
    git clone https://github.com/CristiansArevalom/CristiansArevalom-MovilAppCrudShopchallenge1.git
    ```

3. Navega al directorio del proyecto clonado:

    ```bash
    cd MovilAppCrudShopchallenge1
    ```

### Abrir el proyecto en Android Studio

1. Abre Android Studio.
2. Selecciona **File > Open...** y navega al directorio del proyecto clonado.
3. Selecciona la carpeta del proyecto y haz clic en **OK**.

### Configurar el entorno

1. Android Studio debería descargar e instalar automáticamente las dependencias del proyecto.
2. Asegúrate de que el SDK de Android y las herramientas de construcción estén actualizados:
    - Ve a **File > Project Structure**.
    - Selecciona **SDK Location** y asegúrate de que la ruta del SDK de Android sea correcta.
    - Ve a **Tools > SDK Manager** y asegúrate de que las versiones necesarias del SDK de Android estén instaladas.

## Ejecutar el proyecto

1. Conecta un dispositivo Android a tu computadora o inicia un emulador.
2. Selecciona **Run > Run 'app'** o haz clic en el botón de ejecución (ícono de un triángulo verde) en la barra de herramientas.

## Estructura del proyecto

- **Model**: Clases que representan los datos (por ejemplo, `Product`).
- **View**: Interfaces y actividades que interactúan con el usuario (por ejemplo, `MainActivity,LoginActivity,ProductActivity,RegisterActivity`).
- **Presenter**: Clases que manejan la lógica de negocio y median entre el modelo y la vista (por ejemplo, `ProductPresenterImpl,IProductPresenter`).
- **DAO**: Clases que manejan las operaciones de base de datos (por ejemplo, `ProductDAO`).
- **Database**: Clases que configuran y gestionan la base de datos (por ejemplo, `DataBaseHelper`).

## License

MIT