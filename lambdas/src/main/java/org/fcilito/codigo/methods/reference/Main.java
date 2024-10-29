package org.fcilito.codigo.methods.reference;

import org.fcilito.codigo.streams.CollectorUser;
import org.fcilito.codigo.streams.User;

import java.net.Inet4Address;
import java.util.*;
import java.util.function.Supplier;


public class Main {
    public static void main(String[] args) {

        /*TODO Metodos por Referencia
         *  Rcordando el conepto de metodos por referencia, basciamente es una forma abreviada, mas clara y legible
         *  de escribir el llamado a un metodo en las expresiones lambdas, por ejemplo en lugar de tener algo como
         *  esto .forEach(element -> System.out.println(element)); en donde hacemos el llamado al metodo println,
         *  podemos hacer referencia al nombre del metodo; .forEach(System.out::println)
         *  la jvm se encargara de dectar pasarle el argumento al metodo.
         *  la estrcurra para llamar a un metodo por referencia es basicamente:
         *  * NombreClase::nombreMetodo
         *  * nombreObjecto::nombreMetodo
         *
         *TODO: Existen practicamente 4 tipos de llamdas a metodos por referencia:
         * * Referencia a un metodo statico -> NombreClase::nombreMetodo
         * * Referencia a un metodo de instancia de un Objecto -> nombreObjecto::nombreMetodo
         * * Referencia a un metodo de instancia de un Objecto arbitrario -> NombreClase::nombreMetodo}
         *   un objecto arbitrario es basiccamente cualquier instancia de un objecto, cualquier objecto
         *   dentro de un conjecto de objectos, ya sea una lista, map, set, o cualquier otro.
         *   Lo qu se hace en este ecenario se aplica el metodo de la clase a cada objecto dentro de la colecion
         *   de datos, sin referirnos a cada uno en particular.
         * * Referencia al contrcutor de un clase -> NombreClase::new
         *
         * */

        int[] numbers = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<User> users = Arrays.asList(new User("JuuamPis Gonzales", 23),
                new User("Carlos Zarmiento", 43),
                new User("Rizardo Do Santos", 50));
        Arrays.stream(numbers)
                .map(Main::cubeFromNumber) //TODO Referencia a un metodo estatico
                .forEach(System.out::println);

        System.out.println();
        System.out.println();
        Calculator calculator = new Calculator();
        Arrays.stream(numbers)
                .map(calculator::toSquare) //TODO referencia a un metodo de instancia de un objecto
                .forEach(System.out::println);


        System.out.println();
        System.out.println();
        users.stream().map(User::getName).forEach(System.out::println);
        Map<String, Integer> mapUsers = users.stream()
                .collect(HashMap::new, //TODO Referencia al constructor de una clase para crear un objecto
                        (map, user) -> map.put(user.getName(), user.getAge()),
                        HashMap::putAll);
        System.out.println(mapUsers);
    }

    private static int cubeFromNumber(int number) {
        return number * number;
    }
}
