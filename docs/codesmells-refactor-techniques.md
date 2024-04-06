[Índice](../CSDT-2024.md)

## Code Smells :warning:
* Falta un archivo .gitignore para excluir archivos de construcción del control de versiones.
* La falta de documentación dificulta la construcción y ejecución del proyecto.
* La construcción del proyecto no se ejecuta correctamente.
* Se observa el uso de literales en lugar de constantes adecuadas. En [Email.java](https://github.com/JulianBenitez99/YummyFoods-CSDT/blob/f1ceebdc73f7c8204ee09008d37a568b7da57b0e/src/main/java/com/yummyfoods/utility/Email.java#L17)
* La indentación del código es confusa y dificulta la legibilidad.
* Los directorios no están organizados de manera adecuada.
* Se encuentran impresiones (prints) innecesarias en el código. En [RecipeController.java](https://github.com/JulianBenitez99/YummyFoods-CSDT/blob/f1ceebdc73f7c8204ee09008d37a568b7da57b0e/src/main/java/com/yummyfoods/spring/controller/RecipeController.java#L379) y otros.
* Existen comentarios irrelevantes o poco claros.
* La ausencia de pruebas dificulta la verificación y validación del código.
* Se utilizan modificadores redundantes en algunas partes del código. En [LoginService.java](https://github.com/JulianBenitez99/YummyFoods-CSDT/blob/f1ceebdc73f7c8204ee09008d37a568b7da57b0e/src/main/java/com/yummyfoods/spring/service/LoginService.java#L9) y otros.
* Se identifica código que podría resultar inseguro en términos de seguridad de la aplicación. En [UserDAOImpl.java](https://github.com/JulianBenitez99/YummyFoods-CSDT/blob/f1ceebdc73f7c8204ee09008d37a568b7da57b0e/src/main/java/com/yummyfoods/spring/dao/UserDAOImpl.java#L36).

## Técnicas de Refactoring :recycle:
* Remover código muerto
* Sustituir bucle por tubería
* Reemplazar literales con constantes
* Descomponer condicionales
* Consolidar expresiones condicionales
* Introducir aserción
* Sustituir Excepción por Precontrol


