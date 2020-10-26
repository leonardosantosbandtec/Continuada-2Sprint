import java.util.Scanner;

public class App {

    public static void main(String[] args) {


        Scanner leitor = new Scanner(System.in);

        int maxPoke = 0;
        String[] pokemon;


        double[][] status;
        double[] mediaStatus = new double[2];
        boolean validado = false;


        while (!validado) {
            try {

                System.out.println("Digite a quantidade maxima de pokemons que vai registrar :)(Só podem de 5 a 10 em!!)");

                maxPoke = leitor.nextInt();

                if (maxPoke >= 5 && maxPoke <= 10) {
                    validado = true;

                } else {

                    throw new Exception("O numero de pokemons deve estar entre 5 e 10." +
                            " Digite novamente.");
                }
            } catch (Exception erro) {
                System.out.println(erro);
            }
        }


        pokemon = new String[maxPoke];
        status = new double[maxPoke][2];



        for (int i=0; i < pokemon.length; i++) {
            System.out.println("Digite o nome do pokemon");
            pokemon[i] = leitor.next();
        }


        for (int linha=0; linha < status.length; linha++) {
            for (int coluna=0; coluna < status[0].length; coluna++) {
                System.out.println("Digite o valor(dano) do " + (coluna+1)+
                        " Super ataque do " + pokemon[linha]);
                status[linha][coluna]= leitor.nextDouble();
            }
        }


        for (int coluna=0; coluna < status[0].length; coluna++) {
            double soma = 0;
            for (int linha=0; linha < status.length; linha++) {
                soma += status[linha][coluna];
            }
            mediaStatus[coluna]= soma / maxPoke;

        }




        System.out.printf("%-15s%13s%10s%13s\n", "POKEMON",
                "Super(1)", "Super(2)", "DANO MAXIMO");

        for (int linha=0; linha < status.length; linha++) {
            double somar = 0;
            System.out.printf("%-15s",pokemon[linha]);

            for (int coluna=0; coluna < status[0].length; coluna++) {
                System.out.printf("%10.2f",status[linha][coluna]);
                somar += status[linha][coluna];
            }
            System.out.printf("%10.2f", somar);
            System.out.println();
        }
        // Exibição das médias
        System.out.println();
        System.out.printf("%-15s", "MÉDIA ATKs:");
        for (int i=0; i < mediaStatus.length; i++) {
            System.out.printf("%10.2f",mediaStatus[i]);
        }
    }

}
