/****************************************************************************************************************/
// 解决方案一 对象适配器的实现
public abstract class Target {
    public abstract void request();
}

public class Adaptee {
    public void specificRequest(){
        // 适配者里的接口
    }
}

public class Adapter extends Target {
    private Adaptee adaptee; // 维持一个对适配者对象的引用

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.specificRequest(); // 转发调用
    }
}

public class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target target = new Adapter(adaptee);
        target.request();
    }
}

/****************************************************************************************************************/
// 解决方案二 带配置文件的对象适配器的实现
public abstract class Target {
    public abstract void request();
}

public class Adaptee {
    public void specificRequest() {
        // 适配者里的接口
    }
}

public class Adapter extends Target {
    private Adaptee adaptee; // 维持一个对适配者对象的引用

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.specificRequest(); // 转发调用
    }
}

// 配置文件
<?xml version="1.0"?>
<config>
    <className>adaptee</className>
</config>

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
            NodeList nl = doc.getElementsByTagName("className");
            Node classNode = nl.item(0).getFirstChild();
            String cName = classNode.getNodeValue();

            // 通过类名生成实例对象并将其返回
            Class c = Class.forName(cName);
            Object obj = c.newInstance();
            return obj;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

public class Client {
    public static void main(String[] args) {
        Target target;  // 针对抽象目标接口编程
        target = (Target)XMLUtil.getBean(); // 读取配置文件，反射生成对象
        target.request();
    }
}

/****************************************************************************************************************/
// 解决方案三 类适配器的实现
interface Target {
    public abstract void request();
}

public class Adaptee {
    public void specificRequest() {
        // 适配者里的接口
    }
}

public class Adapter extends Adaptee implements Target {
    public void request() {
        specificRequest();
    }
}

public class Client {
    public static void main(String[] args) {
        Target target = new Adapter();
        target.request();
    }
}

/****************************************************************************************************************/
// 应用实例解决方案
public abstract class MList {
    public abstract List<HashMap<String, String>> getData();
}

public class Mdata {
    public Cursor retrieveData(){
        // 返回Cursor数据类型
    }
}

public class MAdapter extends MList {
    Mdata mdata;

    public MAdapter(Mdata mdata) {
        this.mdata = mdata;
    }

    public List<HashMap<String, String>> getData() {
        Cursor cursor = mdata.retrieveData();
        // 构建List<HashMap<String, String>>，实现数据转换
        return;
    }
}

public class MViewer {
    Mdata mdata = new Mdata();
    MList mlist = new MAdapter(mdata);
    mlist.getData();
}
