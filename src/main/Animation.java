package main;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.effect.Bloom;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

//Отвечает за анимацию персоны
public class Animation {
	//Массив картинок тела персоны
	private ImageView[] p;
	//Переходы перемещение, прокрутки и свечения соответственно
	private TranslateTransition tt[];
	private RotateTransition rt[];
	private FadeTransition ft[];
	//Длительность анимации (чем меньше, тем быстрее)
	private static final Duration dur = new Duration(1300);
	
	           //Массив картинок тела персоны
	public Animation (ImageView[] human) {
		p = human;
		
		tt = new TranslateTransition [p.length];
		rt = new RotateTransition [p.length];
		ft = new FadeTransition [p.length];
		//Заполнение массивов переходов
		for (int i = 0; i < p.length; i++) {
			tt[i] = new TranslateTransition(dur);
			rt[i] = new RotateTransition(dur);
			ft[i] = new FadeTransition(dur);
			
			tt[i].setNode(p[i]);
			rt[i].setNode(p[i]);
			ft[i].setNode(p[i]);
		}
	}
	
	
	//Включает операцию отправления персоны в Рай
	public void toRay () { playAnim (0, -500, 0, 1.0f); }
	//Включает операцию отправления персоны в Ад		
	public void toAd () { playAnim (-400, 700, 360, 1.0f); }
	//Включает операцию респауна персоны
	public void toRespawn () { playAnim (0, 0, 0, 0); }
	
	//Универсальная функция проигрывания анимациии
	         //движение к: абциссе  ординате   углу.         свечение
	private void playAnim (int toX, int toY, int toRotate, float toFade) {
		for (int i = 0; i < p.length; i++) {
			
			tt[i].setFromX(0);
			tt[i].setFromY(0);
			tt[i].setToX(toX);	
			tt[i].setToY(toY);
			
			rt[i].setToAngle(toRotate);
			
			ft[i].setFromValue(1.0);
		    ft[i].setToValue(toFade);
						
			tt[i].play();
			rt[i].play();
			ft[i].play();
		}
		tt[p.length - 1].setOnFinished(new EventHandler<ActionEvent>() {
			//При окончании анимации
			@Override
			//Возврат персоны на прежнее место
			public void handle(ActionEvent event) {
				for (int i = 0; i < p.length; i++) {
					TranslateTransition tt = new TranslateTransition(new Duration(1), p[i]);
					FadeTransition ft = new FadeTransition(new Duration(1), p[i]);
					tt.setToX(0);
					tt.setToY(0);
					p[i].setRotate(0);
					ft.setToValue(1.0);
					ft.play();
					tt.play();
				}
				
			}
		});
	}
	
	
	//Анимация свечения кнопки "Играть" в меню
	public static void glowAnimation (Node node) {
		if (node.getEffect() != null) return;
		Runnable r = new Runnable() {			
			@Override
			public void run() {		
				Bloom bloom = new Bloom (0.5f);
				byte k = 1;
				while (true) {
					if (bloom.getThreshold() > 0.8f || bloom.getThreshold() <= 0.2f) k = (byte) -k;
					bloom.setThreshold(bloom.getThreshold() + 0.05f*k);
					node.setEffect(bloom);
					try {
						Thread.sleep(70);
					} catch (InterruptedException e) {}
				}
				
			}
		};
		Thread t = new Thread(r);
		t.setDaemon(true);
		t.start();
	}
	
}
