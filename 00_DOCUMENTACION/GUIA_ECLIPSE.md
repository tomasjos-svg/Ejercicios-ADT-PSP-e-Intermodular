# Guía de uso en Eclipse

## Requisitos recomendados

- Eclipse IDE for Java Developers o Enterprise Java and Web Developers.
- JDK 21 configurado en **Window > Preferences > Java > Installed JREs**.
- Maven integrado (m2e), incluido habitualmente en Eclipse.
- Docker Desktop y los motores de base de datos solo para los proyectos que los necesiten.

## Importar un proyecto

1. Descomprima el paquete en una ruta corta, por ejemplo `C:\DAM\workspace-docente`.
2. En Eclipse seleccione **File > Import**.
3. Para proyectos Maven use **Maven > Existing Maven Projects**; para los demás, **General > Existing Projects into Workspace**.
4. Seleccione una carpeta de proyecto concreta. Evite importar los 85 a la vez en el workspace de trabajo diario.
5. En proyectos Maven ejecute **Maven > Update Project**.
6. Compruebe **Project > Properties > Java Build Path** y el JDK 21.

## Forma de trabajo recomendada

- Cree un workspace de Eclipse por módulo o unidad.
- Importe únicamente los ejemplos y ejercicios que vaya a utilizar.
- Duplique el proyecto antes de preparar una versión para el alumnado.
- Use variables de entorno para contraseñas y claves; nunca las publique en Git.
- Revise puertos y URLs de Oracle, MySQL, MongoDB y servicios REST antes de ejecutar.

## Diagnóstico rápido

- **JRE System Library incorrecta:** configure JDK 21 y ajuste el *Compiler compliance level*.
- **Dependencias rojas:** en Maven, ejecute *Update Project* y compruebe la conexión a Internet.
- **Proyecto no detectado:** importe desde la carpeta que contiene `.project` o `pom.xml`.
- **Puerto ocupado:** cambie el puerto local o detenga el servicio que lo utiliza.
- **Conexión rechazada:** compruebe contenedor, hostname, puerto, servicio/PDB y credenciales.
