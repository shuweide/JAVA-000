# 學習筆記

## 如何看 java bytecode 

### javap : Disassembles one or more class files.
* [javap docs](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javap.html)
* -c : Prints disassembled code, for example, the instructions that comprise the Java bytecodes, for each of the methods in the class.
* -verbose/-v : Prints stack size, number of locals and arguments for methods.

### Test 1：Hello Geek

```java
public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello Geek");
    }
}
```

```
javap -c -v Hello
```

```java
public class Hello {
  public Hello();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0 
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LHello;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=1, args_size=1
         0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
         3: ldc           #3                  // String Hello Geek
         5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
         8: return
      LineNumberTable:
        line 3: 0
        line 4: 8
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       9     0  args   [Ljava/lang/String;

}
```

### Hello();
1. aload_0：取得 LocalVariableTable Slot 0 推入 Stack
2. invokespecial #1：執行常量池 #1 method

### main(java.lang.String[]);
1. getstatic #2：將常量池 #2 推入 Stack
2. ldc #3：將常量池 #3 推入 Stack
3. invokevirtual #4：執行常量池 #4

### Test 2：基礎型別、算數、迴圈

