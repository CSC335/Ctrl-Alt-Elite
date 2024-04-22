package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 * Represents the collection of Snake Accounts
 *
 * @author Kayla Pierson
 */

public class SnakeAccountCollection implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<SnakeAccount> accounts;
	public int overallHighScore;

	public SnakeAccountCollection() {
		accounts = new ArrayList<>();
		readOverallHighScore();
	}

	public List<SnakeAccount> getAccounts() {
		return accounts;
	}

	public SnakeAccount getAccount(String username) {
		for (SnakeAccount account : accounts) {
			if (account.getUsername().equals(username)) {
				return account;
			}
		}
		return null;
	}

	public boolean addAccount(SnakeAccount account) {
		for (SnakeAccount existingAccount : accounts) {
			if (existingAccount.getUsername().equals(account.getUsername())) {
				showAlert("Username already taken!");
				return false;
			}
		}
		accounts.add(account);
		return true;
	}

	public int getOverallHighScore() {
		return overallHighScore;
	}

	public void updateOverallHighScore(int newHighScore) {
		overallHighScore = newHighScore;
	}

	public void writeState() {
		try (FileOutputStream fileOut = new FileOutputStream("jukebox_accounts.ser");
				ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
			objectOut.writeObject(accounts);
			writeOverallHighScore();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readState() {
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("jukebox_accounts.ser"))) {
			accounts = (List<SnakeAccount>) inFile.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void readOverallHighScore() {
		try (ObjectInputStream inFile = new ObjectInputStream(new FileInputStream("overall_high_score.ser"))) {
			overallHighScore = (int) inFile.readObject();
		} catch (IOException | ClassNotFoundException e) {
			overallHighScore = 0; // If unable to read, initialize to 0
		}
	}

	private void writeOverallHighScore() {
		try (ObjectOutputStream outFile = new ObjectOutputStream(new FileOutputStream("overall_high_score.ser"))) {
			outFile.writeObject(overallHighScore);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAlert(String message) {
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("Alert");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}