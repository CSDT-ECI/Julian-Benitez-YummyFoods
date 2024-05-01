# Testing Debt
El proyecto no contiene ningún tipo de pruebas:
* Según los [commits](https://github.com/CSDT-ECI/Julian-Benitez-YummyFoods/commit/0e59c3bf927b553c0d5b7da4790a2fbe59ac4c89), como `final testing done, everything is working fine`, indica que se realizaron pruebas manuales.
* Existe complejidad en generación de pruebas debido a la falta de desacoplamiento y la dependencia de servicios externos.
* No se maneja el método de Test Driven Development (TDD).


## Pruebas y Escenarios
* Se implementaron pruebas unitarias para la clase `UserDAOImpl` mediante mocks.
* Se implementaron pruebas unitarias para la clase `UserChangePasswordValidator`.
    * Estas pruebas pueden ser replicadas para las demás clases de validación y DAO.
* Se puede implementar pruebas de integración para verificar el correcto funcionamiento de los controladores y servicios.

## Mejoras Propuestas

### Capa Innecesaria
Podría eliminarse una capa de abstracción en el código, por ejemplo en la clase `LoginServiceImpl`:
```java
@Override
@Transactional
public List<Login> login()
{
    return loginDAO.listLogin();
}
```

Las clases `Service` únicamente llaman a los métodos de los `DAO`, por lo que se podría eliminar la capa de `DAO`, dado que el nombre (Data Access Object) no corresponde con la acción que realizan, y que la clase `Service` realice la acción directamente.

Esto simplificaría el código y facilitaría la creación de pruebas.

### Desacoplamiento
Para la clase [Email](../src/main/java/com/yummyfoods/utility/Email.java), el código quemado y las dependencias en el mismo método hacen complicado las pruebas unitarias. Por ejemplo: 

```java
public static ClientResponse sendEmail(String to,String subject,String body,String from) {
    ...
    client.addFilter(new HTTPBasicAuthFilter("api", "key-87gm0tkpfhb1eel7q7regznhf75ntl44"));
    WebResource webResource=client.resource("https://api.mailgun.net/v2/yummyfoods.mailgun.org/messages");
    ...
}
```

Se podría extraer la dependencia de `Mailgun` a una clase aparte, y utilizar inyección de dependencias para facilitar las pruebas. Además de esto, se podría utilizar un archivo de configuración para el API Key.

### Extraer métodos
La clase [ImageServlet](../src/main/java/com/yummyfoods/servlet/ImageServlet.java) contiene un método muy largo que podría ser extraído en varios métodos más pequeños. Facilitando la lectura y la creación de pruebas.

### Pruebas de Cobertura
Se podrían implementar pruebas de cobertura para verificar que se están probando todos los caminos posibles del código.

### Pruebas de Integración
Se podrían implementar pruebas de integración para verificar el correcto funcionamiento de los servicios.

### Ejecución Automática
Se podría implementar un sistema de integración continua para ejecutar las pruebas automáticamente en cada commit.

[🏠](index.md) |[anterior](clean-code-xp-practices.md) | [siguiente](static-analysis.md)
