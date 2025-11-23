public class Arma{

    // NOME DAS ARMAS
    public static final String espada = "Espada";
    public static final String lanca = "Lança";
    public static final String machado = "Machado";
    public static final String rede_adaga = "Rede e Adaga";
    public static final String arco = "Arco e Flecha";

    // ATRIBUTOS

    private String nome;
    private int  forcaBase;
    private String  habilidadeEspecial;
    private int chanceHabilidade; // PERCENTUAL DE 0 A 100 DE FALHAR NO TURNO
    private boolean ignoraArmadura;

    // CONSTRUTORES

    public Arma(String nome){
        this.nome = nome;
        configurarArma(nome);

    }

    private void configurarArma(String nome){
        String key = nome == null ? "" : nome.toLowerCase().trim();
        switch(key){

            case "espada" -> {
                this.forcaBase = 4;
                this.habilidadeEspecial = "Duplo Ataque";
                this.chanceHabilidade = 20;
                this.ignoraArmadura = false;
            }
            case "lanca", "lança" -> {
                this.forcaBase = 4;
                this.habilidadeEspecial = "Desarmar";
                this.chanceHabilidade = 20;
                this.ignoraArmadura = false;
            }
            case "machado" -> {
                this.forcaBase = 5;
                this.habilidadeEspecial = "Quebrar Armadura";
                this.chanceHabilidade = 100;
                this.ignoraArmadura = false;
            }
            case "rede_adaga", "rede e adaga" -> {
                this.forcaBase = 3;
                this.habilidadeEspecial = "Paralisar";
                this.chanceHabilidade = 50;
                this.ignoraArmadura = false;
            }
            case "arco", "arco e flecha" -> {
                this.forcaBase = 2;
                this.habilidadeEspecial = null;
                this.chanceHabilidade = 100;
                this.ignoraArmadura = true;
            }
            default -> {
                this.forcaBase = 1;
                this.habilidadeEspecial = null;
                this.chanceHabilidade = 0;
                this.ignoraArmadura = false;
            }
        }
    }
    // GETTERS

    public String getNome(){
    return  nome;
    }
    
    public int getForcaBase(){
        return forcaBase;
    }

    public String getHabilidadeEspecial(){
        return habilidadeEspecial;
    }

    public int getChanceHabilidade(){
        return chanceHabilidade;
    }

    public boolean getIgnoraArmadura(){
        return ignoraArmadura;
    }
}


