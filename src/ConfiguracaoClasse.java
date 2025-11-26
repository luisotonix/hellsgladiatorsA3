public class ConfiguracaoClasse {

    // Constantes das classes — agora SEM ACENTO e tudo MINÚSCULO
    // para garantir que a comparação via equalsIgnoreCase funcione sempre.
    // Mantém compatibilidade total com uso por String.
    public static final String TANQUE = "tanque";
    public static final String ASSASSINO = "assassino";
    public static final String BARBARO = "barbaro";
    public static final String LANCEIRO = "lanceiro";
    public static final String ARQUEIRO = "arqueiro";

    // Método de normalização para garantir que TODAS as comparações sejam consistentes
    // Remove acentos e converte para minúsculo.
    private String normalizar(String texto) {
        if (texto == null) return "";
        return java.text.Normalizer.normalize(texto, java.text.Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .toLowerCase();
    }

    /**
     * Retorna configuração da classe
     * Formato: [HP, Dano, Armadura, ChanceCritico, Velocidade]
     */
    public int[] getConfiguracao(String tipoClasse) {

        // Normaliza a entrada para evitar problemas com acentos e maiúsculas
        String classe = normalizar(tipoClasse);

        switch (classe) {

            case TANQUE:
                return new int[]{10, 5, 3, 0, 0};

            case ASSASSINO:
                return new int[]{7, 3, 0, 5, 2};

            case BARBARO:
                return new int[]{9, 4, 2, 2, 1};

            case LANCEIRO:
                return new int[]{9, 4, 1, 2, 1};

            case ARQUEIRO:
                return new int[]{8, 2, 0, 3, 2};

            // Caso passe algo errado, retorna padrão
            default:
                return new int[]{9, 3, 2, 2, 1};
        }
    }

    /**
     * Retorna arma inicial da classe
     */
    public String getArmaInicial(String tipoClasse) {

        // Mesmo sistema de normalização
        String classe = normalizar(tipoClasse);

        switch (classe) {
            case TANQUE: return Arma.machado;
            case ASSASSINO: return Arma.rede_adaga;
            case BARBARO: return Arma.espada;
            case LANCEIRO: return Arma.lanca;
            case ARQUEIRO: return Arma.arco;
            default: return Arma.espada; // padrão
        }
    }

    /**
     * Lista todas as classes
     */
    public String[] listarClasses() {
        // Mantém exatamente como era esperado pelo sistema
        return new String[]{TANQUE, ASSASSINO, BARBARO, LANCEIRO, ARQUEIRO};
    }
}
