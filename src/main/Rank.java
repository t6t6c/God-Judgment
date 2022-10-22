package main;

import java.text.DecimalFormat;
import exception.GameEndException;
import javafx.scene.control.Label;

//Класс, отвечающий за изменение статуса у игрока.
public final class Rank {
	//Формат значения статуса
	private final DecimalFormat df = new DecimalFormat("#0.00");
    //Лайбелы - счётчики для интерфейса программы 
	private Label lRank;
	private Label lAd;
	private Label lLife;
	private Label lRay;
	//Счётчики Рая, Ада и Респауна
	private int summRay = 0;
	private int summAd = 0;
	private int summRespawn = 0;
	//Константы, хранящие максимальное, минимальное и дефолтное значение статуса.
	public final static double DEFAULT_STATUS = 10.00;
	public final static double MAX_STATUS = 30.00;
	public final static double MIN_STATUS = 0.00;
	//Соответствующее значение коэффициента k для каждого режима игры. 
	public final static float MODE_LONG = 0.1f;
	public final static float MODE_NORMAL = 0.3f;
	public final static float MODE_FAST = 0.6f;
	public final static float MODE_ONE_CHANGE = 100;
	//коэффициент k, нужный для подсчёта изменения статуса
	private float k;
	//начальный статус
	private double statusRayAd = DEFAULT_STATUS;

	
            //Счётчик ранга  Счётчик Ад Счётчик Рес  Счётчик Рай Режим игры(задаётся через константы
	public Rank(Label lRank, Label lAd, Label lLife, Label lRay, float mode) {
		this.lRank = lRank;
		this.lAd = lAd;
		this.lLife = lLife;
		this.lRay = lRay;	
		k = mode;
	}
	
	
	//Вызывается перед отправкой в рай персоны и порождает исключение конца игры
	public void addRay (int positiveMinusNegative) throws GameEndException { 
		summRay++; 
		setRank (positiveMinusNegative < 0 ?
				positiveMinusNegative*2.5f : positiveMinusNegative);
	}
	//Вызывается перед отправкой в ад персоны и порождает исключение конца игры
	public void addAd (int positiveMinusNegative) throws GameEndException { 
		summAd++; 
		setRank (positiveMinusNegative > 0 ?
				-positiveMinusNegative*2.5f : -positiveMinusNegative);
	}
	//Вызывается перед возрождением персоны и порождает исключение конца игры
	public void addLife (int positiveMinusNegative) throws GameEndException {
		summRespawn++;
		setRank (Math.abs(positiveMinusNegative) > 0 ?
				-0.7f*Math.abs(positiveMinusNegative) : 2f);
	}
	
	
	//Завершает расчёт изменения статуса и выводит его на экран.
	//Также вызывает исключение конца игры.
	private void setRank (float arg1) throws GameEndException{
		
		statusRayAd += arg1 * k; 
		
		setCounts();
		setStatus();
		
		if (statusRayAd < MIN_STATUS) throw new GameEndException (false);
		else if (statusRayAd > MAX_STATUS) throw new GameEndException (true);
	}
	
	
	//Обновляет значения счётчиков на экране
	private void setCounts () {
		lAd.setText(String.valueOf(summAd));
		lRay.setText(String.valueOf(summRay));
		lLife.setText(String.valueOf(summRespawn));
	}
	//Обновляет значение статуса на экране	
	private void setStatus () { lRank.setText(df.format(statusRayAd)); }
	
	
	//Геттеры
	public int getSummRay()     { return summRay; }
	public int getSummAd()      { return summAd; }
	public int getSummRespawn() { return summRespawn; }	
	public float getMode ()     { return k; };

}
