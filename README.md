El enunciado de la entrevista era el siguiente:
	
Realizar e implementar un sistema que gestione cuestionarios y recoja las respuestas

Hay 3 tipos de preguntas:
	
 	Las preguntas de valoración de 5 estrellas:

		Creación:
			 · Mostrar el título
			 · El valor de las respuestas puede ir del 1 al 10, si no se especifica irá de 1 a 5 por defecto.
		
		Respuesta:
			 · Se enviará solo el resultado seleccionado


	Las preguntas de Selección múltiple:	
		
		Creación:
			 · Mostrar el título
			 · Opción de respuesta múltiple (mínimo 2)
		
		Respuesta:
			 · Se enviará uno o más valores seleccionados
	
	Las preguntas de selección única:
		Creación:
			 · Mostrar el título 
			 · Opción de respuesta múltiple (?)

		Respuesta:
			 · Se enviará solo el resultado seleccionado	

API Specification:

		PUT: /question/{id:uuid}: Crea una nueva pregunta con los detalles especificados arriba
		POST: /question/{id:uuid}: Responde una pregunta dado su ID

	Ambos endpoints deberían soportar una solicitud flexible adecuándose a los requisitos de cada tipo de pregunta
El sistema implementa una API REST para crear y responder preguntas únicamente de valoración, las cuales permiten a los usuarios calificar en una escala numérica del 1 al 10, aunque si no se especifica rango al crear la pregunta, será del 1 al 5.

El proyecto sigue la siguiente arquitectura típica de un proyecto Spring Boot:

	com.interview.questionnaireManagement/
	├── Controller/
	│   └── QuestionController.java
	├── DTO/
	│   ├── ApiResponseDTO.java
	│   ├── CreateMultiSelectQuestionDTO.java
	│   ├── CreateRatingQuestionDTO.java
	│   ├── CreateSingleSelectQuestionDTO.java
	│   ├── MultiSelectAnswerDTO.java
	│   ├── QuestionResponseDTO.java
	│   ├── RatingAnswerDTO.java
	│   └── SingleSelectAnswerDTO.java
	├── Entities/
	│   ├── MultiSelectQuestion.java
	│   ├── RatingQuestion.java
	│   └── SingleSelectQuestion.java
	├── Repositories/
	│   ├── MultiSelectQuestionRepository.java
	│   ├── RatingQuestionRepository.java
	│   └── SingleSelectQuestionRepository.java
	├── Service/
	│   ├── MultiSelectQuestionService.java
	│   ├── QuestionTypeFactory.java
	│   ├── RatingQuestionService.java
	│   └── SingleSelectQuestionService.java
	└── QuestionnaireManagementApplication.java
Donde tenemos:

	1. Capa de Entidades (Entities)
	
	RatingQuestion: Preguntas de valoración con rango personalizable (1-10)
	MultiSelectQuestion: Preguntas con múltiples opciones seleccionables
	SingleSelectQuestion: Preguntas con una sola opción seleccionable
	
	2. Capa de Repositorios (Repositories)
	
	Implementan almacenamiento en memoria usando HashMap<UUID, Entity>
	Proporcionan métodos para guardar, buscar y verificar existencia
	Cada tipo de pregunta tiene su propio repositorio
	
	3. Capa de Servicios (Service)
	
	Servicios específicos para cada tipo de pregunta:
	
	Validación de datos
	Lógica de negocio específica
	Interacción con repositorios
	
	QuestionTypeFactory: Implementa el patrón Factory
	
	Centraliza la creación y respuesta de diferentes tipos de preguntas
	Maneja distintos tipos mediante un enum QuestionType
	Delega a los servicios específicos
	
	4. Capa de DTOs (Data Transfer Objects)
	
	DTOs de creación para cada tipo de pregunta
	DTOs de respuesta para cada tipo de pregunta
	ApiResponseDTO: Estandariza el formato de respuesta
	
	5. Capa de Controladores (Controller)
	
	QuestionController: Controlador unificado
	
	Expone endpoints para todos los tipos de preguntas
	Usa el patrón REST (PUT para crear, POST para responder)
	Utiliza el factory para delegar operaciones
	URLs organizadas por tipo: /question/{tipo}/{id}

Y el flujo de datos sería el siguiente:

	Para la creación de preguntas:
	Cliente → QuestionController → QuestionTypeFactory → Servicio específico → Repositorio → Entidad
 
	Para las respuestas a las preguntas:
	Cliente → QuestionController → QuestionTypeFactory → Servicio específico → Repositorio → Validación → Respuesta

Los componentes principales de la aplicación son:

	1. Entidades: Aquí crearemos las distintas entidades (o tablas, en la base de datos) de los distintos tipos de preguntas, de momento esta aplicación solo gestiona las preguntas de tipo "5-Star Rating", por lo que solo tenemos el archivo RatingQuestion.java  
		Características:
			- Cada pregunta tiene un ID, un título y un rango de valores (mínimo y máximo)
			- El rango predeterminado es de 1 a 5 si no se especifica a la hora de crear una nueva pregunta
			- Incluye un método para validar si una respuesta está dentro del rango permitido (1 a 10)
	
	2. DTOs:
		CreateRatingQuestionDTO: Contiene los datos necesarios para crear una pregunta.
		RatingAnswerDTO: Contiene la respuesta a una pregunta.
	
	3. Repositorio: Proporciona acceso a los datos de las preguntas
		Utiliza un HashMap para almacenar las preguntas (solo se almacenan en memoria)
		Proporciona los métodos para guardar, buscar y verificar si existen las preguntas

	4. Servicios: Contiene la lógica para manejar las preguntas
		Características:
			- Almacena las preguntas en un mapa con el ID como clave
			- Proporciona métodos para crear y responder preguntas

	5. Controllers: Expone los endpoints de la API REST, actualmente utilizaremos /question/{id} en lugar de /question/{id:uuid}, para facilitar el trabajo y comprobar que el código funcione correctamente
		Características:
			- Define las rutas de la API utilizando anotaciones de Spring MVC
			- Inyecta el servicio utilizando la anotación @Autowired
			- Mapea las peticiones HTTP a los métodos del servicio
			- Incluye un endpoint de prueba para verificar que la API funciona correctamente (/question/test)
   
	6. Clases "especiales":
 		QuestionTypeFactory: Clase utilizada para centralizar la creación y respuesta de los distintos tipos de preguntas y para facilitar la adición de nuevos tipos de preguntas en el futuro.
   			Características:
      				- Se inyectan todos los servicios específicos con la etiqueta @Autowired (para Rating, MultiSelect y SingleSelect)
	  			- Define un enum que enumera todos los tipos de preguntas disponibles
      				- El método createQuestion recibe el tipo de pregunta, el ID y el DTO correspondiente, los procesa, verifica que el DTO sea compatible (con el método instanceof), maneja excepciones y devuelve un booleano indicando si hubo éxito o no
	  			- El método answerQuestion es similar al anterior, pero lo utilizaremos para responder preguntas
      
   		ApiResponseDTO: DTO genérico utilizado para estandarizar los formatos de respuesta de la API
     			Características:
				- Utilizaremos un campo de tipo T (genérico), que nos permita almacenar cualquier tipo de dato (incluido nulos).
    				- El campo success indicará si la operación fue exitosa o no. 
				- El campo message mostrará un mensaje descriptivo para el usuario.

      		Funcionamiento de estas clases:
			- El QuestionController recibirá las peticiones HTTP (GET, POST, PUT)
   			- Llamará al QuestionTypeFactory para procesar la petición según el tipo de pregunta
      			- El factory utilizará el servicio requerido para dicho tipo de pregunta
	 		- El controlador convierte el resultado en un ApiResponseDTO
    			- El usuario recibe una respuesta con formato consistente (por ejemplo, en lugar de un 201, recibirá el siguiente mensaje: "Pregunta de valoración creada correctamente")
     
		Endpoints de la API:

			1. PUT /question/{id}: Crea una nueva pregunta de valoración.
   				- Parámetros:
    				- id (long): Identificador único de la pregunta
   				- Cuerpo de la petición:
     					{
       						"title": "¿Cómo calificarías nuestro servicio?",
       						"minValue": 1,
       						"maxValue": 5
     					}
     
 				- Respuesta:
     					- true: Si la pregunta se creó correctamente
     					- false: Si hubo algún error

			2. POST /question/{id}: Responde a una pregunta existente.
   				- Parámetros:
     				- id (long): Identificador de la pregunta a responder
   				- Cuerpo de la petición:
				     {
       					"answer": 4
    				     }
   				- Respuesta:
     					- true: Si la respuesta es válida
     					- false: Si la respuesta es inválida o la pregunta no existe

			3. GET /question/test: Endpoint de prueba para verificar que la API está funcionando.
   				- Respuesta: "Api funcionando"

		 
		Funcionalidades implementadas:
			- Creación de preguntas de valoración:
				- Título personalizable
				- Rango de valores personalizable del 1 al 10
				- Valores por defecto del 1 al 5 si no se especifican

			- Respuesta a preguntas: 
				- Validación de que la respuesta está dentro del rango permitido
				- Manejo de errores en caso de que la pregunta no exista
    

Patrones de diseño utilizados:

	- Patrón Factory: Para centralizar la creación y manipulación de objetos
	- Patrón repository: Para abstraer la persistencia de datos
 	- Patrón DTO: Separación de objetos de transferencia y entidades de dominio
  	- Arquitectura en capas: Separación clara de responsabilidades
   
Tecnologías utilizadas:

	- Java 17: Lenguaje de programación
	- Spring Boot 3.2.5: Framework para aplicaciones Java
	- Spring Web: Para crear la API REST
	- Spring DevTools: Para el desarrollo
	- Lombok: Para reducir código repetitivo


