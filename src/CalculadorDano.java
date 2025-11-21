public class CalculadorDano {

    private static final int CHANCE_CRITICO = 15;

    public int calcularDano(Gladiador atacante, Gladiador defensor) {
        Arma arma = atacante.getArma();
        int danoBase = arma.getForcaBase();

        // 1. Verificar crítico

        boolean critico = false;

        if (atacante.getCritico() > 0 && Aleatorio.chance(CHANCE_CRITICO)) {
            danoBase *= 2;
            critico = true;
            System.out.println(" CRÍTICO! ");

        }

        // 2 BONÛS DO HOPLOMACHUS DO PRIMEIRO ATAQUE

        if (atacante.getTipoClasse().equals(ConfiguracaoClasse.Arqueiro) && atacante.getStatus().isPrimeiroAtaque()) {
            danoBase *= 2;// Primeiro golpe sempre crítico
            atacante.getStatus().usouPrimeiroAtaque();
            System.out.println(" PRIMEIRO DISPARO FOI PERFEITO! ");

        }

        // 3.BONUS DO SECUTOR CONTRA RETIARIUS
        if (atacante.getTipoClasse().equals(ConfiguracaoClasse.Lanceiro)
                && defensor.getTipoClasse().equals(ConfiguracaoClasse.Assassino)) {
            danoBase += 2;
            System.out.println(" COUNTER! +2 DE DANO ");
        }

        // 4. PENALIDADE DO SECUTOR CONTRA OUTRAS CLASSES

        if (atacante.getTipoClasse().equals(ConfiguracaoClasse.Lanceiro)
                && !defensor.getTipoClasse().equals(ConfiguracaoClasse.Assassino)) {
            danoBase -= 1;
        }

        // 5. APLICA HABILIDADES ESPECIAIS DA ARMA
        aplicarHabilidadeArma(atacante, defensor, arma);

        // 6. APLICA ARMADURA(SE A ARMA NAO IGNORAR)
        int danoFinal = danoBase;
        if (!arma.getIgnoraArmadura() && defensor.temArmadura()) {
            danoFinal = danoBase - defensor.getReducaoDano();
            if (danoFinal < 0)
                danoFinal = 0;
        }

        return danoFinal;

    }

public void  aplicarHabilidadeArma(Gladiador atacante, Gladiador defensor, Arma arma){
String habilidade = arma.getHabilidadeEspecial();

switch(habilidade){
    case "Duplo Ataque" -> {
        if(Aleatorio.chance(arma.getChanceHabilidade())){
            System.out.println(" DUPLO ATAQUE");
            // Segundo ataque será executado no próximo turno
        }
        }

    case "Desarmar" -> {
        if(Aleatorio.chance(arma.getChanceHabilidade())){
            defensor.getStatus().desarmar();
            System.out.println(" DESARME! Oponente perde arma! ");
                    }
        }

    case "Quebrar Armadura" -> {
        defensor.getStatus().adicionarGolpeEscudo();
        if(defensor.getStatus().getGolpesNoEscudo() >= 2){
            defensor.quebrarArmadura();
            System.out.println(" ARMADURA DESTRUÍDA ");
        }
        }

    case "Paralisar" ->  {
        defensor.getStatus().paralisar();
        System.out.println(" PARALISADO! Perde o próximo turno");
    }
}
    //SANGRAMENTO DO THRAEX
    if(atacante.getTipoClasse().equals(ConfiguracaoClasse.Barbaro)){
       if(Aleatorio.chance(20)){
        defensor.getStatus().causarSangramento();
        System.out.println(" SANGRAMENTO! 1 DANO POR 2 TURNOS !");
       } 

    }
}

}

