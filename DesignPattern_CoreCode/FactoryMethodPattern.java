/****************************************************************************************************************/
// 解决方案一 工厂方法模式的实现
public abstract class Product {
    public void sameOperation();
}

public class ConcreteProduct extends Product {
    public void sameOperation() {

    }
}

// 在抽象工厂中声明了工厂方法但并未实现工厂方法，具体产品对象的创建由其子类负责
public abstract class Factory {
    public Product factoryMethod();
}

// 具体工厂类实现了工厂方法，不同的具体工厂可以创建不同的具体产品
// 具体工厂类在实现工厂方法时除了创建具体产品对象之外
// 还可以负责产品对象的初始化工作以及一些资源和环境配置工作，例如连接数据库、创建文件等
public class ConcreteFactory implements Factory{
    public Product factoryMethod() {
        Product product = new ConcreteProduct();
        return product;
    }
}

// 客户端针对抽象工厂编程，可在运行时再指定具体工厂类
public class Client {
    public static void main(String args[]) {
        Factory factory;
        factory = new ConcreteFactory();
        Product product;
        product = factory.factoryMethod();
        product.sameOperation();
    }
}

/****************************************************************************************************************/
// 解决方案二 带配置文件的工厂方法模式的实现
public abstract class Product {
    public void sameOperation();
}

public class ConcreteProduct extends Product {
    public void sameOperation() {

    }
}

// 在抽象工厂中声明了工厂方法但并未实现工厂方法，具体产品对象的创建由其子类负责
public abstract class Factory {
    public Product factoryMethod();
}

// 具体工厂类实现了工厂方法，不同的具体工厂可以创建不同的具体产品
// 具体工厂类在实现工厂方法时除了创建具体产品对象之外
// 还可以负责产品对象的初始化工作以及一些资源和环境配置工作，例如连接数据库、创建文件等
public class ConcreteFactory implements Factory{
    public Product factoryMethod() {
        Product product = new ConcreteProduct();
        return product;
    }
}

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class XMLUtil {
    // 该方法用于从XML配置文件中提取具体产品类名，并返回一个实例对象
    public static Object getBean() {
        try {
            // 创建文档对象
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("config.xml"));

            // 获取包含产品类名的文本节点
            NodeList nodeList = doc.getElementsByTagName("productType");
            Node classNode = nodeList.item(0).getFirstChild();
            String productType = classNode.getNodeValue();

            // 通过类名生成实例对象并将其返回
            Class className = Class.forName(productType);
            Object object = className.newInstance();
            return object;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class Client {
    public static void main(String args[]) {
        Factory factory;
        factory = (Factory)XMLUtil.getBean();
        Product product;
        product = factory.factoryMethod();
        product.sameOperation();
    }
}

<!— config.xml -->
<?xml version="1.0"?>
<config>
    <className>ConcreteFactory</className>
</config>

/****************************************************************************************************************/
// 解决方案三 工厂方法的重载
public abstract class Product {
    public void sameOperation();
}

public class ConcreteProduct extends Product {
    public void sameOperation() {

    }
}

// 在抽象工厂中声明了工厂方法但并未实现工厂方法，具体产品对象的创建由其子类负责
public abstract class Factory {
    public Product factoryMethod();
    public Product factoryMethod(String arg);
    public Product factoryMethod(Object object);
}

public class ConcreteFactory implements Factory{
    public Product factoryMethod() {
        Product product = new ConcreteProduct();
        return product;
    }
    public Product factoryMethod(String arg) {
        Product product = new ConcreteProduct();
        return product;
    }
    public Product factoryMethod(Object object) {
        Product product = new ConcreteProduct();
        return product;
    }
}

// 客户端针对抽象工厂编程，可在运行时再指定具体工厂类
public class Client {
    public static void main(String args[]) {
        Factory factory;
        factory = new ConcreteFactory();
        Product product;
        product = factory.factoryMethod();
        product.sameOperation();
    }
}

/****************************************************************************************************************/
// 解决方案四 工厂方法的隐藏
public abstract class Product {
    public void sameOperation();
}

public class ConcreteProduct extends Product {
    public void sameOperation() {

    }
}

// 在工厂类中直接调用具体产品类的业务方法sameOperation()
public abstract class Factory {
    public abstract Product factoryMethod(); // 抽象方法
    public void sameOperation(){
        Product product = this.factoryMethod();
        product.sameOperation();
    }
}

public class ConcreteFactory implements Factory{
    public Product factoryMethod() {
        Product product = new ConcreteProduct();
        return product;
    }
}

public class Client {
    public static void main(String args[]) {
        Factory factory;
        factory = (Factory)XMLUtil.getBean();
        factory.sameOperation(); // 直接使用工厂对象来调用产品对象的业务方法
    }
}

/****************************************************************************************************************/
// 应用实例解决方案
// 在抽象产品类中声明了公共业务方法但并未实现，具体业务方法的实现由其子类负责
public abstract class Document {
    public void sameOperation();
}

public class HtmlDoc extends Document {
    public void sameOperation() {

    }
}

public class PdfDoc extends Document {
    public void sameOperation() {

    }
}

// 在抽象工厂中声明了工厂方法但并未实现工厂方法，具体产品对象的创建由其子类负责
public abstract class DocCreator {
    public Product factoryMethod();
}

public class HtmlCreator implements DocCreator{
    public Document factoryMethod() {
        Document htmlDoc = new HtmlDoc();
        return htmlDoc;
    }
}

public class PdfCreator implements DocCreator{
    public Document factoryMethod() {
        Document pdfDoc = new PdfDoc();
        return pdfDoc;
    }
}

public class Client {
    public static void main(String args[]) {
        DocCreator docCreator1;
        docCreator1 = new HtmlCreator();
        Document document1;
        document1 = docCreator1.factoryMethod();
        document1.sameOperation();

        DocCreator docCreator2;
        docCreator2 = new PdfCreator();
        Document document2;
        document2 = docCreator2.factoryMethod();
        document2.sameOperation();
    }
}
