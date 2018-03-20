package nl.han.dea.marijn.database.enums;

public enum Verdubbeling implements KeyValueEnum{
    STANDAARD("standaard"),
    VERDUBBELD("verdubbeld"),
    NIETBESCHIKBAAR("niet-beschikbaar");

    private final String text;


    Verdubbeling(String text) {
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
