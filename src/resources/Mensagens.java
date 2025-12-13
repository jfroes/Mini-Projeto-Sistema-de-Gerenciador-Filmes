package resources;

public class Mensagens {
    private Mensagens(){}

    public static String erro(String mensagem){
        return Cores.NEGRITO + Cores.BRANCO + Cores.FUNDO_VERMELHO + mensagem + Cores.RESET;
    }

    public static String sucesso(String mensagem){
        return Cores.NEGRITO + Cores.BRANCO + Cores.FUNDO_VERDE+ mensagem + Cores.RESET;
    }
}
