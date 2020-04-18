/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*finalizado*/
package controllers;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;
import static javafx.application.Platform.exit;
import models.Cadastrar;
import models.Logar;

/**
 *
 * @author Luiz_
 */
public class Jogar {
    
    public void menuJogo() throws SQLException, NoSuchAlgorithmException{
        
        Jogador jogador = new Jogador();//instancia um objeto jogador
        Scanner nome = new Scanner(System.in);//instancia um objeto escolha
        Scanner escolha = new Scanner(System.in);//instancia um objeto escolha
        Scanner senha = new Scanner(System.in);//instacia um objeto senha
        Scanner conf_senha = new Scanner(System.in);//instancia um objeto conf_senha
        Scanner termo = new Scanner(System.in);
        Logar login = new Logar();
        Cadastrar cadastro = new Cadastrar();
        
        int x = 0;
        while(x != 3){
            System.out.println("\n");
            System.out.println("JOGO DA VELHA");
            System.out.println("Cadastrar ( 1 )");
            System.out.println("Jogar ( 2 )");
            System.out.println("Sair ( 3 )");
            x = escolha.nextInt();
            
            switch(x) {
                case 1:
                    int y = 0;
                    System.out.println("Por questões de segurança sua senha será enviada"
                            + "ao banco de dados de forma\ncriptograda, esse termo foi criado"
                            + "para sua própria segurança contra invasão\nao banco de dados e"
                            + "vazamento de informações, você está de acordo?\nse sim digite 1 "
                            + "se não digite 2");
                    y = termo.nextInt();
                    switch(y){
                        case 1: 
                            System.out.println("Digite seu nome: ");
                            jogador.setNome(nome.nextLine());//joga o nome pra dentro do metodo SETNOME
                            System.out.println("Digite sua senha: ");
                            jogador.setSenha(senha.nextLine());//joga a senha pra dentro do metodo SETSENHA1
                            System.out.println("Confirme sua senha: ");
                            jogador.setConf_Senha(conf_senha.nextLine());//joga a senha pra dentro do metoddo SETSENHA2
                            cadastro.recebeDados(jogador);
                            break;
                        
                        case 2:
                            menuJogo();
                            break;
                            
                        default: 
                            System.out.println("Comando Inválido!!");
                    }
                     
                    break;
                    
                case 2:
                     System.out.println("---------- Login -----------\n");
                     System.out.println("Nome: ");
                     jogador.setNome(nome.nextLine());
                     System.out.println("Senha: ");
                     jogador.setSenha(senha.nextLine());
                     login.Autenticar(jogador);       
                    break;
                    
                case 3:
                        exit();
                    break;
                    
                default: 
                    System.out.println("Comando Inválido!!");
            }
        }
    }
    
    public void entrarJogo(controllers.Jogador jogador) throws SQLException, NoSuchAlgorithmException{
       
        Scanner ler = new  Scanner(System.in);
        Jogo jogar = new Jogo();
        String posicao;
        int valida = 0, jogada = 0;
        
        do{
            System.out.println("quer jogar contra player ou contra pc?");
            System.out.println("Player (1)");
            System.out.println("PC (2)");
            valida = ler.nextInt();

        }while(valida != 1 && valida != 2);

    if(valida == 1){     
        
        System.out.println("Jogo começou");
        jogar.Mostrar();
      
            while(true){
            do{
                System.out.println(jogador.getNome() + " insira posição: ");
                posicao = ler.next();
                while(!jogar.Valida(posicao)){
                    System.out.println("Jogada inválida");
                    System.out.println(jogador.getNome() + " insira posição: ");
                    posicao = ler.next();
                    valida = 0;
                }
                jogar.Jogada(posicao, "X");
                valida = 1;
                
            }while(valida == 0); //fim jog 1
            
                jogada++;
                valida = 0;
                jogar.Mostrar();
            
            if(!jogar.Ganhou(jogada, jogador).equals("null")){
                break;
            }
               
            do{
                System.out.println("Jogador 2 insira posição: ");
                posicao = ler.next();
                while(!jogar.Valida(posicao)){
                    System.out.println("Jogada inválida");
                    System.out.println("Jogador 2 insira posição: ");
                    posicao = ler.next();
                    valida = 0;
                }
                jogar.Jogada(posicao, "O");
                valida = 1;
                
            }while(valida == 0); //fim jog 1
            
            jogada++;
            valida = 0;
            jogar.Mostrar();
                
            if(!jogar.Ganhou(jogada, jogador).equals("null")){
                break;
            }
               
        }  
            entrarJogo(jogador);
        
        
            }else {
                System.out.println("Jogo começou");
                jogar.Mostrar();
          
            while(true){
                       
            do{
                System.out.println(jogador.getNome() + " insira posição: ");
                posicao = ler.next();
                while(!jogar.Valida(posicao)){
                    System.out.println("Jogada inválida");
                    System.out.println(jogador.getNome() + " insira posição: ");
                    posicao = ler.next();
                    valida = 0;
                }
                jogar.Jogada(posicao, "X");
                valida = 1;
                
            }while(valida == 0); //fim jog 1
            
                jogada++;
                valida = 0;
                jogar.Mostrar();
            
            if(!jogar.Ganhou(jogada, jogador).equals("null")){
                break;
            }
               
            do{
                posicao =  Integer.toString((int)(Math.random() * 10));
                while(!jogar.Valida(posicao)){
                    posicao =  Integer.toString((int)(Math.random() * 10));
                    valida = 0;
                }
                jogar.Jogada(posicao, "O");
                valida = 1;
                
            }while(valida == 0); //fim jog 1
                System.out.println("A máquina jogou: \n");
                jogada++;
                valida = 0;
                jogar.Mostrar();
                
            if(!jogar.Ganhou(jogada, jogador).equals("null")){
                break;
            }  
        }   
            entrarJogo(jogador);
            }
    }
}
