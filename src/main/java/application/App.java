package application;

import controller.Controller;
import view.input.ConsoleInput;
import view.input.Input;
import view.output.ConsoleDisplay;
import view.output.Display;

public class App {
    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Display display = new ConsoleDisplay();
        Controller controller = new Controller(input, display);
        controller.run();
    }
}
