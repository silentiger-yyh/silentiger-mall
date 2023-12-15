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

## 开发框架搭建

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

   ![image-20231215201407836](images/image-20231215201407836.png)

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

   ![image-20231215202304891](images/image-20231215202304891.png)

5. 在nacos配置中心->配置管理->配置列表中创建yaml配置文件

   先选择刚才创建的命名空间，然后创建配置

   ![image-20231215202520440](images/image-20231215202520440.png)

   ![image-20231215202636792](images/image-20231215202636792.png)

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
           server-addr: 192.168.83.142:8848
           # 配置文件prefix
           prefix: ${spring.application.name}
           # 配置文件的格式
           file-extension: yaml
           # 配置文件的环境
           group: DEFAULT_GROUP
         discovery:
           # 注册中心地址
           server-addr: 192.168.83.142:8848
           # 所属命名空间id，用于区分开发、测试、生产
           namespace: 7274eeb1-8473-45b7-96e2-2813da4e959f
           # 设置为false可以只发现其他服务但不注册自身，用于本地代码调试
           register-enabled: true
   ```

7. 启动demo

   ![image-20231215203335396](images/image-20231215203335396.png)

   ![image-20231215203355746](images/image-20231215203355746.png)

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
           server-addr: 192.168.83.142:8848
           # 配置文件prefix
           prefix: ${spring.application.name}
           # 配置文件的格式
           file-extension: yaml
           # 配置文件的环境
           group: DEFAULT_GROUP
         discovery:
           # 注册中心地址
           server-addr: 192.168.83.142:8848
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
           - id: mall-cloud-demo-route
             #lb 表示从 nacos 中按照名称获取微服务,并遵循负载均衡策略，user-service 对应微服务应用名
             uri: lb://mall-cloud-demo
             # 断言，路径匹配
             predicates:
               - Path=/mall-demo/**
             filters:
               - StripPrefix=1       # 使用过滤器 
   
   
   # 关于filters的过滤器说明：
   # - StripPrefix=1: 以http://localhost:9999/mall-demo/demo/time为例
     # 若无StripPrefix，网关转发到微服务的请求不会过滤掉前缀mall-demo
     # 若有StripPrefix，网关转发到微服务的请求w'w'w会过滤掉前缀mall-demo，=1代表过滤掉一个前缀
   ```

   
