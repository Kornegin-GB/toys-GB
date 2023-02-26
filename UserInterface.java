import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    protected static List<Toys> toysList = new ArrayList<>();

    protected static List mainMenu() {
        List menu = new ArrayList<>();
        menu.add(0, "Добавить игрушку");
        menu.add(1, "Изменить % выпадения игрушки");
        menu.add(2, "Разыграть игрушку");
        menu.add(3, "Выдать игрушку");
        menu.add(4, "Выход из программы");

        return menu;
    }

    protected static void addToys() {
        boolean isTrue = true;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название игрушки: ");
        String nameToys = scanner.nextLine();
        int countToys = 0;
        while (isTrue) {
            System.out.print("Введите количество игрушек: ");
            if (scanner.hasNextInt()) {
                isTrue = false;
                countToys = scanner.nextInt();
            } else {
                System.out.println("Вы ввели не число!");
                scanner = new Scanner(System.in);
            }
        }
        isTrue = true;
        int frequencyDrops = 0;
        while (isTrue) {
            System.out.print("Введите % выпадения игрушки от 100 до 200: ");
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

    protected static void showMaimMenu() {
        System.out.println("\nГлавное меню");
        for (int i = 0; i < UserInterface.mainMenu().size(); i++) {
            System.out.printf("%d. %s\n", i + 1, UserInterface.mainMenu().get(i));
        }
        System.out.println();
    }

    protected static void changeToys() {
        boolean isTrue = true;
        if (toysList.isEmpty()) {
            System.out.println("\nСписок игрушек пуст");
        } else {
            for (int i = 0; i < toysList.size(); i++) {
                System.out.println(toysList.get(i));
            }
            Scanner scanner = new Scanner(System.in);
            while (isTrue) {
                System.out.print("Выберите номер записи для изменения: ");
                if (scanner.hasNextInt()) {
                    int entryNumber = scanner.nextInt();
                    if (entryNumber >= 1 && entryNumber <= toysList.size()) {
                        System.out.println("\nВыбранная запись для изменения\n" + toysList.get(entryNumber - 1));
                        //System.out.print("\nВведите новый % выпадения от 100 до 200: ");
                        while (isTrue) {
                            System.out.print("\nВведите новый % выпадения от 100 до 200: ");
                            if (scanner.hasNextInt()) {
                                int newFrequencyDrops = scanner.nextInt();
                                if (newFrequencyDrops >= 100 && newFrequencyDrops <= 200) {
                                    toysList.get(entryNumber - 1).frequencyDrops = newFrequencyDrops;
                                    System.out.println("Новая запись игрушки\n" + toysList.get(entryNumber - 1));
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
}
