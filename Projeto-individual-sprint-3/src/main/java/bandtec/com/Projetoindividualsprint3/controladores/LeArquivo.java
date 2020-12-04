package bandtec.com.Projetoindividualsprint3.controladores;

import bandtec.com.Projetoindividualsprint3.dominios.Produto;
import bandtec.com.Projetoindividualsprint3.repositorio.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

        public class LeArquivo {

            @Autowired
            private ProdutoRepository repository;

        public  void leArquivo(String nomeArq) {




            BufferedReader entrada = null;
            String registro;
            String tipoRegistro;
            String curso, ra, nomeAluno, nomeDisciplina;
            double media;
            int qtdFalta, contRegistro=0;

            // Abre o arquivo
            try {
                entrada = new BufferedReader(new FileReader(nomeArq));
            } catch (IOException e) {
                System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
            }

            // Lê os registros do arquivo
            try {
                // Lê um registro
                registro = entrada.readLine();

                while (registro != null) {
                    // Obtém o tipo do registro
                    tipoRegistro = registro.substring(0, 2); // obtém os 2 primeiros caracteres do registro

                    if (tipoRegistro.equals("00")) {
                        System.out.println("Header");
                        System.out.println("Tipo de arquivo: " + registro.substring(2, 6));
                        int periodoLetivo= Integer.parseInt(registro.substring(6,11));
                        System.out.println("Período letivo: " + periodoLetivo);
                        System.out.println("Data/hora de geração do arquivo: " + registro.substring(11,30));
                        System.out.println("Versão do layout: " + registro.substring(30,32));
                    }
                    else if (tipoRegistro.equals("01")) {
                        System.out.println("\nTrailer");
                        int qtdRegistro = Integer.parseInt(registro.substring(2,12));
                        if (qtdRegistro == contRegistro) {
                            System.out.println("Quantidade de registros gravados compatível com quantidade lida");
                        }
                        else {
                            System.out.println("Quantidade de registros gravados não confere com quantidade lida");
                        }
                    }
                    else if (tipoRegistro.equals("02")) {
                        if (contRegistro == 0) {
                            System.out.println();
                            System.out.printf("%-5s %-8s %-50s %-40s %5s %6s\n", "CURSO","RA","NOME DO ALUNO","DISCIPLINA",
                                    "MÉDIA", "FALTAS");

                        }

                        Produto produto = new Produto();
                        produto.setCodProc(Integer.parseInt(registro.substring(2,7)));
                        produto.setNome(registro.substring(7,15));
                        produto.setPreco(Double.parseDouble(registro.substring(15,65)));
                        produto.setUnidade(Integer.parseInt(registro.substring(65,105)));
                        produto.setDescricao(registro.substring(105,110));
                        produto.setTipoProduto(registro.substring(130,150));

                        repository.save(produto);


                        curso = registro.substring(2,7);
                        ra = registro.substring(7,15);
                        nomeAluno = registro.substring(15,65);
                        nomeDisciplina = registro.substring(65,105);
                        media = Double.parseDouble(registro.substring(105,110).replace(',','.'));
                        qtdFalta = Integer.parseInt(registro.substring(110,113));


                        contRegistro++;
                    }
                    else {
                        System.out.println("Tipo de registro inválido");
                    }

                    // lê o próximo registro
                    registro = entrada.readLine();
                }

                // Fecha o arquivo
                entrada.close();
            } catch (IOException e) {
                System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
            }

        }

        public static void main(String[] args) {
            String nomeArq = "ArquivoNotas.txt";

        }

    }



