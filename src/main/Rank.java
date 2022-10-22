package main;

import java.text.DecimalFormat;
import exception.GameEndException;
import javafx.scene.control.Label;

//�����, ���������� �� ��������� ������� � ������.
public final class Rank {
	//������ �������� �������
	private final DecimalFormat df = new DecimalFormat("#0.00");
    //������� - �������� ��� ���������� ��������� 
	private Label lRank;
	private Label lAd;
	private Label lLife;
	private Label lRay;
	//�������� ���, ��� � ��������
	private int summRay = 0;
	private int summAd = 0;
	private int summRespawn = 0;
	//���������, �������� ������������, ����������� � ��������� �������� �������.
	public final static double DEFAULT_STATUS = 10.00;
	public final static double MAX_STATUS = 30.00;
	public final static double MIN_STATUS = 0.00;
	//��������������� �������� ������������ k ��� ������� ������ ����. 
	public final static float MODE_LONG = 0.1f;
	public final static float MODE_NORMAL = 0.3f;
	public final static float MODE_FAST = 0.6f;
	public final static float MODE_ONE_CHANGE = 100;
	//����������� k, ������ ��� �������� ��������� �������
	private float k;
	//��������� ������
	private double statusRayAd = DEFAULT_STATUS;

	
            //������� �����  ������� �� ������� ���  ������� ��� ����� ����(������� ����� ���������
	public Rank(Label lRank, Label lAd, Label lLife, Label lRay, float mode) {
		this.lRank = lRank;
		this.lAd = lAd;
		this.lLife = lLife;
		this.lRay = lRay;	
		k = mode;
	}
	
	
	//���������� ����� ��������� � ��� ������� � ��������� ���������� ����� ����
	public void addRay (int positiveMinusNegative) throws GameEndException { 
		summRay++; 
		setRank (positiveMinusNegative < 0 ?
				positiveMinusNegative*2.5f : positiveMinusNegative);
	}
	//���������� ����� ��������� � �� ������� � ��������� ���������� ����� ����
	public void addAd (int positiveMinusNegative) throws GameEndException { 
		summAd++; 
		setRank (positiveMinusNegative > 0 ?
				-positiveMinusNegative*2.5f : -positiveMinusNegative);
	}
	//���������� ����� ������������ ������� � ��������� ���������� ����� ����
	public void addLife (int positiveMinusNegative) throws GameEndException {
		summRespawn++;
		setRank (Math.abs(positiveMinusNegative) > 0 ?
				-0.7f*Math.abs(positiveMinusNegative) : 2f);
	}
	
	
	//��������� ������ ��������� ������� � ������� ��� �� �����.
	//����� �������� ���������� ����� ����.
	private void setRank (float arg1) throws GameEndException{
		
		statusRayAd += arg1 * k; 
		
		setCounts();
		setStatus();
		
		if (statusRayAd < MIN_STATUS) throw new GameEndException (false);
		else if (statusRayAd > MAX_STATUS) throw new GameEndException (true);
	}
	
	
	//��������� �������� ��������� �� ������
	private void setCounts () {
		lAd.setText(String.valueOf(summAd));
		lRay.setText(String.valueOf(summRay));
		lLife.setText(String.valueOf(summRespawn));
	}
	//��������� �������� ������� �� ������	
	private void setStatus () { lRank.setText(df.format(statusRayAd)); }
	
	
	//�������
	public int getSummRay()     { return summRay; }
	public int getSummAd()      { return summAd; }
	public int getSummRespawn() { return summRespawn; }	
	public float getMode ()     { return k; };

}
