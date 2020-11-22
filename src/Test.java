import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        JogoForca jogo = new JogoForca();
        Scanner tec = new Scanner(System.in);

        while (true) {
            String chute = tec.nextLine();
            Character entrada = chute.charAt(0);

            System.out.println("apos chute");
            System.out.println(jogo.chute(entrada));
        }
    }
}
