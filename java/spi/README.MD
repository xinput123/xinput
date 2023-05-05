## SPI机制

JDK提供的SPI(Service Provider Interface)机制，因为这个机制是针对厂商 或者插件的，也可以在一些框架的扩展中看到。其核心类java.util.ServiceLoader可以
在jdk1.8的文档中看到详细的介绍。虽然不太常见，但并不代表它不常用，恰恰相反，你无时无刻不在用它。

## 自定义SPI项目

### 1、项目结构

- 1、api 提供的接口
- 2、invoker 用来测试的主项目
- 3、brother-api、sister-api 两个厂商对interface的不同实现，所以他们会依赖于 api

### 2、api模块

只有一个接口，没有任何实现。

### 3、brother-api

#### 3-1、pom.xml

```
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>api</artifactId>
    <version>${project.version}</version>
</dependency>
```

规范的具体实现类必然要依赖规范接口

#### 3-2、com.spi.api.BrotherSay

```
public class BrotherSay implements Say {
    @Override
    public void say() {
        System.out.println("Brother say!");
    }
}
```

#### 3-3、resources\META-INF\services\com.xinput.spi.api.Say

```
com.xinput.spi.api.BrotherSay
```

> **每一个SPI接口都需要在自己项目的静态资源目录中声明一个services文件， 文件名为实现规范接口的类名全路径，在此例中便是com.spi.api.Say，在文件中， 则写上一行具体实现类的全路径，在此例中便是 com.spi.api.BrotherSay。**

### 4、sister-api模块与Brother-api模块相似，除了resources\META-INF\services\com.xinput.spi.api.Say 文件

```
com.xinput.spi.api.SisterSay
```

### 5、invoker模块

spi-demo 是我们自己的项目了。如果我们想使用 brother-api 的 Say 实现，是需要将其的依赖引入。

```
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>api</artifactId>
</dependency>

<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>brother-api</artifactId>
</dependency>
```

#### 5-1、主类

```
public class Main {
    public static void main(String[] args) {
        ServiceLoader<Say> printerLoader = ServiceLoader.load(Say.class);
        for (Say say : printerLoader) {
            say.say();
        }
    }
}
```

#### 5-2、如果想换成 sister-api实现，只需要更新依赖即可

```
<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>api</artifactId>
</dependency>

<dependency>
    <groupId>${project.groupId}</groupId>
    <artifactId>sister-api</artifactId>
</dependency>
```

## SPI的实际应用

#### 1、在mysql-connector-java-xxx.jar中发现了META-INF\services\java.sql.Driver文件，里面只有两行记录：

```
com.mysql.jdbc.Driver
com.mysql.fabric.jdbc.FabricMySQLDriver
```

- java.sql.Driver是一个规范接口，
- com.mysql.jdbc.Driver 和 com.mysql.fabric.jdbc.FabricMySQLDriver则是mysql-connector-java-xxx.jar对这个规范的实现接口。
