import java.util.Scanner;

public class InterfaceConsole {
    public void mostrarMenuPrincipal() throws InterruptedException {
        System.out.println("Hell's Gladiators");
        System.out.print("Carregando");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".");
        Thread.sleep(500);
        System.out.print(".\n\n\n\n\n\n");
        Thread.sleep(500);
        System.out.println("--------------------------------------------");
        System.out.println("Bem-vindo ao Coliseu!");
        System.out.println("--------------------------------------------\n\n\n\n\n");
        System.out.println("Seu objetivo é sobreviver dentro do coliseu e conquistar vitórias.");
        System.out.println("1 - Iniciar Jogo");
        System.out.println("2 - Ver Regras");
        System.out.println("3 - Ver Estatísticas");
        System.out.println("4 - Sair");
        System.out.println("Digite o número correspondente à opção que deseja escolher para começar:");
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public void iniciarJogo() {
        Scanner scanner = new Scanner(System.in);
        boolean continueLoop = true;
        String nome;
        Gladiador gladiador = new Gladiador();
        Arena arena = new Arena();
        Oponente oponente = new Oponente();
        
        System.out.println("Classes disponíveis para escolha:");
        System.out.println("1 - Tanque\nHP: 10\nArma: Machado (Força: 5)\nBônus: Armadura reduz 3 de dano, chance de quebrar armadura do oponente após 2 ataques\nPenalidade: Ataca sempre por último\n\n");
        System.out.println("2 - Assassino\nHP: 7\nArma: Rede e Adaga (Força: 2)\nBônus: Rede paralisa o oponente\nPenalidade: Não tem armadura\n\n");
        System.out.println("3 - Bárbaro\nHP: 9\nArma: Espada (Força: 3)\nBônus: Armadura reduz 2 de dano, chance de fazer o oponente sangrar (+1 de dano por 2 turnos)\nPenalidade: \n\n");
        System.out.println("4 - Lanceiro\nHP: 9\n Arma: Lança (Força: 4)\nBônus: Armadura reduz 1 de dano, chance de desarmar oponente\nPenalidade: \n\n");
        System.out.println("5 - Arqueiro\nHP: 8\nArma: Arco e flecha (Força: 2)\nBônus: Arco ignora armadura, oponente precisa estar perto para atacar\nPenalidade: \n");
        while (continueLoop) {
            System.out.println("Digite o número correspondente à classe que deseja escolher:");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
            switch (escolha) {
                case 1 -> {
                    System.out.println("Você escolheu a classe Tanque!");
                    System.out.print("Digite o nome do seu gladiador: ");
                    nome = scanner.nextLine();
                    gladiador.setGladiador(nome, "Tanque", "machado");
                    continueLoop = false;
                }
                case 2 -> {
                    System.out.println("Você escolheu a classe Assassino!");
                    System.out.print("Digite o nome do seu gladiador: ");
                    nome = scanner.nextLine();
                    gladiador.setGladiador(nome, "Assassino", "rede_adaga");
                    continueLoop = false;
                }
                case 3 -> {
                    System.out.println("Você escolheu a classe Bárbaro!");
                    System.out.print("Digite o nome do seu gladiador: ");
                    nome = scanner.nextLine();
                    gladiador.setGladiador(nome, "Bárbaro", "espada");
                    continueLoop = false;
                }
                case 4 -> {
                    System.out.println("Você escolheu a classe Lanceiro!");
                    System.out.print("Digite o nome do seu gladiador: ");
                    nome = scanner.nextLine();
                    gladiador.setGladiador(nome, "Lanceiro", "lanca");
                    continueLoop = false;
                }
                case 5 -> {
                    System.out.println("Você escolheu a classe Arqueiro!");
                    System.out.print("Digite o nome do seu gladiador: ");
                    nome = scanner.nextLine();
                    gladiador.setGladiador(nome, "Arqueiro", "arco");
                    continueLoop = false;
                }
                default -> System.out.println("Escolha inválida. Por favor, selecione uma classe válida.");
            }
        }
        // CRIAR OPONENTE ALEATÓRIO E INICIAR PARTIDAS EM LOOP
        boolean repetirPartida = true;
        while (repetirPartida) {
            oponente = new Oponente();
            oponente = oponente.getOponente(); // atribui oponente sorteado

            System.out.println("\nSeu oponente: " + oponente.getNome() + " (" + oponente.getTipoClasse() + ")");
            System.out.println("HP: " + oponente.getHp());

            // criar arena nova para cada partida
            Arena arenaPartida = new Arena();
            arenaPartida.iniciarBatalha(gladiador, oponente);

            // Pergunta ao usuário se quer jogar outra partida
            System.out.println("\nDeseja iniciar outra partida com o mesmo gladiador?");
            System.out.println("1 - Sim");
            System.out.println("2 - Não (voltar ao menu)");
            int opc = 0;
            while (true) {
                if (scanner.hasNextInt()) {
                    opc = scanner.nextInt();
                    scanner.nextLine(); // consumir newline
                    if (opc == 1 || opc == 2) break;
                } else {
                    scanner.nextLine(); // descartar entrada inválida
                }
                System.out.println("Entrada inválida. Digite 1 para Sim ou 2 para Não:");
            }

            if (opc == 1) {
                // restaurar o gladiador para a próxima batalha (reinicia HP e status)
                gladiador.setGladiador(gladiador.getNome(), gladiador.getTipoClasse(), gladiador.getArma().getNome());
                repetirPartida = true;
            } else {
                repetirPartida = false;
            }
        }
        // não fechar scanner aqui (System.in usado pelo menu principal)
    }

    public void mostrarRegras() {
        System.out.println("Cada classe tem seu HP máximo, arma e habilidade especial. Depois de escolher sua classe, seu gladiador será jogado no Coliseu para lutar contra outros gladiadores e sobreviver eventos especiais...\nCada turno você poderá escolher entre certas ações e o último gladiador com HP é o vencedor!");
    }
public void mostrarEstatisticas() {
    GerenciadorRanking ranking = new GerenciadorRanking();
    ranking.exibirTop10();
}


}
