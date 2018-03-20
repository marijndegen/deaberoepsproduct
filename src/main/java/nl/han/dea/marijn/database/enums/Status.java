package nl.han.dea.marijn.database.enums;

public enum Status implements KeyValueEnum {
    OPGEZEGD("opgezegd"),
    ACTIEF("actief"),
    PROEF("proef");


    private final String text;

    Status(String text) {
        this.text = text;
    }

    @Override
    public String getEnumValue() {
        return text;
    }

    @Override
    public String toString() {
        return text;
    }
}
