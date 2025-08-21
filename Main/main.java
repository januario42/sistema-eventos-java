package Main;
import controller.EventController;
import view.ConsoleView;

public class main {
    public static void main(String[] args) {
       
        EventController controlador = new EventController();
        
 
        ConsoleView visao = new ConsoleView(controlador);
        

        visao.iniciar();
    }
}
