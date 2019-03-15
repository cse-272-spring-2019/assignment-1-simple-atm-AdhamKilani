package testJavaFX;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class LoginForm {

	private AtmLogic user;
	public AtmLogic getUser() {
		return user;
	}
	private Stage stage;
	public LoginForm(Stage stage){
		this.stage=stage;
	}
	private Scene scene;
	public Scene getScene(){
		return this.scene;
	}
	private HomeScreen homeScreen;
	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}
	
	
	public void loginScene(){
		user = new AtmLogic((short) 1111);
		
		Label passwordLable = new Label(" Password: ");
		PasswordField passwordField = new PasswordField();
		Button submit = new Button("Login");
		Button exit = new Button("Exit");
		Label hint = new Label("Pin is \"1111\" ");
		
		GridPane grid = new GridPane();
		grid.add(passwordLable,0,0);
		grid.add(passwordField,0,1);
		grid.add(submit,0,2);
		grid.add(exit,9, 9);
		grid.add(hint,0,3);
		
		submit.setOnAction(eventHandler->{
			String password = passwordField.getText();
			boolean valid = user.validatePin(password);
			if (valid){
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setContentText("Welcome To ATM Application !");
				alert.show();
				homeScreen.setUser(user);
				stage.setScene(homeScreen.getScene());
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("Invalid Pin Number!");
				alert.show();
			}
		});
		
		exit.setOnAction(eventHandler ->{
			System.exit(0);
		});
		scene = new Scene(grid,400,400);
	}
	
	
}

