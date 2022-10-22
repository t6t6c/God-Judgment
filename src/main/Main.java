package main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

//������� �����, � �������� ���������� ������ ���������
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
            Parent root = FXMLLoader.load(getClass().getResource("Glav.fxml"));
            primaryStage.setTitle("��������� ���");
            primaryStage.setScene(new Scene(root, 1000, 850));
            primaryStage.show();
		} catch (IOException e) {}
	}
	
	public static void main(String[] args) { launch(args); }
}
