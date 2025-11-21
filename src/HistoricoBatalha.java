package com.gladiators.ranking;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoricoBatalha {
    private String dataHora;
    private String gladiador1;
    private String gladiador2;
    private String vencedor;
    private int turnosDuracao;
    private int danoTotalCausado;
    
    public HistoricoBatalha(String glad1, String glad2, String vencedor, 
                           int turnos, int dano) {
        this.gladiador1 = glad1;
        this.gladiador2 = glad2;
        this.vencedor = vencedor;
        this.turnosDuracao = turnos;
        this.danoTotalCausado = dano;
        
        // Pega data/hora atual
        LocalDateTime agora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        this.dataHora = agora.format(formato);
    }
    
    // Getters
    public String getDataHora() { return dataHora; }
    public String getGladiador1() { return gladiador1; }
    public String getGladiador2() { return gladiador2; }
    public String getVencedor() { return vencedor; }
    public int getTurnosDuracao() { return turnosDuracao; }
    public int getDanoTotalCausado() { return danoTotalCausado; }
    
    @Override
    public String toString() {
        return dataHora + " | " + gladiador1 + " vs " + gladiador2 + 
               " | Vencedor: " + vencedor + " | " + turnosDuracao + " turnos";
    }
}