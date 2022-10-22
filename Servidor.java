import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
* SOCKET
Utiilizando o modelo cliente/servidor (Socket) criar um MVP de um chatbot.

SERVIDOR

O servidor dever� ter um pequeno dicion�rio que responder� perguntas de acordo com a mensagem enviada pelos clientes. Caso a mensagem n�o seja reconhecida, o servidor apresentar� uma mensagem solicitando que o cliente escreva o questionamento novamente
e guardar� a informa��o que ele n�o conseguiu responder de acordo com os crit�rios definidos mais abaixo.

CLIENTE
O cliente deve ter a possibilidade de encaminhar perguntas quantas forem necess�rias.

Para finalizar a conversa, o cliente deve encaminhar a mensagem SAIR. O servidor ir� reconhecer o comando e encerrar� o conex�o entre as partes.
 
Ao final da execu��o, al�m de responder as quest�es mapeadas, o servidor dever� emitir o relat�rio de quest�es n�o respondidas com as informa��es solicitadas. As informa��es s�o: a quest�o n�o respondida, o cliente que solicitou a informa��o, o ip da m�quina que mandou o questionamento.

Al�m disso, dever� contabilizar quantas perguntas ele conseguiu responder.

-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
*/
public class Servidor {
	public static void main(String[] args) {
		int porta=8080;
		String mensagem="bnldsfkn";
		
		ServerSocket serv_socket;
		Socket cliente;
		InputStreamReader inputReader;
		BufferedReader leitor;
		
		
		try {
			serv_socket=new ServerSocket(porta);
			System.out.println("Est� esperando o cliente enviar algo");
			cliente=serv_socket.accept();
			inputReader=new InputStreamReader(cliente.getInputStream());
			leitor=new BufferedReader(inputReader);
		}catch(IOException e) {
			System.out.println("N�o foi");
			e.printStackTrace();
		}
		
		
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
