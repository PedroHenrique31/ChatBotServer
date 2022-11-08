import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
                Autores
       Pedro Henrique Carneiro de Araújo 22108287
       Eduardo Akio Shimura Amemiya 22007658
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
		String mensagem="bnldsfkn";
		
		ServerSocket serv_socket=null;
		Socket cliente;
		InputStreamReader inputReader;
		BufferedReader leitor=null;
		PrintStream saida=null;
		
		
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
			while(!(verificaFim(mensagem)) || (mensagem!=null)) {
				//cliente=serv_socket.accept();
				System.out.println("A palavra recebida foi: "+mensagem);
				saida.println("O servidor recebeu a mensagem: \n"+mensagem);
				mensagem=leitor.readLine();
				
			}
		}catch(IOException e2) {
			System.out.println("Deu ruim na leitura de mensagem.");
			mensagem="SAIR";}
	}
	public static boolean verificaFim(String mensagem) {
		boolean saida=false;
		saida=mensagem.equals("SAIR");
		return saida;
	}
}
