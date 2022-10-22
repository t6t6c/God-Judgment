package exception;

//»сключение, которое будет возникать при конце игры 
//(когда статус игрока станет меньше 0 или больше 30).
@SuppressWarnings("serial")
public class GameEndException extends Exception {
	private boolean win;
	
	public GameEndException (boolean isWin) {
		super ("Game end.");
		win = isWin;
	}
	
	
	public boolean isWin () { return win; }
}
