public class StatusBatalha {
    private boolean paralizado;
    private boolean desarmado;
    private boolean sangrando;
    private int turnosParalisia;
    private int turnosSangramento;
    private int golpesNoEscudo; // Para contar golpes do machado
    private boolean primeiroAtaque; // Para Hoplomachus
    
    public StatusBatalha() {
        this.paralizado = false;
        this.desarmado = false;
        this.sangrando = false;
        this.turnosParalisia = 0;
        this.turnosSangramento = 0;
        this.golpesNoEscudo = 0;
        this.primeiroAtaque = true;
    }
    
    // Atualiza status no início do turno
    public void atualizarStatus() {
        // Reduz contadores
        if (turnosParalisia > 0) {
            turnosParalisia = 0;
            if (turnosParalisia == 0) {
                paralizado = false;
            }
        }
        
        if (turnosSangramento > 0) {
            turnosSangramento--;
            if (turnosSangramento == 0) {
                sangrando = false;
            }
        }
    }
    
    // Métodos para aplicar efeitos
    public void paralisar() {
        this.paralizado = true;
        this.turnosParalisia = 1;
    }
    
    public void desarmar() {
        this.desarmado = true;
    }
    
    public void causarSangramento() {
        this.sangrando = true;
        this.turnosSangramento = 2;
    }
    
    public void adicionarGolpeEscudo() {
        golpesNoEscudo++;
    }
    
    public void usouPrimeiroAtaque() {
        primeiroAtaque = false;
    }
    
    // Getters
    public boolean isParalizado() { return paralizado; }
    public boolean isDesarmado() { return desarmado; }
    public boolean isSangrando() { return sangrando; }
    public int getGolpesNoEscudo() { return golpesNoEscudo; }
    public boolean isPrimeiroAtaque() { return primeiroAtaque; }
}