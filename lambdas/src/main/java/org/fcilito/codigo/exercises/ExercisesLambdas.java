package org.fcilito.codigo.exercises;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.stream.Stream.of;

public class ExercisesLambdas {

	public static void main(String[] args) {

		/*
		 * TODO: Ejercicios propuestos en el curso
		 */

		List<Integer> arrayNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// TODO: Crear una función anónima que nos permita calcular el promedio de un
		// arreglo de números enteros.
		Function<List<Integer>, Double> averageOfNumbers = numbers -> numbers.stream().mapToDouble(Double::valueOf)
				.average().orElse(0.0);
		System.out.println("Average Of Numbers " + averageOfNumbers.apply(arrayNumbers));

		// TODO: Crear una función anónima que calcule el factorial dado un número
		// entero
		Function<Integer, Integer> factorialOfNumber = number -> (number >= 0)
				? IntStream.rangeClosed(1, number).reduce(1, (a, b) -> a * b)
				: 0;
		System.out.println("Factorial " + factorialOfNumber.apply(6));

		// TODO: Crear una función anónima que permita conocer si un número es par.
		Predicate<Integer> isEvenNumber = number -> number % 2 == 0;
		System.out.println("Is event Number " + isEvenNumber.test(8));

		// TODO: Dado un arreglo de números enteros, crear una función anónima que
		// retorne el número mayor.
		Function<List<Integer>, Integer> maxNumber = numbers -> numbers.stream().max(Integer::compareTo).orElse(null);
		System.out.println("Mayor number is " + maxNumber.apply(arrayNumbers));

		// TODO:Dado un arreglo de números enteros, crear una función anónima que
		// retorne el número menor.
		Function<List<Integer>, Integer> minNumber = numbers -> numbers.stream().min(Integer::compareTo).orElse(null);
		System.out.println("Minor number is " + minNumber.apply(arrayNumbers));

		// TODO: Dado un arreglo de números enteros, crear una función anónima que
		// retorne el número que más se repite.
		Function<List<Integer>, Integer> mostRepeatedNumber = numbers -> numbers.stream()
				.collect(Collectors.groupingBy(Function.identity(), HashMap::new, Collectors.counting())).entrySet()
				.stream().max(Comparator.comparingLong(Map.Entry::getValue)).map(Map.Entry::getKey).orElse(null);
		System.out.println("most repeat Number " + mostRepeatedNumber
				.apply(Arrays.asList(1, 2, 12, 61, 3, 4, 5, 6, 7, 8, 9, 10, 61, 100, 100, 1, 1, 2)));

		// TODO: Crear una función anónima que reciba como parámetro 3 numeros enteros.
		// La función retorna el número mayor.}
		MayorBetweenThree maxBetweenThree = (numberOne, numberTwo, numberThree) -> of(numberOne, numberTwo, numberThree)
				.max(Integer::compareTo).orElse(null);
		System.out.println("Greater than three numbers " + maxBetweenThree.mayor(151, 15, 15));

		// TODO: Crear una función anónima que reciba dos parámetros, un string y un
		// numero entero. La función retorna un string el cual será el resultado de
		// multiplicar ambos parámetros.
		BiFunction<Integer, String, String> repeatStringNTimes = (number, word) -> word.repeat(number);
		System.out.println("Repeat Word  is " + repeatStringNTimes.apply(3, "hello"));
	}
}

@FunctionalInterface
interface MayorBetweenThree {

	Integer mayor(Integer numberOne, Integer numberTwo, Integer numberThree);
}
