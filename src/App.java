import controllers.FilmeController;
import dto.FilmeDTO;
import resources.Cores;
import resources.Mensagens;
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
                        System.out.println(Mensagens.erro("ENTRADA INVÁLIDA. DIGITE UM NÚMERO."));
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
                            System.out.println(Mensagens.erro("OPÇÃO INVÁLIDA"));

                        }
                    }

                    }while(opcaoSubMenuPesquisaFilme != 0);
                    break;
                }
                case 3 ->{
                    Character opcaoCase3 = 'S';
                    do{
                        System.out.println("INSIRA AS INFORMAÇÕES:");
                        System.out.print("TITULO: ");
                        String titulo = sc.nextLine();
                        System.out.print("GENERO: ");
                        String genero = sc.nextLine();
                        System.out.print("DIRETOR: ");
                        String diretor = sc.nextLine();
                        int ano = lerInteiro(sc, "ANO: ");
                        double nota = lerDouble(sc, "NOTA: ");

                        System.out.println(controller.addFilme(titulo, genero, diretor, ano, nota));


                        System.out.println("INSERIR NOVO FILME? S/N");
                        opcaoCase3 = sc.next().charAt(0);
                        sc.nextLine();

                    }while (Character.toUpperCase(opcaoCase3) != 'N');

                    System.out.println(menu());
                }

                case 4 ->{
                    Character opcaoCase4 = 'S';
                    do {
                        System.out.println(":::LISTA DE FILMES CADASTRADOS:::");
                        System.out.println();
                        controller.findAll();
                        System.out.println();
                        System.out.println("DIGITE O ID DO FILME A SER DELETADO");
                        int id = sc.nextInt();

                        controller.deleteFilme(id);

                        System.out.println("DELETAR OUTRO FILME? S/N");
                        opcaoCase4 = sc.next().charAt(0);
                        sc.nextLine();

                    }while (Character.toUpperCase(opcaoCase4) != 'N');

                    System.out.println(menu());

                }

                case 0 ->{
                    System.out.println("ENCERRANDO O SISTEMA...");
                    break;
                }

                default -> {
                    System.out.println(Mensagens.erro("OPÇÃO INVÁLIDA"));
                    System.out.println(menu());

                }
            }


        }while (opcao != 0);


        sc.close();

    }

    private static String menu(){
        return Cores.NEGRITO + """
                ╔══════════════════════════╗
                ║ :::CÁTALOGO DE FILMES::: ║ 
                ║                          ║ 
                ║       MENU PRINCIPAL     ║ 
                ║   DIGITE UMA OPÇÃO:      ║ 
                ║   1 - LISTAR FILMES      ║
                ║   2 - PESQUSISAR FILME   ║ 
                ║   3 - CADASTRAR FILME    ║ 
                ║   4 - DELETAR FILME      ║    
                ║   0 - SAIR               ║
                ╚══════════════════════════╝""" + Cores.RESET ;
    }

    private static String subMenuPesquisaFilme(){
        return Cores.NEGRITO + """
                ╔═════════════════════════════════╗
                ║     :::CÁTALOGO DE FILMES:::    ║ 
                ║                                 ║ 
                ║        MENU DE BUSCCA           ║
                ║                                 ║       
                ║   BUSCAR FILME POR:             ║ 
                ║   1 - TITULO                    ║
                ║   2 - GENERO                    ║ 
                ║   3 - DIRETOR                   ║ 
                ║   4 - ANO                       ║    
                ║   0 - VOLTAR AO MENU PRINCIPAL  ║
                ╚═════════════════════════════════╝""" + Cores.RESET ;
    }

    private static int lerInteiro(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                int valor = sc.nextInt();
                sc.nextLine(); // Limpa o buffer
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(Mensagens.erro("ENTRADA INVÁLIDA. DIGITE UM NÚMERO."));
            }
        }
    }


    private static double lerDouble(Scanner sc, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            try {
                double valor = sc.nextDouble();
                sc.nextLine(); // Limpa o buffer
                return valor;
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println(Mensagens.erro("ENTRADA INVÁLIDA. DIGITE UM NÚMERO."));
            }
        }
    }

}
