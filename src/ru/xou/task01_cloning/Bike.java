package ru.xou.task01_cloning;

class Wheel {
    int d;
    Wheel (int di) {
        d = di;
    }
}

public class Bike implements Cloneable{

    /*
    @Override
    public Bike clone() {
        try {
             super.clone();
            //return (Bike) super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }
    */

    @Override
    protected Bike clone() {
            return new Bike(this.wheelCount, this.w.d);
    }

    @Override
    public String toString(){
        return "The bike has " + this.wheelCount + " with diameter of " +
                this.w.d + " cm";

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

        System.out.println(bike1);
        System.out.println(bike2);

        bike1.wheelCount = 1;
        bike1.w.d = 29;
        bike2.wheelCount = 4;
        bike2.w.d = 35;

        System.out.println(bike1);
        System.out.println(bike2);

    }
}
