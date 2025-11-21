package com.gladiators.ia;

import com.gladiators.modelo.*;

public class IAOponente {
    
    public String escolherClasse(String classeJogador) {
        System.out.println("🤖 IA está pensando...");
        
        // Lógica de counter
        String classeEscolhida = EstrategiaMatchmaking.getCounter(classeJogador);
        
        System.out.println("🤖 IA escolheu: " + classeEscolhida);
        
        return classeEscolhida;
    }
    
    public Gladiador criarOponente(String classeJogador) {
        String classe = escolherClasse(classeJogador);
        Gladiador oponente = new Gladiador("Gladiador IA", classe);
        
        // Configura arma
        ConfiguracaoClasse config = new ConfiguracaoClasse();
        String armaInicial = config.getArmaInicial(classe);
        oponente.setArma(new Arma(armaInicial));
        
        return oponente;
    }
}