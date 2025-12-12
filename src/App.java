import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        boolean sair = false;

        System.out.println(menu());

        do {

            int opcao = sc.nextInt();

            sair = opcao == 0 ? true : false;

        }while (sair == false);


        sc.close();

    }

    public static String menu(){
        return """
                :::CÁTALOGO DE FILMES:::
                ------------------------
               \s
                DIGITE UMA OPÇÃO:
                1 - LISTAR FILMES
                2 - PESQUSISAR FILME
                3 - CADASTRAR FILME
                4 - DELETAR FILME
                0 - SAIR             \s
               \s""";
    }
}
