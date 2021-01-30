package lesson5;

public class Winner {
    private String winnerName = "";

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        if (this.winnerName.equals(""))
            this.winnerName = winnerName;
    }

}
