package org.fcilito.codigo;

import org.fcilito.codigo.functionalinterfaces.IFunctionalInterfaceExample;
import org.fcilito.codigo.functionalinterfaces.IFunctionalInterfaceWithReturn;
import org.fcilito.codigo.functionalinterfaces.IFunctionalWithParameters;

public class Main {
	public static void main(String[] args) {

		/*
		 * TODO: Considerando que las interfaces funcionales destacan por ser interfaces
		 * que solamente tienen un único método abstracto, independientemente de la
		 * cantidad de métodos por defecto o estáticos que se puedan tener en estas, ya
		 * sea la necesidad presente; las interfaces funcionales, en especial si son
		 * creadas por nosotros, podremos tener cada una de las implementaciones del
		 * método de la interface que necesitamos mediante el uso de las expresiones
		 * lambdas, evitando tener que crear clases de implementación o clases anónimas.
		 * 
		 * La instancia de una variable declarada del tipo de una interfaz funcional es
		 * la expresión lambda con la que damos el comparamiento o implementación al
		 * método abstracto de la interface.
		 * 
		 * Posteriomente, mandamos a llamar al método abstrato de nuestro objeto del
		 * tipo de la interfaz, y se aplicará toda la implementación llevada acabo
		 * mediante la expresión lambda.
		 *
		 * Notas:
		 * 
		 * Cuando nuestra implementación del método abstracto por medio de la expresión
		 * lambda no tiene parámetros, simplemente se colocan los parentesis sin nada
		 * dentro ().
		 * 
		 * Cuando solo se tiene un único parámetro en la definición del método
		 * abstracto, en cambio, no se necesita colocar los parentesis.
		 * 
		 * Cuando solo se tiene una única sentencia de código, no hay necesidad de
		 * colocar las llaves. Simplemente basta con la sentencia; de igual manera, si
		 * se tiene un retorno y es una única sentencia de código, el retorno será
		 * inferido automáticamente; no tenemos que explicarlo; por el contrario, esto
		 * sí lo tendremos que hacer si se tienen múltiples sentencias aparte de las
		 * llaves {{}}. Debemos de especificar cual es el retorno según la definición
		 * del método abstracto.
		 */
		IFunctionalInterfaceExample iFunctionalExample = () -> System.out.println("Hello nice to meet you");
		iFunctionalExample.sayHello();

		IFunctionalWithParameters iFunctionalWithParameters = name -> System.out
				.println("Hello nice to meet you " + name);
		iFunctionalWithParameters.sayHelloWithName("Juampis Gonzales");

		IFunctionalInterfaceWithReturn iFunctionalInterfaceWithReturn = (number1, number2) -> number1 * number2;
		System.out.println("Multiplication result " + iFunctionalInterfaceWithReturn.operation(5, 2));
	}
}