package tictactoe;

public class Player {
    public String name;
    public PlayingPiece piece;

    public Player(String name, PlayingPiece pc) {
        this.name = name;
        this.piece = pc;
    }

    // we can also define methods like getPlayer, getPiece etc.
    public String getName() {
        return this.name;
    }

    public PlayingPiece getPiece() {
        return piece;
    }
}
