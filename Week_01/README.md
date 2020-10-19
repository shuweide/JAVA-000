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

#### Hello();
1. aload_0：取得 LocalVariableTable Slot 0 推入 Stack
2. invokespecial #1：執行常量池 #1 method

#### main(java.lang.String[]);
1. getstatic #2：將常量池 #2 推入 Stack
2. ldc #3：將常量池 #3 推入 Stack
3. invokevirtual #4：執行常量池 #4

- - -

### Test 2：基礎型別、算數、迴圈

* bytecode - https://emacsist.github.io/2017/06/19/jvm%E5%AD%97%E8%8A%82%E7%A0%81%E5%AD%A6%E4%B9%A0%E4%B8%8E%E7%90%86%E8%A7%A3
  

```java
public class Hello2 {
    public static void main(String[] args) {
        int times = 5;
        double money = 5.1234;
        while (times > 0) {
            money = money * times;
            times--;
        }
        System.out.println(money);
    }
}
```

```
javap -c -v Hello2
```

```java
public class Hello2 {
    
    ...略

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=4, locals=4, args_size=1
         0: iconst_5
         1: istore_1
         2: ldc2_w        #2                  // double 5.1234d
         5: dstore_2
         6: iload_1
         7: ifle          21
        10: dload_2
        11: iload_1
        12: i2d
        13: dmul
        14: dstore_2
        15: iinc          1, -1
        18: goto          6
        21: return
      LineNumberTable:
        line 3: 0
        line 4: 2
        line 5: 6
        line 6: 10
        line 7: 15
        line 9: 21
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      22     0  args   [Ljava/lang/String;
            2      20     1 times   I
            6      16     2 money   D
      StackMapTable: number_of_entries = 2
        frame_type = 253 /* append */
          offset_delta = 6
          locals = [ int, double ]
        frame_type = 14 /* same */
}
```
#### main(java.lang.String[]);

LVT：LoaclVariableTable
S：Stack

1. iconst_5：將5推入S
2. istore_1：S推出至LVT Slot 1
3. ldc2_w #2：將常量池 #2 (5.1234d) 推入 S
4. dstore_2：S取出至LVT Slot 2
5. iload_1：LVT Slot 1(5) 推入 S
6. ifle 21：S值<=0，則跳到 Label 21
7. dload_2：LVT Slot 2 推入 S
8. iload_1：LVT Slot 1 推入 S
9. i2d：S int轉double
10. dmul：將S最上面兩個相乘後推入S
11. dstore_2：S取出至LVT Slot 2
12. iinc 1, -1：將LVT Slot 1減1
13. goto 6：回到 Label 6

- - -