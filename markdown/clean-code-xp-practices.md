[Índice](../CSDT-2024.md)

# Clean Code
## Cumplen
* Escalable
* Principio menor asombro

## No cumplen
* **Entendible:** Existen funciones cuyo propósito no es claro, por ejemplo [getRecipeByUser](../src/main/java/com/yummyfoods/spring/controller/RecipeController.java), realiza una lógica sobre las páginas que es confuso.
    * Se recomienda renombrar variables y modificar la lógica mediante métodos auxiliares para comprender el funcionamiento.
* **Testeable:** El código no posee pruebas unitarias ni de aceptación.
    * Es necesario crear pruebas unitarias para verificar el correcto funcionamiento. 
* **Regla del Boy Scout:** No existe refactorización del código fuente.
    * Refactorizar el código es importante para mantenerlo legible.
* **Código Enfocado:** Existen métodos que realizan demasiadas funciones.
    * Separar esta lógica en métodos auxiliares.
* **Abstracción:** El método `doGet` en [ImageServlet.java](../src/main/java/com/yummyfoods/spring/servlet/ImageServlet.java) posee 74 líneas, siendo un método bastante extenso. 
    * Identificar que puede extraerse para disminuir la cantidad de líneas en el método y mejora la responsabilidad de la clase. 
* **Duplicidad:** En diferentes funciones de la clase [LoginController.java](../src/main/java/com/yummyfoods/spring/controller/LoginController.java) se repite un condicional para verificar si un atributo es nulo.
    * Utilizar métodos auxiliares para crear una sola vez el código necesario. 

# Principios
Debido a los puntos anteriores, los siguientes principios no se están cumpliendo:
* **KISS (Keep It Simple, ~~Stupid~~)**
* **DRY (Don't Repeat Yourself)** 

# XP Practices
* **Test-Driven Development (TDD)**: Aunque el código ya esté desarrollado, verificar mediante las pruebas es un beneficio porque permite identificar si la lógica está construida de forma correcta.
* **Refactoring:** Mediante la refactorización, se buscan mejorar la calidad general del código y hacerlo más legible sin alterar su comportamiento.
* **Continuous Integration:** Existe la necesidad de integrar los cambios realizados, por lo que después de cada compilación y construcción de código tenemos que integrar que todas las pruebas se ejecuten automáticamente para todo el proyecto.
* **Code Standards**: Contar con un estándar de código ayuda a mantener la consistencia en todo el código fuente, tanto actual como futuro. Esto facilita la realización de cambios y garantiza la calidad del código.
* **Collective Code Ownership**: Dado que no soy el autor del código, es fundamental adoptar un enfoque colectivo para realizar cambios, evitando culpar a individuos específicos.

