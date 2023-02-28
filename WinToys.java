import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class WinToys {
    /**
     * Список победителей
     */
    protected static List<String> winToysList = new ArrayList<>();
    /**
     * Список разыгранных призов
     */
    protected static List<String> listOfRaffledToys = new ArrayList<>();
    /**
     * Список выданных призов
     */
    protected static List<String> listOfIssuedToys = new ArrayList<>();
    /**
     * Файл с победителями
     */
    protected static String winnerFileName = "WinnerList.txt";
    /**
     * Файл с выданными призами
     */
    protected static String prizeAwardFileName = "ListPrizeAwarded.txt";

    /**
     * Метод сортировки призов по возрастанию % выпадения
     */
    protected static List<Toys> sortedList() {
        List<Toys> sortToysList = new ArrayList<>(UserInterface.toysList);
        sortToysList.sort(Comparator.comparingInt(o -> o.frequencyDrops));
        return sortToysList;
    }

    /**
     * Метод получения случайного числа для определения выигранного приза
     */
    protected static int randomChance() {
        float numberRandom = (float) Math.random() * 100;
        return (int) numberRandom + 100;
    }

    /**
     * Метод уменьшения количества разыгрывающих призов
     */
    private static void reducingCount(List<Toys> ListToys, int index) {
        ListToys.get(index).countToys = ListToys.get(index).countToys - 1;
    }

    /**
     * Метод удаление закончившихся призов
     */
    protected static void delToys(List<Toys> ListToys, int index) {
        if (ListToys.get(index).countToys == 0) {
            ListToys.remove(index);
        }
    }

    /**
     * Метод розыгрыша призов
     */
    protected static void lotteryToys() {
        int givenChance = randomChance();
        List<Toys> sortedListToys = sortedList();
        if (!sortedListToys.isEmpty()) {
            for (int i = sortedListToys.size() - 1; i >= 0; i--) {
                if (sortedListToys.get(i).countToys != 0) {
                    if (sortedListToys.get(0).frequencyDrops > givenChance) {
                        reducingCount(UserInterface.toysList, 0);
                        UserInterface.listOfWinners(sortedListToys, 0);
                        delToys(UserInterface.toysList, 0);
                        break;
                    } else if (sortedListToys.get(i).frequencyDrops <= givenChance) {
                        reducingCount(UserInterface.toysList, i);
                        UserInterface.listOfWinners(sortedListToys, i);
                        delToys(UserInterface.toysList, i);
                        break;
                    }
                }
            }
            FileOperations.createFile(winToysList, winnerFileName);
        } else {
            System.out.println("Список призов пуст. Внесите данные о призах");
        }
    }

    /**
     * Метод выдачи призов
     */
    protected static void givOutToys() {
        boolean isTrue = true;
        if (listOfRaffledToys.isEmpty() && FileOperations.checkingForFileAvailability(winnerFileName)) {
            FileOperations.readerFileList(listOfRaffledToys, winnerFileName);
        }
        if (listOfIssuedToys.isEmpty() && FileOperations.checkingForFileAvailability(prizeAwardFileName)) {
            FileOperations.readerFileList(listOfIssuedToys, prizeAwardFileName);
        }
        if (!listOfIssuedToys.isEmpty()) {
            listOfIssuedToys.forEach(ob -> listOfRaffledToys.remove(ob));
        }
        if (!listOfRaffledToys.isEmpty()) {
            UserInterface.showWinner();
            System.out.println();
            Scanner scanner = new Scanner(System.in);
            while (isTrue) {
                System.out.printf("Введите номер победителя (от 1 до %d) для выдачи приза: ", listOfRaffledToys.size());
                if (scanner.hasNextInt()) {
                    int number = scanner.nextInt();
                    if (number >= 1 && number <= listOfRaffledToys.size()) {
                        listOfIssuedToys.add(listOfRaffledToys.get(number - 1));
                        listOfRaffledToys.remove(number - 1);
                        FileOperations.createFile(listOfIssuedToys, prizeAwardFileName);
                        isTrue = false;
                    } else {
                        System.out.println("Нет такого победителя");
                    }
                } else {
                    System.out.println("Вы ввели не число");
                }
            }
        } else {
            System.out.println("Нет разыгранных призов");
        }
    }
}
