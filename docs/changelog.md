[Índice](../CSDT-2024.md)
# Bitácora de Desarrollo

## Cambios y Refactors

### Unit Tests

- **Descripción:** Se agregaron pruebas unitarias.
- **Motivación:** No existían pruebas unitarias en el proyecto.
- **Archivos Afectados:**
  - [UsersDAOImplTest](../src/test/java/com/yummyfoods/spring/dao/UsersDAOImplTest.java)
  - [UserChangePasswordValidatorTest](../src/test/java/com/yummyfoods/spring/validator/UserChangePasswordValidatorTest.java)
- **Resultado Final:** Al agregar pruebas unitarias se logra mejorar la calidad del código y se facilita la detección de errores.

### Uso de Tomcat
- **Descripción:** Se eliminó el uso de Jetty desplegado y se cambió por Tomcat como plugin de Maven.
- **Motivación:** El realizar debugging en Jetty no embebido es más complicado. Usar Tomcat como plugin de Maven facilita la ejecución de la aplicación.
- **Archivos Afectados:**
  - [pom.xml](../pom.xml) 
- **Resultado Final:** Permite desplegar la aplicación de manera más sencilla y realizar cambios de forma más rápida.
- [commit](https://github.com/CSDT-ECI/Julian-Benitez-YummyFoods/pull/5/commits/77e1d0d363288d9b2c973326481dca57605efd8b)

### Refactorización de Código (EmailService)
- **Descripción:** Se refactorizó el código para mejorar la legibilidad y mantenibilidad.
- **Motivación:** El código original era difícil de entender y mantener.
- **Archivos Afectados:**
  - [EmailService.java](../src/main/java/com/yummyfoods/spring/service/EmailService.java)
- **Resultado Final:** Se logra mejorar la calidad del código y facilitar futuras modificaciones.
- [commit](https://github.com/CSDT-ECI/Julian-Benitez-YummyFoods/pull/5/commits/a1d80fa596d3356464d58f0345326a8744a962b7)

### GitHub Actions
- **Descripción:** Se agregó un flujo de trabajo de GitHub Actions para construir, probar y analizar la aplicación.
- **Motivación:** Facilitar la integración continua y asegurar la calidad del código.
- **Archivos Afectados:**
  - [GitHub Actions](../.github/workflows/build-test-analyze.yml) 
- **Resultado Final:** Se logra obtener un feedback rápido sobre la calidad del código y se facilita la colaboración en el proyecto.
