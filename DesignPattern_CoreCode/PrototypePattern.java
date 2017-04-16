/****************************************************************************************************************/
// 简化的原型设计模式
public class ConcretePrototype implements  Cloneable {
    public ConcretePrototype clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("Not Support Cloneable");
        }
        return (ConcretePrototype)object;
    }
}

public class Client {
    // Object相当于抽象原型类
    Object concretePrototype = new ConcretePrototype();
    Object cloneConcretePrototype = concretePrototype.clone();
    // ConcretePrototype concretePrototype = new ConcretePrototype();
    // ConcretePrototype cloneConcretePrototype = concretePrototype.clone();
}

/****************************************************************************************************************/
// 解决方案一 原型模式的实现
public interface Prototype extends Cloneable {
    public Prototype clone();
}

public class ConcretePrototypeA implements Prototype {
    public Prototype clone() {
        Object object = null;
        try {
            object = super.clone();
　　    } catch (CloneNotSupportedException exception) {
            System.err.println("Not Support Cloneable");
　　    }
        return (Prototype)object;
    }
}

public class ConcretePrototypeB implements Prototype {
    public Prototype clone() {
        Object object = null;
        try {
            object = super.clone();
　　    } catch (CloneNotSupportedException exception) {
            System.err.println("Not Support Cloneable");
　　    }
        return (Prototype)object;
    }
}

public class Client {
    Prototype concretePrototypeA = new ConcretePrototypeA();
    Prototype cloneConcretePrototypeA = concretePrototypeA.clone();
    Prototype concretePrototypeB = new ConcretePrototypeB();
    Prototype cloneConcretePrototypeB = concretePrototypeB.clone();
    public void operation(){
        // 客户端进行的操作
    }
}

/****************************************************************************************************************/
// 解决方案二 浅克隆原型模式的实现
public class Aggregation {
    // setter()方法
    // getter()方法
    public void operation(){
    }
}

public class ConcretePrototype implements Cloneable {
    private Aggregation aggregation;

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    public Aggregation getAggregation() {
        return this.aggregation;
    }

    // 使用clone()方法实现浅克隆
    public ConcretePrototype clone() {
        Object object = null;
        try {
            object = super.clone();
　　    } catch (CloneNotSupportedException exception) {
            System.err.println("Not support cloneable");
　　    }
        return (ConcretePrototype)object;
    }
}

public class Client {
    ConcretePrototype concretePrototype  = new ConcretePrototype(); //创建原型对象
    Aggregation aggregation = new Aggregation(); //创建聚合对象
    concretePrototype.setAggregation(aggregation);  //将聚合对象添加到原型对象中
    ConcretePrototype cloneConcretePrototype = concretePrototype.clone(); //调用克隆方法创建克隆原型对象

    System.out.println("原型对象与克隆对象是否相同？ " + (concretePrototype == cloneConcretePrototype));
    System.out.println("原型对象与克隆对象的引用类型成员变量是否相同？ " +
        (concretePrototype.getAggregation() == cloneConcretePrototype.getAggregation()));
}

/****************************************************************************************************************/
// 解决方案三 深克隆原型模式的实现
public class Aggregation implements Serializable {
    // setter()方法
    // getter()方法
    public void operation() {
    }
}

public class  ConcretePrototype implements Serializable {
    private Aggregation aggregation;

    public void setAggregation(Aggregation aggregation) {
        this.aggregation = aggregation;
    }

    public Aggregation getAggregation() {
        return this.aggregation;
    }

   // 使用序列化技术实现深克隆
   public ConcretePrototype deepClone() throws IOException, ClassNotFoundException, OptionalDataException {
       // 将对象写入流中
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
       objectOutputStream.writeObject(this);
       // 将对象从流中取出
       ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
       ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
       return  (WeeklyLog)objectInputStream.readObject();
   }
}

public class Client {
    ConcretePrototype concretePrototype  = new ConcretePrototype(); //创建原型对象
    Aggregation aggregation = new Aggregation(); //创建聚合对象
    concretePrototype.setAggregation(aggregation);  //将聚合对象添加到原型对象中

    try {
        ConcretePrototype cloneConcretePrototype = concretePrototype.deepClone(); //调用克隆方法创建克隆原型对象
    } catch(Exception e) {
        System.out.println("Clone Failure");
    }
    System.out.println("原型对象与克隆对象是否相同？ " + (concretePrototype == cloneConcretePrototype));
    System.out.println("原型对象与克隆对象的引用类型成员变量是否相同？ " +
        (concretePrototype.getAggregation() == cloneConcretePrototype.getAggregation()));
}

/****************************************************************************************************************/
// 解决方案四 带原型管理器的原型模式的实现
public interface Prototype extends Cloneable {
    public Prototype clone();
}

public class ConcretePrototypeA implements Prototype {
    public Prototype clone() {
        Prototype object = null;
        try {
            object = super.clone();
　　    } catch (CloneNotSupportedException exception) {
            System.err.println("Not Support Cloneable");
　　    }
        return (Prototype)object;
    }
}

public class ConcretePrototypeB implements Prototype {
    public Prototype clone() {
        Prototype object = null;
        try {
            object = super.clone();
　　    } catch (CloneNotSupportedException exception) {
            System.err.println("Not Support Cloneable");
　　    }
        return (Prototype)object;
    }
}

// 原型管理器（使用饿汉式单例实现）
public class PrototypeManager {
    //定义一个Hashtable，用于存储原型对象
    private Hashtable hashtable = new Hashtable();
    private static PrototypeManager prototypeManager = new PrototypeManager();

    // 为Hashtable增加ConcretePrototype对象
    private PrototypeManager() {
        hashtable.put("CPA", new ConcretePrototypeA());
        hashtable.put("CPB", new ConcretePrototypeB());
    }
    // 增加新的原型对象类型
    public void addPrototype(String key, Prototype prototype) {
        hashtable.put(key, prototype);
    }
    // 通过浅克隆获取对应原型对象类型的克隆对象
    public Prototype getPrototype(String key) {
        return  ((Prototype)hashtable.get(key)).clone();
    }
    // 返回当前的原型管理器对象
    public static PrototypeManager getPrototypeManager() {
        return prototypeManager;
    }
}

public class Client {
    // 获取原型管理器对象
    PrototypeManager prototypeManager = PrototypeManager.getPrototypeManager();
    Prototype concretePrototypeA1, concretePrototypeA2, concretePrototypeB1, concretePrototypeB2;
    concretePrototypeA1 = prototypeManager.getPrototype("CPA");
    concretePrototypeA2 = prototypeManager.getPrototype("CPA");
    System.out.println(concretePrototypeA1 == concretePrototypeA2);

    concretePrototypeB1 = prototypeManager.getPrototype("CPB");
    concretePrototypeB2 = prototypeManager.getPrototype("CPB");
    System.out.println(concretePrototypeB1 == concretePrototypeB2);
}

/****************************************************************************************************************/
// 应用实例的解决方案
public class Notification implements  Cloneable {
    private String title;
    private String content;
    private Footer footer;
    // setter() getter()方法
    public Notification clone() {
        Notification object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException exception) {
            System.err.println("Not Support Cloneable");
        }
        return (Notification)object;
    }
}

public class Client {
    Notification notification = new Notification();
    Notification cloneNotification = notification.clone();
}
