package main;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import message.ShowMessage;
import person.Person;

//Класс, предназначченные для скрытия и показа частей игры на экране
public class ObjectsVisible {
	//Все части игры(интерфейса)
	private Object[] pg;
	//Массив картинок тела персоны 
	private ImageView iPerson[] = new ImageView [5];
	//Список поступков персоны
	private TextArea memo;
	//Строка имени и фамилии персоны
	private Label lName; 
	//Кнопка "Играть" в главном меню
	private Button bGame;
	//Панель с режимами игры (радиокнопками)
	private AnchorPane pm;
	
          //                 Главная панель        Панель режимов      Массив картинок тела
	public ObjectsVisible (AnchorPane panelGame, AnchorPane panelMode, ImageView[] iPerson) {
		pg = panelGame.getChildren().toArray();		
		pm = panelMode;
		for (int i = 0; i < iPerson.length; i++) this.iPerson[i] = iPerson[i]; 
		sort ();
	}
	
	
	//Помещает объекты панели в нужные поля.
	private void sort () {
		for (int i = 0; i < pg.length; i++) {
			     if (pg[i] instanceof Label) lName = (Label) pg[i];
			else if (pg[i] instanceof Button) bGame = (Button) pg[i];
			else if (pg[i] instanceof TextArea) memo = (TextArea) pg[i];
		}
	}
	
	
	//Скрывает персону
	public void hidePerson () {
		for (int i = 0; i < iPerson.length; i++) iPerson[i].setImage(null);
        lName.setText(null);   	
    	memo.clear();
	}
	
	//Показывает персону
    public void showPerson (Person person) {
    	try {
    		for (int i = 0; i < iPerson.length; i++) iPerson[i].setImage(person.getAppearance()[i]);		
		    lName.setText(person.getName() + " " + person.getSurname());    	 	
    	    for (int i = 0; i < person.getActions().size(); i++) 
    		    memo.appendText(person.getActions().get(i) + "\n");
    	    
		} catch (NullPointerException e) { new ShowMessage("Не найдено частей персоны."); }
    }
    
    
    //Скрывает/показывает игровой интерфейс.
    public void showGame (boolean b) {
    	for (int i = 0; i < pg.length; i++) ((Node) pg[i]).setVisible(b);
    	bGame.setVisible(!b);
    	pm.setVisible(!b);
    }
    
    
    

	
}
