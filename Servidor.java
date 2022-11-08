import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.Set;

/**
                Autores
       Pedro Henrique Carneiro de Araújo 22108287
       Eduardo Akio Shimura Amemiya 22007658
       Vinícius de Oliveira Perpétuo 21908298
       Ednei Bezerra da Costa 21160584
*/

/*
* SOCKET
Utiilizando o modelo cliente/servidor (Socket) criar um MVP de um chatbot.

SERVIDOR.

O servidor deverá ter um pequeno dicionário que responderá perguntas de acordo com a mensagem enviada pelos clientes. Caso a mensagem não seja reconhecida, o servidor apresentará uma mensagem solicitando que o cliente escreva o questionamento novamente
e guardará a informação que ele não conseguiu responder de acordo com os critérios definidos mais abaixo.

CLIENTE
O cliente deve ter a possibilidade de encaminhar perguntas quantas forem necessárias.

Para finalizar a conversa, o cliente deve encaminhar a mensagem SAIR. O servidor irá reconhecer o comando e encerrará o conexão entre as partes.
 
Ao final da execução, além de responder as questões mapeadas, o servidor deverá emitir o relatório de questões não respondidas com as informações solicitadas. As informações são: a questão não respondida, o cliente que solicitou a informação, o ip da máquina que mandou o questionamento.

Além disso, deverá contabilizar quantas perguntas ele conseguiu responder.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
public class Servidor {
	public static void main(String[] args) {
		int porta=8080;
		boolean finalizaServidor=true;
		String mensagem="bnldsfkn";
		
		ServerSocket serv_socket=null;
		Socket cliente;
		InputStreamReader inputReader;
		BufferedReader leitor=null;
		PrintStream saida=null;
		
		Respostas respondedor=new Respostas();
		ContadorErros erros=new ContadorErros();
		
		
		try {
			serv_socket=new ServerSocket(porta);
			System.out.println("Está esperando o cliente enviar algo");
			cliente=serv_socket.accept();
			inputReader=new InputStreamReader(cliente.getInputStream());
			leitor=new BufferedReader(inputReader);
			mensagem=leitor.readLine();
			saida=new PrintStream(cliente.getOutputStream());
		}catch(IOException e) {
			System.out.println("Não foi");
			e.printStackTrace();
		}
		
		
		System.out.println("Servidor ouvindo na porta: "+porta);
		System.out.println("Digite qualquer palavra, quando quiser sair digite SAIR.");
		
		try {
			while(finalizaServidor) {
				//cliente=serv_socket.accept();
				System.out.print("A palavra recebida foi: "+mensagem+" ");
				String algo=buscaRespostas(respondedor, mensagem,erros);
				saida.println("O servidor diz: "+algo);
				finalizaServidor=(!verificaFim(mensagem) && mensagem!=null); 
				System.out.println(finalizaServidor);
				mensagem=leitor.readLine();
				if(!finalizaServidor)
					erros.geraRelatorio();
				
			}
		}catch(IOException e2) {
			System.out.println("Deu ruim na leitura de mensagem.");
			mensagem="SAIR";
			erros.geraRelatorio();
		}
	}
	public static boolean verificaFim(String mensagem) {
		boolean saida=false;
		saida="SAIR".equals(mensagem);
		return saida;
	}
	public static String buscaRespostas(Respostas respondedor,String questao,
			ContadorErros listaErros) {
		String resposta="nem busquei sua pergunta",
				question=questao.toLowerCase();// converte para minusculo
		Set<String> chaves=respondedor.chaves();
		
		//System.out.println(chaves);
		
		//System.out.println("quest�o: "+question);
		for (String string : chaves) {
			//System.out.println("Chave da vez: "+string);
			
			if(question.contains(string)) {
				resposta=respondedor.responde(string);
				return resposta;
			}
		}
		resposta="N�o entendi o que voc� disse.";
		listaErros.deuErro(question);
		//System.out.println("Resposta enviada pelo metodo buscaResposta: "+resposta);
		return resposta;
	}
}
