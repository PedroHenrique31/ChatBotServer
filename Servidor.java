
public class Servidor {
	public static void main(String[] args) {
		int porta=4096;
		String mensagem="Não";
		System.out.println("Servidor ouvindo na porta: "+porta);
		System.out.println("Digite qualquer palavra, quando quiser sair digite SAIR.");
		while(!(verificaFim(mensagem))) {
			System.out.println("Sua palavra foi: "+mensagem);
			mensagem="SAIR";
		}
	}
	public static boolean verificaFim(String mensagem) {
		boolean saida=false;
		saida=mensagem.equals("SAIR");
		return saida;
	}
}
