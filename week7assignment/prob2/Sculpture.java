public class Sculpture extends ArtPiece {
    private final String title;

    public Sculpture(String title) {
        this.title = title;
    }

    @Override
    public String describe() {
        return "Sculpture: " + title + ", carved from stone";
    }
}