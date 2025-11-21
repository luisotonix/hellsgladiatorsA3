package com.gladiators.ia;

public class EstrategiaMatchmaking {
    
    public static String getCounter(String classeJogador) {
        ConfiguracaoClasse configClasse = new ConfiguracaoClasse();
        
        // Sistema de counters
        switch (classeJogador) {
            case configClasse.RETIARIUS:
                return configClasse.SECUTOR; // Secutor > Retiarius
                
            case configClasse.HOPLOMACHUS:
                return configClasse.MURMILLO; // Tanque > Glass Cannon
                
            case configClasse.MURMILLO:
                return configClasse.RETIARIUS; // Mobilidade > Lentidão
                
            case configClasse.THRAEX:
                return configClasse.HOPLOMACHUS; // Burst > Versatilidade
                
            case configClasse.SECUTOR:
                return configClasse.THRAEX; // Versátil > Especialista
                
            default:
                return configClasse.THRAEX; // Padrão
        }
    }
}