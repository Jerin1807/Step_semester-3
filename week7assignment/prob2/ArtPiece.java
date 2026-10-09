public abstract class ArtPiece {
    private static int nextId = 0;
    private final String pieceId;

    protected ArtPiece() {
        pieceId = "PIECE-" + (++nextId);
    }

    public abstract String describe();

    public String getPieceId() {
        return pieceId;
    }
}