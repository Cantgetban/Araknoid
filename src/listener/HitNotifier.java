package listener;

/**
 * @author Amit Cohen, ID=208428714
 * The class HitNotifier represents an object that get hits.
 */
public interface HitNotifier {
    /**
     * This function adds a listener to the object.
     * @param hl - the listener that we want to add.
     */
    void addHitListener(HitListener hl);

    /**
     * This function removes a listener to the object.
     * @param hl - the listener that we want to remove.
     */
    void removeHitListener(HitListener hl);
}
