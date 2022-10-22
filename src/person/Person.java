package person;

import java.util.ArrayList;
import constants.HumanActions;
import javafx.scene.image.Image;

//Класс, хранящий в себе персону
public class Person {
	
	//Массив картинок тела персоны
	private Image[] app;
	//Лист с действиями персоны
	private ArrayList<String> act;
	//Имя и фамилия персоны 
	private String n, sn;
	private int status;
	
	//РПН - разница между кол-ом положительных и отричательных поступков персоны
	
	//           Массив картинок тела        Действия          РПН          Имя           Фамилия
	public Person (Image[] appearance, ArrayList<String> actions, int status, String name, String surname) {
		app = appearance;
		act = actions;
		n = name;
		sn = surname;
		this.status = status;
	}
	
	
	//Геттеры и сеттеры
	public Image[] getAppearance () { return app; }
	
	public ArrayList<String> getActions () { return act; }
	
	public int getStatus () { return status; }
	public void setStatus(int status) { this.status = status; }
	
	public String getName () { return n; }
	public void setName (String name) { n = name; }
	
	public String getSurname () { return sn; }
	public void setSurname (String surname) { sn = surname; }
	
	
	//Возвращает булево значение респауна персоны: true - персону возрождали, false - нет.
	public Boolean isRespawn () {
		if (getActions().contains(HumanActions.RESPAWN)) return true;
		return false;
	}

	
}
