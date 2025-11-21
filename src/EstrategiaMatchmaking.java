package com.gladiators.ia;

import com.gladiators.modelo.ConfiguracaoClasse;

public class EstrategiaMatchmaking {
    
    public static String getCounter(String classeJogador) {
        // Sistema de counters
        switch (classeJogador) {
            case ConfiguracaoClasse.RETIARIUS:
                return ConfiguracaoClasse.SECUTOR; // Secutor > Retiarius
                
            case ConfiguracaoClasse.HOPLOMACHUS:
                return ConfiguracaoClasse.MURMILLO; // Tanque > Glass Cannon
                
            case ConfiguracaoClasse.MURMILLO:
                return ConfiguracaoClasse.RETIARIUS; // Mobilidade > Lentidão
                
            case ConfiguracaoClasse.THRAEX:
                return ConfiguracaoClasse.HOPLOMACHUS; // Burst > Versatilidade
                
            case ConfiguracaoClasse.SECUTOR:
                return ConfiguracaoClasse.THRAEX; // Versátil > Especialista
                
            default:
                return ConfiguracaoClasse.THRAEX; // Padrão
        }
    }
}