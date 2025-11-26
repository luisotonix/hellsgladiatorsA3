public class GerenciadorTurnos {

    private int turnoAtual = 0;

    public void proximoTurno() {
        turnoAtual++;
    }

    public int getTurnoAtual() {
        return turnoAtual;
    }
    
    public void determinarOrdem(Gladiador jogador, Gladiador oponente) {
        // Quem tem maior velocidade de ataque vai primeiro
        if (jogador.getVelocidadeAtaque() > oponente.getVelocidadeAtaque()) {
            jogador.setOrdemAtaque(1);
            oponente.setOrdemAtaque(2);
        } else {
            jogador.setOrdemAtaque(2);
            oponente.setOrdemAtaque(1);
        }
    }
}  
