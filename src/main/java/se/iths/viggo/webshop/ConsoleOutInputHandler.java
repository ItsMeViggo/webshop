package se.iths.viggo.webshop;

public class ConsoleOutInputHandler implements OutInputHandler {

    @Override
    public String prompt(String message) {
        return IO.readln(message);
    }

    @Override
    public void info(String message) {
        IO.println(message);
    }

    @Override
    public String menu() {
        return IO.readln("""
                1. Lägg till produkt
                2. Lista alla produkter
                3. Visa information om en produkt
                4. Avsluta applikationen
                :""");
    }
}
