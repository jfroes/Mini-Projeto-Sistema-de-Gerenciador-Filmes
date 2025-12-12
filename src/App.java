import controllers.FilmeController;
import dto.FilmeDTO;
import services.FilmeService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        FilmeController controller = new FilmeController();

        int opcao;

        System.out.println(menu());

        do {

             opcao = sc.nextInt();
             sc.nextLine();

            switch (opcao) {
                case 1 -> {
                    controller.findAll();
                    System.out.println(menu());
                    break;
                }

                case 2 ->{
                    int opcaoSubMenuPesquisaFilme = -1;
                    do {
                    System.out.println(subMenuPesquisaFilme());

                    try {
                        opcaoSubMenuPesquisaFilme = sc.nextInt();
                    }catch (InputMismatchException e){
                        sc.nextLine();
                        System.out.println("Entrada inválida. Digite um número");
                        continue;
                    }
                    sc.nextLine();

                    switch (opcaoSubMenuPesquisaFilme){
                        case 1 -> {
                            System.out.print("DIGITE O TITULO DO FILME: ");
                            String titulo = sc.nextLine();
                            controller.findByTitulo(titulo);

                        }

                        case 2 -> {
                            System.out.print("DIGITE O GENERO DO FILME: ");
                            String genero = sc.nextLine();
                            controller.findByGenero(genero);
                        }

                        case 3 -> {
                            System.out.print("DIGITE O DIRETOR DO FILME: ");
                            String diretor = sc.nextLine();
                            controller.findByDiretor(diretor);
                        }

                        case 4 ->{
                            System.out.print("DIGITE O ANO DO FILME: ");
                            int ano = sc.nextInt();
                            controller.findByAno(ano);
                        }

                        case 0 ->{
                            System.out.println("VOLTANDO AO MENU PRINCIPAL...");
                            System.out.println(menu());

                        }

                        default -> {
                            System.out.println("OPÇÃO INVÁLIDA");

                        }
                    }

                    }while(opcaoSubMenuPesquisaFilme != 0);
                    break;
                }
                case 0 ->{
                    System.out.println("ENCERRANDO O SISTEMA...");
                    break;
                }
            }


        }while (opcao != 0);


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

    public static String subMenuPesquisaFilme(){
        return """
                BUSCAR FILME POR:
                -----------------
                1 - TITULO
                2 - GENERO
                3 - DIRETOR
                4 - ANO
                0 - VOLTAR AO MENU PRINCIPAL
                \s""";
    }
}
