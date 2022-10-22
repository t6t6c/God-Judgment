package main;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import person.Person;

public class Queue {
	 private LinkedList<Person> q;  //�������
	 public static final int STANDART_MAX = 8; //������������ ����������� ���������� ��������� � �������
	 public int max;  //������������ ���������� ��������� � �������
	 //����������� �����������
	 public Queue () {
		 q = new LinkedList<Person>();
		 max = STANDART_MAX;
	 }
	 //�����������, ������� ������������� ������������ ���������� ���������
	 public Queue (int max) {
		 q = new LinkedList<Person>();
		 this.max = max;
	 }
	 
	 
	 //�������� ������� � ������ �������
	 public void addElement (Person element){		 
		 if (q.size() == max) {
			 System.out.println("������� �����������.");
		 }
		 q.addFirst(element);
	 }
	 
	 //������ � ������ ������� � ����� �������
	 public Person getElement () {
		 try { 
			 return q.removeLast(); 
		 } catch (NoSuchElementException e) { 
			 System.out.println("��� ���������"); 
			 return null;
		 }
	 } 
	 
}