import java.util.Scanner;

public class SistemaCombate {
    private Gladiador jogador;
    private Gladiador oponente;
    private CalculadorDano calculador;
    private GerenciadorTurnos gerenciadorTurnos;
    private Plateia plateia;                    
    
    public SistemaCombate(Gladiador jogador, Gladiador oponente) {
        this.jogador = jogador;
        this.oponente = oponente;
        this.calculador = new CalculadorDano();
        this.gerenciadorTurnos = new GerenciadorTurnos();
        this.plateia = new Plateia();
    }
    
    public void iniciarCombate() {
        System.out.println("=== COMBATE INICIADO ===");
        
        while (jogador.estaVivo() && oponente.estaVivo()) {
            executarTurno();
            plateia.reagir();
        }
        
        exibirVencedor();
    }
    
    private void executarTurno() {
        gerenciadorTurnos.proximoTurno();
        
        System.out.println("\n--- TURNO " + gerenciadorTurnos.getTurnoAtual() + " ---");
        System.out.println(jogador.getNome() + " HP: " + jogador.getHp());
        System.out.println(oponente.getNome() + " HP: " + oponente.getHp());
        
        // Seu turno
        if (!jogador.getStatus().isParalizado()) {
            System.out.println("\n1 - Atacar\n2 - Defender\n3 - Esquivar");
            Scanner scanner = new Scanner(System.in);
            int escolha = scanner.nextInt();
            // Processar escolha
        }
        
        // Atualiza status (paralisia, sangramento)
        jogador.getStatus().atualizarStatus();
        oponente.getStatus().atualizarStatus();
        
        // Determina ordem de ataque
        Gladiador primeiro = gerenciadorTurnos.determinarOrdem(jogador, oponente);
        Gladiador segundo = (primeiro == jogador) ? oponente : jogador;
        
        // Primeiro ataca
        if (!primeiro.getStatus().isParalizado()) {
            executarAtaque(primeiro, segundo);
        }
        
        // Segundo ataca (se estiver vivo)
        if (segundo.estaVivo() && !segundo.getStatus().isParalizado()) {
            executarAtaque(segundo, primeiro);
        }
        
        // Dano de sangramento
        if (jogador.getStatus().isSangrando()) {
            jogador.receberDano(1);
        }
        if (oponente.getStatus().isSangrando()) {
            oponente.receberDano(1);
        }
    }
    
    private void executarAtaque(Gladiador atacante, Gladiador defensor) {
        int dano = calculador.calcularDano(atacante, defensor);
        defensor.receberDano(dano);
        
        System.out.println(atacante.getNome() + " causou " + dano + " de dano!");
        System.out.println(defensor.getNome() + " HP: " + defensor.getHp());
    }
    
    public void exibirVencedor() {
        if (jogador.estaVivo()) {
            System.out.println("🏆 VOCÊ VENCEU! 🏆");
        } else {
            System.out.println("💀 VOCÊ FOI DERROTADO! 💀");
        }
    }
    
    public Gladiador obterVencedor() {
        if (jogador.estaVivo()) {
            return jogador;
        } else {
            return oponente;
        }
    }
}