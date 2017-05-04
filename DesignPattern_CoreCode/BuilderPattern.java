/****************************************************************************************************************/
// 解决方案一 建造者模式的实现
public class Product {
	private String partA; // 定义部件，部件可以是任意类型，包括值类型和引用类型
	private String partB;
	private String partC;

    public void setPartA(String partA){
        this.partA = partA;
    }
    public void setPartB(String partB){
        this.partB = partB;
    }
    public void setPartC(String partC){
        this.partC = partC;
    }
    public String getPartA(){
        return this.partA;
    }
    public String getPartB(){
        return this.partB;
    }
    public String getPartC(){
        return this.partC;
    }
}

// 在抽象建造者类中定义了产品的创建方法和返回方法
public abstract class Builder {
    // 创建产品对象
	protected Product product = new Product();

	public abstract void buildPartA();
	public abstract void buildPartB();
	public abstract void buildPartC();

    // 返回产品对象
	public Product getResult() {
		return product;
	}
}

// 具体的建造者实现
public class ConcreteBuilder extends Builder {
    public void buildPartA() {
        product.setPartA("partA");
    }
    public void buildPartB() {
        product.setPartB("partB");
    }
    public void buildPartC() {
        product.setPartC("partC");
    }
}

// 隔离客户与创建过程，控制产品的创建过程
public class Director {
	private Builder builder;

    // 传入具体的建造者类型
	public Director(Builder builder) {
		this.builder = builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

    // 产品构建与组装方法
	public Product construct() {
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC();
		return builder.getResult();
	}
}

public class Client{
    public static void main(String[] args) {

        Builder builder = new ConcreteBuilder();
        Director director = new Director(builder);
        Product product = director.construct();
    }
}

/****************************************************************************************************************/
// 解决方案二 带配置文件的建造者模式的实现
public class Product {
	private String partA; // 定义部件，部件可以是任意类型，包括值类型和引用类型
	private String partB;
	private String partC;

    public void setPartA(String partA){
        this.partA = partA;
    }
    public void setPartB(String partB){
        this.partB = partB;
    }
    public void setPartC(String partC){
        this.partC = partC;
    }
    public String getPartA(){
        return this.partA;
    }
    public String getPartB(){
        return this.partB;
    }
    public String getPartC(){
        return this.partC;
    }
}

// 在抽象建造者类中定义了产品的创建方法和返回方法
public abstract class Builder {
    // 创建产品对象
	protected Product product = new Product();

	public abstract void buildPartA();
	public abstract void buildPartB();
	public abstract void buildPartC();

    // 返回产品对象
	public Product getResult() {
		return product;
	}
}

// 具体建造者 1 的实现
public class ConcreteBuilder1 extends Builder {
    public void buildPartA() {
        product.setPartA("partA1");
    }
    public void buildPartB() {
        product.setPartB("partB1");
    }
    public void buildPartC() {
        product.setPartC("partC1");
    }
}

// 具体建造者 2 的实现
public class ConcreteBuilder2 extends Builder {
    public void buildPartA() {
        product.setPartA("partA2");
    }
    public void buildPartB() {
        product.setPartB("partB2");
    }
    public void buildPartC() {
        product.setPartC("partC2");
    }
}

// 隔离客户与创建过程，控制产品的创建过程
public class Director {
	private Builder builder;

    // 传入具体的建造者类型
	public Director(Builder builder) {
		this.builder = builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

    // 产品构建与组装方法
	public Product construct() {
		builder.buildPartA();
		builder.buildPartB();
		builder.buildPartC();
		return builder.getResult();
	}
}

// 配置文件
<?xml version="1.0"?>
<config>
       <className>ConcreteBuilder1</className>
</config>

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import Java.io.*;
public class XMLUtil {
    // 该方法用于从XML配置文件中提取具体类类名，并返回一个实例对象
    public  static Object getBean() {
        try {
            // 创建文档对象
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("config.xml"));

            // 获取包含类名的文本节点
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            // 通过类名生成实例对象并将其返回
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
         } catch(Exception e) {
             e.printStackTrace();
             return null;
         }
     }
}

public class Client{
    public static void main(String[] args) {

        Builder builder;
        builder = (Builder)XMLUtil.getBean(); // 反射生成具体建造者对象
        Director director = new Director(builder);
        Product product = director.construct();
    }
}

/****************************************************************************************************************/
// 应用实例解决方案
public class Document {
	private String type;
	private String name;
	private String price;

    public void setType(String type){
        this.type = type;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setPrice(String price){
        this.price = price;
    }
    public String getType(){
        return this.type;
    }
    public String getName(){
        return this.name;
    }
    public String getPrice(){
        return this.price;
    }
}

// 在抽象建造者类中定义了文档的创建方法和返回方法
public abstract class Builder {
    // 创建文档对象
	protected Document document = new Document();

	public abstract void buildType();
	public abstract void buildName();
	public abstract void buildPrice();

    // 返回文档对象
	public Document getResult() {
		return document;
	}
}

// XML建造者的实现
public class XMLBuilder extends Builder {
    public void buildType() {
        product.setType("type: xml");
    }
    public void buildName() {
        product.setName("name: xml");
    }
    public void buildPrice() {
        product.setPrice("price: xml");
    }
}

// Json建造者的实现
public class JsonBuilder extends Builder {
    public void buildType() {
        product.setType("type: json");
    }
    public void buildName() {
        product.setName("name: json");
    }
    public void buildPrice() {
        product.setPrice("price: json");
    }
}

// 隔离客户与创建过程，控制文档的创建过程
public class Director {
	private Builder builder;

    // 传入具体的文档建造者类型
	public Director(Builder builder) {
		this.builder = builder;
	}

	public void setBuilder(Builder builder) {
		this.builder = builder;
	}

    // 文档构建与组装方法
	public Document construct() {
		builder.buildType();
		builder.buildName();
		builder.buildPrice();
		return builder.getResult();
	}
}

public class Client{
    public static void main(String[] args) {

        Builder builder = new XMLBuilder();
        Director director = new Director(builder);
        Document document = director.construct();
    }
}
