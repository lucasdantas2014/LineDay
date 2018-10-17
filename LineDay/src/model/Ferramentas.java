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
				System.out.println("Digite um numero inteiro, exemplo: 1 ou 2 ou 3");
			}
		}
	}
	
	public static Date leitorDate() {
		//Formatacao do tipo dd/mm/yyyy
	    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		Scanner sc = new Scanner(System.in);
		while(true){
		    

        System.out.println("Digite o dia: ");
        int dia = leitorInteiro();
        if(dia == -2){
          return null;
        }

        System.out.println("Digite o mes: ");
        int mes = leitorInteiro();
        if(mes == -2){
          return null;
        }

        System.out.println("Digite o ano: ");
        int ano = leitorInteiro();
        if(ano == -2){
          return null;
        }

        String deadlineStr = dia + "/" + mes + "/" + ano;

        if(ano == -2 || mes == -2 || dia == -2){
          return null;
        }

        if(ano < 0){
          System.out.println("!!!Data Invalida!!!Digite a data novamente");
          continue;
        }

        if(mes < 0 || mes > 12){
          System.out.println("!!!Data Invalida!!!Digite a data novamente");
          continue;
        }

        if(dia < 0){
          System.out.println("!!!Data Invalida!!!Digite a data novamente");
          continue;
        }
        if(dia > 28 && mes == 2 && ano%4 > 0){
          System.out.println("!!!Data Invalida!!!Digite a data novamente");
          continue;
        }
        
        
        if(dia > 29 && mes == 2 && ano%4 == 0){
          System.out.println("!!!Data Invalida!!!Digite a data novamente");
          continue;
        }

        
        if(dia > 30){
          if((mes == 4 || mes == 6 || mes == 9 || mes == 11)){
            System.out.println("!!!Data Invalida!!!Digite a data novamente");
            continue;
          }
        }

        if(dia > 31){
          if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
            System.out.println("!!!Data Invalida!!!Digite a data novamente");
            continue;
          }
        }


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
