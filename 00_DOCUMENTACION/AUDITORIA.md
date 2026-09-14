# Auditoría técnica de reorganización

- Proyectos inventariados: **85**.
- Proyectos adaptados de bibliotecas absolutas a Maven: **18**.
- Proyectos con configuración sensible externalizada: **12**.
- Artefactos excluidos: repositorios Git anidados, `.metadata`, `target`, `bin`, `build`, `.class`, `.jar`, logs y temporales.
- Destino principal: Eclipse con JDK 21.

## Alcance de la validación

Se comprueban estructura, inventario, ausencia de artefactos excluidos, XML de `pom.xml`, `.project` y `.classpath`, rutas absolutas conocidas y patrones comunes de secretos. La compilación integral no se garantiza porque varios proyectos requieren bases de datos, servicios, dependencias o ficheros externos específicos.
