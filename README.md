# Workspace docente reorganizado: ADT y PSP

Conjunto de **85 proyectos Java** reorganizados para uso docente, principalmente con **Eclipse y JDK 21**.

## Organización

- **ADT / RA1:** 26 proyectos
- **ADT / RA2:** 9 proyectos
- **ADT / RA3:** 4 proyectos
- **ADT / RA5:** 1 proyectos
- **ADT / RA6:** 2 proyectos
- **PSP / RA1:** 3 proyectos
- **PSP / RA2:** 22 proyectos
- **PSP / RA3:** 16 proyectos
- **TRANSVERSALES / ADT-PSP:** 2 proyectos

La carpeta `00_DOCUMENTACION` contiene el catálogo completo, la guía de importación, el mapa orientativo de RA/CE, la selección canónica, el plan de modernización y la auditoría técnica. Cada proyecto incluye su propia ficha `README.md`.

## Criterios aplicados

- Separación por módulo, RA y bloque temático.
- Conservación del código fuente y recursos útiles.
- Eliminación de compilados, carpetas de salida, repositorios Git anidados y bibliotecas binarias.
- Sustitución de rutas absolutas a JAR por Maven en 18 proyectos.
- Externalización de configuraciones sensibles en 12 proyectos.
- Metadatos Eclipse conservados o adaptados cuando existían.

Consulte primero `00_DOCUMENTACION/GUIA_ECLIPSE.md`.
