package se.iths.viggo.webshop;

public interface OutInputHandler {

    String prompt(String message);

    void info(String message);

    String menu();
}
