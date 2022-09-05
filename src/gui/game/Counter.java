package gui.game;

/**
 * @author Aviya elgrably 209251891
 * class for counter.
 */
public class Counter {
    private int num;

    /**
     * @param num - the number.
     */
    public Counter(int num) {
        this.num = num;
    }

    /**
     * add number to current count.
     *
     * @param number - the number.
     */
    void increase(int number) {
        num += number;
    }

    /**
     * subtract number from current count.
     *
     * @param number - the number.
     */
    void decrease(int number) {
        num -= number;
    }

    /**
     * @return -  get current count.
     */
    public int getValue() {
        return num;
    }
}
