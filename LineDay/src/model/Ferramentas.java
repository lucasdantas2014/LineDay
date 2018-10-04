package model;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Ferramentas {
	
	public static int leitorInteiro() {
		Scanner sc = new Scanner(System.in);
		while(true){
			try{
				int n = Integer.parseInt(sc.nextLine());
				return n;
			}catch (Exception e){
				System.out.println("Digite um número inteiro, exemplo: 1 ou 2 ou 3");
			}
		}
	}
	
	public static Date leitorDate() {
		//Formatação do tipo dd/mm/yyyy
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Scanner sc = new Scanner(System.in);
		while(true){
		    String deadlineStr = sc.nextLine();
		    try {
		      Date deadline = formato.parse(deadlineStr);
		      return deadline;
		    } catch (ParseException e) {
		      System.out.println(e);
		      System.out.println("Erro: Digite a data no formato correto, exemplo -> 03/02/2019");
		    }
		}
	}
}
