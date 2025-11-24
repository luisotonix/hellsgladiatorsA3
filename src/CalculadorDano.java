public class CalculadorDano {

    private static final int CHANCE_CRITICO = 15;

    public int calcularDano(Gladiador atacante, Gladiador defensor) {
        Arma arma = atacante.getArma();
        int danoBase = arma.getForcaBase();

        // REMOVER DEBUG antes de release
        // System.out.println("DEBUG: atacante=" + atacante.getNome() + " arma=" + arma.getNome() + " forcaBase=" + danoBase + " ignoraArmadura=" + arma.getIgnoraArmadura());
        // System.out.println("DEBUG: defensor=" + defensor.getNome() + " temArmadura=" + defensor.temArmadura() + " reducao=" + defensor.getReducaoDano());

        // 1. Verificar crítico

        boolean critico = false;

        if (atacante.getCritico() > 0 && Aleatorio.chance(CHANCE_CRITICO)) {
            danoBase *= 2;
            critico = true;
            System.out.println(" CRÍTICO! ");

        }

        // 2 BONÛS DO ARQUEIRO DO PRIMEIRO ATAQUE

        if (atacante.getTipoClasse().equals("arqueiro") && atacante.getStatus().isPrimeiroAtaque()) {
            danoBase *= 2;// Primeiro golpe sempre crítico
            atacante.getStatus().usouPrimeiroAtaque();
            System.out.println(" PRIMEIRO DISPARO FOI PERFEITO! ");

        }

        // 3.BONUS DO LANCEIRO CONTRA ASSASSINO
        if (atacante.getTipoClasse().equals("lanceiro")
                && defensor.getTipoClasse().equals("assassino")) {
            danoBase += 2;
            System.out.println(" COUNTER! +2 DE DANO ");
        }

        // 4. PENALIDADE DO LANCEIRO CONTRA OUTRAS CLASSES

        if (atacante.getTipoClasse().equals("lanceiro")
                && !defensor.getTipoClasse().equals("assassino")) {
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

        // após calcular danoFinal, adicione:
        // System.out.println("DEBUG: danoBase=" + danoBase + " danoFinal=" + danoFinal); // remover
        return danoFinal;

    }

    public int calcularDano(Gladiador atacante, Gladiador defensor, int defesa) {
        Arma arma = atacante.getArma();
        int danoBase = arma.getForcaBase();

        // REMOVER DEBUG antes de release
        // System.out.println("DEBUG: atacante=" + atacante.getNome() + " arma=" + arma.getNome() + " forcaBase=" + danoBase + " ignoraArmadura=" + arma.getIgnoraArmadura());
        // System.out.println("DEBUG: defensor=" + defensor.getNome() + " temArmadura=" + defensor.temArmadura() + " reducao=" + defensor.getReducaoDano());

        // 1. Verificar crítico

        boolean critico = false;

        if (atacante.getCritico() > 0 && Aleatorio.chance(CHANCE_CRITICO)) {
            danoBase *= 2;
            critico = true;
            System.out.println(" CRÍTICO! ");

        }

        // 2 BONÛS DO ARQUEIRO DO PRIMEIRO ATAQUE

        if (atacante.getTipoClasse().equals("arqueiro") && atacante.getStatus().isPrimeiroAtaque()) {
            danoBase *= 2;// Primeiro golpe sempre crítico
            atacante.getStatus().usouPrimeiroAtaque();
            System.out.println(" PRIMEIRO DISPARO FOI PERFEITO! ");

        }

        // 3.BONUS DO LANCEIRO CONTRA ASSASSINO
        if (atacante.getTipoClasse().equals("lanceiro")
                && defensor.getTipoClasse().equals("assassino")) {
            danoBase += 2;
            System.out.println(" COUNTER! +2 DE DANO ");
        }

        // 4. PENALIDADE DO LANCEIRO CONTRA OUTRAS CLASSES

        if (atacante.getTipoClasse().equals("lanceiro")
                && !defensor.getTipoClasse().equals("assassino")) {
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

        // após calcular danoFinal, adicione:
        // System.out.println("DEBUG: danoBase=" + danoBase + " danoFinal=" + danoFinal); // remover
        return danoFinal/defesa;

    }

public void  aplicarHabilidadeArma(Gladiador atacante, Gladiador defensor, Arma arma){
String habilidade = arma.getHabilidadeEspecial();

   if (habilidade == null) {
        return; // Sai do método se não tiver habilidade
    }

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
    if(atacante.getTipoClasse().equals("barbaro")){
       if(Aleatorio.chance(20)){
        defensor.getStatus().causarSangramento();
        System.out.println(" SANGRAMENTO! 1 DANO POR 2 TURNOS !");
       } 

    }
}

}

