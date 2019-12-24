## 自己动手实现的轻量级依赖注入框架V0.3版本

V0.3版本大量重构，对BeanFactory进行了抽象，使得项目结构更加合理。

该框架具有如下功能：
1. 可以通过注解标记组件类，被标记的类会被扫描并添加到容器中。
2. 解析组件类的依赖关系，进行依赖注入。
3. 解决组件类相互引用等异常情况。
4. 根据组件注解的描述信息，返回单例对象，或者返回新的对象。

该框架实现了jsr330规范和jsr250规范，实现了基本的依赖注入功能。

项目设计：

![design](images/design.png)

#### 更新日志:

1. 去除了V0.2版本中的Spring注解，仅支持jsr330和jsr250注解
2. 暂时删去了aop功能，后面会直接实现AspectJ注解支持
3. 重新设计了项目，尽可能的面向接口编程

##### todolist:
1. 完善测试用例
2. 支持AspectJ，实现AOP拦截


[博主博客](https://juejin.im/entry/599f901bf265da247779f0da)

#### jsr330和jsr250注解

[Spring核心——JSR250与资源控制](https://my.oschina.net/chkui/blog/1858734)


javax.annotation.security 包中有以下内容：

- @DeclareRoles 声明角色
- @DenyAll  拒绝所有角色
- @PermitAll  授权所有惧色
- @RolesAllowed  角色授权
- @RunAs 运行模式

JSR-330标准注解
https://maxwell.gitbook.io/way-to-architect/java-yu-yan/zhu-jie/chang-yong-zhu-jie/jsr-330biao-zhun-zhu-jie


Java依赖注入标准（JSR-330，Dependency Injection for Java）1.0 规范主要是面向依赖注入使用者，而对注入器实现、配置并未作详细要求。目前 Spring 、Guice 已经开始兼容该规范，JSR-299（Contexts and Dependency Injection for Java EE platform，参考实现 Weld ）在依赖注入上也使用该规范。JSR-330 规范并未按 JSR 惯例发布规范文档，只发布了规范 API 源码。
从Spring 3.0开始，Spring开始支持JSR-330标准的注解。这些注解和Spring注解扫描的方式是一直的，开发者只需要引入javax.inject即可。

<dependency>
    <groupId>javax.inject</groupId>
    <artifactId>javax.inject</artifactId>
    <version>xxx</version>
</dependency>


@Autowired  @Inject @Inject注解没有required属性，但是可以通过Java 8的Optional取代

@Component  @Named  JSR_330标准并没有提供复合的模型，只有一种方式来识别组件

@Scope("singleton") @Singleton  JSR-330默认的作用域类似Spring的prototype，然而，为何和Spring的默认保持一致，JSR-330标准中的Bean在Spring中默认也是单例的。如果要使用非单例的作用域，开发者应该使用Spring的@Scope注解。java.inject也提供一个@Scope注解，然而，这个注解仅仅可以用来创建自定义的作用域时才能使用。

@Qualifier  @Qualifier/@Named   javax.inject.Qualifier仅仅是一个元注解，用来构建自定义限定符的。而String的限定符（比如Spring中的@Qualifier）可以通过javax.inject.Named来实现

ObjectFactory   Provider    javax.inject.Provider是SpringObjectFactory的另一个选择，通过get()方法来代理，Provider可以和Spring的@Autowired组合使用

java.lang.reflect.Member
