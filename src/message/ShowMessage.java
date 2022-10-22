package message;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ShowMessage {
	
	public ShowMessage (String message) {
		 final Stage dialog = createStage();		         
         VBox box = createBox();
         
         createLabel (message, box);        
         createScene (dialog, box);
         createButton (dialog, box);
	}
	
	
	
	private Stage createStage () {
		Stage dialog = new Stage();		 
        dialog.initModality(Modality.APPLICATION_MODAL);
        return dialog;
	}
	
	private VBox createBox () {
		VBox box = new VBox(20);
        box.setAlignment(Pos.CENTER);
        return box;
	}
	
		
	private void createLabel (String message, VBox box) {
		Label label = new Label(message);
        label.setFont(new Font("Times New Roman", 16));       
        label.setWrapText(true);
        label.setTextAlignment(TextAlignment.CENTER);
        box.getChildren().add(label);
	}
		
	private void createScene (Stage dialog, VBox box) {
		Scene dialogScene = new Scene(box, 300, 200);
        dialog.setScene(dialogScene);
        dialog.show();
	}
	
	private void createButton (Stage dialog, VBox box) {
		Button button = new Button ("OK");
		button.prefHeight(20);
		button.setPrefSize(50, 50);
		button.setOnMouseClicked(MouseEvent -> {
			dialog.close();
		});
		box.getChildren().add(button);
	}
	
}
