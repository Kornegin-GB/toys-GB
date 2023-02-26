import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WinToys {
    /**
     * Список победителей
     */
    protected static List<String> winToysList = new ArrayList<>();

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
     * Метод розыгрыша призов
     */
    protected static void lotteryToys() {
        int givenChance = randomChance();
        List<Toys> sortedListToys = sortedList();
        if (!sortedListToys.isEmpty()) {
            if (sortedListToys.size() > 1) {
                for (int i = sortedListToys.size() - 1; i >= 0; i--) {
                    if (sortedListToys.get(i).frequencyDrops <= givenChance) {
                        reducingCount(sortedListToys, i);
                        UserInterface.listOfWinners(sortedListToys, i);
                        break;
                    } else if (sortedListToys.get(0).frequencyDrops > givenChance) {
                        reducingCount(sortedListToys, i);
                        UserInterface.listOfWinners(sortedListToys, i);
                        break;
                    }
                }
            } else {
                reducingCount(sortedListToys, 0);
                UserInterface.listOfWinners(sortedListToys, 0);
            }
            System.out.println(winToysList.get(winToysList.size() - 1));
        } else {
            System.out.println("Список призов пуст. Внесите данные о призах");
        }
    }
}
