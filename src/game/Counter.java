package game;

/**
 * @author Amit Cohen, ID=208428714
 * The class Counter representes a Counter.
 */
public class Counter {
    private int count;

    /**
     * This is a construction method.
     */
    public Counter() {
        this.count = 0;
    }

    /**
     * This function increases the counter by the number she gets.
     * @param number - the number that the counter will be increased by.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * This function decreases the counter by the number she gets.
     * @param number - the number that the counter will be decreased by.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * This is an accessor method, the method returns the value of the counter.
     * @return - the value of the counter.
     */
    public int getValue() {
        return this.count;
    }
}
