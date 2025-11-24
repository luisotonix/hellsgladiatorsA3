import java.util.Scanner;

public class Main {
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws InterruptedException  {
        InterfaceConsole ui = new InterfaceConsole();
        Scanner scanner = new Scanner(System.in);
        OUTER:
        while (true) {
            ui.mostrarMenuPrincipal();
            int opcao = scanner.nextInt();
            switch (opcao) {
                case 1 -> ui.iniciarJogo();
                case 2 -> ui.mostrarRegras();
                default -> {
                    break OUTER;
                }
            }
        }

        scanner.close();
        
    }
}

