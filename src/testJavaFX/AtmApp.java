package testJavaFX;
import javafx.application.Application;
import javafx.stage.Stage;

public class AtmApp extends Application {

	public static void main(String[] args) {
		
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("ATM Application");
		
		LoginForm loginForm = new LoginForm(primaryStage);
		HomeScreen homeScreen = new HomeScreen(primaryStage);
		
		loginForm.loginScene();
		homeScreen.homeScene();
		loginForm.setHomeScreen(homeScreen);
		homeScreen.setLoginForm(loginForm);
		
		primaryStage.setScene(loginForm.getScene());
		primaryStage.show();
		
	}

}
