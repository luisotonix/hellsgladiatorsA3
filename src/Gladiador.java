public class Gladiador {
    protected String nome;
    protected String tipoClasse;
    protected int hp;
    protected int hpMaximo;
    protected Arma arma;
    protected int armadura;
    protected int reducaoDano;
    protected int chanceCritico;
    protected int velocidadeAtaque;
    protected StatusBatalha status;

    public Gladiador() {
        
    }

    public void setGladiador(String nome, String tipoClasse, String nomeArma) {
        this.nome = nome;
        this.tipoClasse = tipoClasse;
        this.arma = new Arma(nomeArma);
        this.status = new StatusBatalha();
        
        ConfiguracaoClasse config = new ConfiguracaoClasse();
        aplicarConfiguracao(config.getConfiguracao(tipoClasse));
    }
    
    protected void aplicarConfiguracao(int[] config) {
        this.hpMaximo = config[0];
        this.hp = config[0];
        this.reducaoDano = config[1];
        this.armadura = config[2];
        this.chanceCritico = config[3];
        velocidadeAtaque = config[4];
    }
    
public void receberDano(int dano) {
    // Armadura já foi aplicada em CalculadorDano.calcularDano()
    hp -= dano;  // 👈 Apenas subtrai o dano já calculado
    if (hp < 0) hp = 0;
}
    
    public boolean estaVivo() {
        return hp > 0;
    }
    
    public void restaurarParaNovaBatalha() {
        this.hp = this.hpMaximo;
        this.status = new StatusBatalha();
    }

    // Getters e Setters
    public String getNome() { 
        return nome; 
    }
    public String getTipoClasse() { 
        return tipoClasse;   
    }
    public int getHp() { 
        return hp;
    }
    public int getHpMaximo() {
         return hpMaximo; 
    }
    public Arma getArma() {
         return arma; 
    }
    public void setArma(Arma arma) {
         this.arma = arma; 
    }
    public int getReducaoDano() {
         return reducaoDano; 
    }
    public int getArmadura() { 
        return armadura; 
    }
    public int getCritico() {
         return chanceCritico; 
    }
    public StatusBatalha getStatus() { 
        return status; 
    }

    public boolean temArmadura() {
        return (armadura > 0);
    }

    public int getVelocidadeAtaque() {
        return velocidadeAtaque;
    }

   public int quebrarArmadura () {
    return (armadura = 0);
   }
}
