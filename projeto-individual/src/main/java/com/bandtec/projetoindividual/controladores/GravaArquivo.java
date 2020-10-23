package com.bandtec.projetoindividual.controladores;

import com.bandtec.projetoindividual.dominios.Produto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GravaArquivo {

    public void gravaRegistro (String nomeArq, String registro) {

        ListaObj<Produto> lista;
        Produto produto =  new Produto();

        BufferedWriter saida = null;
        String caminho = ".\\src\\main\\resources\\static\\";
        try {
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)
            saida = new BufferedWriter(new FileWriter(caminho+nomeArq, true));
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }

        try {
            saida.append(registro + "\n");
            saida.close();

        } catch (IOException e) {
            System.err.printf("Erro ao gravar arquivo: %s.\n", e.getMessage());
        }
    }

    public void chama(Produto p) {

        String header = "";
        String nomeArq = "ArquivoProduto.txt";
        String corpo = "";
        String trailer = "";
        int contRegDados = 0;

        // Monta o registro header
        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        header += "00NOTA20202";
        header += formatter.format(dataDeHoje);
        header += "01";

        // Grava o registro header
        gravaRegistro(nomeArq, header);

        // Monta o corpo

        // 1o registro de dados
        corpo += "02";
        // %-5s : para strings, usamos s
        //        - alinha para a esquerda, campo de 5 caracteres, completa
        //        espaços com brancos à direita
        corpo += String.format("%-5s", "ADS");     // aluno.getCurso()
        corpo += "01181070";
        corpo += String.format("%-50s", "Maria Aparecida da Silva");  // aluno.getNome()
        corpo += String.format("%-40s", "Banco de Dados");
        // %05.2f : para número com casas decimais, usamos f
        //          alinha para a direita, porque não tem o -
        //          completa com zeros à esquerda
        //          formata com 2 casas decimais, separado por vírgula
        corpo += String.format("%05.2f", 7.5);
        // %03d : para número inteiro, usamos d
        //        alinha para a direita e completa com zeros à esquerda
        corpo += String.format("%03d", 10);
        // incrementa o contador de registros de dados
        contRegDados++;
        // chama o método para gravar um registro
        gravaRegistro(nomeArq, corpo);

        // 2o registro de dados - OBS.: Não usar += no primeiro campo do corpo agora!
        corpo = "02";
        corpo += String.format("%-5s", "BD");  // aluno.getCurso()
        corpo += "01181080";
        corpo += String.format("%-50s", "José Oliveira");
        corpo += String.format("%-40s", "Algoritmos");
        corpo += String.format("%05.2f", 5.75);
        corpo += String.format("%03d", 7);
        contRegDados++;
        gravaRegistro(nomeArq, corpo);

        // 3o registro de dados
        corpo = "02";
        corpo += String.format("%-5s", "REDES");
        corpo += "01181090";
        corpo += String.format("%-50s", "Fulano Teixeira");
        corpo += String.format("%-40s", "Segurança");
        corpo += String.format("%05.2f", 8.0);
        corpo += String.format("%03d", 15);
        contRegDados++;
        gravaRegistro(nomeArq, corpo);


        // monta o trailer
        trailer += "01";
        trailer += String.format("%010d", contRegDados);
        gravaRegistro(nomeArq, trailer);

    }

}
