package main;

import constants.HumanActions;
import exception.GameEndException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import message.ShowMessage;
import person.Person;
import person.RandomPerson;
//������� �����, ������� ��������� � ������������ ������, � ����� ������ �� ��������� ����.
public class GlavController {

    @FXML
    private Button bRay, bAd, bRespawn, bBegin;  
    @FXML
    private ImageView ihead, ibody, iboots, ihandr, ihandl;     
    @FXML
    private TextArea memo;       
    @FXML
    private Label lRank, lAd, lRespawn, lRay, lname;
    @FXML
    private AnchorPane pGame, pMode;
    @FXML
    private RadioButton rb1, rb2, rb3, rb4;
       
    private Queue queue;   
    private Person person;
    private Rank rank;
    private RandomPerson rp;
    private ImageView iperson[];
    private Animation an;
    private ObjectsVisible ob;
    private float mode;

    //�������������� ���������.
    @FXML
    void initialize() {
    	Animation.glowAnimation(bBegin); 
    	iperson = new ImageView[] {iboots, ibody, ihandl, ihandr, ihead};
    	an = new Animation (iperson);
    	ob = new ObjectsVisible(pGame, pMode, iperson);
    	ob.showGame(false);
    	ToggleGroup bg = new ToggleGroup();
    	rb1.setToggleGroup(bg);
    	rb2.setToggleGroup(bg);
    	rb3.setToggleGroup(bg);
    	rb4.setToggleGroup(bg);
    	mode = Rank.MODE_NORMAL;
    }
    
    
    //��������� ������ �������� ������� �� ������ �������� � ���������� ������ ��������
    public void lockButtons () {
		bAd.setDisable(true);
		bRay.setDisable(true);
		bRespawn.setDisable(true);
		timer();
    }
    
    
    //�������� ������� ������� �� ��������� � �������
    public void nextPerson () {
    	ob.hidePerson();
        person = queue.getElement();
        ob.showPerson(person);
    }
    
    
    //����� ������� �� ������ �������� ������� � ��
    @FXML
    void onAd(MouseEvent event) {
    	try {
			rank.addAd(person.getStatus());
    	queue.addElement(rp.getPerson());
    	lockButtons();
    	an.toAd();
    	} catch (GameEndException e) { gameEnd(e); }
    }
    //����� ������� �� ������ �������� ������� � ���
    @FXML
    void onRay(MouseEvent event) {
    	try {
    	    rank.addRay(person.getStatus());
    	queue.addElement(rp.getPerson());
    	lockButtons();
    	an.toRay();
    	} catch (GameEndException e) { gameEnd(e); }
    }
    //����� ������� �� ������ �������� �������
    @FXML
    void onRespawn(MouseEvent event) {
    	if (person.getActions().contains(HumanActions.RESPAWN)){
    		new ShowMessage ("�� �� ������ ��������� ����� �������� ������ ���!");
    		return;
    	}
    	try {
			rank.addLife(person.getStatus());
    	person.getActions().add(HumanActions.RESPAWN);
    	rp.addActions(2, person);
    	queue.addElement(person);
    	an.toRespawn();
    	lockButtons();
    	} catch (GameEndException e) { gameEnd(e); }
    }
    
    
    //���������� ������� ������ ������. ��������� �� ����������� ��� ������ ����.
    @FXML
    void onStart(MouseEvent event) { 
    	lRank.setText(String.valueOf(Rank.DEFAULT_STATUS));
    	lRay.setText("0");
    	lAd.setText("0");
    	lRespawn.setText("0");
    	rp = new RandomPerson();
    	queue = rp.getQueue();
    	rank = new Rank(lRank, lAd, lRespawn, lRay, mode);
    	nextPerson(); 
    	ob.showGame(true); 
    }
    
    //������ ������ �����������
    @FXML
    void onRb1(ActionEvent event) { mode = Rank.MODE_LONG; }
    @FXML
    void onRb2(ActionEvent event) { mode = Rank.MODE_NORMAL; }
    @FXML
    void onRb3(ActionEvent event) { mode = Rank.MODE_FAST; }
    @FXML
    void onRb4(ActionEvent event) { mode = Rank.MODE_ONE_CHANGE; }
    
    
    //������, ������� ���������� ������ ����� �������� �����. ����������� ����������� � ���������. 
    void timer () {
    	Runnable r = new Runnable() {			
			@Override
			public void run() {
				try {
					Thread.sleep(1300);
				} catch (Exception e) {}
				Platform.runLater(new Runnable() {
				    @Override
				    public void run() {
				    	bAd.setDisable(false);
						bRay.setDisable(false);
						bRespawn.setDisable(false);
						nextPerson();
				    }
				});
			}
		};
		new Thread(r).start();
    }
    
    
    //����������, ����� ���� �������������. ������� ��� �������� ������ � ������� ���������
    //�� ��������� ����.
    void gameEnd (GameEndException e) {
    	ob.showGame(false);
    	rp = null;
    	queue = null;
    	
    	String str;
    	if (e.isWin()) str = "��� ������ �������� " + Rank.MAX_STATUS + ", �� ��������!";
    	else str = "��� ������ ���� ������ " + Rank.MIN_STATUS + ", �� ���������!";
    	str += "\n����� ���������� � ��: " + rank.getSummAd() +
    		   "\n����� ���������� � ���: " + rank.getSummRay() +
    		   "\n����� ����������: " + rank.getSummRespawn();
    	new ShowMessage(str);
    	rank = null;
    }
    
}
