package ru.xou.hello;

public class HelloWorld {
    public static void main(String[] args) {
        Parent p1 = new Child();
        Object p2 = new Object();

        System.out.println(p1.toString());
        System.out.println(p2.toString());


    }

}

class Parent {

    public boolean equals(Object obj){
        if (this == obj)
            return true;
        else return false;
    }

    public int hashCode() {
        return 42;
    }

}

class Uncle {

}

class Child extends Parent{

}

class Child2 extends Parent {

}

class GrandChild extends Child {

};