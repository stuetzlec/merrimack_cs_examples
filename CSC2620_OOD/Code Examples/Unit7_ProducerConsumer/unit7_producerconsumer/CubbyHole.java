package unit7_producerconsumer;

class CubbyHole {

    private int contents;
    private boolean availableToBeConsumed = false;

    public synchronized int get() {
        while (availableToBeConsumed == false) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        availableToBeConsumed = false;
        notifyAll(); // "Wakes up" all threads that are "waiting"
        return contents;
    }

    public synchronized void put(int value) {
        while (availableToBeConsumed == true) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        contents = value;
        availableToBeConsumed = true;
        notifyAll(); // "Wakes up" all threads that are "waiting"
    }
}