package application;

import controller.Controller;
import view.display.ConsoleDisplay;
import view.display.Display;
import view.input.ConsoleInput;
import view.input.Input;

public class Application {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Display display = new ConsoleDisplay();
        Controller controller = new Controller(input, display);
        controller.run();
    }
}
