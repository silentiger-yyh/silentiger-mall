# 基于SpringCloudAlibaba商城系统

## 依赖版本选择

- JDK：1.8
- MySQL：5.7
- Spring Cloud：2021.0.5
- Spring Cloud Alibaba：2021.0.5.0
- Spring Boot：2.7.14
- mysql-connector-java：8.0.19
- spring-boot-starter-jdbc：3.2.0
- druid-spring-boot-starter：1.1.23
- mybatis-plus-boot-starter：3.4.3.4

## 开发环境搭建

### 父工程创建以及子工程demo

1. 创建maven父工程，只保留pom.xml文件和.gitignore文件即可，其他的全部删掉

   父工程完整依赖如下，后续如果需要其他依赖再继续添加，基础框架只需要这么些

   **注意dependencies和dependencyManagement的区别**

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
     xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
     <modelVersion>4.0.0</modelVersion>
     <parent>
       <groupId>org.springframework.boot</groupId>
       <artifactId>spring-boot-starter-parent</artifactId>
       <version>2.7.14</version>
       <relativePath/>
     </parent>
   
     <groupId>silentiger-mall</groupId>
     <artifactId>silentiger-mall-cloud</artifactId>
     <version>1.0-SNAPSHOT</version>
     <packaging>pom</packaging>
       <!-- 创建子模块之后加入-->
     <modules
       <module>mall-gateway</module>
       <module>mall-cloud-demo</module>
     </modules>
   
     <!--  父工程定义的属性子工程会继承-->
     <properties>
       <spring.boot.version>2.7.14</spring.boot.version>
       <springcloud.version>2021.0.5</springcloud.version>
       <springcloud.alibaba.version>2021.0.5.0</springcloud.alibaba.version>
       <mysql-connector-java.version>8.0.19</mysql-connector-java.version>
       <springboot-starter-jdbc.version>3.2.0</springboot-starter-jdbc.version>
       <druid-spring-boot-starter.version>1.1.23</druid-spring-boot-starter.version>
       <mybatis-plus-boot-starter.version>3.4.3.4</mybatis-plus-boot-starter.version>
       <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
       <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
     </properties>
   
     <!--
     如果父pom中是<dependencies>时，那么子pom会自动继承父pom依赖，不需要子pom去导入
     这里是所有springboot子模块公共依赖
     -->
     <dependencies>
       <!-- test -->
       <dependency>
         <groupId>org.springframework.boot</groupId>
         <artifactId>spring-boot-starter-test</artifactId>
         <version>${spring.boot.version}</version>
         <scope>test</scope>
       </dependency>
       <dependency>
         <groupId>org.junit.jupiter</groupId>
         <artifactId>junit-jupiter-api</artifactId>
         <version>5.9.2</version>
         <scope>test</scope>
       </dependency>
     <!--    配置中心-->
       <dependency>
         <groupId>com.alibaba.cloud</groupId>
         <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
       </dependency>
     <!--    服务发现-->
       <dependency>
         <groupId>com.alibaba.cloud</groupId>
         <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
       </dependency>
       <!--   解决报错Add a spring.config.import=nacos: property to your configuration.
       If configuration is not required add spring.config.import=optional:nacos: instead.
       To disable this check, set spring.cloud.nacos.config.import-check.enabled=false.
       在springboot2.4+中无法使用bootstrap，这个注解可以重新启用
       -->
       <dependency>
         <groupId>org.springframework.cloud</groupId>
         <artifactId>spring-cloud-starter-bootstrap</artifactId>
       </dependency>
   
       <!-- commons-lang3 -->
       <dependency>
         <groupId>org.apache.commons</groupId>
         <artifactId>commons-lang3</artifactId>
         <version>3.11</version>
       </dependency>
       <dependency>
         <groupId>org.projectlombok</groupId>
         <artifactId>lombok</artifactId>
         <version>1.18.16</version>
       </dependency>
   
     </dependencies>
   
     <!--
      如果父pom中是<dependencyManagement>
      则子pom不会自动继承父pom的依赖，除非子pom中声明，声明需要groupId和artifactId，无需给到version
      第二种方法是用来约束子pom，若要使用，需声明
      -->
     <dependencyManagement>
       <dependencies>
         <!-- spring boot starters -->
         <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-web</artifactId>
           <version>${spring.boot.version}</version>
         </dependency>
         <dependency>
           <groupId>org.springframework.cloud</groupId>
           <artifactId>spring-cloud-dependencies</artifactId>
           <version>${springcloud.version}</version>
           <type>pom</type>
           <scope>import</scope>
         </dependency>
         <dependency>
           <groupId>com.alibaba.cloud</groupId>
           <artifactId>spring-cloud-alibaba-dependencies</artifactId>
           <version>${springcloud.alibaba.version}</version>
           <type>pom</type>
           <scope>import</scope>
         </dependency>
         <dependency>
           <groupId>com.alibaba.cloud</groupId>
           <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
           <version>${springcloud.alibaba.version}</version>
           <type>pom</type>
           <scope>import</scope>
         </dependency>
         <dependency>
           <groupId>com.alibaba.cloud</groupId>
           <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
           <version>${springcloud.alibaba.version}</version>
           <type>pom</type>
           <scope>import</scope>
         </dependency>
         <!-- mysql -->
         <dependency>
           <groupId>mysql</groupId>
           <artifactId>mysql-connector-java</artifactId>
           <version>${mysql-connector-java.version}</version>
         </dependency>
         <!--jdbc 数据库连接-->
         <dependency>
           <groupId>org.springframework.boot</groupId>
           <artifactId>spring-boot-starter-jdbc</artifactId>
           <version>${springboot-starter-jdbc.version}</version>
         </dependency>
         <!-- druid -->
         <dependency>
           <groupId>com.alibaba</groupId>
           <artifactId>druid-spring-boot-starter</artifactId>
           <version>${druid-spring-boot-starter.version}</version>
         </dependency>
         <!-- mybatis plus -->
         <dependency>
           <groupId>com.baomidou</groupId>
           <artifactId>mybatis-plus-boot-starter</artifactId>
           <version>${mybatis-plus-boot-starter.version}</version>
         </dependency>
       </dependencies>
     </dependencyManagement>
   
     <build>
       <plugins>
         <plugin>
           <artifactId>maven-site-plugin</artifactId>
           <configuration>
             <locales>en,fr</locales>
           </configuration>
         </plugin>
       </plugins>
     </build>
   </project>
   
   ```

2. maven创建springboot子工程

   完整工程目录和pom依赖如下

   ![image-20231215201407836](docs/images/image-20231215201407836.png)

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <parent>
           <groupId>silentiger-mall</groupId>
           <artifactId>silentiger-mall-cloud</artifactId>
           <version>1.0-SNAPSHOT</version>
       </parent>
   
       <artifactId>mall-cloud-demo</artifactId>
       <packaging>jar</packaging>
   
       <name>mall-cloud-demo</name>
   
       <properties>
       </properties>
   
       <dependencies>
           <!-- spring boot starters -->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-web</artifactId>
           </dependency>
           <!-- mysql -->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
           </dependency>
           <!--jdbc 数据库连接-->
           <dependency>
               <groupId>org.springframework.boot</groupId>
               <artifactId>spring-boot-starter-jdbc</artifactId>
           </dependency>
           <!-- druid -->
           <dependency>
               <groupId>com.alibaba</groupId>
               <artifactId>druid-spring-boot-starter</artifactId>
           </dependency>
           <!-- mybatis plus -->
           <dependency>
               <groupId>com.baomidou</groupId>
               <artifactId>mybatis-plus-boot-starter</artifactId>
           </dependency>
       </dependencies>
   </project>
   ```

   **注意：这里我创建了bootstrap.yml，在springboot2.4+默认不启用的，若要使用需要添加依赖**

   ```xml
   <!--   解决报错Add a spring.config.import=nacos: property to your configuration.
   If configuration is not required add spring.config.import=optional:nacos: instead.
   To disable this check, set spring.cloud.nacos.config.import-check.enabled=false.
   在springboot2.4+中无法使用bootstrap，这个注解可以重新启用
   -->
   <dependency>
     <groupId>org.springframework.cloud</groupId>
     <artifactId>spring-cloud-starter-bootstrap</artifactId>
   </dependency>
   ```

3. 下载并启动nacos服务

   官方文档：https://nacos.io/zh-cn/docs/v2/quickstart/quick-start.html

   启动完成之后在浏览器使用http://serverip:8848/nacos访问

4. 在nacos配置中心定义命名空间

   命名空间可以用于区分不同的系统、环境（dev、uat、prod）等，名称和描述自定义即可

   ![image-20231215202304891](docs/images/image-20231215202304891.png)

5. 在nacos配置中心->配置管理->配置列表中创建yaml配置文件

   先选择刚才创建的命名空间，然后创建配置

   ![image-20231215202520440](docs/images/image-20231215202520440.png)

   ![image-20231215202636792](docs/images/image-20231215202636792.png)

   **注意，这里的DataID是和服务名称挂钩的，比如服务名称拟定为mall-cloud-demo，则这里的DataID可起为mall-cloud-demo、mall-cloud-demo.yaml、mall-cloud-demo-dev.yaml，当然，这里的dev也是需要和bootstrap中配置的匹配，nacos才能正确读取到配置信息**

   yaml配置示例：

   ```yaml
   server:
     port: 8080
   
   spring:
     datasource:
       url: jdbc:mysql://192.168.83.142:3306/silentiger-mall-nacos?charset=UTF-8&locale=en_US.UTF-8&timezone=UTC-8
       username: root
       password: 123456
       driver-class-name: com.mysql.cj.jdbc.Driver
   ```

6. 配置bootstrap.yml

   前面说的依赖一定要加，否则bootstap无法生效

   ```yml
   spring:
     application:
       name: mall-cloud-demo
     profiles:
       active: dev  
     cloud:
       nacos:
         config:
           # 命名空间ID（不是名称）
           namespace: 7274eeb1-8473-45b7-96e2-2813da4e959f
           # 配置中心的地址
           server-addr: nacos:8848
           # 配置文件prefix
           prefix: ${spring.application.name}
           # 配置文件的格式
           file-extension: yaml
           # 配置文件的环境
           group: DEFAULT_GROUP
         discovery:
           # 注册中心地址
           server-addr: nacos:8848
           # 所属命名空间id，用于区分开发、测试、生产
           namespace: 7274eeb1-8473-45b7-96e2-2813da4e959f
           # 设置为false可以只发现其他服务但不注册自身，用于本地代码调试
           register-enabled: true
   ```

7. 启动demo

   ![image-20231215203335396](docs/images/image-20231215203335396.png)

   ![image-20231215203355746](docs/images/image-20231215203355746.png)

### 网关gateway

1. 创建子工程

   完整pom如下：

   ```xml
   <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
       <modelVersion>4.0.0</modelVersion>
       <parent>
           <groupId>silentiger-mall</groupId>
           <artifactId>silentiger-mall-cloud</artifactId>
           <version>1.0-SNAPSHOT</version>
       </parent>
   
       <artifactId>mall-gateway</artifactId>
       <packaging>jar</packaging>
   
       <name>mall-gateway</name>
   
       <properties>
       </properties>
   
       <dependencies>
           <!-- gateway 路由网关 -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-gateway</artifactId>
           </dependency>
           <!-- 整合nacos需要添加负载均衡依赖,否则在通过网关请求接口时会报错503 -->
           <dependency>
               <groupId>org.springframework.cloud</groupId>
               <artifactId>spring-cloud-starter-loadbalancer</artifactId>
           </dependency>
   
       </dependencies>
   </project>
   ```

   完整bootstrap如下：（与demo一样，只是name变了而已！！！）

   ```yml
   spring:
     application:
       name: mall-gateway
     profiles:
       active: dev
     cloud:
       nacos:
         config:
           # 命名空间
           namespace: 7274eeb1-8473-45b7-96e2-2813da4e959f
           # 配置中心的地址
           server-addr: nacos:8848
           # 配置文件prefix
           prefix: ${spring.application.name}
           # 配置文件的格式
           file-extension: yaml
           # 配置文件的环境
           group: DEFAULT_GROUP
         discovery:
           # 注册中心地址
           server-addr: nacos:8848
           # 所属命名空间id，用于区分开发、测试、生产
           namespace: 7274eeb1-8473-45b7-96e2-2813da4e959f
           # 设置为false可以只发现其他服务但不注册自身，用于本地代码调试
           register-enabled: true
   ```

   配置中心mall-gateway.yaml配置如下：

   ```yaml
   server:
     port: 9999
   spring:
     cloud:
       gateway:
         discovery:
           locator:
             enabled: false
         routes:
           # 采用自定义路由 ID,可以添加多个路由
           - id: mall-cloud-demo
             #lb 表示从 nacos 中按照名称获取微服务,并遵循负载均衡策略，user-service 对应微服务应用名
             uri: lb://mall-cloud-demo
             # 断言，路径匹配
             predicates:
               - Path=/mall-cloud-demo/**
             filters:
               - StripPrefix=1       # 使用过滤器 
   
   
   # 
   # - StripPrefix=1: 以http://localhost:9999/mall-demo/demo/time为例
     # 若无StripPrefix，网关转发到微服务的请求不会过滤掉前缀mall-demo
     # 若有StripPrefix，网关转发到微服务的请求w'w'w会过滤掉前缀mall-demo，=1代表过滤掉一个前缀
   ```

   **关于filters的过滤器说明：**

   1. StripPrefix过滤器

      ```yaml
      filters:
       - StripPrefix=1       # 使用过滤器 
      ```

      - 若无StripPrefix，网关转发到微服务的请求不会过滤掉前缀mall-demo

      - 若有StripPrefix，网关转发到微服务的请求w'w'w会过滤掉前缀mall-demo，=1代表过滤掉一个前缀

   2. PrefixPath 过滤器

      - 作用： 它的作用和`StripPrefix`正相反，是在URL路径前面添加一部分的前缀

      ```yaml
      filters:
        - PrefixPath=/lbs
      ```

      这将会把`/lbs`添加到路由`prefixpath_route`匹配到的所有请求的路径的前面。

      所以对`/hello`的请求将会被发送到`/lbs/hello`。

### gateway聚合knife4j（swagger2规范）

#### **第一步**：在子模块mall-cloud-demo中添加依赖

这里没有写版本号，因为版本号在父工程的pom中指定了，这里用的是4.3.0版本

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi2-spring-boot-starter</artifactId>
</dependency>
```

#### **第二步**：在nacos配置中心mall-cloud-demo.yaml中添加knife4j配置

```yaml
# knife4j
knife4j:
  enable: true
  openapi:
    title: Knife4j官方文档
    description: "`我是测试`,**你知道吗9**"
    email: xiaoymin@foxmail.com
    concat: 八一菜刀
    url: https://docs.xiaominfo.com
    version: v4.0
    license: Apache 2.0
    license-url: https://stackoverflow.com/
    terms-of-service-url: https://stackoverflow.com/
    group:
      test1:
        group-name: default  # 注意这里一定要是default分组
        api-rule: package
        api-rule-resources:
          - org.gateway.controller
```

**注意：**对于分组名称，必须是**default**，因为在后续的gateway聚合方式中，采用的是discovery，也就是服务发现的方式，官方也说明了这种方式的分组名称必须是default

#### **第三步**：写测试接口，并启动服务，打开mall-cloud-demo服务的接口文档查看是否配置成功

```java
@RestController
@RequestMapping("/demo")
@Api(tags = "测试controller")
public class DemoController {
    @GetMapping("/time")
    @ApiOperation("测试接口")
    public String getTime() {
        return String.valueOf(DateUtil.getTimeNow());
    }
}
```

访问 http://localhost:8080/doc.html（注意：这里访问的是demo服务，不是网关）

![image-20231216153736618](docs/images/image-20231216153736618.png)

#### 第四步（聚合1）：在gateway网关pom中添加依赖

两个版本都是4.3.0，在父工程的pom中指定了

```xml
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-openapi2-spring-boot-starter</artifactId>
</dependency>
<dependency>
    <groupId>com.github.xiaoymin</groupId>
    <artifactId>knife4j-gateway-spring-boot-starter</artifactId>
</dependency>
```

#### 第五步（聚合2）：在nacos配置中心的mall-gateway.yaml中添加knife4j配置

```yaml
knife4j:
  gateway:
    # ① 第一个配置，开启gateway聚合组件
    enabled: true
    # ② 第二行配置，设置聚合模式采用discover服务发现的模式
    strategy: discover
    discover:
      # ③ 第三行配置，开启discover模式
      enabled: true
      # ④ 第四行配置，聚合子服务全部为Swagger2规范的文档
      version: swagger2
```

这里有个点需要非常注意的，在配置gateway路由ID的时候，为了不给自己挖坑，把ID配置成服务名称，然后断言中也配置成服务名称，因为gateway聚合接口时需要调用微服务的  /v2/api-docs  接口获取服务接口信息，在调接口的时候需要指定服务，也就是需要添加接口前缀，这个前缀必须要和断言中配置的能匹配上才能调通接口，所以为了不给自己挖坑就配置一样的吧。

当然，也不是绝对的，因为这个前缀时可以通过一些方法获取的，可直接拿到断言中配置的Path信息，但是这样会稍显麻烦。

```yaml
spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: false
      routes:
        # 采用自定义路由 ID
        - id: mall-cloud-demo
          #lb 表示从 nacos 中按照名称获取微服务,并遵循负载均衡策略，user-service 对应微服务应用名
          uri: lb://mall-cloud-demo
          # 断言，路径匹配
          predicates:
            - Path=/mall-cloud-demo/**
          filters:
            - StripPrefix=1       # 使用过滤器 
```

#### 第六步（聚合3）：在gateway中添加 /swagger-resources接口

在访问gateway的接口文档中心的时候，会先调用/swagger-resources获取资源信息，也就是左上角的下拉菜单选项。

![image-20231216160101474](docs/images/image-20231216160101474.png)

1. 先创建一个`Knife4jResourceProvider`

   ```java
   
   import lombok.RequiredArgsConstructor;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.beans.factory.annotation.Value;
   import org.springframework.cloud.client.discovery.DiscoveryClient;
   import org.springframework.cloud.gateway.route.RouteLocator;
   import org.springframework.stereotype.Component;
   import org.springframework.util.CollectionUtils;
   import springfox.documentation.swagger.web.SwaggerResource;
   import springfox.documentation.swagger.web.SwaggerResourcesProvider;
   
   import java.util.ArrayList;
   import java.util.List;
   
   /**
    * description: 这里就是从路由配置中获取各个服务的路由信息
    * author: silentiger@yyh
    * date: 2023-12-16
    */
   @Component
   @RequiredArgsConstructor
   public class Knife4jResourceProvider implements SwaggerResourcesProvider {
       // swagger2默认的url后缀
       private static final String SWAGGER2_URL = "/v2/api-docs";
       // 路由定位器
       private final RouteLocator routeLocator;
       // 网关应用名称
       @Value("${spring.application.name}")
       private String gatewayName;
       // nacos客户端
       @Autowired
       private DiscoveryClient discoveryClient;
       /**
        * 获取 Swagger 资源
        *    获取条件：所有配置的网关路由服务 - 排除网关地址 - 未注册到nacos的服务或者nacos存在异常的服务
        */
       @Override
       public List<SwaggerResource> get() {
           List<SwaggerResource> resources = new ArrayList<>();
           List<String> routeHosts = new ArrayList<>();
           // 1. 获取路由Uri 中的Host=> 服务注册则为服务名=> app-service001
           routeLocator.getRoutes()
                   .filter(route -> route.getId() != null)
                   //过滤掉网关的文档信息
                   .filter(route -> !gatewayName.equals(route.getId()))
                   //根据服务状态注入api文档信息
                   .filter(route -> !CollectionUtils.isEmpty(discoveryClient.getInstances(route.getUri().getHost())))
                   .subscribe(route -> routeHosts.add(route.getId()));   // 获取routeID作为"/v2/api-docs"的前缀
           // 2. 创建自定义资源
           for (String routeHost : routeHosts) {
               String serviceUrl = "/" + routeHost + SWAGGER2_URL; // 后台访问添加服务名前缀
               SwaggerResource swaggerResource = new SwaggerResource(); // 创建Swagger 资源
               swaggerResource.setUrl(serviceUrl); // 设置访问地址
               swaggerResource.setName(routeHost); // 设置名称
               swaggerResource.setSwaggerVersion("2.0.0");
               resources.add(swaggerResource);
           }
           return resources;
       }
   }
   ```

2. 创建接口 `Knife4jResourceController`

   ```java
   import lombok.RequiredArgsConstructor;
   import org.gateway.config.knife4j.Knife4jResourceProvider;
   import org.springframework.http.HttpStatus;
   import org.springframework.http.ResponseEntity;
   import org.springframework.web.bind.annotation.RequestMapping;
   import org.springframework.web.bind.annotation.RestController;
   import springfox.documentation.swagger.web.SwaggerResource;
   
   import java.util.List;
   
   @RestController
   @RequestMapping("/swagger-resources")
   @RequiredArgsConstructor
   public class Knife4jResourceController {
       private final Knife4jResourceProvider knife4jResourceProvider;
       @RequestMapping
       public ResponseEntity<List<SwaggerResource>> swaggerResources() {
           return new ResponseEntity<>(knife4jResourceProvider.get(), HttpStatus.OK);
       }
   }
   ```

   #### 第七步：访问网关查看

   http://gatewayIp:port/doc.html

   最终效果见第六步图片

### 整合Oauth2

在mall-auth服务中整合

oauth2根据使用场景不同，分成了4种模式

- 授权码模式（authorization code）
- 简化模式（implicit）
- 密码模式（resource owner password credentials）
- 客户端模式（client credentials）

在项目中我们通常使用授权码模式，也是四种模式中最复杂的，通常网站中经常出现的微博，qq第三方登录，都会采用这个形式。

Oauth2授权主要由两部分组成：

- Authorization server：认证服务
- Resource server：资源服务

在实际项目中以上两个服务可以在一个服务器上，也可以分开部署。下面结合spring boot来说明如何使用。

#### 准备工作：建数据库表

客户端信息可以存储在内存、redis和数据库。在实际项目中通常使用redis和数据库存储。本文采用数据库。表及字段说明参照：[Oauth2数据库表说明](https://andaily.com/spring-oauth-server/db_table_description.html) 。

```sql
DROP TABLE IF EXISTS `clientdetails`;
DROP TABLE IF EXISTS `oauth_access_token`;
DROP TABLE IF EXISTS `oauth_approvals`;
DROP TABLE IF EXISTS `oauth_client_details`;
DROP TABLE IF EXISTS `oauth_client_token`;
DROP TABLE IF EXISTS `oauth_refresh_token`;

CREATE TABLE `clientdetails` (
  `appId` varchar(128) NOT NULL,
  `resourceIds` varchar(256) DEFAULT NULL,
  `appSecret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `grantTypes` varchar(256) DEFAULT NULL,
  `redirectUrl` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additionalInformation` varchar(4096) DEFAULT NULL,
  `autoApproveScopes` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`appId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `oauth_access_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  `authentication` blob,
  `refresh_token` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(128) NOT NULL,
  `resource_ids` varchar(256) DEFAULT NULL,
  `client_secret` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(256) DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) DEFAULT NULL,
  `authorities` varchar(256) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) DEFAULT NULL,
  `autoapprove` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_client_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication_id` varchar(128) NOT NULL,
  `user_name` varchar(256) DEFAULT NULL,
  `client_id` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`authentication_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `oauth_code`;
CREATE TABLE `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `oauth_refresh_token` (
  `token_id` varchar(256) DEFAULT NULL,
  `token` blob,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 为了测试方便先插入一条客户端信息。
INSERT INTO `oauth_client_details` VALUES ('dev', '', 'dev', 'app', 'password,client_credentials,authorization_code,refresh_token', 'http://www.baidu.com', '', 3600, 3600, '{\"country\":\"CN\",\"country_code\":\"086\"}', 'false');

-- 用户、权限、角色用到的表如下:
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `role`;
DROP TABLE IF EXISTS `user_role`;
DROP TABLE IF EXISTS `role_permission`;
DROP TABLE IF EXISTS `permission`;

CREATE TABLE `user` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`username` varchar(255) NOT NULL,
`password` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `role` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`name` varchar(255) NOT NULL,
PRIMARY KEY (`id`) 
);
CREATE TABLE `user_role` (
`user_id` bigint(11) NOT NULL,
`role_id` bigint(11) NOT NULL
);
CREATE TABLE `role_permission` (
`role_id` bigint(11) NOT NULL,
`permission_id` bigint(11) NOT NULL
);
CREATE TABLE `permission` (
`id` bigint(11) NOT NULL AUTO_INCREMENT,
`url` varchar(255) NOT NULL,
`name` varchar(255) NOT NULL,
`description` varchar(255) NULL,
`pid` bigint(11) NOT NULL,
PRIMARY KEY (`id`) 
);

INSERT INTO user (id, username, password) VALUES (1,'user','e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO user (id, username , password) VALUES (2,'admin','e10adc3949ba59abbe56e057f20f883e'); 
INSERT INTO role (id, name) VALUES (1,'USER');
INSERT INTO role (id, name) VALUES (2,'ADMIN');
INSERT INTO permission (id, url, name, pid) VALUES (1,'/**','',0);
INSERT INTO permission (id, url, name, pid) VALUES (2,'/**','',0);
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);
```



#### 第一步：添加依赖

```xml
<!-- springSecurity-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<!-- Oauth2-->
<dependency>
    <groupId>org.springframework.security.oauth</groupId>
    <artifactId>spring-security-oauth2</artifactId>
    <version>2.5.0.RELEASE</version>
</dependency>
```

#### 第二步：添加认证服务端配置

```java
package org.gateway.config.oauth2;

import org.gateway.config.exception.CustomWebResponseExceptionTranslator;
import org.gateway.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * 认证服务端配置
 * @Author silentiger@yyh
 * @Date 2023-12-17 14:10:01
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    /**
     * 注入权限验证控制器 来支持 password grant type
     */
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomWebResponseExceptionTranslator webResponseExceptionTranslator;

    /**
     * 注入userDetailsService，开启refresh_token需要用到
     */
    @Autowired
    UserServiceImpl userDetailService;
    /**
     * 设置保存token的方式，一共有五种，这里采用数据库的方式
     */
    @Autowired
    private TokenStore tokenStore;
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()")
                .checkTokenAccess("permitAll()")
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        //开启密码授权类型
        endpoints.authenticationManager(authenticationManager);
        //配置token存储方式
        endpoints.tokenStore(tokenStore);
        //自定义登录或者鉴权失败时的返回信息
        endpoints.exceptionTranslator(webResponseExceptionTranslator);
        //要使用refresh_token的话，需要额外配置userDetailsService
        endpoints.userDetailsService(userDetailService);
    }
}
```

#### 第三步：资源服务器配置

```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
/**
 * 资源服务器配置
 *
 * @Author silentiger@yyh
 * @Date 2023-12-17 14:14:58
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 这里设置需要token验证的url
     * 可以在WebSecurityConfigurerAdapter中排除掉，
     * 对于相同的url，如果二者都配置了验证
     * 则优先进入ResourceServerConfigurerAdapter,进行token验证。而不会进行
     * WebSecurityConfigurerAdapter 的 basic auth或表单认证。
     */
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.requestMatchers()
                .antMatchers("/no")
//                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/no")
//                .antMatchers("/**")
                .authenticated();
    }
}
```

#### 第四步：SpringSecurity配置

```java
import org.gateway.config.exception.CustomWebResponseExceptionTranslator;
import org.gateway.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.Objects;

/**
 * SpringSecurity配置文件,支持password模式要配置AuthenticationManager
 * @Author silentiger@yyh
 * @Date 2023-12-17 13:52:58
 */

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userDetailService;

    @Autowired
    private CustomWebResponseExceptionTranslator customWebResponseExceptionTranslator;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //校验用户
        auth.userDetailsService(userDetailService)
                .passwordEncoder(new PasswordEncoder() {
                                     //对密码进行加密
                                     @Override
                                     public String encode(CharSequence charSequence) {
//                    System.out.println(charSequence.toString());
                                         return DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                                     }

                                     //对密码进行判断匹配
                                     @Override
                                     public boolean matches(CharSequence charSequence, String s) {
                                         String encode = DigestUtils.md5DigestAsHex(charSequence.toString().getBytes());
                                         return s.equals(encode);
                                     }
                                 }
                );
    }

    /**
     * anyRequest          |   匹配所有请求路径
     * access              |   SpringEl表达式结果为true时可以访问
     * anonymous           |   匿名可以访问
     * denyAll             |   用户不能访问
     * fullyAuthenticated  |   用户完全认证可以访问（非remember-me下自动登录）
     * hasAnyAuthority     |   如果有参数，参数表示权限，则其中任何一个权限可以访问
     * hasAnyRole          |   如果有参数，参数表示角色，则其中任何一个角色可以访问
     * hasAuthority        |   如果有参数，参数表示权限，则其权限可以访问
     * hasIpAddress        |   如果有参数，参数表示IP地址，如果用户IP和参数匹配，则可以访问
     * hasRole             |   如果有参数，参数表示角色，则其角色可以访问
     * permitAll           |   用户可以任意访问
     * rememberMe          |   允许通过remember-me登录的用户访问
     * authenticated       |   用户登录后可访问
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//            .requestMatchers()
//            .antMatchers("/oauth/**","/user/login")
//            .and()
//            .authorizeRequests()
//            .antMatchers("/oauth/**").authenticated()
//            .and()
//            .formLogin().loginPage( "/login" );
        http
                // CRSF禁用，因为不使用session
                .csrf().disable()
                // 认证失败处理类
//            .exceptionHandling().authenticationEntryPoint(customWebResponseExceptionTranslator).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                // 对于登录login 验证码captchaImage 允许匿名访问
                .antMatchers("/**/login", "/captchaImage").permitAll()
                .antMatchers(
                        HttpMethod.GET,
                        "/*.html",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
//            .antMatchers("/profile/**").anonymous()
//            .antMatchers("/common/download**").anonymous()
//            .antMatchers("/common/download/resource**").anonymous()
//            .antMatchers("/swagger-ui.html").anonymous()
                .antMatchers("/swagger-resources/**").anonymous()
//            .antMatchers("/webjars/**").anonymous()
                .antMatchers("/*/api-docs").anonymous()
//            .antMatchers("/druid/**").anonymous()
                //Demo目录下的请求不需要鉴权认证
                .antMatchers("/demo/**").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return Objects.equals(charSequence.toString(), s);
            }
        };
    }
}
```

#### 第五步：还需要UserDetailSerivce

```java
@Service
public class UserServiceImpl UserDetailsService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //查数据库
        User user = userMapper.loadUserByUsername( username );
        if (null != user) {
            List<Role> roles = roleMapper.getRolesByUserId( user.getId() );
            user.setAuthorities( roles );
        }
        return user;
    }
}
```

#### 第六步：其他，entity和mapper、异常类、token存储方式bean

```java
// entity
@Data
public class Role  implements GrantedAuthority {

    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
@Data
public class RolePermission {

    private String url;
    private String roleName;

}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails, Serializable {

    private Long id;
    private String username;
    private String password;
    private List<Role> authorities;

    /**
     * 用户账号是否过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 用户账号是否被锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 用户密码是否过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 用户是否可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
// mapper
@Mapper
public interface PermissionMapper extends BaseMapper<RolePermission> {

    @Select( "SELECT A.NAME AS roleName,C.url FROM role AS A LEFT JOIN role_permission B ON A.id=B.role_id LEFT JOIN permission AS C ON B.permission_id=C.id" )
    List<RolePermission> getRolePermissions();

}

@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select( "SELECT A.id,A.name FROM role A LEFT JOIN user_role B ON A.id=B.role_id WHERE B.user_id=${userId}" )
    List<Role> getRolesByUserId(@Param("userId") Long userId);

}

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select( "select id , username , password from user where username = #{username}" )
    User loadUserByUsername(@Param("username") String username);

}
//  异常类
@Component
public class CustomWebResponseExceptionTranslator implements WebResponseExceptionTranslator {
    @Override
    public ResponseEntity translate(Exception e) {

//        InternalAuthenticationServiceException oAuth2Exception = (InternalAuthenticationServiceException) e;
        return ResponseEntity
                .status(200)
                .body(e.getMessage());
//                .body(new InternalAuthenticationServiceException(oAuth2Exception.getMessage()));
    }
}

// token存储方式bean
@Configuration
public class Beans {
    /**
     * 数据源
     */
    @Autowired
    private DataSource dataSource;

    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore( dataSource );
    }
}

```

#### 第七步：测试

1. 密码模式

   http://127.0.0.1:8070/oauth/token?username=admin&password=123456&grant_type=password&client_id=dev&client_secret=dev

   ![image-20231217221626702](docs/images/image-20231217221626702.png)

2. 客户端模式

   http://127.0.0.1:8070/oauth/token?grant_type=client_credentials&client_id=dev&client_secret=dev

   ![image-20231217221543780](docs/images/image-20231217221543780.png)

3. 授权码模式

4. 简化模式

