package bandtec.com.Projetoindividualsprint3.controladores;

import bandtec.com.Projetoindividualsprint3.dominios.Produto;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.FormatterClosedException;

public class ListaObj <T> {


    private T[] vetor;
    private int nroElem;


    public ListaObj(int tam) {
        vetor = (T[]) new Object[tam];
        nroElem = 0;
    }


    public boolean adiciona(T valor) {
        if (nroElem >= vetor.length) {
            System.out.println("Lista está cheia");
            return false;
        } else {
            vetor[nroElem++] = valor;

            return true;
        }
    }


    public void exibe() {
        System.out.println("\nExibindo elementos da lista:");
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
        System.out.println();
    }


    public int busca(T valor) {
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i].equals(valor)) {
                return i;
            }
        }
        return -1;
    }


    public boolean removePeloIndice(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return false;
        } else {

            for (int i = indice; i < nroElem - 1; i++) {
                vetor[i] = vetor[i + 1];
            }

            nroElem--;
            return true;
        }
    }


    public boolean removeElemento(T valor) {

        return removePeloIndice(busca(valor));
    }

    public int getTamanho() {
        return nroElem;
    }

    public T getElemento(int indice) {
        if (indice < 0 || indice >= nroElem) {
            return null;
        } else {
            return vetor[indice];
        }
    }

    public void limpa() {
        nroElem = 0;
    }

    public void gravaProduto(ListaObj<Produto> listaObj, Boolean csv) {

        FileWriter arq = null;
        Formatter saida = null;
        boolean deuRuim = false;
        String nomeArquivo = "";
        String caminho = ".\\src\\main\\resources\\static\\";


        Date dataDeHoje = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        if (csv) {

            nomeArquivo = "Produto.csv";


        } else {
            nomeArquivo = "Produto.txt";
        }


        try {
            arq = new FileWriter( nomeArquivo, true);
            saida = new Formatter(arq);
        } catch (IOException erro) {
            System.err.println("Erro ao abrir arquivo");
            System.exit(1);
        }


        try {

            for (int i = 0; i < listaObj.getTamanho(); i++) {
                Produto p = listaObj.getElemento(i);

                if (csv) {
                    saida.format("%d;%d;%s;%.2f%n", p.getCodProc(),
                            p.getUnidade(), p.getNome(), p.getPreco());
                } else {


                }
            }


        } catch (FormatterClosedException erro) {
            System.err.println("Erro ao gravar no arquivo");
            deuRuim = true;
        } finally {

            saida.close();

            try {
                arq.close();
            } catch (IOException erro) {
                System.err.println("Erro ao fechar arquivo.");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }


    public static void gravaRegistro(String nomeArq, String registro) {
        BufferedWriter saida = null;
        try {
            // o argumento true é para indicar que o arquivo não será sobrescrito e sim
            // gravado com append (no final do arquivo)
            saida = new BufferedWriter(new FileWriter(nomeArq, true));
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


}
