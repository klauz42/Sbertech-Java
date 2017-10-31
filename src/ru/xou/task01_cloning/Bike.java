package ru.xou.task01_cloning;

class Wheel {
    int d;
    Wheel (int di) {
        d = di;
    }
}

public class Bike implements Cloneable{

    @Override
    protected Bike clone() {
            return new Bike(this.wheelCount, this.w.d);
    }

    int wheelCount;
    Wheel w;
    Bike (int wCount, int wDiameter) {
        wheelCount = wCount;
        w = new Wheel(wDiameter);
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Bike bike1 = new Bike(2, 26);
        Bike bike2 = bike1.clone();

        bike1.wheelCount = 1;
        bike1.w.d = 29;
        bike2.wheelCount = 4;
        bike2.w.d = 35;


        System.out.println(bike1.wheelCount);
        System.out.println(bike1.w.d);
        System.out.println(bike2.wheelCount);
        System.out.println(bike2.w.d);

    }
}
