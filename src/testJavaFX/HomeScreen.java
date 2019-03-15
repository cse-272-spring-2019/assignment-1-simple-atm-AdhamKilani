package testJavaFX;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

public class HomeScreen {
	
	byte j=0,max=0,check=0;
	private AtmLogic user;

	public void setUser(AtmLogic user) {
		this.user = user;
	}

	private Stage stage;

	public HomeScreen(Stage stage) {
		this.stage = stage;
	}

	private Scene scene;

	public Scene getScene() {
		return this.scene;
	}

	private LoginForm loginForm;

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}

	public void homeScene() {

		Label space1 = new Label("");
		Label space2 = new Label("");
		Label space3 = new Label("");
		Label space4 = new Label("");
		Label welcome = new Label("Project ATM made by 5542");
		Label balance = new Label();
		Button deposit = new Button("Deposit");
		Button withdraw = new Button("Withdraw");
		Button balanceInquiry = new Button("Balance\nInquiry");
		Button next = new Button("Next");
		Label Txt = new Label();
		Button previous = new Button("Previous");
		Button logout = new Button("Logout");
		Button exit = new Button("Exit");
		Label amount = new Label();
		TextField amountAdded = new TextField();
		Button submit = new Button("Submit");
		
		GridPane grid = new GridPane();
		grid.add(space1,0,3);
		grid.add(amount, 10, 4);
		grid.add(space2,0,4);
		grid.add(amountAdded, 10, 5);
		grid.add(space3,0,5);
		grid.add(submit, 10, 6);
		grid.add(space4,0,14);
		grid.add(welcome, 2, 2);
		grid.add(deposit, 0, 5);
		grid.add(withdraw, 0, 6);
		grid.add(balanceInquiry, 0, 8);
		grid.add(balance, 1, 8);
		grid.add(next, 1, 12);
		grid.add(Txt, 2, 12);
		grid.add(previous, 0, 12);
		grid.add(logout, 0, 22);
		grid.add(exit, 10, 22);
		amountAdded.setVisible(false);
		submit.setVisible(false);
		amount.setVisible(false);
		deposit.setOnAction(eventHandler -> {
			
			balance.setVisible(false);
			amountAdded.setVisible(true);
			submit.setVisible(true);
			amount.setVisible(true);
			amount.setText("Enter the amount to deposit ");
			amountAdded.setText("");
			
			submit.setOnAction(eventHandler2 -> {
				String amountDep = amountAdded.getText();
				boolean valid = user.validateMoney(amountDep);
				if (valid && Double.parseDouble(amountDep) < 2140000000) {
					user.deposit(Double.parseDouble(amountDep));
					user.addToHistory(amountDep+"$ Deposited");
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setContentText(Double.parseDouble(amountDep)+"$ Deposited Susccessfully!");
					alert.show();
					amountAdded.setText("");
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Invalid Amount!");
					alert.show();
					amountAdded.setText("");
					return;
				}
			});
		});
		withdraw.setOnAction(eventHandler -> {	
			
			balance.setVisible(false);
			amountAdded.setVisible(true);
			submit.setVisible(true);
			amount.setVisible(true);
			amount.setText("Enter the amount to withdraw ");
			amountAdded.setText("");
		
			submit.setOnAction(eventHandler2 -> {
				String amountWithdraw = amountAdded.getText();
				boolean valid = user.validateMoney(amountWithdraw);
				if (valid) {
					if(Double.parseDouble(amountWithdraw) < user.balanceInquiry()){
						user.withdraw(Double.parseDouble(amountWithdraw));
						user.addToHistory(amountWithdraw+"$ Withdrawn");
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setContentText(Double.parseDouble(amountWithdraw)+"$ Withdrawn Successfully!");
						alert.show();
						amountAdded.setText("");
					} else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Amount greater than balance !!");
						alert.show();
						return;
					}
					
				} else {
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Invalid Amount!");
					alert.show();
					return;
				}
			});
		});
		balanceInquiry.setOnAction(eventHandler -> {
			balance.setVisible(true);
			amountAdded.setVisible(false);
			submit.setVisible(false);
			amount.setVisible(false);
			balance.setText("Your Balance is " + user.balanceInquiry() + "$ ");
		});
		next.setOnAction(eventHandler ->{
			j=user.getJ();
			max=user.getMax();
			check=user.getCheck();
			if(check==1){
				if (j==max){
				Txt.setText(user.next());
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("Last history entry");
				alert.show();
			}else {
				Txt.setText(user.next());
			}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("No Transactions Occured!!");
				alert.show();
			}
			
		});
		previous.setOnAction(eventHandler ->{
			j=user.getJ();
			max=user.getMax();
			check=user.getCheck();
			if(check==1){
				if (j==0){
				Txt.setText(user.previous());
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setContentText("First history entry");
				alert.show();
			}else {
				Txt.setText(user.previous());
			}
			}else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setContentText("No Transactions Occured!!");
				alert.show();
			}
			
		});
		logout.setOnAction(eventHandler -> {
			amountAdded.setVisible(false);
			submit.setVisible(false);
			amount.setVisible(false);
			balance.setVisible(false);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setContentText("Goodbye !");
			alert.show();
			stage.setScene(loginForm.getScene());
		});
		exit.setOnAction(eventHandler ->{
			System.exit(0);
		});

		

		scene = new Scene(grid, 800, 600);

	}
}
