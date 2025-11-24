public class AnimacaoCombate {
    
    public static void mostrarAtaque(String atacante, String defensor, int dano) {
        System.out.println("\\n⚔️  " + atacante + " ataca " + defensor + "!");
        pausar(500);
        System.out.println("💥 " + dano + " de DANO!");
        pausar(800);
    }
    
    public static void mostrarCritico() {
        System.out.println("\\n");
        System.out.println("  ⭐ ⭐ ⭐ ⭐ ⭐");
        System.out.println("  💥 CRÍTICO! 💥");
        System.out.println("  ⭐ ⭐ ⭐ ⭐ ⭐");
        pausar(1000);
    }
    
    public static void mostrarHabilidade(String habilidade) {
        System.out.println("\\n🌟 Habilidade ativada: " + habilidade + " 🌟");
        pausar(800);
    }
    
    public static void mostrarMorte(String nome) {
        System.out.println("\\n");
        System.out.println("  ╔═══════════════════════════╗");
        System.out.println("  ║   💀 " + nome + " MORREU! 💀   ║");
        System.out.println("  ╚═══════════════════════════╝");
        pausar(1500);
    }
    
    public static void mostrarVitoria() {
        System.out.println("\\n");
        System.out.println("  ╔═══════════════════════════╗");
        System.out.println("  ║   🏆 VOCÊ VENCEU! 🏆     ║");
        System.out.println("  ╚═══════════════════════════╝");
        System.out.println();
        System.out.println("  A plateia está EUFÓRICA! 🎉");
    }
    
    public static void mostrarDerrota() {
        System.out.println("\\n");
        System.out.println("  ╔═══════════════════════════╗");
        System.out.println("  ║   💀 VOCÊ PERDEU! 💀     ║");
        System.out.println("  ╚═══════════════════════════╝");
        System.out.println();
        System.out.println("  A plateia está decepcionada... 😔");
    }
    
    private static void pausar(int milissegundos) {
        try {
            Thread.sleep(milissegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}