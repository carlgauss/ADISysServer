package util;

public class Entry<X, Y> {

    private final X firstValue;
    private final Y secondValue;

    public Entry(X firstValue, Y secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public X getFirstValue() {
        return firstValue;
    }

    public Y getSecondValue() {
        return secondValue;
    }
}
