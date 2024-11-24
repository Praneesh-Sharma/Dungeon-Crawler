public enum Direction {
    NORTH(2),SOUTH(0),EAST(3),WEST(1); // refers to the row number of the heroTileSheetLowRes.png
    private int frameLineNumber;

    Direction(int frameLineNumber) {
        this.frameLineNumber = frameLineNumber;
    }

    public int getFrameLineNumber() {
        return frameLineNumber;
    }
}
