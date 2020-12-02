# demo
prueba de registros de Logs
La aplicacion se realizo con spring boot , esta aplicacion tiene la finalidad de registrar en consola, archivo simple plano y 
base de datos h2 los mensajes log  que se enviara de manera de request simulando comportamiento en caso ocurra algun mensajes de log de  warnig,error, message.

proceso de compilacion:
se descarga la fuente del repositorio, se importa al eclipse la fuente luego
se agrega en el maven buil especificamente en el goal el los comandos maven :clean install -P emb aplicamos y cerramos,
nos posicionamos en el proyecto click derecho y run as->maven buil."con esto contruimos el jar".
crear la imagen docker:
primero arrancamos docker en nuestra local,
nos ubicamos en la raiz de nuestra aplicacion basicamente donde se encuentra nuestro archivo Dockerfile abrimos con un emulador de consola
y escribimos los comandos docker:
para crear imagen, arrancar el contenedor con el puerto 5000.
luego de ello que nuestro jar esta en el contenedor apuntamos al puerto 5000 de nuestro postman.

para dicha simulacion se usara la herramienta posttman.
con la sigiguiente estructura.
endpoint del servicio expuesto:
tipo request:  post
mensajes HTTP : Json
 url:  http://localhost:5000/log/save
 body:
 {
	 "messageText":"prueba de log",
	 "message":"true",	
	 "warning":"false",
	 "error":"false"
}
 El cuerpo de mensaje segun la logica que se manejo es:
 caso de exito.
 
 messageText: el mensaje deber ser diferente de vacio o nullo para que la aplicacion siga su flujo.
 message:true significa hubo un caso de exito en una funcionalidad de prueba.
 warning:false significa que  no sucedio un warnig
 error: false   significa que no sucedio un error
 
  messageText: el mensaje deber ser diferente de vacio o nullo para que la aplicacion siga su flujo.
 message:false significa que trazo una advertencia en el flujo del programa
 warning:true significa que  sucedio un warnig en una funcionalidad x
 error: false   significa que no sucedio un error
 
  messageText: el mensaje deber ser diferente de vacio o nullo para que la aplicacion siga su flujo.
 message:false  significa que no hubo un caso de exito en una funcionalidad x.
 warning:false significa que no sucedio un warnig en una funcionalidad x
 error: false   significa que  sucedio un error en una funcionalidad x
 
 caso de errores:
 messageText : si el mensaje es vacio o nullo el servicio respondera en la consola "el mensaje no deberia ser nullo o vacio".
 message,warning,error: si los 3 atributos del cuerpo del mensaje son verdaderos mostrara mensaje en la consola "error log multiples para trazar" esto significa, para que la aplicacion responda segun la logica esque que se debe que un log de los 3 sea verdadero(simulando un caso verdadero de exception).
 en caso de que 2 atributos  de los 3 atributos mencionados  sean verdaderos monstrara un mensaje en la consola  "error se necesita un log para trazar".
 
 
