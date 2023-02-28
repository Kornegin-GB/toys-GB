import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    /**
     * Список призов
     */
    protected static List<Toys> toysList = new ArrayList<>();
    protected static boolean isTrue = true;

    /**
     * Список главного меню
     */
    protected static List<String> mainMenu() {
        List<String> menu = new ArrayList<>();
        menu.add(0, "Добавить приз");
        menu.add(1, "Изменить % выпадения приза");
        menu.add(2, "Разыграть приз");
        menu.add(3, "Выдать приз");
        menu.add(4, "Удалить файл с выданными призами");
        menu.add(5, "Выход из программы");
        return menu;
    }

    /**
     * Отображение главного меню
     */
    protected static void showMaimMenu() {
        System.out.println("\nГлавное меню");
        for (int i = 0; i < mainMenu().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, mainMenu().get(i));
        }
        System.out.println();
    }

    /**
     * Метод добавляет список призов
     */
    protected static void addToys() {
        isTrue = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название приза: ");
        String nameToys = scanner.nextLine();
        int countToys = 0;
        int frequencyDrops = 0;
        while (isTrue) {
            System.out.print("Введите количество призов: ");
            if (scanner.hasNextInt()) {
                countToys = scanner.nextInt();
                isTrue = false;
            } else {
                System.out.println("Вы ввели не число!");
                scanner = new Scanner(System.in);
            }
        }
        isTrue = true;
        while (isTrue) {
            System.out.print("Введите % выпадения приза от 100 до 200: ");
            if (scanner.hasNextInt()) {
                frequencyDrops = scanner.nextInt();
                if (frequencyDrops >= 100 && frequencyDrops <= 200) {
                    isTrue = false;
                } else {
                    System.out.println("Вы ввели не правильное число");
                }
            } else {
                System.out.println("Вы ввели не число!");
                scanner = new Scanner(System.in);
            }
        }
        int id = toysList.size() + 1;
        toysList.add(new Toys(id, nameToys, countToys, frequencyDrops));

        System.out.printf("\nВы добавили:\n%s\n", toysList.get(toysList.size() - 1));
    }

    /**
     * Метод изменяет % выпадения приза
     */
    protected static void changeToys() {
        if (toysList.isEmpty()) {
            System.out.println("\nСписок призов пуст");
        } else {
            for (Toys toys : toysList) {
                System.out.println(toys);
            }
            Scanner scanner = new Scanner(System.in);
            while (isTrue) {
                System.out.print("Выберите номер записи для изменения: ");
                if (scanner.hasNextInt()) {
                    int entryNumber = scanner.nextInt();
                    if (entryNumber >= 1 && entryNumber <= toysList.size()) {
                        System.out.println("\nВыбранная запись для изменения\n" + toysList.get(entryNumber - 1));
                        while (isTrue) {
                            System.out.print("\nВведите новый % выпадения от 100 до 200: ");
                            if (scanner.hasNextInt()) {
                                int newFrequencyDrops = scanner.nextInt();
                                if (newFrequencyDrops >= 100 && newFrequencyDrops <= 200) {
                                    toysList.get(entryNumber - 1).frequencyDrops = newFrequencyDrops;
                                    System.out.println("Новая запись приза\n" + toysList.get(entryNumber - 1));
                                    isTrue = false;
                                } else {
                                    System.out.println("Введен недопустимый процент");
                                }
                            } else {
                                System.out.println("Вы ввели не число");
                                scanner = new Scanner(System.in);
                            }
                        }
                    } else {
                        System.out.println("Нет записи с таким номером");
                    }
                } else {
                    System.out.println("Вы ввели не число");
                    scanner = new Scanner(System.in);
                }
            }
        }
    }

    /**
     * Метод ввода победителя выигравшего приз
     */
    protected static String inputInWinner() {
        Scanner scanner = new Scanner(System.in);
//        System.out.printf("Ваш приз %s\n", prize);
        System.out.print("Введите имя победителя: ");
        return scanner.nextLine();
    }

    /**
     * Метод формирования списка победителей и выигранных призов
     */
    protected static void listOfWinners(List<Toys> listWinner, int index) {
        WinToys.winToysList.add("Победитель " + inputInWinner() +
                ". Название приза: " + listWinner.get(index).nameToys +
                " (id " + listWinner.get(index).id + ") в количестве - " +
                1 + " шт.");
        System.out.printf("Ваш приз %s\n", listWinner.get(index).nameToys);
    }

    protected static void showWinner() {
        for (int i = 0; i < WinToys.listOfRaffledToys.size(); i++) {
            System.out.println((i + 1) + " " + WinToys.listOfRaffledToys.get(i));
        }
    }
}
