import java.util.ArrayList;

public class GerenciadorRanking {
    private ArrayList<Estatisticas> rankings;
    private GerenciadorArquivos gerenciadorArquivos;
    
    public GerenciadorRanking() {
        this.rankings = new ArrayList<>();
        this.gerenciadorArquivos = new GerenciadorArquivos();
        carregarRankings();
    }
    
    public void salvarBatalha(HistoricoBatalha batalha) {
        gerenciadorArquivos.salvarHistorico(batalha);
        atualizarEstatisticas(batalha);
    }
    
    private void atualizarEstatisticas(HistoricoBatalha batalha) {
        // Atualiza stats do vencedor
        Estatisticas stats = buscarOuCriar(batalha.getVencedor());
        stats.adicionarVitoria();
        stats.adicionarKill();
        
        // Salva rankings atualizados
        gerenciadorArquivos.salvarRanking(rankings);
    }
    
    private Estatisticas buscarOuCriar(String nome) {
        for (Estatisticas stat : rankings) {
            if (stat.getNome().equals(nome)) {
                return stat;
            }
        }
        
        // Se não encontrou, cria novo
        Estatisticas novo = new Estatisticas(nome);
        rankings.add(novo);
        return novo;
    }
    
    public void carregarRankings() {
        rankings = gerenciadorArquivos.carregarRanking();
    }
    
    public void exibirTop10() {
        System.out.println("╔════════════════════════════════╗");
        System.out.println("║         🏆 RANKING 🏆         ║");
        System.out.println("╚════════════════════════════════╝");
        
        // Ordena por vitórias (simples bubble sort)
        ordenarPorVitorias();
        
        int limite = Math.min(10, rankings.size());
        for (int i = 0; i < limite; i++) {
            Estatisticas stat = rankings.get(i);
            System.out.println((i+1) + ". " + stat.getNome() + 
                             " - " + stat.getVitorias() + " vitórias");
        }
    }
    
    private void ordenarPorVitorias() {
        // Bubble Sort simples
        for (int i = 0; i < rankings.size() - 1; i++) {
            for (int j = 0; j < rankings.size() - i - 1; j++) {
                if (rankings.get(j).getVitorias() < rankings.get(j+1).getVitorias()) {
                    // Troca
                    Estatisticas temp = rankings.get(j);
                    rankings.set(j, rankings.get(j+1));
                    rankings.set(j+1, temp);
                }
            }
        }
    }
}