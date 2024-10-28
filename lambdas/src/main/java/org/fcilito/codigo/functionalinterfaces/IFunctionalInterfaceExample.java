package org.fcilito.codigo.functionalinterfaces;

/*
* TODO:
Una interface funcional es una interface que se caracteriza por tener un único método abstracto.
No importa la cantidad de métodos por defecto o sáticos que se puedan tener en la interface,
mientras se tenga un único método abstracto tendremos una interface funcional.
Para garantizar esto podemos anotar la interface con la anotación @FunctionalInterface para indicarle a Java
que será una interface funcional, así si agregamos más de un método abstracto nos lanzará un error.

Ahora, las interfaces funcionales, sea que las creemos nosotros, como es este el caso,
o la utilización de las que se nos provee en el package Util de java, como Functional y demás,
son el corazón de la programación funcional en java, estas se pude decir que son las que permitene el uso de las
epxresiones lambdas para poder darle implmentacion al metodo abstracto con el que cuenta la interface funcional,
evitandonos el tener que crear clases de implmentacion o clases anonimas para darle una implmentacion a dicho metodo.
Nos proveen de un lenguaje un poco más declarativo de que queremos hacer, y no de cómo lo queremos.


Cuanod utlizariamos una interface functional?
La principal razón para utilizar una interfaz funcional es cuando tenemos una interfaz que solo tendrá un método abstracto,
en cuyo caso es buena idea, si las implementaciones de ese método abstracto no requeriran un estado o
tengan una lógica muy compleja y grande, que puede llevar a la implementación de expresiones lambdas demasiado complejas
y difíciles de entender. En ese caso es mejor tener clases de implementación para esa interfaz. De no ser así,
es buena idea para tener un código más conciso, fácil de entender y limpio la utilización de una interface funcional,
ya que por medio de las expresiones lambda no evitaremos crear clases de implementación para una lógica que puede ser
simple a pesar de que tenga varias implementaciones.

* */
@FunctionalInterface
public interface IFunctionalInterfaceExample {

	void sayHello();

}