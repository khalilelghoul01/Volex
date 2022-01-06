import Volex.Window;

public class Main {
    public static void main(String[] args) {
        Window window = Window.get(400, 400, "Volex");
        window.color(0, 0, 0);
        window.run();
    }
}