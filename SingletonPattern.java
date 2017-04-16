/****************************************************************************************************************/
// 解决方案一 单例模式的经典实现
public class Singleton {
    // 利用一个静态变量来记录Singleton的唯一实例。
    private static Singleton uniqueInstance;

    // 把构造器声明为私有的，只有Singleton类内才可以调用构造器。
    private Singleton() {
    }

    // 用getInstance()方法实例化对象，并返回这个实例。
    public static Singleton getInstance() {
        // 如果uniqueInstance是空的，表示还没有创建实例。
        if (uniqueInstance == null) {
            // 如果uniqueInstance是空的，我们就利用私有的构造器产生一个Singleton实例并把它赋值给uniqueInstance静态变量中。
            // 请注意，如果我们不需要这个实例，它就永远不会产生，这就是“延迟实例化”。
            uniqueInstance = new Singleton();
        }
        return uniqueInstance;
    }
}

/****************************************************************************************************************/
// 解决方案二 饿汉式单例模式实现
public class EagerSingleton {
     // 在静态初始化器中创建单例类的实例，保证了线程安全
    private static final EagerSingleton instance = new EagerSingleton();
    private EagerSingleton() { }

    public static EagerSingleton getInstance() {
        // 已经创建好了实例，直接返回
        return instance;
    }
}

/****************************************************************************************************************/
// 解决方案三 懒汉式单例模式实现
public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {
    }
    // 通过增加synchronized关键字到getInstance()方法中，我们迫使每个线程在进入这个方法之前，要先等候别的线程离开该方法。
    // 也就是说，不会有两个线程可以同时进入这个方法。
    synchronized public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

/****************************************************************************************************************/
// 双重检查锁定实现单例模式
public class LazySingleton {
    private volatile static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        //第一重判断
        if (instance == null) {
            //锁定代码块
            synchronized (LazySingleton.class) {
                //第二重判断
                if (instance == null) {
                    instance = new LazySingleton(); //创建单例实例
                }
            }
        }
        return instance;
    }
}

/****************************************************************************************************************/
// IoDH技术实现单例模式
// Initialization on Demand Holder
public class Singleton {
    private Singleton() {
    }

    private static class HolderClass {
            private final static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return HolderClass.instance;
    }
}

/****************************************************************************************************************/
// 应用实例的解决方案
public class Configuration{
    private static Configuration configurationInstance;
    private Configuration(){}
    private static Configuration getInstance(){
        if (configurationInstance == null) {
            configurationInstance = new Configuration();
        }
        return configurationInstance;
    }
}
