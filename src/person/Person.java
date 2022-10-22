package person;

import java.util.ArrayList;
import constants.HumanActions;
import javafx.scene.image.Image;

//�����, �������� � ���� �������
public class Person {
	
	//������ �������� ���� �������
	private Image[] app;
	//���� � ���������� �������
	private ArrayList<String> act;
	//��� � ������� ������� 
	private String n, sn;
	private int status;
	
	//��� - ������� ����� ���-�� ������������� � ������������� ��������� �������
	
	//           ������ �������� ����        ��������          ���          ���           �������
	public Person (Image[] appearance, ArrayList<String> actions, int status, String name, String surname) {
		app = appearance;
		act = actions;
		n = name;
		sn = surname;
		this.status = status;
	}
	
	
	//������� � �������
	public Image[] getAppearance () { return app; }
	
	public ArrayList<String> getActions () { return act; }
	
	public int getStatus () { return status; }
	public void setStatus(int status) { this.status = status; }
	
	public String getName () { return n; }
	public void setName (String name) { n = name; }
	
	public String getSurname () { return sn; }
	public void setSurname (String surname) { sn = surname; }
	
	
	//���������� ������ �������� �������� �������: true - ������� ����������, false - ���.
	public Boolean isRespawn () {
		if (getActions().contains(HumanActions.RESPAWN)) return true;
		return false;
	}

	
}
