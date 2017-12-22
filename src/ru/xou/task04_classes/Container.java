package ru.xou.task04_classes;

public class Container {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));

        Selector fs = sequence.forwardSelector();
        while (!fs.end()) {
            System.out.println(fs.current() + " ");
            fs.next();
        }
        System.out.println("---------------");
        Selector bs = sequence.backwardSelector();
        while (!bs.end()) {
            System.out.println(bs.current());
            bs.next();
        }
    }
}


class Sequence {
    private Object[] items;
    private int next = 0;


    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    public Selector forwardSelector() {
        return new Selector() {
            int i = 0;

            @Override
            public boolean end() {
                if (i == items.length)
                return true;
                else return false;
            }

            @Override
            public Object current() {
                return items[i];
            }

            @Override
            public void next() {
                i++;
            }
        };

    }

    public Selector backwardSelector() {
        return new Selector() {
            int i = items.length - 1;

            @Override
            public boolean end() {
                if (i < 0)
                    return true;
                else return false;
            }

            @Override
            public Object current() {
                return items[i];
            }

            @Override
            public void next() {
                i--;
            }
        };
    }
}


    interface Selector {
        boolean end();

        Object current();

        void next();
    }

