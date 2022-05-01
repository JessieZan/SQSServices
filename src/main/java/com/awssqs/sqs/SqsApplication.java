package com.awssqs.sqs;

import com.awssqs.sqs.services.SQSServiceConsumer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.awssqs.sqs.services.SQSServiceProvider;

import java.time.LocalDate;
import java.util.Scanner;

@SpringBootApplication
public class SqsApplication {


	public static void main(String[] args) throws InterruptedException {

		SpringApplication.run(SqsApplication.class, args);

		Scanner teclado = new Scanner(System.in);
		int opcao;
		String msg;

		do {
			System.out.println("Bem vindo a Mensageria");
			System.out.println("1-Enviar Mensagem / 2-Receber Mensagem / 0-Sair");
			opcao = teclado.nextInt();
			switch(opcao) {
				case 1:
					System.out.println("Digite a mensagem a ser enviada");
					msg = teclado.nextLine();
					SQSServiceProvider.sendMessage(msg + LocalDate.now());
					break;
				case 2:
					System.out.println("Lendo mensagens ...");
					while(true){
						SQSServiceConsumer.messageReader();
						// Thread.sleep(1000); // Desabilitado por causa do Long Polling para econmizar $$
					}
				case 0:
					System.out.println("Obrigado pela preferencia!!");
					break;
				default:

					System.out.println(">>>>> Opcao Invalida <<<<<<");
			}

		} while (opcao != 0);
	}



}
