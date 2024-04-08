package model;

public class ScoreManager implements java.io.Serializable {

    private int highScore, currentScore;
    
    public ScoreManager() {
        currentScore = 0;
    }
    
    public void updateScore(int increaseScore) {
        currentScore += increaseScore;
    }
    
    public void displayScore() {
    
    }
    
}
