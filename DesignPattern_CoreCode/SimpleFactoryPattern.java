/****************************************************************************************************************/
// 解决方案一 简单工厂模式的实现
public abstract class Product {
    public void methodSame() {
        // 所有产品类的公共方法
    }
    // 声明产品的抽象方法
    public abstract void methodDiff();
}

public class ConcreteProductA extends Product {
    public void methodDiff() {
        // A类型产品特有的方法
    }
}

public class ConcreteProductB extends Product {
    public void methodDiff() {
        // B类型产品特有的方法
    }
}

public class Factory {
    // 静态工厂方法
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProductA();
            // 初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProductB();
            // 初始化设置product
        }
        return product;
    }
}

public class Client {
    public static void main(String args[]) {
        Product product;
        product = Factory.getProduct("A"); // 通过工厂类创建产品对象
        product.methodSame();
        product.methodDiff();
    }
}

/****************************************************************************************************************/
// 解决方案二 简化的简单工厂模式的实现
public abstract class Product {
    public void methodSame() {
        // 所有产品类的公共方法
    }
    // 声明抽象业务方法
    public abstract void methodDiff();
    // 静态工厂方法
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProductA();
            // 初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProductB();
            // 初始化设置product
        }
        return product;
    }
}

public class ConcreteProductA extends Product {
    public void methodDiff() {
        // A类型产品特有的方法
    }
}

public class ConcreteProductB extends Product {
    public void methodDiff() {
        // B类型产品特有的方法
    }
}

public class Client {
    public static void main(String args[]) {
        Product product;
        product = Product.getProduct("A"); // 通过工厂类创建产品对象
        product.methodSame();
        product.methodDiff();
    }
}

/****************************************************************************************************************/
// 解决方案三 改进的简单工厂模式的实现
public abstract class Product {
    public void methodSame() {
        // 所有产品类的公共方法
    }
    // 声明抽象业务方法
    public abstract void methodDiff();
}

public class ConcreteProductA extends Product {
    public void methodDiff() {
        // A类型产品特有的方法
    }
}

public class ConcreteProductB extends Product {
    public void methodDiff() {
        // B类型产品特有的方法
    }
}

public class Factory {
    // 静态工厂方法
    public static Product getProduct(String arg) {
        Product product = null;
        if (arg.equalsIgnoreCase("A")) {
            product = new ConcreteProductA();
            // 初始化设置product
        }
        else if (arg.equalsIgnoreCase("B")) {
            product = new ConcreteProductB();
            // 初始化设置product
        }
        return product;
    }
}

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import java.io.*;

public class XMLUtil {
    // 该方法用于从XML配置文件中提取产品类型，并返回类型名
    public static String getProductType() {
        try {
            // 创建文档对象
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            Document doc;
            doc = builder.parse(new File("config.xml"));

            // 获取包含产品类型的文本节点
            NodeList nodeList = doc.getElementsByTagName("productType");
            Node classNode = nodeList.item(0).getFirstChild();
            String productType = classNode.getNodeValue().trim();
            return productType;
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class Client {
    public static void main(String args[]) {
        Product product;
        String productType = XMLUtil.getProductType(); // 读取配置文件中的参数
        product = Factory.getProduct(productType); // 创建产品对象
        product.methodSame();
        product.methodDiff();
    }
}

/****************************************************************************************************************/
// 应用实例的解决方案
public abstract class User {
	public void sameOperation() {
        System.out.println("user共同拥有的操作");
	}
	public abstract void diffOperation();
}

public class Employee extends User {
	public void diffOperation() {
        // employee特有的方法
	}
}

public class Administrator extends User {
	public void diffOperation() {
        // administrator特有的方法
	}
}

public class Manager extends User {
	public void diffOperation() {
        // manager特有的方法
	}
}

public class UserFactory {
	public static User getUser(int permission) {
		if(0 == permission) {
			return new Employee();
		} else if(1 == permission) {
			return new Manager();
		} else if(2 == permission) {
			return new Administrator();
		} else {
			return null;
		}
	}
}

public class UserDAO {
    public int findPermission(String userName, String userPassword) {
    	if("zhangsan" == userName && "123456" == userPassword) {
    		return 2;
    	} else {
    		return -1;
    	}
    }
}

public class Client {
	public static void main(String args[]) {
        try {
         	User user;
         	UserDAO userDao = new UserDAO();
         	int permission = userDao.findPermission("zhangsan", "123456");
         	user = UserFactory.getUser(permission);
         	user.sameOperation();
         	user.diffOperation();
         } catch(Exception e) {
         	System.out.println(e.getMessage());
         }
	}
}
