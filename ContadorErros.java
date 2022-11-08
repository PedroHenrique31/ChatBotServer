import java.util.ArrayList;

public class ContadorErros {
	ArrayList<String> mensagensDeramErro;
	int qtdErros;
	
	public ContadorErros() {
		mensagensDeramErro=new ArrayList<String>(100);
		this.qtdErros=0;
	}
	
	public void deuErro(String frase) {
		this.qtdErros++;
		mensagensDeramErro.add(frase);
	}
	public void geraRelatorio() {
		System.out.println("=================================================================================");
		System.out.println("Este é um relatório das mensagens que não puderam ser respondidas pelo servidor.");
		System.out.println("No total foram "+this.qtdErros+" mensagens que não puderam ser devidamente respondidas");
		System.out.println("A seguir as mensagens listadas");
		
		for(String frase: mensagensDeramErro) {
			System.out.println(frase);
		}// fim for
		System.out.println("=================================================================================");
	}
}
