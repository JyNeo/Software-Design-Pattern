/****************************************************************************************************************/
// 解决方案一 抽象工厂模式的实现
public abstract class AbstractFactory {
    public abstract AbstractProductA createProductA(); // 工厂方法一
    public abstract AbstractProductB createProductB(); // 工厂方法二
}

public class ConcreteFactory1 extends AbstractFactory {
    // 工厂方法一
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    // 工厂方法二
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}

public class ConcreteFactory2 extends AbstractFactory {
    // 工厂方法一
    public AbstractProductA createProductA() {
        return new ConcreteProductA1();
    }

    // 工厂方法二
    public AbstractProductB createProductB() {
        return new ConcreteProductB1();
    }
}

public abstract class AbstractProductA{
    public void sameOperation();
}

public class ConcreteProductA1 extends AbstractProductA{
    public void sameOperation(){

    }
}

public class ConcreteProductA2 extends AbstractProductA{
    public void sameOperation(){

    }
}

public abstract class AbstractProductB{
    public void sameOperation();
}

public class ConcreteProductB1 extends AbstractProductA{
    public void sameOperation(){

    }
}

public class ConcreteProductB2 extends AbstractProductA{
    public void sameOperation(){

    }
}

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class XMLUtil {
    // 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean() {
        try {
            // 创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("config.xml"));

            // 获取包含类名的文本节点
            NodeList nodeList = doc.getElementsByTagName("className");
            Node classNode = nodeList.item(0).getFirstChild();
            String className = classNode.getNodeValue();

            // 通过类名生成实例对象并将其返回
            Class class = Class.forName(className);
            Object object = class.newInstance();
            return object;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

<?xml version="1.0"?>
<config>
    <className>ConcreteFactory1</className>
</config>

public class Client {
    public static void main(String args[]) {
        // 使用抽象层定义
        AbstractFactory factory;
        AbstractProductA abstractProductA;
        AbstractProductB abstractProductB;

        factory = (AbstractFactory)XMLUtil.getBean();
        abstractProductA = factory.createProductA();
        abstractProductB = factory.createProductB();
        abstractProductA.sameOperation();
        abstractProductB.sameOperation();
    }
}

/****************************************************************************************************************/
// 应用实例解决方案
public abstract class ChartFactory {
    public abstract Pie createPie(); // 工厂方法一
    public abstract Bar createBar(); // 工厂方法二
}

public class FlatStyleFactory extends ChartFactory {
    // 工厂方法一
    public Pie createPie() {
        return new FlatPie();
    }

    // 工厂方法二
    public Bar createBar() {
        return new FlatBar();
    }
}

public class CrystalStyleFactory extends ChartFactory {
    // 工厂方法一
    public Pie createPie() {
        return new CrystalPie();
    }

    // 工厂方法二
    public Bar createBar() {
        return new CrystalBar();
    }
}

public abstract class Pie{
    public void display();
}

public class FlatPie extends Pie{
    public void display(){

    }
}

public class CrystalPie extends Pie{
    public void display(){

    }
}

public abstract class Bar{
    public void display();
}

public class FlatBar extends Bar{
    public void display(){

    }
}

public class CrystalBar extends Bar{
    public void display(){

    }
}

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class XMLUtil {
    // 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public static Object getBean() {
        try {
            // 创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("config.xml"));

            // 获取包含类名的文本节点
            NodeList nodeList = doc.getElementsByTagName("className");
            Node classNode = nodeList.item(0).getFirstChild();
            String className = classNode.getNodeValue();

            // 通过类名生成实例对象并将其返回
            Class class = Class.forName(className);
            Object object = class.newInstance();
            return object;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

<?xml version="1.0"?>
<config>
    <className>FlatCrystalFactory</className>
</config>

public class CDrawer {
    public static void main(String args[]) {
        // 使用抽象层定义
        ChartFactory factory;
        Pie pie;
        Bar bar;

        factory = (ChartFactory)XMLUtil.getBean();
        pie = factory.createPie();
        bar = factory.createBar();
        pie.display();
        bar.display();
    }
}
