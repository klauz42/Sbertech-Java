package ru.xou.task01_cloning;

class Wheel implements Cloneable{
    int d;
    Wheel (int di) {
        d = di;
    }

    @Override
    public Wheel clone() {
        try {
            return (Wheel) super.clone();
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Wheel other = (Wheel) obj;
        if (this.d != other.d)
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 41;
        return (prime * this.d);
    }

}

public class Bike implements Cloneable{

    private int wheelCount;
    private Wheel w;

    Bike (int wCount, int wDiameter) {
        wheelCount = wCount;
        w = new Wheel(wDiameter);
    }

    @Override
    public Bike clone() {
        try {
             Bike clonedBike = (Bike) super.clone();
             clonedBike.w = (Wheel) w.clone();
             return clonedBike;
        }
        catch( CloneNotSupportedException ex ) {
            throw new InternalError();
        }
    }

    @Override
    public String toString(){
        return "The bike has " + this.wheelCount + " with diameter of " +
                this.w.d + " cm";

    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        Bike other = (Bike) obj;

        if ((this.w.d != other.w.d) || (this.wheelCount != other.wheelCount))
            return false;
        return true;
    }

    @Override
    public int hashCode(){
        final int prime = 67;
        return (prime * this.w.d * this.wheelCount);
    }


    public static void main(String[] args) throws CloneNotSupportedException {
        //создадим объект и склонируем
        Bike bike1 = new Bike(2, 26);
        Bike bike2 = bike1.clone();
        //выведем информацию о них
        System.out.println(bike1);
        System.out.println(bike2);
        //сравним и проверим симметричность
        System.out.println(bike1.equals(bike2));
        System.out.println(bike2.equals(bike1));
        //изменим оба объекта
        bike1.wheelCount = 1;
        bike1.w.d = 29;
        bike2.wheelCount = 4;
        bike2.w.d = 35;
        //вновь выведем информацию
        System.out.println(bike1);
        System.out.println(bike2);
        //и сравним
        System.out.println(bike1.equals(bike2));
        System.out.println(bike2.equals(bike1));
        //проверим рефлексивность для equals
        System.out.println(bike1.equals(bike1));
        //транзитивность
        bike2 = bike1.clone();
        Bike bike3 = bike1.clone();
        System.out.println(bike1);
        System.out.println(bike2);
        System.out.println(bike3);
        System.out.println((bike1.equals(bike2))&&(bike2.equals(bike3))&&(bike1.equals(bike3)));
        //изменим третий объект и проверим хэш-коды
        bike3.wheelCount = 1;
        bike3.w.d = 66;
        System.out.println(bike1.hashCode());
        System.out.println(bike2.hashCode());
        System.out.println(bike3.hashCode());
        //и для колесс
        System.out.println(bike1.w.hashCode());
        System.out.println(bike2.w.hashCode());
        System.out.println(bike3.w.hashCode());

    }
}
