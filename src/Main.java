import java.util.Scanner;
public class Main {
    private final Scanner sc;
    private Board bo;
    private boolean started=false;

    public Main() {
        sc = new Scanner(System.in);
    }

    public static void main(String[] arg) {
        System.out.println("Welcome to the boardgame");
        Main ppl = new Main();
        int option = 0;
        do {
            option = ppl.showMenu();
            ppl.runMenu(option);
        } while (option != 0);
    }

    public int showMenu() {
        if (!started) {
            System.out.println("Choose one of the following options\n" +
                    "(0)Exit\n" +
                    "(1)Start new game\n");
            int option = sc.nextInt();
            sc.nextLine();
            return option;
        }else{
            System.out.println("Choose one of the following options\n" +
                    "(0)Exit\n" +
                    "(1)Start new game\n" +
                    "(2)Roll the dice!\n"+
                    "(3)See board\n");
            int option = sc.nextInt();
            sc.nextLine();
            return option;
        }
    }

    public void runMenu(int option) {
        switch (option) {
            case 1 :
                System.out.println("Type the number of columns");
                int columns = sc.nextInt();
                sc.nextLine();
                System.out.println("Type the number of rows");
                int rows = sc.nextInt();
                sc.nextLine();
                System.out.println("Type the number player 1's name");
                String p1 = sc.nextLine();
                System.out.println("Type the number player 2's name");
                String p2 = sc.nextLine();
                bo = new Board(rows,columns);
                bo.create(p1,p2);
                System.out.println(bo.print());
                started=true;
                break;
            case 2:
                if(started){
                    System.out.println(bo.round());
                    started=bo.isStarted();
                }else {
                    System.out.println("You need to start a new game!");
                }

                break;
            case 3:
                if(started){
                    System.out.println(bo.print());
                }else {
                    System.out.println("You need to start a new game!");
                }
                break;
            case 0:
                System.out.println("Bye!!");
                break;

            default:
                System.out.println("Choose a valid option!");
                break;
        }
    }
}