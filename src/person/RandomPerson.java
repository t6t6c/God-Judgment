package person;

import java.util.ArrayList;
import java.util.Random;
import constants.HumanActions;
import constants.Names;
import constants.Pictures;
import constants.Surnames;
import javafx.scene.image.Image;
import main.Queue;

//Класс, отвечающий за создание случайных объектов
public class RandomPerson {
	private Random r = new Random ();
	//Выдаёт случайно сгенерированную персону
	public Person getPerson () {
		
		int status = 0;
		Image[] images = new Image[5];
		images[0] = Pictures.BOOTS[r.nextInt(Pictures.BOOTS.length)];
		images[1] = Pictures.BODIES[r.nextInt(Pictures.BODIES.length)];
		images[2] = Pictures.HANDSL[r.nextInt(Pictures.HANDSL.length)];
		images[3] = Pictures.HANDSR[r.nextInt(Pictures.HANDSR.length)];
		images[4] = Pictures.HEADS[r.nextInt(Pictures.HEADS.length)];
		
		String n = Names.NAMES[r.nextInt(Names.NAMES.length)];
		String sn = Surnames.SURNAMES[r.nextInt(Surnames.SURNAMES.length)];
		
		int k = r.nextInt(4) + 4;
		ArrayList<String> actions = new ArrayList<String>();
		Person person = new Person (images, actions, status, n, sn);
		addActions(k, person);
		
		return person;	
	}
	
	
	//Выдаёт очередь, заполненную случайно сгенерированными персонами
	public Queue getQueue () {
		Queue queue = new Queue();
		for (int i = 0; i < queue.max; i++) queue.addElement(getPerson());
		return queue;
	}
	
	
	//Добавляет случайно сгенерированный поступок к персоне
	public void addActions (int amount, Person person) {
		int status = person.getStatus();
		int d;
		String[] act;
		for (int i = 0; i < amount; i++) {
			if (r.nextBoolean()) {
				act = HumanActions.ACTIONS_POSITIVE;
				status++;
			} else {
				act = HumanActions.ACTIONS_NEGATIVE;
				status--;
			}		
			do 	d = r.nextInt(act.length);
			while (person.getActions().contains(act[d]));
			person.getActions().add(act[d]);
		}
//		person.getActions().add(String.valueOf(status));
		person.setStatus(status);
	}
	
}
