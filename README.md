# LINK EJERCICIO 1: 
https://github.com/Jaime-Lopez-Diaz-Parcial-1-Eventos/Ejercicio-1-lista-tareas-pendientes.git

# LINK EJERCICIO 2:
https://github.com/Jaime-Lopez-Diaz-Parcial-1-Eventos/Ejercicio-2-lista-compra.git

# LINK EJERCICIO 3:
https://github.com/Jaime-Lopez-Diaz-Parcial-1-Eventos/MisTareas2.git


Jaime López Díaz

# Ejercicio 1 - Lista de Tareas Pendientes

## Descripción del Proyecto
Este proyecto es una aplicación de lista de tareas desarrollada en Android. La aplicación permite a los usuarios agregar, visualizar, eliminar y marcar como completadas sus tareas. Está diseñada con soporte para cambio de idioma (Español e Inglés) y night mode. Además, utiliza Firebase Firestore como base de datos remota y SQLite para almacenamiento local. Se implementa concurrencia mediante `ExecutorService` para mejorar la eficiencia en el acceso a la base de datos.

## Funcionalidades
- **Agregar Tarea**: Los usuarios pueden agregar nuevas tareas a la lista. Las tareas se almacenan en Firebase Firestore y en SQLite localmente.
- **Marcar Tarea como Completada**: Los usuarios pueden marcar tareas como completadas. Esto actualizará tanto la base de datos local como la remota.
- **Eliminar Tarea**: Los usuarios pueden eliminar tareas de la lista, lo cual también actualizará la base de datos.
- **Ver Tareas Pendientes y Completadas**: La lista se puede filtrar para ver solo tareas pendientes o solo tareas completadas.
- **Modo Oscuro**: Soporte para modo oscuro utilizando `SharedPreferences` para almacenar la preferencia del usuario.
- **Cambio de Idioma**: Cambio dinámico de idioma entre Español e Inglés. La aplicación reinicia la actividad actual para aplicar los cambios de idioma.
- **Concurrencia**: Las operaciones en la base de datos se ejecutan en hilos separados usando `ExecutorService` para evitar el bloqueo de la interfaz de usuario.

## Estructura del Proyecto

### Paquetes
- `activity`: Contiene las actividades principales de la aplicación (`MainActivity` y `SettingsActivity`).
- `adapter`: Contiene `TaskAdapter`, que es el adaptador para mostrar las tareas en una lista.
- `dataBase`: Contiene la configuración y los métodos para la base de datos local con `SQLiteHelper` y `TaskDao`.
- `domain`: Define el modelo `Task` para representar una tarea.

### Archivos XML Principales
- **`activity_main.xml`**: Layout principal de la aplicación.
- **`settings_activity.xml`**: Layout de la pantalla de configuración.
- **`task_item_pending.xml` y `task_item_completed.xml`**: Layouts para las tareas pendientes y completadas en la lista.

## Explicación de la Concurrencia Implementada
La concurrencia es una parte importante de este proyecto. Todas las operaciones de la base de datos que podrían bloquear el hilo principal (UI) se ejecutan en un hilo de fondo para asegurar una experiencia de usuario fluida. Para esto, utilizamos `ExecutorService`.

- **ExecutorService**: Configurado en `MainActivity` para manejar tareas de base de datos en segundo plano.
    - **Método `addNewTask()`**: Agrega una nueva tarea a Firebase y SQLite. Una vez que la tarea se añade a Firebase, `ExecutorService` se encarga de agregarla a SQLite.
    - **Método `loadTasks()`**: Carga las tareas de SQLite o Firebase. Si no hay tareas en SQLite, carga las tareas desde Firebase y las guarda en SQLite usando `ExecutorService`.
    - **Método `deleteTask()`**: Elimina la tarea de Firebase y luego de SQLite en un hilo de fondo.
    - **Método `markTaskAsDone()`**: Marca la tarea como completada en Firebase y SQLite en segundo plano.

## Explicación de la Lógica de Idioma
Para implementar el cambio de idioma, se utiliza un `Switch` en la actividad de configuración. Cuando el usuario cambia el idioma, el nuevo valor se guarda en `SharedPreferences` y se llama al método `setAppLanguage()` en `SettingsActivity`. Este método:

1. Cambia la configuración de `Locale` según el idioma seleccionado.
2. Actualiza la configuración de la aplicación y reinicia la actividad para aplicar el cambio de idioma.

El cambio de idioma afecta a toda la aplicación, permitiendo alternar entre Español e Inglés de forma dinámica.

## Explicación del Modo Oscuro
El modo oscuro se implementa utilizando `SharedPreferences` para guardar la preferencia del usuario y `AppCompatDelegate` para aplicar el tema seleccionado en toda la aplicación. El `Switch` en `SettingsActivity` permite activar o desactivar el modo oscuro, aplicando `MODE_NIGHT_YES` o `MODE_NIGHT_NO` según la preferencia del usuario.

