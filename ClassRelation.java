// 关联对应的Java代码
class Customer {
    private Integer id;
    private String name;
    private Set<Order> orders;
    public Set<Order> getOrders() {
        return orders;
    }
    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}

class Order {
    private Integer id;
    private float money;
    private Customer customer;
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

// 依赖对应的Java代码
class Bicycle {
    public void expand(Pump pump) {
        pump.blow();
    }
}

class Pump {
    public void blow() {
        System.out.println("正在充气......");
    }
}

// 聚合对应的Java代码
class Computer {
    private MainBoard mainBoard;
    private DisplayCard displayCard;

    public void on() {
        System.out.println("开启计算机......");
    }
    public void close() {
        System.out.println("关闭计算机......");
    }
    public void run() {
        System.out.println("计算机正在运行......");
    }
}

class MainBoard {
    public void control() {
        System.out.println("控制计算机......");
    }
}

class DisplayCard {
    public void display() {
        System.out.println("计算显示数据......");
    }
}

// 组合对应的Java代码
class People {
    private Head head;
    private Hand hand;
    private Leg leg;
    public void think() {
        head.think();
    }
    public void holdThing() {
        hand.holdThing();
    }
    public void walk() {
        leg.walk();
    }
}

class Head {
    public void think() {
        System.out.println("思考......");
    }
}

class Hand {
    public void holdThing() {
        System.out.println("拿东西......");
    }
}

class Leg {
    public void walk() {
        System.out.println("走路......");
    }
}

// 泛化对应的Java代码
class People {
    protected String name;
    protected String sex;
    protected Date birthday;
    public void eat() {
        System.out.println(name + "正在吃饭......");
    }
    public void drink() {
        System.out.println(name + "正在喝水......");
    }
    public void sleep() {
        System.out.println(name + "正在休息......");
    }
}

class Student extends People {
     public void study() {
         System.out.println(name + "正在学习......");
     }
 }

// 实现对应的Java代码
public interface Driver {
    void drive();
}

class CarDriver implements Driver {
    public void drive() {
        System.out.println("驾驶汽车......");
    }
}

class PlaneDriver implements Driver {
    public void drive() {
        System.out.println("驾驶飞机......");
    }
}
