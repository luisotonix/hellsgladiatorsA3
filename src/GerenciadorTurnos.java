public class GerenciadorTurnos {

    private int turnoAtual = 1;

    public void proximoTurno() {
        turnoAtual++;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }
    
      public Gladiador determinarOrdem(Gladiador jogador, Gladiador oponente) {
        // Quem tem maior velocidade de ataque vai primeiro
        if (jogador.getVelocidadeAtaque() > oponente.getVelocidadeAtaque()) {
            return jogador;
        } else if (oponente.getVelocidadeAtaque() > jogador.getVelocidadeAtaque()) {
            return oponente;
        } else {
            // Se empatar, sorteia
            return Aleatorio.chance(50) ? jogador : oponente;
        }
    }
}
