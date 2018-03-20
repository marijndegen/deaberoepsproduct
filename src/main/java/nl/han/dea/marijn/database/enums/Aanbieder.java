package nl.han.dea.marijn.database.enums;

public enum Aanbieder implements KeyValueEnum{
    VODAGONE("vodagone"),
    ZIGGO("ziggo");

    private final String text;


    Aanbieder(String text) {
        this.text = text;
    }

    public String getEnumValue(){
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
