import java.util.Scanner;

public class SistemaCombate {
    private Gladiador jogador;
    private Oponente oponente;
    private CalculadorDano calculador;
    private GerenciadorTurnos gerenciadorTurnos;
    private Plateia plateia;
    private Aleatorio aleatorio;                 
    
    public SistemaCombate(Gladiador jogador, Oponente oponente) {
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
        
        // Atualiza status (paralisia, sangramento)
        jogador.getStatus().atualizarStatus();
        oponente.getStatus().atualizarStatus();

        // Seu turno
        if (!jogador.getStatus().isParalizado()) {
            System.out.println("\n1 - Atacar\n2 - Defender");
            while (voltar) {
                Scanner scanner = new Scanner(System.in);
                int escolha = scanner.nextInt();
                switch (escolha) {
                    case 1:
                        jogador.setAcao(escolha);
                        voltar = false;
                        break;
                    case 2:
                        jogador.setAcao(escolha);
                        voltar = false;
                        break;
                    default:
                        System.out.println("Escolha inválida! Tente novamente: ");
                }
            }
        } else {
            jogador.setAcao(0);
        }

        if (!oponente.getStatus().isParalizado()) {
            oponente.setAcao(oponente.decidirAcao());
        } else {
            oponente.setAcao(0);
        }
        
        // Determina ordem de ataque
        gerenciadorTurnos.determinarOrdem(jogador, oponente);
        
        // Primeiro ataca
        if (jogador.getAcao() == 1 && jogador.getOrdemAtaque() == 1) {
            if (oponente.getAcao() == 2) {
                if (aleatorio.chance(60)) {
                    System.out.println("OPONENTE CONSEGUIU SE DEFENDER TOTALMENTE!");
                } else {
                    System.out.println("OPONENTE CONSEGUIU SE DEFENDER PARCIALMENTE!");
                    executarAtaque(jogador, oponente, 2);
                }
            } else {
                executarAtaque(jogador, oponente);
            }
        }
        
        
        if (oponente.getAcao() == 1 && oponente.getOrdemAtaque() == 1) {
            if (jogador.getAcao() == 2) {
                if (aleatorio.chance(60)) {
                    System.out.println("DEFESA TOTAL!");
                } else {
                    System.out.println("DEFESA PARCIAL!");
                    executarAtaque(oponente, jogador, 2);
                }
            } else {
                executarAtaque(oponente, jogador);
            }
        }

        // Segundo ataca (se estiver vivo)
        if (jogador.getAcao() == 1 && jogador.getOrdemAtaque() == 2) {
            if (oponente.getAcao() == 2) {
                if (aleatorio.chance(60)) {
                    System.out.println("OPONENTE CONSEGUIU SE DEFENDER TOTALMENTE!");
                } else {
                    System.out.println("OPONENTE CONSEGUIU SE DEFENDER PARCIALMENTE!");
                    executarAtaque(jogador, oponente, 2);
                }
            } else {
                executarAtaque(jogador, oponente);
            }
        }
        
        if (oponente.getAcao() == 1 && oponente.getOrdemAtaque() == 2) {
            if (jogador.getAcao() == 2) {
                if (aleatorio.chance(60)) {
                    System.out.println("DEFESA TOTAL!");
                } else {
                    System.out.println("DEFESA PARCIAL!");
                    executarAtaque(oponente, jogador, 2);
                }
            } else {
                executarAtaque(oponente, jogador);
            }
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

    private void executarAtaque(Gladiador atacante, Gladiador defensor, int defesa) {
        int dano = calculador.calcularDano(atacante, defensor, defesa);
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
