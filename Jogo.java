package jogo;

//Importações essenciais!

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

//Fim das importações

/**
 *
 * @author Marcus Monteiro
 */
public class Jogo implements ActionListener, Runnable { 

    private static JFrame janela; 
    private int x;
    private boolean ir, executar;
    private JButton obj;

    public static void main(String[] args) {
        janela = new JFrame("Tira ao alvo"); //Criando o frame
        janela.setBackground(Color.ORANGE); //Definindo a cor de fundo do frame
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Definindo a opção de fechamento do frame
        janela.setState(JFrame.MAXIMIZED_BOTH); //Definindo o frame para iniciar maximizado
        janela.setSize(800, 600); //Definindo o tamanho
        janela.setLayout(null); //Definindo layout

//---------------------------- Fim das definições do Frame! --------------------        

        Jogo ouvido = new Jogo();//Criando listener
        
//---------------------------- Fim das definições do Listener! -----------------        

        JButton alvo = new JButton("Alvo");//Criando botão Alvo
        alvo.setBounds(0, 0, 50, 30);//Definindo a posição inicial e o tamanho do botão
        alvo.setBackground(Color.blue);//Definindo a cor de fundo do botão
        alvo.setForeground(Color.GREEN);//Definindo a cor da letra
        alvo.addActionListener(ouvido);//Adicionando o listener

//---------------------------- Fim das definições do botão Alvo! ---------------

        JButton pedra = new JButton("Pedra");//Criando botão Pedra
        pedra.setBounds(400, 500, 50, 30);//Definindo a posição inicial e o tamanho do botão
        pedra.setBackground(Color.red);//Definindo a cor de fundo do botão
        pedra.setForeground(Color.yellow);//Definindo a cor da letra
        pedra.addActionListener(ouvido);//Adicionando o listener

//---------------------------- Fim das definições do botão Pedra! --------------        
        
        janela.add(alvo);   //Adicicionando o botão Alvo no frame
        janela.add(pedra);  //Adicicionando o botão Pedra no frame
        janela.setVisible(true); //Deixando o frame visivel
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        obj = (JButton) e.getSource();

        if (obj.getText().equals("Alvo")) { //IF para identificar o botão clicado
            executar = true; //Variavel de controle do while da Thread
            
            Thread t = new Thread(this); // Criando a Thread
            t.start(); //Iniciando a Thread
        }else{ //Else para dar instruções ao botão Pedra
            executar = false;//Variavel de controle do while da Thread
            
            if(obj.getX() == x){ //IF para verificar se a posição do botão alvo é a mesma da pedra
                janela.setTitle("Parabéns você Acertou!"); //Comando Basico de saida
            }else{ //Else para instruir o que deve ser feito se caso a posição do botão alvo for diferente da do botão Pedra
                janela.setTitle("Você errou o alvo!");//Comando Basico de saida
            }
        }
    }

    @Override
    public void run() { //Inicio da Thread
        while (executar) {//While de controle da execução da thread

//Bloco para verificar se o botão Alvo está na ultima posição possivel, e se estiver mudar a direção do mesmo
            
            if (x == 0 && !ir) {
                ir = true;
            } else if (x == 800 && ir) {
                ir = false;
            }
            
//---------------Fim do Bloco--------------
            
//Bloco para indicar quantas possições o botão alvo irá "andar"  
            
            if (ir) {
                x = x + 50;
            } else {
                x = x - 50;
            }
            
//---------------Fim do Bloco--------------
            
            obj.setLocation(x, 0); //Definir a posição do Botão Alvo
        }
    }
}
