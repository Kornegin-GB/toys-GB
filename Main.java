import java.util.Scanner;

public class Main {
    private static boolean run = true;

    public static void main(String[] args) {
        while (run) {
            runProgram();
        }
        System.out.println("\nПрограмма завершила работу");
    }

    protected static void runProgram() {
        UserInterface.showMaimMenu();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Выберите пункт меню: ");
        if (scanner.hasNextInt()) {
            int numberMenu = scanner.nextInt();
            if (numberMenu >= 1 && numberMenu <= UserInterface.mainMenu().size()) {
                if (numberMenu == 1) {
                    System.out.println("\nДобавить игрушку");
                    UserInterface.addToys();
                } else if (numberMenu == 2) {
                    System.out.println("\nИзменить % выпадения игрушки");
                    UserInterface.changeToys();
                } else if (numberMenu == 3) {
                    System.out.println("\nРазыграть игрушку");
                } else if (numberMenu == 4) {
                    System.out.println("\nВыдать игрушку");
                } else if (numberMenu == 5) {
                    run = false;
                }
            } else {
                System.out.println("\nВыбран не верный пункт меню");
                System.out.printf("Выберите от 1 до %d:\n", UserInterface.mainMenu().size());
            }
        } else {
            System.out.println("Вы ввели не число");
        }
    }
}
