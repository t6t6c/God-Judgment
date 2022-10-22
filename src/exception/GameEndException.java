package exception;

//����������, ������� ����� ��������� ��� ����� ���� 
//(����� ������ ������ ������ ������ 0 ��� ������ 30).
@SuppressWarnings("serial")
public class GameEndException extends Exception {
	private boolean win;
	
	public GameEndException (boolean isWin) {
		super ("Game end.");
		win = isWin;
	}
	
	
	public boolean isWin () { return win; }
}
