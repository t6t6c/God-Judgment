package main;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import person.Person;

public class Queue {
	 private LinkedList<Person> q;  //очередь
	 public static final int STANDART_MAX = 8; //Максимальное стандартное количество элементов в очереди
	 public int max;  //максимальное количество элементов в очереди
	 //Стандартный конструктор
	 public Queue () {
		 q = new LinkedList<Person>();
		 max = STANDART_MAX;
	 }
	 //Конструктор, который устанавливает максимальное количество элементов
	 public Queue (int max) {
		 q = new LinkedList<Person>();
		 this.max = max;
	 }
	 
	 
	 //Добавить элемент в начало очереди
	 public void addElement (Person element){		 
		 if (q.size() == max) {
			 System.out.println("Очередь переполнена.");
		 }
		 q.addFirst(element);
	 }
	 
	 //Убрать и выдать элемент с конца очереди
	 public Person getElement () {
		 try { 
			 return q.removeLast(); 
		 } catch (NoSuchElementException e) { 
			 System.out.println("Нет элементов"); 
			 return null;
		 }
	 } 
	 
}