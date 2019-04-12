# aifotools-common
Android 日常开发工具包

注解动态权限验证
```java
    @Permission(Manifest.permission.CAMERA)
    private void takeAPicture() {
        Log.i(TAG, "Go to take a picture");
    }
```

注解多次点击处理
```java
    @SingleClick
    @Override
    public void onClick(View v) {
        takeAPicture();
    }
```



简单依赖注入
```java

    @Inject
    Persion persion;
    
    private void textInject() {
        persion.school.printSchool();
    }
    
```

```java
public class Persion {

    @Inject
    public School school;

    public Persion(){
    }
    public void printSelf() {
        System.out.println("I got you here ;!");
    }

}
```

单利工厂
```java
@InstanceFactory
public class Persion {

    @Inject
    public School school;

    public Persion(){
    }
    public void printSelf() {
        System.out.println("I got you here ;!");
    }

}
```
```java
    private void testInstanceFactory() throws InstantiationException, IllegalAccessException {
        Persion persion = (Persion) InstanceFactory.create(Persion.class);
    }
```
