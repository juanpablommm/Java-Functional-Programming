package org.fcilito.codigo.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) {

		/*

		TODO: Streams
		Los stream, son las abstracciones que nos permiten trabajar con estructuras de datos como colecciones o demas,
		de manera funcional, procesando los datos de manera secuencial o paralela, donde se facilitan las operaciones de
		filtrado, mapeo y reducción, por ejemplo, para cuando se tiene que realizar una suma de todos los elementos de una colección.

		Mantieniendo en lo posible al máximo el concepto de funciones puras en la programación funcional, es decir,
		se evita en lo posible el cambio o alteración de estados o varibles globales fuera del alcance,
		dado que esto puede producir errores inesperados en el código o que si se tienen procesos que se ejecutan en paralelo,
		mas de un hilo esté alrerando una varible que está siendo usada por otro; los stream, no son mutables,
		dada una operación intermedia aplicada sobre un stream, devuelve uno nuevo.

		Cabe recordar cómo funcionan los stream, donde básicamente tenemos un recurso desde el cual se crea el Stream,
		y tenemos dos tipos de operaciones a aplicar durante el proceso del stream, a lo cual se le conoce como estructura de proceso,
		o el pipeline del Stream, que es básicamente todo el flujo que tiene el Stream desde su creación, la aplicación de las operaciones necesarias y la obtención del resultado final:

		Operaciones intermedias: Las operaciones intermedias que se aplican sobre los elementos del stream, ya sea filtrar,
		mapear el tipo de dato, y demás. Estas son Lazy (Peresosas), no se ejecutan hasta que en sí, se llame a una operación terminal.
		Estas nos devuelven un nuevo stream a diferencia de las terminales.

		Operaciones Terminales: Estas son las que, como su nombre lo dice, terminan el Stream, de la cual ya se puede obtener un retorno
		del dato deseado o la operacion final que vamos apliciar al dato deseado, y son las que cierran el recuro del stream, para que este no
		quede abierto.
		Estas generan ya el resultado o un efecto secundario, como una colección o un valor único.

		TODO Flujo: recurso -> operaciones intermedias -> operación terminal.
		 */

		/*
		 * TODO profundidzar mas en los metodos como flatMap y demas para las
		 * collections anidaddas profundizar en los emtodos como sorted y collect,
		 * entender bien las intetrfaces funcionales que estos reciben como paremtros
		 */

		/*TODO formas de creacion de de un Stream
		 *
		 * * recurso(collection, array, etc).stream() => se llama al metodo stream que se tiene de la interface Collection, por ende esta
		 * presente para todas las colecciones en la jerarquia de clases que se tiene
		 *
		 * * Stream.of( conjunto de elementos) => se llama al metodo of() presente en la interface Stream, del cual se tiene
		 * 3 variantes, uno que presenta argumentos variables para el conjunto de datos, uno que se acepta un unico dado y
		 * y uno que acepta un unico dato pero es nulleable es decir que puede que el stream quede vacio.
		 *
		 * * Arrays.stream(Array de datos) => se llama a cualquiera de los metodos stream de la clase Array que reciben como
		 * parametro el array del tipo de dato del cual se quiere sacar el stream
		 *
		 * * Stream.generate(condicion para crear el stream) => se crea un stream infinito, en base a una condicion que se pasa
		 * como parametro, donde el parametro del metodo generate es una functional interface "Supplier" cuyo metodo abstracto
		 * para la implementacion de la expresion lambda, tiene un tipo generico que es el retorno, a entragrar con la lambda, no
		 * resive argumentos.
		 *
		 * * Stream.iterate() => al igual que genetare, crea un stream infinito que itera sobre un valor inicial y aplica una función,
		 * este recibe dos interfaces funcionales, teniendo dos variantes, una que proporciona el valor inical y una
		 * functional interface UnaryFuntional, la cual tien 3 genericos, recibe dos parametros el metodo asbtracto y hace
		 * su respectivo retorno, los 3 parametros son del mismo tipo.
		 * Su otra variante recibe una a parte de la UnaryFunctional otra functinal interface, que es el Predicate, para aplicar
		 * una condicion
		 *
		 * Files.lines(path del archivo) => este crea un stream de String, con cada linea del archivo que vallamos a leeer,
		 * es ideal usar esto es cuando tenemos un archivo grande que queremos evitar cargarlo en meoria, pero tampoco es lo
		 * sucientemte gran como para no utlizar un Buffer con mas control.
		 * */

		System.out.println("Stream from Files");
		try (Stream<String> stringStream = Files.lines(Path.of("/home/juampis/Desktop/personal-study/Java Functional Programming/lambdas/src/main/java/org/fcilito/codigo/streams/test.txt"))) {
			stringStream.forEach(System.out::println);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		System.out.println();
		System.out.println("map");
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		/*TODO map: con la operciacion intermedai map, lo que hacemos es hacemos es poder transformar el tipo de stream
		 *  que se tiene a otro tipo, practicamente es un mapper.
		 *  recive como parametro un functional interface de tipo "Function, cuyo metodo abstracto para la implementacion
		 *  tiene dos genericos T que el parametro del tipo de dato que recibe y R que es el tipo de dato que retorna.
		 *  Em este caso se utliza map para obtener el cuadrado de cada numero, el resultado del map un Stream del mismo tipo Integer " */
		numbers.stream().map(number -> number * number).forEach(number -> System.out.print(number + ", "));

		//En este otro ecenario se mapea el tipo Stream Integer a un Stream de Entrys, dado que por cada numero
		//se crea un Entry que contine como clave el mismo numero y como valor su cuadrado
		System.out.println();
		numbers.stream().map(number -> new SimpleEntry<>(number, number * number)).forEach(number -> System.out.print(number + ", "));


		/*TODO flatMap: Este tiene el mismo funcionamiento que el Map normal, con la diferencia de
		 		que si por ejemplo tenmos con Stream que se creo con el metodo of(), que si
		 		recordamos una de sus esctrituras recibe argumentos variables para un conjunto de datos,
		 		este nos permitira unirlos todos en el stream para trabjarlos como si fueran un unico,
		 		asi por ejemplo si tenemos varias collections en el stream las podresmo unir en una sola,
		 		teniendo todos los elementos de estas en una unica formada en el stream.
		 */
		System.out.println();
		System.out.println();
		System.out.println("flatMap");
		List<String> listOne = Arrays.asList("Juan", "Carlos", "Ronaldo");
		List<String> listTwo = Arrays.asList("Juliana", "Carla", "Karen");
		List<String> listThree = Arrays.asList("Pancrapio", "Adevecii", "Bitcher");

		Stream.of(listOne, listTwo, listThree).flatMap(Collection::stream).sorted().forEach(name -> System.out.print(name + ", "));


		/*TODO anyMatch: Esta opracion terminal lo que nos permitira es encontrar dentro de la colecion de
					de datos con la que estamos tratando, encontrar como su nombre lo dice cualquier resultado
					que concida con una condificion, como es una operacion terminal nos retornara,
					lo que ya estabamos esperando finalmente del pipeline del stream.
					un unico resultado que es un boolean que nos dira si algun elemento fue encontrado con lo expuesto,
					Recibe como parametro una functional interface "Predicate"; cuyo metodo abstracto para
					la implementacion por medio de la lambda tiene un tipo de dato generico como parametro,
					que es del tipo de dato con el cual en el cuerpo de la expresion, aplicaremos una
					condicion a cumplir, devolviendo un boolean, pues este es el retorno que tiene dicho metodo
					y sobre el cual se evaluara si algun objecto de la colecion que estamos trarando cumplen dicha condicion,
					retornandos un boolean.
					En este caso nos dira si existe algun numero que sea menor que 5

		TODO metodos relacionados, allMatch (retornara el boolean informando si todos los elmentos coinciden con lo expresado en el Predicate)
			y el metodo noneMatch(retornara el boolean informando si todos los elmentos no coinciden con el predicate)
		* */
		System.out.println();
		System.out.println();
		System.out.println("anyMatch");
		List<Integer> ratings = Arrays.asList(10, 6, 5, 4, 4, 3, 8, 10);
		boolean ratingMin = ratings.stream().anyMatch(number -> number < 5);
		System.out.println("There is number minor that 5: " + ratingMin);

		/*TODO filter: esta operacion intermedia lo que nos permitira es aplicar un proceso de filtrado;
					aplicando una condicion sobre los elementos con que estamos trabjando, pero nos dara un
					nuevo stream al ser una operacion intermedia con los elmentos que cumplen con una condicion,
					la cual tambien se expone con un parametro que es una functional interface "Predicate".
					En este caso se filtran todos los Users cuya eda es igual a 25
		 */
		System.out.println();
		System.out.println("filter");
		List<User> users = new ArrayList<>();
		users.add(new User("Carlos", 25));
		users.add(new User("Daniel", 25));
		users.add(new User("Tannia", 25));
		users.add(new User("JLuisa", 24));
		users.add(new User("Raul", 21));

		/*TODO findAny and ifPresentOrElse: en este ejemplo tambien se usan los metodos findAny el cual es
					operacion terminal como su nombre lo dice nos permitira encontra cualquier elmento,
					a diferencia de anyMatch findAny nos traera cualquier elemento de la colecion,
					en este caso cualquier usario de los que se filtraron que tiene 25 años.
					Este metodo no recibe parametros y retonar un Optinal, con el elemento encontrado o cavio si no se encontrp,
					para no aplicar una condicional if validando si estra presente el dato en el optional, podemos utlizar
					ifPresent() que recibira una functional interface "Consumer", cuyo metodo abstracto tiene un generico
					como parametro para aplicar un logica meidante la expresion lmabda, logica que no puede tener retorno
					pues este metodo asbtracto en la functional interface no tien retorno. De igual manera se puede hacer utlizando
					el metodo ifPresentOrElse, pno aplciar una condicional doble de if-else, en donde se recibe un parametro
					extra que es una interface functional "Runnable", tambien muy similar a "Consummer", pero esta no recive
					un parametro ni tiene retorno, funciona en un hilo por separado.
					Estos metoso son idelas para evitar la identacion extra que se puede tener con if-else tradicional
					pero simpre y cuando la logica a implementar no sea muy compleja que sea dicifil de entender la lambda
		* */
		users.stream().filter(user -> user.getAge() == 25).findAny().ifPresentOrElse(
				user -> System.out.println(user.getName() + " is an user with 25 years old"),
				() -> System.out.println("There is no an user with 25 years old"));

		System.out.println();
		System.out.println("sum");
		/*TODO sum: esta operacion terminal como su nombre lo indica se utiliza para sacar la suma de los elemtnos,
				 pero este metodo solo esta presente en los Stream que se trabajan con tipos de datos numericos, como
				 long, int double, con los wrapper no es posible; los cuales para poder trabjar estos
				 tipos primitivos en los Stream, debemos de hacerlo con clases especiales para esto que son IntStream,
				 DoubleStream, y LongStream, que nos permitiran trabjar con stream cuyas coleciones de datos tengan primitivos.
				 Estos tipos de stream dedicados a prmitivos nos permitiran tener metoso mas espciales para estos datos,
				 como sum, para la suma, average para un promedio, entre otros datos, para obtenerlos del stream original
				 tendremos que apoyarnos de la operacion intermedia mapToInt, mapToDouble y matToLong, que
				 nos permitira mapear los elmentos a un tipo primitivo, este metodo recibe como parametro una
				 functional interface "TopIntFunction", que es una especializacion practicamente de "Function",
				 pero esta lo que hace este en su metodo abstracto tener una generico como parametro y un int como return,
				 en el cuerpo de la expresion lambda lo que tendremos en nuestra logica es que retornar dicho int.
				 En este caso como nuestro stream se basabe en una lista de Integer, podemos aprovechar el metodo del wrapper,
				 para sacar el valor primitivo.
		 */
		System.out.println("Sum: " + numbers.stream().mapToInt(Integer::intValue).sum());


		/*TODO average: esta operacion terminal lo que nos permitira es conocer el provedio de los numeros que tengamos en
				 el stream, siempre y cuando estemos trabajando con las clases de Stream para primitivos, IntStream,
				 DoubleStream, y LongStream. nos retornara un Optinal deciado a los tipos primitivos, es decir,
				 un OptionalInt, OptionalDouble, u OptionalLong, dendendiento del tipo de Stream con el que estemos tratando,
				 con el fin de tener una gestion segura de los primitivos, en el caso de que se tenga un valor o no.
				 funcionan de manera muy similar a como se usa un Optional, con algunos metos especialidaos para trabajar
				 con los primitivos, la ventaja de trabjar con estos optinal espcializados para primitivos es que,
				 tendrmoes mejoras de redimiento, dado que no se tiene que convertir el valor primitivo a su respectivo Wrapper,
				 este proceso es el que se le conoce como Autoboxing, lo cual puede tener implicaciones de rendimiento
				 cuando se trabaja con ciclos intensivos, lo cual pasa cuando queremos manejar de manera segura los datos
				 primivisot con Optional<Integer>, Optional<Long>, etc, pues el primitivo tiene que se transformado
				 internamente por su respectivo Wrapper, cosa que no sucede al trabkjar con Optional y los demas.

				 Nota: * AutoBoxing, convertir el primitivo al Wrapper
				 	   * UnBoxing, convertir el Wrapper a primitivo

		 */


		/*TODO average: esta operacion terminal utlizada solamente con IntStream, DoubleStream, y demas stream
			    especializados para trabajar con datos primitivos, lo que nos permite es obtener el promedio
			    de todos los numeros que tengamos en la colecion de datos, de manera como sum, optiene la suma,
			    y nos retornara un OptinalInt, OptionalDouble, etc dependiendo del tipo primivito con el que estemos
			    trabajando
		 */
		System.out.println();
		System.out.println("average");
		numbers.stream().mapToInt(Integer::intValue).average().ifPresentOrElse(
				average -> System.out.println("Average is: " + average),
				() -> System.err.println("There is no value"));


		/*TODO min and max: estas operaciones terminales, no hace falta que se este tratando con un Stream de tipos
					primitivos, si estamos trabajando con una collection de datos de tipos objectos en el stream, podemos
					de igual manera utilizar esa operacion temrinal, la cual nos permitira obtener el valor minimo o el valor
					maximo del Stream, en el que tendremos que pasar con una functional interface "Comparetor", cuyo
					metodo abstracto de tiene dos paremetros del mismo tipo generico y como retorno un numero entero,
					que se base en un negativo si el primer parametro debe de ir primero, 0 si los dos objectos son iguales
					o un numero positivo si el segundo parametro debe de ir primero.
					Para cuando trabajamos con Stream para tipos prmitivos no hace falta pasarle un argumento a estas operaciones
					terminales de min o max, dado que la comparcion se hara con datos primitivos ya de antemanos java reconocera
					cual dado es menor o mayo que el otro. Pero cuando trabajamos con un Stream que contiene datos de
					tipo Object, si es necesario dado que nos permitira definir los criterios que deseamos para saber cual
					objecto es mayor o menor que el otro o iguales, para esto con ayuda de la functional interface "Comprator",
					esta interface functional ya nos prove algunos metodos por default que son utiles en la creacion de comparadores,
					como comparing(), que basicamente facilitara la creacion del comparador bsado unicamente en un atributo,
					recibiendo como parametro un functional interface "Funtional". Y el metodo reversed, que nos permitira
					invertir el orden del comparador.


		 */

		System.out.println();
		System.out.println("min and max");
		numbers.stream().mapToInt(Integer::intValue).min().ifPresentOrElse(
				min -> System.out.println("The min value is: " + min),
				() -> System.err.println("There is no min value"));

		numbers.stream().mapToInt(Integer::intValue).max().ifPresentOrElse(
				max -> System.out.println("The max value is: " + max),
				() -> System.err.println("There is no max value"));


		//example with Stream<> esto es bsaicamente lo que se estaria haciendo si se implmenta una lambda para el metodo compare
		numbers.stream().max((o1, o2) -> (Objects.equals(o1, o2)? 0 : o1 < o2 ? -1 : 1 )).ifPresentOrElse(
				max -> System.out.println("The max value is: " + max),
				() -> System.err.println("There is no max value"));

		/*ahora dado que en este caso se esta trabajando con Integer, podemos usar el meto compareTo que ya se tiene,
		el cual recive dos parmetros de tipo int, y hace practicamente lo mismo.
		 */
		numbers.stream().max(Integer::compareTo).ifPresentOrElse(
				max -> System.out.println("The max value is: " + max),
				() -> System.err.println("There is no max value"));


		/*TODO reduce: esta operacion terminal lo que nos permira en si, como su nombre lo dice es reducir el conjunto de datos,
					a uno solo en base la logica que definamos, es ideal para cuando se requiere tener una varible acomulativa,
					una suma, como otra alteranativa a sum, un producto, una concatenacion, etc;
					cuando se esta trabajando con datos primitivos, el medotodo reduce, reibe como parametro una functional
					interface "IntBinaryOperator, LongBinaryOperator u DoubleBinaryOperator" las cuales el metodo
					abstracto lo que hace es recibir dos parametros del tipo primitivo correcpondiente y retornar
					un primitvo del mismo tipo, aplicando ya la logica nosotros mediante la lambda, estas functional
					interfaces bienenciendo especializaciones de "BinaryOperator" pero sin tipos genericos.
					tenemos dos escrituras del metodo reduce, en una solamente tendremos como parametro a "IntBinaRyOperator",
					o demas correspondientes y en la otra tendremos este parametro mas un primer parametro del tipo de dato del
					primitivo con el que estemos tratando que sera el valor incial para que le daremos al acomulador, o variable
					que al final reducira el stream, por ende si utlizamos este tendremos como resultado de la operacion
					terminal el valor del primitvo puro, dado que asignamos un valor incial y en el caso de que el stream,
					este vacio pues reciviremos ese valor inicial, y en el caso que no utilicemos esta firma sino la otra
					con solamente como parametro la functional interface, recibiremos un Optinal del tipo primitivo.
					Este mismo ecenario pasa para cunado tenemos un Stream de tipos de datos tipo Objects, en este sentido
					contaremos con dos firmas del metodo reduce que tiene o no un paramtro para asingar un valor inicial,
					y recibiremos un Optinal o no dependiendo del cual usemos, pero en este caso como no trabjamos con tipos
					de datos prmiticos nuestra functional interface a utilizar es "BinaryOperator", la cual es una especializacion
					de "BiFunction" en donde los dos tipos de parametros mas el retorno son del mismo tipo generico.


		 */

		System.out.println();
		System.out.println("reduce");
		//ejemplo con tipos primitivos
		System.out.println(IntStream.of(1, 2, 3, 4).reduce(1, (left, right) -> left * right));

		//ejemplo con tipos Objects
		List<String> programmingLanguages = Arrays.asList("Java", "C#", "Python");
		String resultOfLanguages = programmingLanguages.stream().reduce("",
				(accumulator, language) -> String.format("%s | %s", accumulator, language).replaceFirst("^.", ""));
		System.out.println(resultOfLanguages);

		/*TODO: distinct: esta operacion intermedia lo que nos permitira es simplmente eliminar los datos duplicados en el Stream
		 */
		Stream.of("Alejandro", "Carlos", "Arturo", "Alejandro", "Juan", "Carlos").distinct()
				.forEach(name -> System.out.print(name + ", "));

		/*
		* TODO sorted: esta operacion intermedia lo que nos permitira basicamente es poder aplicar un ordenamiento al
		*  los elementos del Stream, en donde para un Stream de tipos de datos primitivos, simplmente es aplicar el metodo
		*  dado que son primitivos la JMV se encargara de ordenarlos ya, en cambio para un Stream de tipos de datos
		*  tipo objecto, se puede dejar se tiene dos opciones no pasarle parametros y dejar que se ordene internamente, o
		*  pasarle una functional interface "Comparator" en donde ya sabemos que el metodo abstracto tiene dos parametros
		*  del mismo tipo y en base al retorno de un valor entero sabremos cual elemento ira primero en la comparativa.
		* 	ahora si aplicamos el el metodo sin parametro el ordenamiento sera acendente, toca tenerlo en cuanta, para aplicar
		* 	un ordanamiento en forma inversa, nos tendremos que apoyar del metodo por dedault en la misma interface: reverseOrder*/
		System.out.println();
		System.out.println("Sorted");
		numbers.stream().sorted().forEach(number -> System.out.print(number + ", "));


		// Se reversa el ordemaniento con ayuda del metodo por default de la functional
		// interface Comparator -> reverseOrder
		System.out.println();
		numbers.stream().sorted(Comparator.reverseOrder()).forEach(number -> System.out.print(number + ", "));

		List<Book> books = Arrays.asList(new Book("El señor de los anillos", 150),
				new Book("Dojn Quijote de la Mancha", 500), new Book("A300", 200), new Book("El Hobbit", 1000),
				new Book("El Principito", 140));


		/*TODO limit: Esta operacion intermedia lo que nos permitira es limitar la cantidad de elmentos que queremos seguir
					majenado en el Stream. donde se le pasa un parametro de tipo long y se toman los primeros n elementos
					en base a dicho parametro para devoler un Stream unicamente con dicha cantidad.
		 */
		System.out.println();
		System.out.println();
		System.out.println("limit");
		books.stream().sorted(Comparator.comparingInt(Book::getCopy).reversed()).limit(3)
				.forEach(book -> System.out.print(book.getTittle() + ", "));

		/*TODO skip: esta operacion intermedia lo que nos permite bsaciamente como su nombre lo dice es, es saltar elementos
					del Stream, se saltan los primeros elementos en base a un para de tipo long; si el numero pasado como
					parametro es mayor o igual al numero de elementos del stream, tedriamos como resultado un Stream vacio.
					Se suele usar con la operacion intermedia limit para simular procesos de paginado, donde el skip, estaria
					aplicandose a la configuracion de la pagina, al saltar n elementos que mencionesmos y el limit para los
					limitar los elémentos a traer en el stream resultante al utilizar skip.

		TODO peek: Es una operacion intermedia que nos permitra inspeccionar cada uno de los elementos del Stream, fue
				creada mas con la finalidad de pruebas, aplciar logs para reivzar cada elemtno en el Stream,
				aunque esta operacion tenga como parametro un functional interface "Consumer", que bien ya sabemos
				el metodo abstracto tiene un argumento de tipo generico y no tiene retorno, se podria estar modificando los
				objecto del Stream dependiendo de la expresion lambda que se defina, dado el concepto de paso por referencia.
				Lo idea es no utilizar esta operacion intermedia con este fin, puesto que no es muy recomendable ni buena practica,
				a demas considerando el echo que en la progmracion funcional lo que se trata es a parte de tener funciones puras es
				evitar el cambio de estado y mutabilidad, a menos que sea necesario, pero para ello nos podemos apoyar de otras operaciones,
				como por ejemplo utilizar la operacion terminal foreach, que tambien recibe como parametro un "Consumer".
		 */
		System.out.println();
		System.out.println();
		System.out.println("skip");
		List<Integer> numbersWithSkip = numbers.stream().peek(System.out::println).skip(8).toList();
		System.out.println();
		numbersWithSkip.forEach(System.out::println);



		/*TODO collect: Esta operacion terminal lo que nos permitira es agrupar los elememtos del Stream, en una colecion
					o tipo de dato especifico que necesitemos, como una lista, set, mapa o demas...
					Este trabaja junto con la interface "Collector", con la cual se define una estrategia
					de acomulacion de los elementos del Stream, esta interface tiene los metodos que definen como se acomulan
					los elementos, como se combinan estos y como se produce el resultado final.
					Esta interface tiene 3 genericos los cuales significan:
					T -> El tipo de los elementos con los cuales estamos trabajando
					R -> El tipo de resultado final
					A -> el tipo del acumulador que se usa durante la recolecion intermedia
					para crear un Collector personalizado en base alguna logica especial a apliciar, se debe de crear una clase
					que implemente esta interface, e implementar los metodos correspondientes, tales como:
					* supplier() -> como su nombre lo indica retornar una functional interface "Supplier", que provee el acumulador,
					un contendor vacio en donde se almacenaran los datos recolectados, por ejemplo si queremos devolver los elementos
					en una lista el metodo debera de devolver un ArrayList::new
					* accumulator() -> Este metodo retonarna un functional interface "BiConsumer", la cual ya se conoce que es una especilizacion
					de la functional interface Consumer, solamente que consta de dos argumentos que recibe como parametros en
					su metodo abstracto, a diferecnia de Consumer, los tipos genericos que se pasan como a dicha interface del
					retorno en el metodo accumulator, son el A, que es el tipo del acomulador y el T que es el tipo del resultado,
					por ejemplo List::add; paa agregar los elmenttos, en caso de que el acomulador se una lista.
					* combiner() -> este metodo retorna una functional interface "BinaryOperator" que como ya sabemos es
					una especializacion de la interface "BiFuntional", simplemente que se tiene los genericos del metodo abstracto del
					mismo tipo, tanto para los argumentos como para el retorno, este metodo combiner para la functional interace
					del retorno estabece el generico del tipo A; ahora su fucion es la de fusionar dos acomuladores intermedios,
					lo cual es necesario para stream paralelos, con el fin de combinar resultados parciales de diferentes subprocesos,
					en el caso de seguir trabjando el collector con un lista, este parametro pordria ser ArrayList::addAll,
					que recibe una collection para agregar los elmentos de esta en la lista que se este trabajando.
					* finisher() ->   este metodo retonrar un interface funtional "Function" en donde se le pasa como los
					datos genericos a esta, el A, y el R; lo que hace basicamente este metodo es convertir el acomulador
					en el resultado final que queremos obtener, en muchas ocaciones el acomulador es el mismo resultado
					final, como por ejemplo una lista, pero en otras ocaciones se requiere una transformacion a algun otro
					tipo de dato.
					* characteristics() -> este metodo retorna un Set del tipo Characteristics, y su funcionalidad es
					describir las caracteristicas del Collector, como por ejemplo:
						* CONCURRENT ->  que indica que el collector puede ser usado en paralelo
						* UNORDERED -> indicando que no importa el orden de los elmentos
						* IDENTITY_FINISH -> indica que el acomulador y el resultado son el mismo, por lo que el metodo
						finisher() -> puede no hacer nada.

				TODO Ahora en cuanto a la operacion terminal, collect, ya sabiendo que resive como parametro un Collector,
					si no es tan puntual el caso de agrupamiento, en que necesitemos crear una clase Collector Personalizada,
					podemos entonces apoyarnos de la clase que nos provee la JDK, la clase "Collectors", la cual tiene diferentes
					metodos staticos que nos brindaran un Collector puntual para operaciones comunes, como agrupar los elmentos
					en una Lista, un Mapa, un Set y demas, dentro de los mas relevantes tenemos:
					* toList() -> nos permitira agurparlos en una lista
					* toMap() -> nos permitira argupar los elmenots en eun mapa,
					 recibe como parametro dos functional interfaces para sacar el kye and value del mapa
					* toSet() -> nos permitira agrupar los elmenetos en un Set.
					* toCollection() -> que nos permitira agrupar los elemtnos en una collection que especificiquemos
					pasando como parametro un functional interface "Supplier" en la cual pasamos la instancia de la colecion.
					* joining() -> con el cual podrmoes agrupar en un Strng, los elementos de tipo String de un Stream, en
					base a un caracter delimitador que pasemos como parametro, bascimaente este nos daria todos los elementos
					dseparados por dicho caracter delimitador, por ejeplo -> java | python | SLQ
					* groupingBy() -> el cual nos permitra agurpar los elementos en un mapa, que tendra como valor una lista
					de los elmentos del stream, y como calve, lo que establezcamos en una expresion lambda que reive como parametro
					para implementar la functional interface "Function".
					* summingInt() -> la cual nos permitira optener un collector, con un Integer como retorno, quebasicmanete sera
					la suma para un atirbuto de tipo intero o numerico de los elmentos de nuestro Stream, que queramos sumar por
					cada uno de todos nuestros datos, como que atributo tulizaremos?, lo definiremos en la logica de dicho metodo
					que resibe como parametro un funciontla interace "ToIntFuntion" que como su nombre lo dice basicamente estaremos
					es utilizando una especializacion de la functional interface Funtion, se reibe cun parametro de tipo generico,
					pero el retorno es lo que cambia, se retonaria un tipo int primitivo.
					Tambien tenemos una sobrecarga del la operacion terminal collect, que nos permitra definir la forma de
					agrupamiento de nuestos dados que queremos de una manera un mas facil que crear un Collector personalizado
					para cuando necesitemos algo puntual o no se requiera usar la clase Collectors, con los Collector por default
					que brinda, tendremos la posibiliad de crear la logica, conuna osbrecarga del metodo qeu recibe 3 paramtros,
					los cuales son de manera similar a los metodos de la interace Collector:
					* Supplier -> Supplier -> por medio supplier, estaremos birndando el contenedor de los elemtos
					* Accumulator -> BiConsummer -> por medio del cual estaremos mencionado como se agregan los lementos al comulador
					* Combiner -> BiConsumer -> deifniendo como fusionar dos acomuladore sintermedios, para stream paralelos

					TODO Nota: acosiar la operacion temrinla collect a la frase "Transformar este Stream en algo util para mit",
						ayuda a comprenderlo mejor.


		 */

		System.out.println();
		System.out.println("collect");

		//TODO (supplier, accumulator y combiner): Opteniendo un lista con los nomnbres de los usarios
		System.out.println();
		System.out.println("* collect with (supplier, accumulator y combiner)");
		List<String> namesOfUsers = users.stream().collect(ArrayList::new,  (list, user) -> list.add(user.getName()), ArrayList::addAll);
		System.out.println(namesOfUsers);
		System.out.println();

		//TODO (supplier, accumulator y combiner): mismo ejemplo pero obtenido un mapa donde la clave es el nomnbre y el value la edad
		System.out.println();
		Map<String, Integer> map = users.stream()
				.collect(HashMap::new,
						(entry, user) -> entry.put(user.getName(), user.getAge()),
						HashMap::putAll);
		System.out.println(map);

		//TODO Collectors por default: ejemplo de la obetencion de un mapa clave nombre lde usuario valor object user
		System.out.println();
		System.out.println("* Collectors por default -> Collectors.toMap");
		/*TODO: el metodo static en la functional interface "Funtion", nos permitra obtener como retorno el mismo argumento
				 que se pasa como parametro al metodo abstracto
		 */
		System.out.println(users.stream().collect(Collectors.toMap(User::getName, Function.identity())));


		/*TODO joining: ejemplo usando el Collector joinin de Collectors, agrupando en una salida String por un caracter
		*  delimitador, se transforma el stream de user a un stream de String, y se agrupan en un String delimitados por |*/
		System.out.println();
		System.out.println("* Collectors por default -> Collectors.joining");
		System.out.println(users.stream().map(User::getName).collect(Collectors.joining("|")));


		/*TODO el metodo joinin tambien tiejne su equivalenten, para trabjar con una varios stream, directamente
		 	 String resultOfLanguagesTwo = String.join(" | ", programmingLanguages);
			 System.out.println(resultOfLanguagesTwo);*/

		//TODO Personal Collector: ejemplo con un Collector personal, creado para la clase User
		System.out.println();
		System.out.println("* With a Personal Collectors for User Class");
		System.out.println(users.stream().collect(new CollectorUser()));
	}
}
