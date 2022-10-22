package main;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import message.ShowMessage;
import person.Person;

//�����, ���������������� ��� ������� � ������ ������ ���� �� ������
public class ObjectsVisible {
	//��� ����� ����(����������)
	private Object[] pg;
	//������ �������� ���� ������� 
	private ImageView iPerson[] = new ImageView [5];
	//������ ��������� �������
	private TextArea memo;
	//������ ����� � ������� �������
	private Label lName; 
	//������ "������" � ������� ����
	private Button bGame;
	//������ � �������� ���� (�������������)
	private AnchorPane pm;
	
          //                 ������� ������        ������ �������      ������ �������� ����
	public ObjectsVisible (AnchorPane panelGame, AnchorPane panelMode, ImageView[] iPerson) {
		pg = panelGame.getChildren().toArray();		
		pm = panelMode;
		for (int i = 0; i < iPerson.length; i++) this.iPerson[i] = iPerson[i]; 
		sort ();
	}
	
	
	//�������� ������� ������ � ������ ����.
	private void sort () {
		for (int i = 0; i < pg.length; i++) {
			     if (pg[i] instanceof Label) lName = (Label) pg[i];
			else if (pg[i] instanceof Button) bGame = (Button) pg[i];
			else if (pg[i] instanceof TextArea) memo = (TextArea) pg[i];
		}
	}
	
	
	//�������� �������
	public void hidePerson () {
		for (int i = 0; i < iPerson.length; i++) iPerson[i].setImage(null);
        lName.setText(null);   	
    	memo.clear();
	}
	
	//���������� �������
    public void showPerson (Person person) {
    	try {
    		for (int i = 0; i < iPerson.length; i++) iPerson[i].setImage(person.getAppearance()[i]);		
		    lName.setText(person.getName() + " " + person.getSurname());    	 	
    	    for (int i = 0; i < person.getActions().size(); i++) 
    		    memo.appendText(person.getActions().get(i) + "\n");
    	    
		} catch (NullPointerException e) { new ShowMessage("�� ������� ������ �������."); }
    }
    
    
    //��������/���������� ������� ���������.
    public void showGame (boolean b) {
    	for (int i = 0; i < pg.length; i++) ((Node) pg[i]).setVisible(b);
    	bGame.setVisible(!b);
    	pm.setVisible(!b);
    }
    
    
    

	
}
