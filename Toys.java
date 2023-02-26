public class Toys {
    protected int id;
    protected String nameToys;
    protected int countToys;
    protected int frequencyDrops;

    public Toys(int id, String nameToys, int countToys, int frequencyDrops) {
        this.id = id;
        this.nameToys = nameToys;
        this.countToys = countToys;
        this.frequencyDrops = frequencyDrops;
    }

    @Override
    public String toString() {
        return id + " " + nameToys + " - " + countToys + " шт. Шанс выпадения - " + frequencyDrops + "%";
    }
}
