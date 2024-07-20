Roger Romero Gonzalez - Code Challenge Yape (Android Mobile Developer)


Resumen:
A continuación, un resumen listado las características principales a destacar del proyecto propuesto para la resolución del challenge propuesto:

Clean Architecture:
El proyecto fue estructurado bajo el principio de Clean Architecture, organizando el código en 3 capas principalmente presentation (código relacionado a UI), domain (código relacionado al funcionamiento del negocio) y data (código relacionado a datos, modelos e interfaces). Adicionalmente se agregaron otras capas más específicas propias del entorno de Android como por ejemplo la capa framework entre otras. Esto nos permite que el proyecto tenga una mejor organización, legibilidad y por lo tanto será más fácil su mantenimiento.

Compose
El proyecto utiliza tanto compose que es el nuevo framework oficial para el desarrollo de UI como XML la manera tradicional de crear interfaces. Se agregaron ambas formas para la creación de la UI para mostrar el conocimiento en código legacy como migración o nuevos desarrollos con compose.
La aplicación simula una migración del sistema convencional de XML con fragmentos donde uno de ellos, el de detalles que es el que tiene más información fue creado con compose, los demás están hechos de XML, de esta forma se demuestra la forma de migración de un proyecto con fragmentos a compose.

Fragmentos
La aplicación utiliza fragmentos para facilitar la reutilización de pantallas, envió de datos y aislamiento de componentes. Los fragmentos se basan en una clase base que define el comportamiento compartido que estos tendrán y además define métodos más claros sobre las tareas comunes de los fragmentos como por ejemplo crear la vista y configurar los observables.

MVVM:
El proyecto se desarrolló con la arquitectura Model-View-ViewModel (MVVM) ya que es la arquitectura oficial recomendada, está arquitectura consta de Modelo (representación de los datos), vista (es la ui) y viewmodel (se encarga de preparar el modelo y notificar a la ui), está arquitectura destaca por su característica reactiva donde la UI se suscribe a eventos en tiempo real dentro del view model que notifica los cambios en tiempo real. La clase view model utilizada es la oficial por lo que cuenta con soporte para Android por lo que está diseñado para manejar el ciclo de vida de las Activities así como los cambios de configuración.

Patrones de diseño
En el proyecto se utilizaron diferentes patrones de diseño oficiales recomendados dentro de los cuales los que se pueden destacar es el patron Repository, Inyection de Dependencias, Singleton, Factory, Observer, Adapter, Builder, State.

Dependency Injection
En el proyecto se utilizó este patron para definir las instacias que deberan de ser proporcionadas a las clases que las necesiten como repositorios y data sources, esto nos facilita el testing  el manteamiento del proyecto.

Hilt
Para la inyección de dependencias se utilizó hilt que es un framework basado en Dagger y que además es el framework oficial recomendado para inyección de dependencias en android por lo que cuenta con soporte para clases de android como Actividades y fragmentos.

Programación Reactiva
Se utilizo un enfoque reactivo en la aplicación por lo que cuando algún live data sufre algún cambio la interfaz es notificada en tiempo real lo que nos permite estar visualizando siempre los datos más recientes. En el caso de compose se utilizó el mismo principio, pero en este caso se utilizó las clase Mutable Sate que es la clase correspondiente para que se ejecute la recomposición cuando algún valor observado modifique su valor lo cual ocasiona que compose vuelva a ejecutar los composables correspondientes para actualizar la UI.

Servicios Web
Se creo un servidor REST mock en linea para la definición de los servicios web, la respuesta de este es en formato json y por el lado de la aplicación se utilizó Retrofit para consumir los servicios web y Gson para la serialización y deserialización del json a las clases correspondientes.

Corrutinas
Para las operaciones en segundo plano se utilizaron corrutinas de kotlin, ya que estas nos permiten ejecutar tareas asíncronas y una vez que tenemos el resultado esperado podemos notificar el hilo principal sobre los detalles a mostrar, de esta manera el hilo principal solo se encarga de mantener la interfaz actualizada y sin ningún tipo de delay.

View binding
Se utilizo view binding para el acceso a los elementos de la interfaz, de esta manera se evitan errores de referencia y errores de tipos de vistas, ya que por cada XML se crea una clase correspondiente con todo los view que este XML contenga y además esta clase se actualiza conforme se modifique el XML.

Data binding
Se utilizo data binding para ligar las entidades a la vista y de esta manera mostrar directamente los valores de esta entidad definido dentro de la misma UI.

Material Design 3
Se utilizo materia design 3 que es el framework recomendado para el estilo de la aplicación, así como tamaños, elementos interactuables, colores etc.

Pruebas Unitarias
Se agregaron pruebas unitarias realizadas en jUnit para testear el comportamiento de los repositorios. Estas pruebas cuentan con soporte para testear corutinas o procesos en segundo plano.

Pruebas De Integración
Se agregaron pruebas de integración en jUnit para testear el correcto funcionamiento del enpoint asi como las datos devueltos por el mismo, estas pruebas son cruciales ya que la aplicación depende principalmente de los datos devueltos por el servidor por lo que estas pruebas nos ayudan a identificar cuando los datos devueltos por el servidor cuenten con alguna inconsistencia. Estas pruebas al ser de integración se ejecutan directamente en el dispositivo lo que nos asegurar resultados más realistas. Estas pruebas cuentan con soporte para testear corrutinas o procesos en segundo plano.

Google Maps SDK
Para poder mostrar la ubicación dentro de la aplicación se utilizó el sdk de google maps que es el recomendado por lo que el proyecto cuenta con el setup necesario para la integración del sdk asi como de google cloud.

Clases Base
Se crearon clases base tanto para actividades como para fragmentos para definir el comportamiento base que deberán de tener estas clases y también se agregaron métodos de conveniencia que facilita la legibilidad y el mantenimiento.

Result Data
Se creo una clase para encapsular las respuestas del servidor que simplifica las peticiones al servidor ya que esta clase puede ser de dos tipos: éxito y error.
En el caso de ser éxito contiene los datos de la petición y en el caso de ser error contiene los datos correspondientes del error, esto permite que el manejo de las peticiones al servidor ser más fácil de entender.

Git
El proyecto está contenido dentro de un repositorio esto para el correcto mantenimiento de las versiones del mismo: 
url del repostitorio: https://github.com/rromerodev3/CodeChallengeYape.git

Gitflow   
El repositorio del proyecto utiliza Gitflow como estrategia para el manejo de branching, esta estrategia define ramas para versiones específicas como por ejemplo development, feature y release. Una vez que una versión esta lista se crea un tag con la version correspondiente, toda la elaboración del challengue fue realizada con gitflow y se podrá validar al analizar el repositorio.
url del repostitorio: https://github.com/rromerodev3/CodeChallengeYape.git


Extra

----------------------------------------------------------------------------------------------- 

CI/CD

En el proyecto se agregó integración continúa utilizando github actions de manera que cada vez que se realiza un pull request hacia la rama development, se ejecuta un build del proyecto para validar que el pull request no contiene errores de compilación y así evitar inyectar bugs previos a despliegue.

Aunque los archivos estan en el proyecto esta funcionalidad solo está disponible en el repositorio desde github ya que es donde se ejecuta el build previo al request, esta es la url del repostitorio: https://github.com/rromerodev3/CodeChallengeYape.git



Disclaimer!

---------------------------------------------------------------------------------------------------- 

En este proyecto se utilizó un conjunto variado de estrategias, arquitecturas, patrones, etc, con el único fin de demostrar el dominio de las mismas, sin embargo, en una aplicación en producción no es recomendable mezclar todas las tecnologías anteriormente mencionadas sino más bien, realizar un análisis de arquitectura para definir cuáles serán las estrategias que deberán ser utilizadas para el proyecto por ejemplo si un proyecto se desarrollara en compose no es recomendable utilizar fragmentos y algunos programadores prefieren no utilizar data binding ya que complica la interfaz. 