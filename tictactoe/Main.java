package tictactoe;

public class Main {
    public static void main(String[] args){
        Game game  = new Game();
        String result = game.startGame();
        System.out.println("--- game over ---");
        System.out.println(result);
    }
}