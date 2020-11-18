# 學習筆記

## 參考資料
* [如何选择合适的 GC 时间 —— USER, SYS, REAL？](https://my.oschina.net/dabird/blog/714569)
* [What do 'real', 'user' and 'sys' mean in the output of time(1)?](https://stackoverflow.com/questions/556405/what-do-real-user-and-sys-mean-in-the-output-of-time1?lq=1)
* [读懂一行Full GC日志](https://cloud.tencent.com/developer/article/1082687)
* [Java虚拟机详解04----GC算法和种类](https://www.cnblogs.com/qianguyihao/p/4744233.html)
* [JVM调优实战：解决CMS concurrent-abortable-preclean LongGC的问题](https://blog.csdn.net/flysqrlboy/article/details/88679457)
* [CMS之promotion failed&concurrent mode failure](https://www.jianshu.com/p/ca1b0d4107c5)
* [深入理解 Java G1 垃圾收集器](http://ghoulich.xninja.org/2018/01/27/understanding-g1-garbage-collector-in-java/)
* [JVM调优实战：G1中的to-space exhausted问题](https://juejin.im/post/6844903923648561166)
  

## Tools
* GCEasy - https://gceasy.io/
* GCViewer - https://github.com/chewiebug/GCViewer
* fastthread - https://fastthread.io/
* Eclipse MAT - https://www.eclipse.org/mat/
* jvisualvm - https://visualvm.github.io/
* jmc - https://www.oracle.com/java/technologies/javase/products-jmc7-downloads.html
* superbechmarker - https://github.com/aliostad/SuperBenchmarker

## GC觀念

### VMOptions
* Print GC：-XX:+PrintGCDetails/-XX:+PrintGC
* Print GC timestamp：-XX:PrintGCDateStamps
* Export GC log：-Xloggc:**filepath**

### Time
* user - User is the amount of CPU time spent in user-mode code (outside the kernel) within the process. This is only actual CPU time used in executing the process. Other processes and time the process spends blocked do not count towards this figure.
* sys - Sys is the amount of CPU time spent in the kernel within the process. This means executing CPU time spent in system calls within the kernel, as opposed to library code, which is still running in user-space. Like ‘user’, this is only CPU time used by the process.
* real - Real is wall clock time – time from start to finish of the call. This is all elapsed time including time slices used by other processes and time the process spends blocked (for example if it is waiting for I/O to complete).

### 算法
#### Mark-Copy
#### Mark-Sweep
#### Mark-Sweep-compact

## Test Machine
* CPU
  * Intel(R) Core(TM) i5-7400 CPU @ 3.00GHz
  * 基本速度:	3.00 GHz
  * 插槽:	1
  * 核心數目:	4
  * 邏輯處理器:	4
  * 模擬:	已啟用
  * L1 快取:	256 KB
  * L2 快取:	1.0 MB
  * L3 快取:	6.0 MB
* 記憶體
  * 16.0 GB
  * 速度:	2400 MHz
  * 已使用插槽:	1 (總共 4)
  * 尺寸:	DIMM
  * 硬體保留:	67.7 MB

## Serial GC

* -XX:+UseSerialGC
* SerialGC使用單線程處理GC，因此user + sys = real。
* 適合用於單核且記憶體只有幾百MB的情況
* YGC使用mark-copy，FGC使用mark-sweep-compact

<details>
  <summary>gc log use Xmx256m, 4227 times</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8722496k free), swap 19198244k(7559524k free)
CommandLine flags: -XX:InitialHeapSize=267326016 -XX:MaxHeapSize=268435456 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
2020-11-14T23:52:27.067+0800: 0.185: [GC (Allocation Failure) 2020-11-14T23:52:27.067+0800: 0.185: [DefNew: 69952K->8704K(78656K), 0.0112920 secs] 69952K->25312K(253440K), 0.0114287 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-14T23:52:27.082+0800: 0.211: [GC (Allocation Failure) 2020-11-14T23:52:27.082+0800: 0.211: [DefNew: 78554K->8702K(78656K), 0.0144672 secs] 95163K->51920K(253440K), 0.0145419 secs] [Times: user=0.00 sys=0.01, real=0.02 secs] 
2020-11-14T23:52:27.114+0800: 0.237: [GC (Allocation Failure) 2020-11-14T23:52:27.114+0800: 0.237: [DefNew: 78297K->8703K(78656K), 0.0096523 secs] 121515K->72416K(253440K), 0.0097023 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-14T23:52:27.129+0800: 0.256: [GC (Allocation Failure) 2020-11-14T23:52:27.129+0800: 0.256: [DefNew: 78634K->8702K(78656K), 0.0119485 secs] 142347K->98689K(253440K), 0.0120008 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.145+0800: 0.277: [GC (Allocation Failure) 2020-11-14T23:52:27.145+0800: 0.277: [DefNew: 78542K->8703K(78656K), 0.0114181 secs] 168529K->123226K(253440K), 0.0114675 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.176+0800: 0.299: [GC (Allocation Failure) 2020-11-14T23:52:27.176+0800: 0.299: [DefNew: 78438K->8703K(78656K), 0.0123444 secs] 192961K->149499K(253440K), 0.0124054 secs] [Times: user=0.00 sys=0.02, real=0.02 secs] 
2020-11-14T23:52:27.192+0800: 0.322: [GC (Allocation Failure) 2020-11-14T23:52:27.192+0800: 0.322: [DefNew: 78655K->8703K(78656K), 0.0110191 secs] 219451K->170932K(253440K), 0.0110842 secs] [Times: user=0.00 sys=0.01, real=0.02 secs] 
2020-11-14T23:52:27.223+0800: 0.346: [GC (Allocation Failure) 2020-11-14T23:52:27.223+0800: 0.346: [DefNew: 78655K->78655K(78656K), 0.0000129 secs]2020-11-14T23:52:27.223+0800: 0.346: [Tenured: 162228K->169419K(174784K), 0.0346177 secs] 240884K->169419K(253440K), [Metaspace: 3347K->3347K(1056768K)], 0.0346986 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-14T23:52:27.270+0800: 0.390: [GC (Allocation Failure) 2020-11-14T23:52:27.270+0800: 0.390: [DefNew: 69952K->69952K(78656K), 0.0000121 secs]2020-11-14T23:52:27.270+0800: 0.390: [Tenured: 169419K->174519K(174784K), 0.0307157 secs] 239371K->180379K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0307843 secs] [Times: user=0.01 sys=0.02, real=0.03 secs] 
2020-11-14T23:52:27.301+0800: 0.432: [Full GC (Allocation Failure) 2020-11-14T23:52:27.301+0800: 0.432: [Tenured: 174519K->174756K(174784K), 0.0322014 secs] 252778K->192591K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0322653 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-14T23:52:27.348+0800: 0.473: [Full GC (Allocation Failure) 2020-11-14T23:52:27.348+0800: 0.473: [Tenured: 174783K->174755K(174784K), 0.0417893 secs] 253432K->196707K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0418564 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-14T23:52:27.395+0800: 0.523: [Full GC (Allocation Failure) 2020-11-14T23:52:27.395+0800: 0.523: [Tenured: 174755K->174506K(174784K), 0.0098509 secs] 253059K->215622K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0099141 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.410+0800: 0.538: [Full GC (Allocation Failure) 2020-11-14T23:52:27.410+0800: 0.538: [Tenured: 174604K->174475K(174784K), 0.0240632 secs] 253200K->221691K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0241290 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-14T23:52:27.450+0800: 0.567: [Full GC (Allocation Failure) 2020-11-14T23:52:27.450+0800: 0.567: [Tenured: 174708K->174453K(174784K), 0.0339671 secs] 253326K->223516K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0340549 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-14T23:52:27.488+0800: 0.606: [Full GC (Allocation Failure) 2020-11-14T23:52:27.488+0800: 0.606: [Tenured: 174453K->174592K(174784K), 0.0455168 secs] 252943K->217605K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0456195 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-14T23:52:27.539+0800: 0.657: [Full GC (Allocation Failure) 2020-11-14T23:52:27.539+0800: 0.657: [Tenured: 174592K->174223K(174784K), 0.0149917 secs] 253137K->230278K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0150707 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-11-14T23:52:27.558+0800: 0.676: [Full GC (Allocation Failure) 2020-11-14T23:52:27.558+0800: 0.676: [Tenured: 174751K->174651K(174784K), 0.0232032 secs] 253354K->233261K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0232930 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.584+0800: 0.702: [Full GC (Allocation Failure) 2020-11-14T23:52:27.584+0800: 0.702: [Tenured: 174651K->174576K(174784K), 0.0216520 secs] 253255K->236162K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0217202 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.608+0800: 0.726: [Full GC (Allocation Failure) 2020-11-14T23:52:27.608+0800: 0.726: [Tenured: 174576K->174631K(174784K), 0.0431342 secs] 253044K->230076K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0431992 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-14T23:52:27.654+0800: 0.771: [Full GC (Allocation Failure) 2020-11-14T23:52:27.654+0800: 0.771: [Tenured: 174631K->174631K(174784K), 0.0103030 secs] 252812K->238116K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0103611 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-14T23:52:27.666+0800: 0.784: [Full GC (Allocation Failure) 2020-11-14T23:52:27.666+0800: 0.784: [Tenured: 174631K->174631K(174784K), 0.0069098 secs] 253154K->240792K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0069746 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-11-14T23:52:27.674+0800: 0.792: [Full GC (Allocation Failure) 2020-11-14T23:52:27.674+0800: 0.792: [Tenured: 174631K->174545K(174784K), 0.0225221 secs] 253238K->241888K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0225796 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.695+0800: 0.816: [Full GC (Allocation Failure) 2020-11-14T23:52:27.695+0800: 0.816: [Tenured: 174545K->174633K(174784K), 0.0397275 secs] 253047K->236557K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0397800 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-14T23:52:27.727+0800: 0.858: [Full GC (Allocation Failure) 2020-11-14T23:52:27.727+0800: 0.858: [Tenured: 174633K->174633K(174784K), 0.0085371 secs] 253193K->241124K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0086086 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.742+0800: 0.869: [Full GC (Allocation Failure) 2020-11-14T23:52:27.742+0800: 0.869: [Tenured: 174633K->174633K(174784K), 0.0119222 secs] 253288K->244382K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0119807 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.758+0800: 0.882: [Full GC (Allocation Failure) 2020-11-14T23:52:27.758+0800: 0.882: [Tenured: 174777K->174777K(174784K), 0.0135291 secs] 253370K->247733K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0135900 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.773+0800: 0.897: [Full GC (Allocation Failure) 2020-11-14T23:52:27.773+0800: 0.897: [Tenured: 174777K->174739K(174784K), 0.0545784 secs] 253421K->245422K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0546583 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-14T23:52:27.820+0800: 0.953: [Full GC (Allocation Failure) 2020-11-14T23:52:27.820+0800: 0.953: [Tenured: 174739K->174739K(174784K), 0.0053932 secs] 253354K->247943K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0054890 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.836+0800: 0.960: [Full GC (Allocation Failure) 2020-11-14T23:52:27.836+0800: 0.960: [Tenured: 174739K->174739K(174784K), 0.0128370 secs] 253243K->248911K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0129026 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.852+0800: 0.974: [Full GC (Allocation Failure) 2020-11-14T23:52:27.852+0800: 0.974: [Tenured: 174739K->174739K(174784K), 0.0133093 secs] 253320K->250062K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0133964 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-14T23:52:27.867+0800: 0.988: [Full GC (Allocation Failure) 2020-11-14T23:52:27.867+0800: 0.988: [Tenured: 174739K->174739K(174784K), 0.0581738 secs] 253364K->246994K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0582504 secs] [Times: user=0.03 sys=0.00, real=0.05 secs] 
2020-11-14T23:52:27.930+0800: 1.048: [Full GC (Allocation Failure) 2020-11-14T23:52:27.930+0800: 1.048: [Tenured: 174739K->174739K(174784K), 0.0036251 secs] 253297K->248970K(253440K), [Metaspace: 3437K->3437K(1056768K)], 0.0036875 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-14T23:52:27.930+0800: 1.053: [Full GC (Allocation Failure) 2020-11-14T23:52:27.930+0800: 1.053: [Tenured: 174739K->174739K(174784K), 0.0154155 secs] 253188K->250012K(253440K), [Metaspace: 3466K->3466K(1056768K)], 0.0155156 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-11-14T23:52:27.945+0800: 1.069: [Full GC (Allocation Failure) 2020-11-14T23:52:27.945+0800: 1.069: [Tenured: 174739K->174739K(174784K), 0.0040941 secs] 253152K->250640K(253440K), [Metaspace: 3479K->3479K(1056768K)], 0.0041755 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-14T23:52:27.945+0800: 1.074: [Full GC (Allocation Failure) 2020-11-14T23:52:27.945+0800: 1.074: [Tenured: 174739K->174540K(174784K), 0.0502949 secs] 253040K->248249K(253440K), [Metaspace: 3485K->3485K(1056768K)], 0.0503595 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-14T23:52:28.008+0800: 1.126: [Full GC (Allocation Failure) 2020-11-14T23:52:28.008+0800: 1.126: [Tenured: 174752K->174752K(174784K), 0.0124712 secs] 253407K->248939K(253440K), [Metaspace: 3486K->3486K(1056768K)], 0.0125507 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-14T23:52:28.008+0800: 1.140: [Full GC (Allocation Failure) 2020-11-14T23:52:28.008+0800: 1.140: [Tenured: 174752K->174752K(174784K), 0.0136294 secs] 253177K->250252K(253440K), [Metaspace: 3503K->3503K(1056768K)], 0.0136978 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
Heap
 def new generation   total 78656K, used 75819K [0x00000000f0000000, 0x00000000f5550000, 0x00000000f5550000)
  eden space 69952K, 100% used [0x00000000f0000000, 0x00000000f4450000, 0x00000000f4450000)
  from space 8704K,  67% used [0x00000000f4cd0000, 0x00000000f528ad40, 0x00000000f5550000)
  to   space 8704K,   0% used [0x00000000f4450000, 0x00000000f4450000, 0x00000000f4cd0000)
 tenured generation   total 174784K, used 174752K [0x00000000f5550000, 0x0000000100000000, 0x0000000100000000)
   the space 174784K,  99% used [0x00000000f5550000, 0x00000000ffff81c8, 0x00000000ffff8200, 0x0000000100000000)
 Metaspace       used 3561K, capacity 4504K, committed 4864K, reserved 1056768K
  class space    used 390K, capacity 392K, committed 512K, reserved 1048576K
```
</details>
<details>
    <summary>gc log use Xmx128m，OOM</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8589280k free), swap 19198244k(6825536k free)
CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseSerialGC 
2020-11-15T00:32:12.359+0800: 0.221: [GC (Allocation Failure) 2020-11-15T00:32:12.359+0800: 0.221: [DefNew: 34693K->4351K(39296K), 0.0075011 secs] 34693K->10058K(126720K), 0.0076165 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T00:32:12.380+0800: 0.241: [GC (Allocation Failure) 2020-11-15T00:32:12.380+0800: 0.241: [DefNew: 39278K->4341K(39296K), 0.0086059 secs] 44984K->20644K(126720K), 0.0086775 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-11-15T00:32:12.397+0800: 0.260: [GC (Allocation Failure) 2020-11-15T00:32:12.397+0800: 0.260: [DefNew: 39285K->4349K(39296K), 0.0090879 secs] 55588K->34124K(126720K), 0.0091434 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-15T00:32:12.413+0800: 0.275: [GC (Allocation Failure) 2020-11-15T00:32:12.413+0800: 0.275: [DefNew: 38623K->4349K(39296K), 0.0088696 secs] 68399K->46148K(126720K), 0.0089394 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-11-15T00:32:12.428+0800: 0.290: [GC (Allocation Failure) 2020-11-15T00:32:12.428+0800: 0.290: [DefNew: 39151K->4348K(39296K), 0.0072805 secs] 80950K->58123K(126720K), 0.0073325 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-11-15T00:32:12.447+0800: 0.309: [GC (Allocation Failure) 2020-11-15T00:32:12.447+0800: 0.309: [DefNew: 39269K->4349K(39296K), 0.0077634 secs] 93044K->69580K(126720K), 0.0078282 secs] [Times: user=0.02 sys=0.00, real=0.01 secs] 
2020-11-15T00:32:12.461+0800: 0.323: [GC (Allocation Failure) 2020-11-15T00:32:12.461+0800: 0.323: [DefNew: 39089K->4348K(39296K), 0.0088286 secs] 104319K->83135K(126720K), 0.0088995 secs] [Times: user=0.00 sys=0.01, real=0.01 secs] 
2020-11-15T00:32:12.477+0800: 0.339: [GC (Allocation Failure) 2020-11-15T00:32:12.477+0800: 0.339: [DefNew: 39260K->39260K(39296K), 0.0000136 secs]2020-11-15T00:32:12.477+0800: 0.339: [Tenured: 78787K->86962K(87424K), 0.0155936 secs] 118047K->90943K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0156911 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-15T00:32:12.499+0800: 0.361: [Full GC (Allocation Failure) 2020-11-15T00:32:12.499+0800: 0.361: [Tenured: 87400K->87336K(87424K), 0.0190392 secs] 126691K->97570K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0192630 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-15T00:32:12.523+0800: 0.385: [Full GC (Allocation Failure) 2020-11-15T00:32:12.523+0800: 0.385: [Tenured: 87336K->87314K(87424K), 0.0211157 secs] 126238K->107392K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0212080 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T00:32:12.548+0800: 0.410: [Full GC (Allocation Failure) 2020-11-15T00:32:12.548+0800: 0.410: [Tenured: 87314K->87394K(87424K), 0.0287717 secs] 126324K->111292K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0288536 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T00:32:12.579+0800: 0.441: [Full GC (Allocation Failure) 2020-11-15T00:32:12.579+0800: 0.441: [Tenured: 87394K->87394K(87424K), 0.0031564 secs] 126630K->114503K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0032348 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.587+0800: 0.449: [Full GC (Allocation Failure) 2020-11-15T00:32:12.587+0800: 0.449: [Tenured: 87394K->87394K(87424K), 0.0028991 secs] 126673K->118703K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0029638 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.592+0800: 0.454: [Full GC (Allocation Failure) 2020-11-15T00:32:12.592+0800: 0.454: [Tenured: 87394K->87394K(87424K), 0.0035663 secs] 126650K->122033K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0036319 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.597+0800: 0.459: [Full GC (Allocation Failure) 2020-11-15T00:32:12.597+0800: 0.459: [Tenured: 87394K->87068K(87424K), 0.0269015 secs] 126322K->120738K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0269640 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T00:32:12.624+0800: 0.486: [Full GC (Allocation Failure) 2020-11-15T00:32:12.624+0800: 0.486: [Tenured: 87294K->87294K(87424K), 0.0020483 secs] 126589K->122479K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0021087 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.627+0800: 0.489: [Full GC (Allocation Failure) 2020-11-15T00:32:12.627+0800: 0.489: [Tenured: 87294K->87294K(87424K), 0.0030220 secs] 126518K->123924K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0030897 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.631+0800: 0.493: [Full GC (Allocation Failure) 2020-11-15T00:32:12.632+0800: 0.493: [Tenured: 87360K->87360K(87424K), 0.0023693 secs] 126634K->124586K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0024686 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.639+0800: 0.500: [Full GC (Allocation Failure) 2020-11-15T00:32:12.639+0800: 0.500: [Tenured: 87360K->87132K(87424K), 0.0259562 secs] 126578K->123331K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0260384 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 
2020-11-15T00:32:12.665+0800: 0.527: [Full GC (Allocation Failure) 2020-11-15T00:32:12.665+0800: 0.527: [Tenured: 87372K->87372K(87424K), 0.0088844 secs] 126635K->124204K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0089575 secs] [Times: user=0.01 sys=0.00, real=0.01 secs] 
2020-11-15T00:32:12.675+0800: 0.536: [Full GC (Allocation Failure) 2020-11-15T00:32:12.675+0800: 0.536: [Tenured: 87372K->87372K(87424K), 0.0027660 secs] 126520K->125679K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0028326 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.678+0800: 0.539: [Full GC (Allocation Failure) 2020-11-15T00:32:12.678+0800: 0.539: [Tenured: 87372K->87372K(87424K), 0.0018050 secs] 126609K->125831K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0018559 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.680+0800: 0.541: [Full GC (Allocation Failure) 2020-11-15T00:32:12.680+0800: 0.541: [Tenured: 87406K->87208K(87424K), 0.0217718 secs] 126701K->125384K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0218530 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T00:32:12.702+0800: 0.564: [Full GC (Allocation Failure) 2020-11-15T00:32:12.702+0800: 0.564: [Tenured: 87208K->87208K(87424K), 0.0023518 secs] 126398K->125508K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0024013 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.705+0800: 0.567: [Full GC (Allocation Failure) 2020-11-15T00:32:12.705+0800: 0.567: [Tenured: 87208K->87208K(87424K), 0.0019067 secs] 126322K->125652K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0019533 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.707+0800: 0.569: [Full GC (Allocation Failure) 2020-11-15T00:32:12.707+0800: 0.569: [Tenured: 87319K->87319K(87424K), 0.0018571 secs] 126515K->125784K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0019066 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.709+0800: 0.571: [Full GC (Allocation Failure) 2020-11-15T00:32:12.709+0800: 0.571: [Tenured: 87396K->87211K(87424K), 0.0158464 secs] 126678K->126017K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0158990 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-15T00:32:12.726+0800: 0.587: [Full GC (Allocation Failure) 2020-11-15T00:32:12.726+0800: 0.587: [Tenured: 87211K->87211K(87424K), 0.0018999 secs] 126409K->126082K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0019634 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:32:12.728+0800: 0.589: [Full GC (Allocation Failure) 2020-11-15T00:32:12.728+0800: 0.589: [Tenured: 87211K->87185K(87424K), 0.0161709 secs] 126082K->126057K(126720K), [Metaspace: 3349K->3349K(1056768K)], 0.0162144 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
Heap
 def new generation   total 39296K, used 39033K [0x00000000f8000000, 0x00000000faaa0000, 0x00000000faaa0000)
  eden space 34944K, 100% used [0x00000000f8000000, 0x00000000fa220000, 0x00000000fa220000)
  from space 4352K,  93% used [0x00000000fa660000, 0x00000000faa5e7f8, 0x00000000faaa0000)
  to   space 4352K,   0% used [0x00000000fa220000, 0x00000000fa220000, 0x00000000fa660000)
 tenured generation   total 87424K, used 87185K [0x00000000faaa0000, 0x0000000100000000, 0x0000000100000000)
   the space 87424K,  99% used [0x00000000faaa0000, 0x00000000fffc47f0, 0x00000000fffc4800, 0x0000000100000000)
 Metaspace       used 3379K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 369K, capacity 388K, committed 512K, reserved 1048576K
```
</details>

## Parallel GC

* -XX:+UseParallelGC
* 指定GC執行的線程數：-XX：ParallelGCThreads=N，預設為CPU核心數
* YGC使用mark-copy，FGC使用mark-sweep-compact
* 由於執行GC是多現成的，因此 user > real 是正常的

<details>
<summary>gc log use Xmx128m, OOM </summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8357428k free), swap 19198244k(6581148k free)
CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
2020-11-15T00:48:57.987+0800: 0.156: [GC (Allocation Failure) [PSYoungGen: 33249K->5101K(38400K)] 33249K->8847K(125952K), 0.0030908 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.003+0800: 0.168: [GC (Allocation Failure) [PSYoungGen: 38381K->5110K(38400K)] 42127K->19864K(125952K), 0.0052208 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.020+0800: 0.184: [GC (Allocation Failure) [PSYoungGen: 38390K->5108K(38400K)] 53144K->33605K(125952K), 0.0043431 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.020+0800: 0.195: [GC (Allocation Failure) [PSYoungGen: 38037K->5118K(38400K)] 66534K->44098K(125952K), 0.0040967 secs] [Times: user=0.03 sys=0.02, real=0.02 secs] 
2020-11-15T00:48:58.036+0800: 0.205: [GC (Allocation Failure) [PSYoungGen: 38290K->5117K(38400K)] 77270K->55344K(125952K), 0.0062503 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.051+0800: 0.216: [GC (Allocation Failure) [PSYoungGen: 38391K->5110K(19968K)] 88618K->64550K(107520K), 0.0027802 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.051+0800: 0.222: [GC (Allocation Failure) [PSYoungGen: 19782K->8110K(29184K)] 79222K->69450K(116736K), 0.0019987 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.051+0800: 0.226: [GC (Allocation Failure) [PSYoungGen: 22949K->11315K(29184K)] 84288K->73568K(116736K), 0.0022111 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.067+0800: 0.231: [GC (Allocation Failure) [PSYoungGen: 26134K->14315K(29184K)] 88387K->79139K(116736K), 0.0036197 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.067+0800: 0.236: [GC (Allocation Failure) [PSYoungGen: 29139K->11452K(29184K)] 93963K->84469K(116736K), 0.0039309 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.067+0800: 0.240: [Full GC (Ergonomics) [PSYoungGen: 11452K->0K(29184K)] [ParOldGen: 73017K->78514K(87552K)] 84469K->78514K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0146646 secs] [Times: user=0.03 sys=0.02, real=0.02 secs] 
2020-11-15T00:48:58.083+0800: 0.257: [Full GC (Ergonomics) [PSYoungGen: 14358K->0K(29184K)] [ParOldGen: 78514K->81989K(87552K)] 92872K->81989K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0141982 secs] [Times: user=0.05 sys=0.00, real=0.02 secs] 
2020-11-15T00:48:58.098+0800: 0.274: [Full GC (Ergonomics) [PSYoungGen: 14778K->0K(29184K)] [ParOldGen: 81989K->85604K(87552K)] 96768K->85604K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0130574 secs] [Times: user=0.03 sys=0.03, real=0.02 secs] 
2020-11-15T00:48:58.114+0800: 0.290: [Full GC (Ergonomics) [PSYoungGen: 14472K->3356K(29184K)] [ParOldGen: 85604K->86991K(87552K)] 100077K->90348K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0132235 secs] [Times: user=0.03 sys=0.02, real=0.02 secs] 
2020-11-15T00:48:58.130+0800: 0.305: [Full GC (Ergonomics) [PSYoungGen: 14848K->4359K(29184K)] [ParOldGen: 86991K->87441K(87552K)] 101839K->91800K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0167784 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-11-15T00:48:58.161+0800: 0.323: [Full GC (Ergonomics) [PSYoungGen: 14544K->8121K(29184K)] [ParOldGen: 87441K->87356K(87552K)] 101986K->95478K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0143715 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.176+0800: 0.339: [Full GC (Ergonomics) [PSYoungGen: 14552K->10744K(29184K)] [ParOldGen: 87356K->87296K(87552K)] 101909K->98040K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0084012 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.176+0800: 0.348: [Full GC (Ergonomics) [PSYoungGen: 14527K->11289K(29184K)] [ParOldGen: 87296K->87296K(87552K)] 101823K->98586K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0019582 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.176+0800: 0.351: [Full GC (Ergonomics) [PSYoungGen: 14764K->11289K(29184K)] [ParOldGen: 87296K->87296K(87552K)] 102060K->98586K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0020281 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.176+0800: 0.353: [Full GC (Ergonomics) [PSYoungGen: 14795K->13012K(29184K)] [ParOldGen: 87296K->87296K(87552K)] 102092K->100309K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0030742 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T00:48:58.192+0800: 0.357: [Full GC (Ergonomics) [PSYoungGen: 14635K->13523K(29184K)] [ParOldGen: 87296K->87274K(87552K)] 101931K->100798K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0119302 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.192+0800: 0.369: [Full GC (Ergonomics) [PSYoungGen: 14418K->13912K(29184K)] [ParOldGen: 87274K->87274K(87552K)] 101693K->101186K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0022077 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T00:48:58.208+0800: 0.371: [Full GC (Ergonomics) [PSYoungGen: 14758K->14590K(29184K)] [ParOldGen: 87274K->87274K(87552K)] 102033K->101865K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0022023 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T00:48:58.208+0800: 0.374: [Full GC (Ergonomics) [PSYoungGen: 14829K->14563K(29184K)] [ParOldGen: 87274K->87126K(87552K)] 102104K->101690K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0148887 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T00:48:58.224+0800: 0.389: [Full GC (Allocation Failure) [PSYoungGen: 14563K->14563K(29184K)] [ParOldGen: 87126K->87101K(87552K)] 101690K->101665K(116736K), [Metaspace: 3349K->3349K(1056768K)], 0.0151908 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
Heap
 PSYoungGen      total 29184K, used 14848K [0x00000000fd580000, 0x0000000100000000, 0x0000000100000000)
  eden space 14848K, 100% used [0x00000000fd580000,0x00000000fe400000,0x00000000fe400000)
  from space 14336K, 0% used [0x00000000ff200000,0x00000000ff200000,0x0000000100000000)
  to   space 14336K, 0% used [0x00000000fe400000,0x00000000fe400000,0x00000000ff200000)
 ParOldGen       total 87552K, used 87101K [0x00000000f8000000, 0x00000000fd580000, 0x00000000fd580000)
  object space 87552K, 99% used [0x00000000f8000000,0x00000000fd50f640,0x00000000fd580000)
 Metaspace       used 3379K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 369K, capacity 388K, committed 512K, reserved 1048576K
```
</details>
<details>
<summary>gc log use Xmx256m, 3054 times</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8797984k free), swap 19198244k(6357320k free)
CommandLine flags: -XX:InitialHeapSize=267326016 -XX:MaxHeapSize=268435456 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
2020-11-15T01:37:40.367+0800: 0.183: [GC (Allocation Failure) [PSYoungGen: 65536K->10745K(76288K)] 65536K->16398K(251392K), 0.0049840 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T01:37:40.382+0800: 0.204: [GC (Allocation Failure) [PSYoungGen: 76281K->10750K(76288K)] 81934K->39712K(251392K), 0.0109235 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2020-11-15T01:37:40.398+0800: 0.224: [GC (Allocation Failure) [PSYoungGen: 76264K->10751K(76288K)] 105226K->57568K(251392K), 0.0074616 secs] [Times: user=0.05 sys=0.00, real=0.02 secs] 
2020-11-15T01:37:40.429+0800: 0.241: [GC (Allocation Failure) [PSYoungGen: 76287K->10749K(76288K)] 123104K->84344K(251392K), 0.0141383 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2020-11-15T01:37:40.453+0800: 0.264: [GC (Allocation Failure) [PSYoungGen: 76199K->10739K(76288K)] 149794K->104372K(251392K), 0.0177009 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2020-11-15T01:37:40.482+0800: 0.293: [GC (Allocation Failure) [PSYoungGen: 75924K->10749K(40448K)] 169557K->127207K(215552K), 0.0131561 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T01:37:40.500+0800: 0.311: [GC (Allocation Failure) [PSYoungGen: 40415K->16479K(58368K)] 156873K->136383K(233472K), 0.0054363 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T01:37:40.510+0800: 0.321: [GC (Allocation Failure) [PSYoungGen: 45702K->23915K(58368K)] 165606K->147778K(233472K), 0.0051920 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-15T01:37:40.523+0800: 0.334: [GC (Allocation Failure) [PSYoungGen: 53611K->28640K(58368K)] 177474K->158517K(233472K), 0.0059273 secs] [Times: user=0.05 sys=0.02, real=0.01 secs] 
2020-11-15T01:37:40.534+0800: 0.345: [GC (Allocation Failure) [PSYoungGen: 58233K->18530K(58368K)] 188111K->166175K(233472K), 0.0108124 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T01:37:40.545+0800: 0.356: [Full GC (Ergonomics) [PSYoungGen: 18530K->0K(58368K)] [ParOldGen: 147645K->141003K(175104K)] 166175K->141003K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0283865 secs] [Times: user=0.05 sys=0.02, real=0.03 secs] 
2020-11-15T01:37:40.578+0800: 0.389: [GC (Allocation Failure) [PSYoungGen: 29547K->11872K(58368K)] 170550K->152875K(233472K), 0.0029652 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T01:37:40.585+0800: 0.397: [GC (Allocation Failure) [PSYoungGen: 40976K->11861K(58368K)] 181979K->164490K(233472K), 0.0049602 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2020-11-15T01:37:40.590+0800: 0.402: [Full GC (Ergonomics) [PSYoungGen: 11861K->0K(58368K)] [ParOldGen: 152628K->158517K(175104K)] 164490K->158517K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0367707 secs] [Times: user=0.06 sys=0.00, real=0.04 secs] 
2020-11-15T01:37:40.633+0800: 0.444: [Full GC (Ergonomics) [PSYoungGen: 29214K->0K(58368K)] [ParOldGen: 158517K->164284K(175104K)] 187731K->164284K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0438215 secs] [Times: user=0.03 sys=0.03, real=0.04 secs] 
2020-11-15T01:37:40.682+0800: 0.493: [Full GC (Ergonomics) [PSYoungGen: 29696K->0K(58368K)] [ParOldGen: 164284K->169818K(175104K)] 193980K->169818K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0473169 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-15T01:37:40.735+0800: 0.546: [Full GC (Ergonomics) [PSYoungGen: 29695K->0K(58368K)] [ParOldGen: 169818K->174141K(175104K)] 199513K->174141K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0519814 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-15T01:37:40.794+0800: 0.605: [Full GC (Ergonomics) [PSYoungGen: 29418K->7170K(58368K)] [ParOldGen: 174141K->174642K(175104K)] 203560K->181812K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0610068 secs] [Times: user=0.08 sys=0.00, real=0.06 secs] 
2020-11-15T01:37:40.860+0800: 0.671: [Full GC (Ergonomics) [PSYoungGen: 29408K->8687K(58368K)] [ParOldGen: 174642K->175054K(175104K)] 204050K->183741K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0648442 secs] [Times: user=0.03 sys=0.01, real=0.07 secs] 
2020-11-15T01:37:40.928+0800: 0.739: [Full GC (Ergonomics) [PSYoungGen: 29361K->13123K(58368K)] [ParOldGen: 175054K->174752K(175104K)] 204415K->187875K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0531740 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-15T01:37:40.984+0800: 0.796: [Full GC (Ergonomics) [PSYoungGen: 29696K->17031K(58368K)] [ParOldGen: 174752K->175098K(175104K)] 204448K->192130K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0584825 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
2020-11-15T01:37:41.045+0800: 0.857: [Full GC (Ergonomics) [PSYoungGen: 29696K->19729K(58368K)] [ParOldGen: 175098K->174780K(175104K)] 204794K->194510K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0500281 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-15T01:37:41.098+0800: 0.909: [Full GC (Ergonomics) [PSYoungGen: 29696K->22814K(58368K)] [ParOldGen: 174780K->174691K(175104K)] 204476K->197505K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0336949 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T01:37:41.133+0800: 0.944: [Full GC (Ergonomics) [PSYoungGen: 29569K->23586K(58368K)] [ParOldGen: 174691K->175066K(175104K)] 204261K->198653K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0400107 secs] [Times: user=0.08 sys=0.00, real=0.04 secs] 
2020-11-15T01:37:41.174+0800: 0.985: [Full GC (Ergonomics) [PSYoungGen: 29658K->25711K(58368K)] [ParOldGen: 175066K->174633K(175104K)] 204725K->200344K(233472K), [Metaspace: 3348K->3348K(1056768K)], 0.0364709 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-15T01:37:41.212+0800: 1.023: [Full GC (Ergonomics) [PSYoungGen: 29512K->25849K(58368K)] [ParOldGen: 174633K->174891K(175104K)] 204145K->200741K(233472K), [Metaspace: 3358K->3358K(1056768K)], 0.0389277 secs] [Times: user=0.09 sys=0.00, real=0.04 secs] 
2020-11-15T01:37:41.252+0800: 1.062: [Full GC (Ergonomics) [PSYoungGen: 29612K->24563K(58368K)] [ParOldGen: 174891K->174916K(175104K)] 204503K->199479K(233472K), [Metaspace: 3367K->3367K(1056768K)], 0.0317926 secs] [Times: user=0.08 sys=0.00, real=0.03 secs] 
2020-11-15T01:37:41.285+0800: 1.095: [Full GC (Ergonomics) [PSYoungGen: 29553K->25122K(58368K)] [ParOldGen: 174916K->174778K(175104K)] 204470K->199901K(233472K), [Metaspace: 3426K->3426K(1056768K)], 0.0365888 secs] [Times: user=0.06 sys=0.00, real=0.03 secs] 
Heap
 PSYoungGen      total 58368K, used 26251K [0x00000000fab00000, 0x0000000100000000, 0x0000000100000000)
  eden space 29696K, 88% used [0x00000000fab00000,0x00000000fc4a2fe0,0x00000000fc800000)
  from space 28672K, 0% used [0x00000000fe400000,0x00000000fe400000,0x0000000100000000)
  to   space 28672K, 0% used [0x00000000fc800000,0x00000000fc800000,0x00000000fe400000)
 ParOldGen       total 175104K, used 174778K [0x00000000f0000000, 0x00000000fab00000, 0x00000000fab00000)
  object space 175104K, 99% used [0x00000000f0000000,0x00000000faaaebd8,0x00000000fab00000)
 Metaspace       used 3476K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 376K, capacity 388K, committed 512K, reserved 1048576K

```
</details>
<details>
<summary>gc log use Xmx1g，10217 times</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8633304k free), swap 19198244k(6242672k free)
CommandLine flags: -XX:InitialHeapSize=267326016 -XX:MaxHeapSize=1073741824 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
2020-11-15T01:40:27.014+0800: 0.166: [GC (Allocation Failure) [PSYoungGen: 65536K->10741K(76288K)] 65536K->22285K(251392K), 0.0085328 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.040+0800: 0.193: [GC (Allocation Failure) [PSYoungGen: 76007K->10747K(141824K)] 87551K->38578K(316928K), 0.0096537 secs] [Times: user=0.00 sys=0.03, real=0.01 secs] 
2020-11-15T01:40:27.091+0800: 0.244: [GC (Allocation Failure) [PSYoungGen: 141819K->10736K(141824K)] 169650K->83916K(316928K), 0.0179330 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2020-11-15T01:40:27.136+0800: 0.290: [GC (Allocation Failure) [PSYoungGen: 141808K->10746K(272896K)] 214988K->130316K(448000K), 0.0174186 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2020-11-15T01:40:27.154+0800: 0.307: [Full GC (Ergonomics) [PSYoungGen: 10746K->0K(272896K)] [ParOldGen: 119569K->114260K(256000K)] 130316K->114260K(528896K), [Metaspace: 3349K->3349K(1056768K)], 0.0279621 secs] [Times: user=0.05 sys=0.00, real=0.03 secs] 
2020-11-15T01:40:27.281+0800: 0.434: [GC (Allocation Failure) [PSYoungGen: 262144K->10722K(272896K)] 376404K->194615K(528896K), 0.0171685 secs] [Times: user=0.01 sys=0.01, real=0.02 secs] 
2020-11-15T01:40:27.298+0800: 0.451: [Full GC (Ergonomics) [PSYoungGen: 10722K->0K(272896K)] [ParOldGen: 183893K->169303K(365056K)] 194615K->169303K(637952K), [Metaspace: 3349K->3349K(1056768K)], 0.0320558 secs] [Times: user=0.08 sys=0.02, real=0.03 secs] 
2020-11-15T01:40:27.377+0800: 0.529: [GC (Allocation Failure) [PSYoungGen: 262144K->75555K(224768K)] 431447K->244858K(589824K), 0.0196706 secs] [Times: user=0.02 sys=0.02, real=0.02 secs] 
2020-11-15T01:40:27.419+0800: 0.571: [GC (Allocation Failure) [PSYoungGen: 224035K->100345K(248832K)] 393338K->281685K(613888K), 0.0172962 secs] [Times: user=0.06 sys=0.00, real=0.02 secs] 
2020-11-15T01:40:27.461+0800: 0.614: [GC (Allocation Failure) [PSYoungGen: 248825K->100344K(217088K)] 430165K->315689K(582144K), 0.0228522 secs] [Times: user=0.05 sys=0.06, real=0.02 secs] 
2020-11-15T01:40:27.502+0800: 0.656: [GC (Allocation Failure) [PSYoungGen: 217046K->90111K(232960K)] 432391K->342520K(598016K), 0.0212639 secs] [Times: user=0.03 sys=0.03, real=0.02 secs] 
2020-11-15T01:40:27.542+0800: 0.695: [GC (Allocation Failure) [PSYoungGen: 206847K->70844K(232960K)] 459256K->367822K(598016K), 0.0212447 secs] [Times: user=0.01 sys=0.02, real=0.02 secs] 
2020-11-15T01:40:27.563+0800: 0.716: [Full GC (Ergonomics) [PSYoungGen: 70844K->0K(232960K)] [ParOldGen: 296978K->266830K(470528K)] 367822K->266830K(703488K), [Metaspace: 3349K->3349K(1056768K)], 0.0440367 secs] [Times: user=0.17 sys=0.00, real=0.04 secs] 
2020-11-15T01:40:27.624+0800: 0.777: [GC (Allocation Failure) [PSYoungGen: 116736K->43685K(232960K)] 383566K->310516K(703488K), 0.0069340 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.646+0800: 0.799: [GC (Allocation Failure) [PSYoungGen: 160240K->37643K(232960K)] 427071K->344145K(703488K), 0.0123426 secs] [Times: user=0.06 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.677+0800: 0.830: [GC (Allocation Failure) [PSYoungGen: 154379K->41760K(232960K)] 460881K->382491K(703488K), 0.0138567 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-15T01:40:27.708+0800: 0.861: [GC (Allocation Failure) [PSYoungGen: 158114K->37414K(232960K)] 498846K->418061K(703488K), 0.0138385 secs] [Times: user=0.03 sys=0.03, real=0.01 secs] 
2020-11-15T01:40:27.737+0800: 0.890: [GC (Allocation Failure) [PSYoungGen: 154150K->42217K(232960K)] 534797K->457440K(703488K), 0.0130384 secs] [Times: user=0.03 sys=0.03, real=0.01 secs] 
2020-11-15T01:40:27.750+0800: 0.903: [Full GC (Ergonomics) [PSYoungGen: 42217K->0K(232960K)] [ParOldGen: 415223K->318810K(540160K)] 457440K->318810K(773120K), [Metaspace: 3349K->3349K(1056768K)], 0.0508477 secs] [Times: user=0.17 sys=0.00, real=0.05 secs] 
2020-11-15T01:40:27.817+0800: 0.969: [GC (Allocation Failure) [PSYoungGen: 116736K->44616K(232960K)] 435546K->363427K(773120K), 0.0070401 secs] [Times: user=0.06 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.838+0800: 0.991: [GC (Allocation Failure) [PSYoungGen: 161352K->45431K(232960K)] 480163K->403748K(773120K), 0.0129616 secs] [Times: user=0.06 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.868+0800: 1.021: [GC (Allocation Failure) [PSYoungGen: 162167K->39713K(230912K)] 520484K->435169K(771072K), 0.0118644 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.898+0800: 1.051: [GC (Allocation Failure) [PSYoungGen: 156015K->35709K(231936K)] 551472K->467342K(772096K), 0.0120513 secs] [Times: user=0.06 sys=0.00, real=0.01 secs] 
2020-11-15T01:40:27.927+0800: 1.080: [GC (Allocation Failure) [PSYoungGen: 152445K->44090K(226304K)] 584078K->509940K(766464K), 0.0159269 secs] [Times: user=0.03 sys=0.01, real=0.02 secs] 
Heap
 PSYoungGen      total 226304K, used 147177K [0x00000000eab00000, 0x00000000ff480000, 0x0000000100000000)
  eden space 121344K, 84% used [0x00000000eab00000,0x00000000f0fabd18,0x00000000f2180000)
  from space 104960K, 42% used [0x00000000f8e00000,0x00000000fb90e850,0x00000000ff480000)
  to   space 108032K, 0% used [0x00000000f2180000,0x00000000f2180000,0x00000000f8b00000)
 ParOldGen       total 540160K, used 465850K [0x00000000c0000000, 0x00000000e0f80000, 0x00000000eab00000)
  object space 540160K, 86% used [0x00000000c0000000,0x00000000dc6eead0,0x00000000e0f80000)
 Metaspace       used 3861K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 426K, capacity 428K, committed 512K, reserved 1048576K

```
</details>

## CMS GC - Mostly Concurrent Mark and Sweep GC

* -XX:+UseConcMarkSweepGC
* YGC使用並行mark-copy(有STW)，FGC使用並發mark-sweep(幾乎無STW)
* CMS併發線程預設為CPU核心的四分之一
* FGC六階段
  * initial mark(STW) 
  * concurrent mark
  * concurrent preclean
  * final remark(STW)
  * concurrent sweep
  * concurrent reset
* CMS在STW上做了很多改進，將STW範圍縮小，許多FGC可以跟App一起進行，但缺點是其沒有壓縮，易造成記憶體碎片問題。

<details>
<summary>gc log use xmx128m, OOM</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(9172376k free), swap 19198244k(9140264k free)
CommandLine flags: -XX:InitialHeapSize=134217728 -XX:MaxHeapSize=134217728 -XX:MaxNewSize=44740608 -XX:MaxTenuringThreshold=6 -XX:OldPLABSize=16 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC 
2020-11-15T23:29:17.781+0800: 0.400: [GC (Allocation Failure) 2020-11-15T23:29:17.781+0800: 0.400: [ParNew: 34944K->4352K(39296K), 0.0199554 secs] 34944K->13055K(126720K), 0.0205364 secs] [Times: user=0.00 sys=0.02, real=0.02 secs] 
2020-11-15T23:29:17.812+0800: 0.430: [GC (Allocation Failure) 2020-11-15T23:29:17.812+0800: 0.430: [ParNew: 39210K->4352K(39296K), 0.0038837 secs] 47913K->21015K(126720K), 0.0039481 secs] [Times: user=0.05 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.825+0800: 0.444: [GC (Allocation Failure) 2020-11-15T23:29:17.825+0800: 0.444: [ParNew: 39296K->4352K(39296K), 0.0086001 secs] 55959K->36149K(126720K), 0.0086771 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-15T23:29:17.841+0800: 0.460: [GC (Allocation Failure) 2020-11-15T23:29:17.841+0800: 0.460: [ParNew: 39287K->4350K(39296K), 0.0068524 secs] 71084K->47211K(126720K), 0.0069300 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-11-15T23:29:17.854+0800: 0.473: [GC (Allocation Failure) 2020-11-15T23:29:17.854+0800: 0.473: [ParNew: 39294K->4346K(39296K), 0.0058688 secs] 82155K->58448K(126720K), 0.0059934 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-11-15T23:29:17.861+0800: 0.479: [GC (CMS Initial Mark) [1 CMS-initial-mark: 54101K(87424K)] 58592K(126720K), 0.0001957 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.861+0800: 0.479: [CMS-concurrent-mark-start]
2020-11-15T23:29:17.862+0800: 0.481: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.862+0800: 0.481: [CMS-concurrent-preclean-start]
2020-11-15T23:29:17.863+0800: 0.481: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.863+0800: 0.481: [CMS-concurrent-abortable-preclean-start]
2020-11-15T23:29:17.868+0800: 0.487: [GC (Allocation Failure) 2020-11-15T23:29:17.868+0800: 0.487: [ParNew: 39151K->4349K(39296K), 0.0054301 secs] 93252K->69681K(126720K), 0.0055116 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.878+0800: 0.496: [GC (Allocation Failure) 2020-11-15T23:29:17.878+0800: 0.496: [ParNew: 39264K->4347K(39296K), 0.0067532 secs] 104597K->83245K(126720K), 0.0068310 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T23:29:17.892+0800: 0.511: [GC (Allocation Failure) 2020-11-15T23:29:17.893+0800: 0.511: [ParNew: 39262K->39262K(39296K), 0.0000208 secs]2020-11-15T23:29:17.893+0800: 0.511: [CMS2020-11-15T23:29:17.893+0800: 0.511: [CMS-concurrent-abortable-preclean: 0.001/0.030 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
 (concurrent mode failure): 78898K->83885K(87424K), 0.0246190 secs] 118160K->83885K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0344571 secs] [Times: user=0.02 sys=0.00, real=0.04 secs] 
2020-11-15T23:29:17.934+0800: 0.552: [GC (Allocation Failure) 2020-11-15T23:29:17.934+0800: 0.552: [ParNew: 34944K->34944K(39296K), 0.0000176 secs]2020-11-15T23:29:17.934+0800: 0.552: [CMS: 83885K->87388K(87424K), 0.0249875 secs] 118829K->97009K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0250962 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T23:29:17.959+0800: 0.577: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87388K(87424K)] 97087K(126720K), 0.0001418 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.959+0800: 0.578: [CMS-concurrent-mark-start]
2020-11-15T23:29:17.960+0800: 0.579: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.960+0800: 0.579: [CMS-concurrent-preclean-start]
2020-11-15T23:29:17.961+0800: 0.579: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.961+0800: 0.579: [CMS-concurrent-abortable-preclean-start]
2020-11-15T23:29:17.961+0800: 0.579: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.961+0800: 0.580: [GC (CMS Final Remark) [YG occupancy: 15743 K (39296 K)]2020-11-15T23:29:17.961+0800: 0.580: [Rescan (parallel) , 0.0001968 secs]2020-11-15T23:29:17.961+0800: 0.580: [weak refs processing, 0.0000092 secs]2020-11-15T23:29:17.961+0800: 0.580: [class unloading, 0.0002512 secs]2020-11-15T23:29:17.962+0800: 0.580: [scrub symbol table, 0.0003797 secs]2020-11-15T23:29:17.962+0800: 0.580: [scrub string table, 0.0001047 secs][1 CMS-remark: 87388K(87424K)] 103131K(126720K), 0.0010096 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.962+0800: 0.581: [CMS-concurrent-sweep-start]
2020-11-15T23:29:17.963+0800: 0.581: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.963+0800: 0.581: [CMS-concurrent-reset-start]
2020-11-15T23:29:17.963+0800: 0.581: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.966+0800: 0.585: [GC (Allocation Failure) 2020-11-15T23:29:17.966+0800: 0.585: [ParNew: 39098K->39098K(39296K), 0.0000203 secs]2020-11-15T23:29:17.966+0800: 0.585: [CMS: 87319K->87368K(87424K), 0.0237654 secs] 126417K->105509K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0238721 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T23:29:17.990+0800: 0.609: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87368K(87424K)] 105711K(126720K), 0.0001538 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.991+0800: 0.609: [CMS-concurrent-mark-start]
2020-11-15T23:29:17.992+0800: 0.610: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.992+0800: 0.610: [CMS-concurrent-preclean-start]
2020-11-15T23:29:17.992+0800: 0.611: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.993+0800: 0.611: [CMS-concurrent-abortable-preclean-start]
2020-11-15T23:29:17.993+0800: 0.611: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.993+0800: 0.611: [GC (CMS Final Remark) [YG occupancy: 33368 K (39296 K)]2020-11-15T23:29:17.993+0800: 0.611: [Rescan (parallel) , 0.0001104 secs]2020-11-15T23:29:17.993+0800: 0.611: [weak refs processing, 0.0000105 secs]2020-11-15T23:29:17.993+0800: 0.611: [class unloading, 0.0002894 secs]2020-11-15T23:29:17.993+0800: 0.611: [scrub symbol table, 0.0003465 secs]2020-11-15T23:29:17.993+0800: 0.612: [scrub string table, 0.0001026 secs][1 CMS-remark: 87368K(87424K)] 120737K(126720K), 0.0009310 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.994+0800: 0.612: [CMS-concurrent-sweep-start]
2020-11-15T23:29:17.994+0800: 0.612: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.994+0800: 0.612: [CMS-concurrent-reset-start]
2020-11-15T23:29:17.994+0800: 0.612: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:17.995+0800: 0.613: [GC (Allocation Failure) 2020-11-15T23:29:17.995+0800: 0.613: [ParNew: 39151K->39151K(39296K), 0.0000184 secs]2020-11-15T23:29:17.995+0800: 0.613: [CMS: 86974K->87312K(87424K), 0.0246938 secs] 126126K->111717K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0248016 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T23:29:18.020+0800: 0.638: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87312K(87424K)] 111896K(126720K), 0.0001479 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.020+0800: 0.638: [CMS-concurrent-mark-start]
2020-11-15T23:29:18.021+0800: 0.639: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.021+0800: 0.639: [CMS-concurrent-preclean-start]
2020-11-15T23:29:18.021+0800: 0.640: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.021+0800: 0.640: [CMS-concurrent-abortable-preclean-start]
2020-11-15T23:29:18.021+0800: 0.640: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.024+0800: 0.642: [Full GC (Allocation Failure) 2020-11-15T23:29:18.024+0800: 0.642: [CMS (concurrent mode failure): 87312K->86959K(87424K), 0.0244476 secs] 126533K->115483K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0245331 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T23:29:18.051+0800: 0.669: [Full GC (Allocation Failure) 2020-11-15T23:29:18.051+0800: 0.669: [CMS: 87128K->87370K(87424K), 0.0275158 secs] 126257K->117658K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0275904 secs] [Times: user=0.03 sys=0.00, real=0.03 secs] 
2020-11-15T23:29:18.078+0800: 0.697: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87370K(87424K)] 117968K(126720K), 0.0001638 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.078+0800: 0.697: [CMS-concurrent-mark-start]
2020-11-15T23:29:18.080+0800: 0.699: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.080+0800: 0.699: [CMS-concurrent-preclean-start]
2020-11-15T23:29:18.080+0800: 0.699: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.080+0800: 0.699: [CMS-concurrent-abortable-preclean-start]
2020-11-15T23:29:18.080+0800: 0.699: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.080+0800: 0.700: [GC (CMS Final Remark) [YG occupancy: 36315 K (39296 K)]2020-11-15T23:29:18.080+0800: 0.700: [Rescan (parallel) , 0.0001731 secs]2020-11-15T23:29:18.081+0800: 0.700: [weak refs processing, 0.0000085 secs]2020-11-15T23:29:18.081+0800: 0.700: [class unloading, 0.0002527 secs]2020-11-15T23:29:18.081+0800: 0.700: [scrub symbol table, 0.0006445 secs]2020-11-15T23:29:18.082+0800: 0.701: [scrub string table, 0.0000998 secs][1 CMS-remark: 87370K(87424K)] 123686K(126720K), 0.0012593 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.082+0800: 0.701: [CMS-concurrent-sweep-start]
2020-11-15T23:29:18.082+0800: 0.702: [Full GC (Allocation Failure) 2020-11-15T23:29:18.082+0800: 0.702: [CMS2020-11-15T23:29:18.083+0800: 0.702: [CMS-concurrent-sweep: 0.000/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 87370K->87297K(87424K), 0.0264499 secs] 126107K->118505K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0266175 secs] [Times: user=0.02 sys=0.00, real=0.03 secs] 
2020-11-15T23:29:18.111+0800: 0.730: [Full GC (Allocation Failure) 2020-11-15T23:29:18.111+0800: 0.730: [CMS: 87405K->87333K(87424K), 0.0211009 secs] 126688K->120644K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0211773 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-15T23:29:18.133+0800: 0.752: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87333K(87424K)] 120685K(126720K), 0.0001919 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.133+0800: 0.752: [CMS-concurrent-mark-start]
2020-11-15T23:29:18.134+0800: 0.753: [Full GC (Allocation Failure) 2020-11-15T23:29:18.134+0800: 0.753: [CMS2020-11-15T23:29:18.134+0800: 0.753: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 87333K->86887K(87424K), 0.0216307 secs] 126457K->123149K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0217199 secs] [Times: user=0.02 sys=0.00, real=0.02 secs] 
2020-11-15T23:29:18.157+0800: 0.776: [Full GC (Allocation Failure) 2020-11-15T23:29:18.157+0800: 0.776: [CMS: 87403K->87396K(87424K), 0.0114624 secs] 126692K->123505K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0115509 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-15T23:29:18.169+0800: 0.788: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87396K(87424K)] 123575K(126720K), 0.0002767 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.169+0800: 0.788: [CMS-concurrent-mark-start]
2020-11-15T23:29:18.170+0800: 0.789: [Full GC (Allocation Failure) 2020-11-15T23:29:18.170+0800: 0.789: [CMS2020-11-15T23:29:18.170+0800: 0.789: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 87396K->86992K(87424K), 0.0212516 secs] 126577K->125816K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0213412 secs] [Times: user=0.03 sys=0.00, real=0.02 secs] 
2020-11-15T23:29:18.192+0800: 0.810: [Full GC (Allocation Failure) 2020-11-15T23:29:18.192+0800: 0.811: [CMS: 87310K->87263K(87424K), 0.0047108 secs] 126567K->126059K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0047885 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.197+0800: 0.815: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87263K(87424K)] 126203K(126720K), 0.0001568 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.197+0800: 0.816: [CMS-concurrent-mark-start]
2020-11-15T23:29:18.197+0800: 0.816: [Full GC (Allocation Failure) 2020-11-15T23:29:18.197+0800: 0.816: [CMS2020-11-15T23:29:18.198+0800: 0.817: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 87263K->87055K(87424K), 0.0206823 secs] 126554K->125953K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0207559 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
2020-11-15T23:29:18.219+0800: 0.837: [Full GC (Allocation Failure) 2020-11-15T23:29:18.219+0800: 0.837: [CMS: 87199K->87199K(87424K), 0.0024750 secs] 126470K->126025K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0025427 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.222+0800: 0.841: [GC (CMS Initial Mark) [1 CMS-initial-mark: 87199K(87424K)] 126154K(126720K), 0.0001721 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.222+0800: 0.841: [CMS-concurrent-mark-start]
2020-11-15T23:29:18.223+0800: 0.842: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.223+0800: 0.842: [CMS-concurrent-preclean-start]
2020-11-15T23:29:18.223+0800: 0.842: [Full GC (Allocation Failure) 2020-11-15T23:29:18.223+0800: 0.842: [CMS2020-11-15T23:29:18.224+0800: 0.843: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 87199K->87055K(87424K), 0.0027549 secs] 126205K->125932K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0028254 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-15T23:29:18.226+0800: 0.845: [Full GC (Allocation Failure) 2020-11-15T23:29:18.226+0800: 0.845: [CMS: 87055K->87030K(87424K), 0.0180366 secs] 125932K->125907K(126720K), [Metaspace: 3348K->3348K(1056768K)], 0.0180861 secs] [Times: user=0.01 sys=0.00, real=0.02 secs] 
Heap
 par new generation   total 39296K, used 38910K [0x00000000f8000000, 0x00000000faaa0000, 0x00000000faaa0000)
  eden space 34944K, 100% used [0x00000000f8000000, 0x00000000fa220000, 0x00000000fa220000)
  from space 4352K,  91% used [0x00000000fa660000, 0x00000000faa3fa80, 0x00000000faaa0000)
  to   space 4352K,   0% used [0x00000000fa220000, 0x00000000fa220000, 0x00000000fa660000)
 concurrent mark-sweep generation total 87424K, used 87030K [0x00000000faaa0000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3379K, capacity 4500K, committed 4864K, reserved 1056768K
  class space    used 369K, capacity 388K, committed 512K, reserved 1048576K
```

</details>

<details>
<summary>gc log use xmx256m, 4243 times</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8334500k free), swap 19198244k(8122056k free)
CommandLine flags: -XX:InitialHeapSize=267326016 -XX:MaxHeapSize=268435456 -XX:MaxNewSize=89481216 -XX:MaxTenuringThreshold=6 -XX:OldPLABSize=16 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC 
2020-11-16T00:31:59.421+0800: 0.145: [GC (Allocation Failure) 2020-11-16T00:31:59.421+0800: 0.145: [ParNew: 69952K->8701K(78656K), 0.0053727 secs] 69952K->23978K(253440K), 0.0055040 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:31:59.440+0800: 0.163: [GC (Allocation Failure) 2020-11-16T00:31:59.440+0800: 0.163: [ParNew: 78653K->8704K(78656K), 0.0086005 secs] 93930K->52456K(253440K), 0.0086733 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:31:59.457+0800: 0.182: [GC (Allocation Failure) 2020-11-16T00:31:59.457+0800: 0.182: [ParNew: 78656K->8702K(78656K), 0.0101853 secs] 122408K->75025K(253440K), 0.0102663 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-16T00:31:59.490+0800: 0.214: [GC (Allocation Failure) 2020-11-16T00:31:59.490+0800: 0.214: [ParNew: 78654K->8696K(78656K), 0.0105546 secs] 144977K->96763K(253440K), 0.0106365 secs] [Times: user=0.02 sys=0.03, real=0.01 secs] 
2020-11-16T00:31:59.500+0800: 0.225: [GC (CMS Initial Mark) [1 CMS-initial-mark: 88066K(174784K)] 97454K(253440K), 0.0001397 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.501+0800: 0.225: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.503+0800: 0.228: [CMS-concurrent-mark: 0.003/0.003 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.503+0800: 0.228: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.504+0800: 0.228: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.504+0800: 0.228: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.513+0800: 0.237: [GC (Allocation Failure) 2020-11-16T00:31:59.513+0800: 0.237: [ParNew: 78648K->8701K(78656K), 0.0123491 secs] 166715K->121555K(253440K), 0.0124186 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-11-16T00:31:59.537+0800: 0.262: [GC (Allocation Failure) 2020-11-16T00:31:59.537+0800: 0.262: [ParNew: 78653K->8702K(78656K), 0.0131484 secs] 191507K->145234K(253440K), 0.0132413 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-16T00:31:59.562+0800: 0.287: [GC (Allocation Failure) 2020-11-16T00:31:59.562+0800: 0.287: [ParNew: 78525K->8699K(78656K), 0.0114624 secs] 215057K->165884K(253440K), 0.0115163 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:31:59.586+0800: 0.311: [GC (Allocation Failure) 2020-11-16T00:31:59.586+0800: 0.311: [ParNew: 78581K->78581K(78656K), 0.0000164 secs]2020-11-16T00:31:59.587+0800: 0.311: [CMS2020-11-16T00:31:59.587+0800: 0.311: [CMS-concurrent-abortable-preclean: 0.002/0.083 secs] [Times: user=0.09 sys=0.03, real=0.08 secs] 
 (concurrent mode failure): 157185K->152271K(174784K), 0.0384834 secs] 235766K->152271K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0386255 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-16T00:31:59.636+0800: 0.361: [GC (Allocation Failure) 2020-11-16T00:31:59.637+0800: 0.361: [ParNew: 69952K->8701K(78656K), 0.0073157 secs] 222223K->174312K(253440K), 0.0073748 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:31:59.644+0800: 0.368: [GC (CMS Initial Mark) [1 CMS-initial-mark: 165610K(174784K)] 175095K(253440K), 0.0001069 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.644+0800: 0.368: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.646+0800: 0.370: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.646+0800: 0.370: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.646+0800: 0.370: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.646+0800: 0.370: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.646+0800: 0.370: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.646+0800: 0.370: [GC (CMS Final Remark) [YG occupancy: 23256 K (78656 K)]2020-11-16T00:31:59.646+0800: 0.370: [Rescan (parallel) , 0.0001440 secs]2020-11-16T00:31:59.646+0800: 0.371: [weak refs processing, 0.0000102 secs]2020-11-16T00:31:59.646+0800: 0.371: [class unloading, 0.0002407 secs]2020-11-16T00:31:59.647+0800: 0.371: [scrub symbol table, 0.0003266 secs]2020-11-16T00:31:59.647+0800: 0.371: [scrub string table, 0.0000869 secs][1 CMS-remark: 165610K(174784K)] 188867K(253440K), 0.0008673 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.647+0800: 0.371: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.647+0800: 0.372: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.647+0800: 0.372: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.648+0800: 0.372: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.656+0800: 0.380: [GC (Allocation Failure) 2020-11-16T00:31:59.656+0800: 0.380: [ParNew (promotion failed): 78580K->78580K(78656K), 0.0070419 secs]2020-11-16T00:31:59.663+0800: 0.387: [CMS: 173713K->174495K(174784K), 0.0389248 secs] 236298K->178896K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0460593 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-16T00:31:59.702+0800: 0.426: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174495K(174784K)] 179040K(253440K), 0.0001263 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.702+0800: 0.426: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.704+0800: 0.428: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.704+0800: 0.428: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.705+0800: 0.429: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.705+0800: 0.429: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.705+0800: 0.429: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.732+0800: 0.456: [GC (CMS Final Remark) [YG occupancy: 22678 K (78656 K)]2020-11-16T00:31:59.732+0800: 0.456: [Rescan (parallel) , 0.0001462 secs]2020-11-16T00:31:59.732+0800: 0.456: [weak refs processing, 0.0000110 secs]2020-11-16T00:31:59.732+0800: 0.456: [class unloading, 0.0002105 secs]2020-11-16T00:31:59.732+0800: 0.456: [scrub symbol table, 0.0003282 secs]2020-11-16T00:31:59.732+0800: 0.456: [scrub string table, 0.0000895 secs][1 CMS-remark: 174495K(174784K)] 197173K(253440K), 0.0008574 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.733+0800: 0.457: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.734+0800: 0.458: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.734+0800: 0.458: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.734+0800: 0.458: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.741+0800: 0.465: [GC (Allocation Failure) 2020-11-16T00:31:59.741+0800: 0.465: [ParNew: 78633K->78633K(78656K), 0.0000147 secs]2020-11-16T00:31:59.741+0800: 0.465: [CMS: 174423K->174307K(174784K), 0.0394534 secs] 253057K->195079K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0395450 secs] [Times: user=0.05 sys=0.00, real=0.04 secs] 
2020-11-16T00:31:59.781+0800: 0.505: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174307K(174784K)] 195367K(253440K), 0.0001464 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.781+0800: 0.505: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.783+0800: 0.507: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.783+0800: 0.507: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.784+0800: 0.508: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.784+0800: 0.508: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.784+0800: 0.508: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.784+0800: 0.508: [GC (CMS Final Remark) [YG occupancy: 36932 K (78656 K)]2020-11-16T00:31:59.784+0800: 0.508: [Rescan (parallel) , 0.0001844 secs]2020-11-16T00:31:59.784+0800: 0.508: [weak refs processing, 0.0000077 secs]2020-11-16T00:31:59.784+0800: 0.508: [class unloading, 0.0002193 secs]2020-11-16T00:31:59.785+0800: 0.509: [scrub symbol table, 0.0003102 secs]2020-11-16T00:31:59.785+0800: 0.509: [scrub string table, 0.0000841 secs][1 CMS-remark: 174307K(174784K)] 211240K(253440K), 0.0008505 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.785+0800: 0.509: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.786+0800: 0.509: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.786+0800: 0.509: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.786+0800: 0.510: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.792+0800: 0.516: [GC (Allocation Failure) 2020-11-16T00:31:59.792+0800: 0.516: [ParNew: 78424K->78424K(78656K), 0.0000156 secs]2020-11-16T00:31:59.792+0800: 0.516: [CMS: 174307K->174587K(174784K), 0.0388982 secs] 252732K->205129K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0389914 secs] [Times: user=0.05 sys=0.00, real=0.04 secs] 
2020-11-16T00:31:59.831+0800: 0.555: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174587K(174784K)] 205488K(253440K), 0.0001376 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.831+0800: 0.555: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.832+0800: 0.557: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.832+0800: 0.557: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.833+0800: 0.558: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.833+0800: 0.558: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.833+0800: 0.558: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.834+0800: 0.558: [GC (CMS Final Remark) [YG occupancy: 47294 K (78656 K)]2020-11-16T00:31:59.834+0800: 0.558: [Rescan (parallel) , 0.0001983 secs]2020-11-16T00:31:59.834+0800: 0.559: [weak refs processing, 0.0000098 secs]2020-11-16T00:31:59.834+0800: 0.559: [class unloading, 0.0002382 secs]2020-11-16T00:31:59.834+0800: 0.559: [scrub symbol table, 0.0003254 secs]2020-11-16T00:31:59.834+0800: 0.559: [scrub string table, 0.0001917 secs][1 CMS-remark: 174587K(174784K)] 221881K(253440K), 0.0010257 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.835+0800: 0.559: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.835+0800: 0.560: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.835+0800: 0.560: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.835+0800: 0.560: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.839+0800: 0.564: [GC (Allocation Failure) 2020-11-16T00:31:59.839+0800: 0.564: [ParNew: 78251K->78251K(78656K), 0.0000137 secs]2020-11-16T00:31:59.839+0800: 0.564: [CMS: 173628K->174782K(174784K), 0.0378544 secs] 251879K->209980K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0379393 secs] [Times: user=0.05 sys=0.00, real=0.04 secs] 
2020-11-16T00:31:59.877+0800: 0.602: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174782K(174784K)] 210650K(253440K), 0.0001349 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.877+0800: 0.602: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.879+0800: 0.603: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.879+0800: 0.604: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.880+0800: 0.604: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.880+0800: 0.604: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.880+0800: 0.604: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.880+0800: 0.605: [GC (CMS Final Remark) [YG occupancy: 55180 K (78656 K)]2020-11-16T00:31:59.880+0800: 0.605: [Rescan (parallel) , 0.0001408 secs]2020-11-16T00:31:59.880+0800: 0.605: [weak refs processing, 0.0000080 secs]2020-11-16T00:31:59.880+0800: 0.605: [class unloading, 0.0002040 secs]2020-11-16T00:31:59.880+0800: 0.605: [scrub symbol table, 0.0003087 secs]2020-11-16T00:31:59.881+0800: 0.605: [scrub string table, 0.0000853 secs][1 CMS-remark: 174782K(174784K)] 229962K(253440K), 0.0007943 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.881+0800: 0.605: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.881+0800: 0.606: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.881+0800: 0.606: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.881+0800: 0.606: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.885+0800: 0.609: [GC (Allocation Failure) 2020-11-16T00:31:59.885+0800: 0.609: [ParNew: 78590K->78590K(78656K), 0.0000165 secs]2020-11-16T00:31:59.885+0800: 0.609: [CMS: 174782K->174640K(174784K), 0.0362587 secs] 253372K->214800K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0363521 secs] [Times: user=0.05 sys=0.00, real=0.04 secs] 
2020-11-16T00:31:59.921+0800: 0.646: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174640K(174784K)] 214960K(253440K), 0.0001457 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.922+0800: 0.646: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.923+0800: 0.648: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.923+0800: 0.648: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.924+0800: 0.649: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.924+0800: 0.649: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.924+0800: 0.649: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.924+0800: 0.649: [GC (CMS Final Remark) [YG occupancy: 59596 K (78656 K)]2020-11-16T00:31:59.924+0800: 0.649: [Rescan (parallel) , 0.0001646 secs]2020-11-16T00:31:59.925+0800: 0.649: [weak refs processing, 0.0000086 secs]2020-11-16T00:31:59.925+0800: 0.649: [class unloading, 0.0002293 secs]2020-11-16T00:31:59.925+0800: 0.649: [scrub symbol table, 0.0003048 secs]2020-11-16T00:31:59.925+0800: 0.650: [scrub string table, 0.0000854 secs][1 CMS-remark: 174640K(174784K)] 234237K(253440K), 0.0008461 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.925+0800: 0.650: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.926+0800: 0.650: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.926+0800: 0.650: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.926+0800: 0.650: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.928+0800: 0.652: [GC (Allocation Failure) 2020-11-16T00:31:59.928+0800: 0.652: [ParNew: 78562K->78562K(78656K), 0.0000139 secs]2020-11-16T00:31:59.928+0800: 0.652: [CMS: 174640K->174758K(174784K), 0.0366517 secs] 253203K->222019K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0367368 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-16T00:31:59.965+0800: 0.689: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174758K(174784K)] 222267K(253440K), 0.0001759 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.965+0800: 0.689: [CMS-concurrent-mark-start]
2020-11-16T00:31:59.966+0800: 0.691: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.966+0800: 0.691: [CMS-concurrent-preclean-start]
2020-11-16T00:31:59.967+0800: 0.692: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.967+0800: 0.692: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:31:59.967+0800: 0.692: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.967+0800: 0.692: [GC (CMS Final Remark) [YG occupancy: 66690 K (78656 K)]2020-11-16T00:31:59.968+0800: 0.692: [Rescan (parallel) , 0.0001456 secs]2020-11-16T00:31:59.968+0800: 0.692: [weak refs processing, 0.0000080 secs]2020-11-16T00:31:59.968+0800: 0.692: [class unloading, 0.0002096 secs]2020-11-16T00:31:59.968+0800: 0.692: [scrub symbol table, 0.0003979 secs]2020-11-16T00:31:59.968+0800: 0.693: [scrub string table, 0.0000873 secs][1 CMS-remark: 174758K(174784K)] 241448K(253440K), 0.0009044 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.968+0800: 0.693: [CMS-concurrent-sweep-start]
2020-11-16T00:31:59.969+0800: 0.693: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.969+0800: 0.693: [CMS-concurrent-reset-start]
2020-11-16T00:31:59.969+0800: 0.693: [CMS-concurrent-reset: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:31:59.970+0800: 0.695: [GC (Allocation Failure) 2020-11-16T00:31:59.970+0800: 0.695: [ParNew: 78433K->78433K(78656K), 0.0000144 secs]2020-11-16T00:31:59.970+0800: 0.695: [CMS: 174758K->174535K(174784K), 0.0420298 secs] 253191K->228686K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0421474 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-16T00:32:00.013+0800: 0.737: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174535K(174784K)] 229392K(253440K), 0.0003303 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.013+0800: 0.737: [CMS-concurrent-mark-start]
2020-11-16T00:32:00.015+0800: 0.739: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.03 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.015+0800: 0.739: [CMS-concurrent-preclean-start]
2020-11-16T00:32:00.016+0800: 0.740: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.016+0800: 0.740: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:32:00.016+0800: 0.740: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.016+0800: 0.741: [GC (CMS Final Remark) [YG occupancy: 77915 K (78656 K)]2020-11-16T00:32:00.016+0800: 0.741: [Rescan (parallel) , 0.0001533 secs]2020-11-16T00:32:00.016+0800: 0.741: [weak refs processing, 0.0000096 secs]2020-11-16T00:32:00.016+0800: 0.741: [class unloading, 0.0003647 secs]2020-11-16T00:32:00.017+0800: 0.741: [scrub symbol table, 0.0003790 secs]2020-11-16T00:32:00.017+0800: 0.741: [scrub string table, 0.0000955 secs][1 CMS-remark: 174535K(174784K)] 252450K(253440K), 0.0010604 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.017+0800: 0.742: [CMS-concurrent-sweep-start]
2020-11-16T00:32:00.018+0800: 0.742: [Full GC (Allocation Failure) 2020-11-16T00:32:00.018+0800: 0.742: [CMS2020-11-16T00:32:00.018+0800: 0.742: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 174661K->174756K(174784K), 0.0404581 secs] 253301K->228226K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0405194 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-16T00:32:00.062+0800: 0.786: [Full GC (Allocation Failure) 2020-11-16T00:32:00.062+0800: 0.786: [CMS: 174756K->174748K(174784K), 0.0363144 secs] 253310K->234420K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0363799 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-16T00:32:00.098+0800: 0.822: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174748K(174784K)] 234564K(253440K), 0.0001564 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.098+0800: 0.822: [CMS-concurrent-mark-start]
2020-11-16T00:32:00.100+0800: 0.824: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.100+0800: 0.824: [CMS-concurrent-preclean-start]
2020-11-16T00:32:00.101+0800: 0.825: [Full GC (Allocation Failure) 2020-11-16T00:32:00.101+0800: 0.825: [CMS2020-11-16T00:32:00.101+0800: 0.825: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 174748K->174759K(174784K), 0.0366133 secs] 253301K->237011K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0366744 secs] [Times: user=0.03 sys=0.00, real=0.04 secs] 
2020-11-16T00:32:00.140+0800: 0.864: [Full GC (Allocation Failure) 2020-11-16T00:32:00.140+0800: 0.864: [CMS: 174759K->174776K(174784K), 0.0465954 secs] 253159K->238655K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0466675 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-16T00:32:00.187+0800: 0.911: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174776K(174784K)] 239011K(253440K), 0.0001602 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.187+0800: 0.911: [CMS-concurrent-mark-start]
2020-11-16T00:32:00.189+0800: 0.913: [CMS-concurrent-mark: 0.002/0.002 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.189+0800: 0.913: [CMS-concurrent-preclean-start]
2020-11-16T00:32:00.189+0800: 0.914: [Full GC (Allocation Failure) 2020-11-16T00:32:00.189+0800: 0.914: [CMS2020-11-16T00:32:00.189+0800: 0.914: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
 (concurrent mode failure): 174776K->174431K(174784K), 0.0520864 secs] 253062K->238523K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0522165 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-16T00:32:00.245+0800: 0.970: [Full GC (Allocation Failure) 2020-11-16T00:32:00.245+0800: 0.970: [CMS: 174620K->174572K(174784K), 0.0543632 secs] 253094K->242036K(253440K), [Metaspace: 3348K->3348K(1056768K)], 0.0544555 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
2020-11-16T00:32:00.300+0800: 1.024: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174572K(174784K)] 242220K(253440K), 0.0001819 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.300+0800: 1.024: [CMS-concurrent-mark-start]
2020-11-16T00:32:00.302+0800: 1.026: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.302+0800: 1.026: [CMS-concurrent-preclean-start]
2020-11-16T00:32:00.303+0800: 1.027: [CMS-concurrent-preclean: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.303+0800: 1.027: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:32:00.303+0800: 1.027: [CMS-concurrent-abortable-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.303+0800: 1.027: [Full GC (Allocation Failure) 2020-11-16T00:32:00.303+0800: 1.027: [CMS (concurrent mode failure): 174572K->174733K(174784K), 0.0549770 secs] 253173K->243235K(253440K), [Metaspace: 3449K->3449K(1056768K)], 0.0550504 secs] [Times: user=0.06 sys=0.00, real=0.06 secs] 
2020-11-16T00:32:00.364+0800: 1.088: [Full GC (Allocation Failure) 2020-11-16T00:32:00.364+0800: 1.088: [CMS: 174733K->174773K(174784K), 0.0521904 secs] 253172K->244626K(253440K), [Metaspace: 3528K->3528K(1056768K)], 0.0522860 secs] [Times: user=0.05 sys=0.00, real=0.05 secs] 
2020-11-16T00:32:00.417+0800: 1.141: [GC (CMS Initial Mark) [1 CMS-initial-mark: 174773K(174784K)] 245013K(253440K), 0.0002512 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.417+0800: 1.141: [CMS-concurrent-mark-start]
2020-11-16T00:32:00.419+0800: 1.143: [CMS-concurrent-mark: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:32:00.419+0800: 1.143: [CMS-concurrent-preclean-start]
Heap
 par new generation   total 78656K, used 70405K [0x00000000f0000000, 0x00000000f5550000, 0x00000000f5550000)
  eden space 69952K, 100% used [0x00000000f0000000, 0x00000000f4450000, 0x00000000f4450000)
  from space 8704K,   5% used [0x00000000f4cd0000, 0x00000000f4d417c8, 0x00000000f5550000)
  to   space 8704K,   0% used [0x00000000f4450000, 0x00000000f4450000, 0x00000000f4cd0000)
 concurrent mark-sweep generation total 174784K, used 174773K [0x00000000f5550000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3630K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 400K, capacity 428K, committed 512K, reserved 1048576K
```

</details>

<details>
<summary>gc log use xmx1g, 10206 times</summary>

```java
Java HotSpot(TM) 64-Bit Server VM (25.271-b09) for windows-amd64 JRE (1.8.0_271-b09), built on Sep 16 2020 19:14:59 by "" with MS VC++ 15.9 (VS2017)
Memory: 4k page, physical 16707876k(8867604k free), swap 19198244k(8000488k free)
CommandLine flags: -XX:InitialHeapSize=267326016 -XX:MaxHeapSize=1073741824 -XX:MaxNewSize=348966912 -XX:MaxTenuringThreshold=6 -XX:OldPLABSize=16 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:+UseConcMarkSweepGC -XX:-UseLargePagesIndividualAllocation -XX:+UseParNewGC 
2020-11-16T00:35:43.964+0800: 0.169: [GC (Allocation Failure) 2020-11-16T00:35:43.964+0800: 0.169: [ParNew: 69920K->8703K(78656K), 0.0061784 secs] 69920K->28138K(253440K), 0.0069175 secs] [Times: user=0.03 sys=0.03, real=0.01 secs] 
2020-11-16T00:35:43.986+0800: 0.191: [GC (Allocation Failure) 2020-11-16T00:35:43.986+0800: 0.191: [ParNew: 78655K->8702K(78656K), 0.0083848 secs] 98090K->56082K(253440K), 0.0084532 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.004+0800: 0.209: [GC (Allocation Failure) 2020-11-16T00:35:44.004+0800: 0.209: [ParNew: 78572K->8704K(78656K), 0.0114795 secs] 125951K->81884K(253440K), 0.0115358 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.025+0800: 0.230: [GC (Allocation Failure) 2020-11-16T00:35:44.025+0800: 0.230: [ParNew: 78656K->8703K(78656K), 0.0118112 secs] 151836K->107773K(253440K), 0.0118784 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.037+0800: 0.242: [GC (CMS Initial Mark) [1 CMS-initial-mark: 99069K(174784K)] 108179K(253440K), 0.0001389 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:35:44.037+0800: 0.242: [CMS-concurrent-mark-start]
2020-11-16T00:35:44.045+0800: 0.250: [CMS-concurrent-mark: 0.008/0.008 secs] [Times: user=0.01 sys=0.01, real=0.01 secs] 
2020-11-16T00:35:44.045+0800: 0.250: [CMS-concurrent-preclean-start]
2020-11-16T00:35:44.046+0800: 0.251: [CMS-concurrent-preclean: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:35:44.046+0800: 0.251: [CMS-concurrent-abortable-preclean-start]
2020-11-16T00:35:44.048+0800: 0.253: [GC (Allocation Failure) 2020-11-16T00:35:44.048+0800: 0.253: [ParNew: 78655K->8703K(78656K), 0.0140631 secs] 177725K->134441K(253440K), 0.0141255 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.073+0800: 0.278: [GC (Allocation Failure) 2020-11-16T00:35:44.073+0800: 0.278: [ParNew: 78655K->8703K(78656K), 0.0130519 secs] 204393K->160956K(253440K), 0.0131230 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.096+0800: 0.301: [GC (Allocation Failure) 2020-11-16T00:35:44.096+0800: 0.301: [ParNew: 78633K->8688K(78656K), 0.0124942 secs] 230885K->183944K(254452K), 0.0125504 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.120+0800: 0.324: [GC (Allocation Failure) 2020-11-16T00:35:44.120+0800: 0.324: [ParNew: 78379K->8702K(78656K), 0.0135272 secs] 253635K->209740K(280332K), 0.0135867 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-11-16T00:35:44.146+0800: 0.350: [GC (Allocation Failure) 2020-11-16T00:35:44.146+0800: 0.350: [ParNew: 78654K->8699K(78656K), 0.0119892 secs] 279692K->230206K(300816K), 0.0120832 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.169+0800: 0.374: [GC (Allocation Failure) 2020-11-16T00:35:44.169+0800: 0.375: [ParNew: 78564K->8703K(78656K), 0.0116962 secs] 300072K->254094K(324664K), 0.0117659 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.193+0800: 0.398: [GC (Allocation Failure) 2020-11-16T00:35:44.193+0800: 0.398: [ParNew: 78655K->8701K(78656K), 0.0109869 secs] 324046K->276547K(347116K), 0.0110496 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.215+0800: 0.420: [GC (Allocation Failure) 2020-11-16T00:35:44.215+0800: 0.421: [ParNew: 78198K->8699K(78656K), 0.0116716 secs] 346045K->298769K(369396K), 0.0117424 secs] [Times: user=0.00 sys=0.03, real=0.01 secs] 
2020-11-16T00:35:44.238+0800: 0.443: [GC (Allocation Failure) 2020-11-16T00:35:44.238+0800: 0.443: [ParNew: 78651K->8701K(78656K), 0.0106674 secs] 368721K->319622K(390272K), 0.0107992 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.261+0800: 0.466: [GC (Allocation Failure) 2020-11-16T00:35:44.261+0800: 0.466: [ParNew: 78586K->8703K(78656K), 0.0123750 secs] 389508K->343206K(413940K), 0.0124579 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.284+0800: 0.489: [GC (Allocation Failure) 2020-11-16T00:35:44.284+0800: 0.489: [ParNew: 78655K->8700K(78656K), 0.0139863 secs] 413158K->369470K(440080K), 0.0140490 secs] [Times: user=0.03 sys=0.01, real=0.01 secs] 
2020-11-16T00:35:44.309+0800: 0.514: [GC (Allocation Failure) 2020-11-16T00:35:44.309+0800: 0.514: [ParNew: 78647K->8703K(78656K), 0.0116395 secs] 439416K->391364K(462104K), 0.0116979 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.331+0800: 0.536: [GC (Allocation Failure) 2020-11-16T00:35:44.331+0800: 0.536: [ParNew: 78316K->8700K(78656K), 0.0119791 secs] 460977K->413430K(484088K), 0.0120338 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.353+0800: 0.558: [GC (Allocation Failure) 2020-11-16T00:35:44.353+0800: 0.558: [ParNew: 78375K->8703K(78656K), 0.0137041 secs] 483105K->441283K(512068K), 0.0137527 secs] [Times: user=0.03 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.378+0800: 0.582: [GC (Allocation Failure) 2020-11-16T00:35:44.378+0800: 0.582: [ParNew: 78655K->8703K(78656K), 0.0118343 secs] 511235K->464594K(535348K), 0.0118857 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.400+0800: 0.605: [GC (Allocation Failure) 2020-11-16T00:35:44.400+0800: 0.605: [ParNew: 78655K->8702K(78656K), 0.0110531 secs] 534546K->485600K(556264K), 0.0111083 secs] [Times: user=0.05 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.422+0800: 0.627: [GC (Allocation Failure) 2020-11-16T00:35:44.422+0800: 0.627: [ParNew: 78631K->8695K(78656K), 0.0106083 secs] 555529K->505405K(576172K), 0.0106697 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.443+0800: 0.647: [GC (Allocation Failure) 2020-11-16T00:35:44.443+0800: 0.648: [ParNew: 78647K->8703K(78656K), 0.0135708 secs] 575357K->531703K(602400K), 0.0136220 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.468+0800: 0.673: [GC (Allocation Failure) 2020-11-16T00:35:44.468+0800: 0.673: [ParNew: 78611K->8699K(78656K), 0.0122187 secs] 601611K->555550K(626392K), 0.0122965 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.492+0800: 0.696: [GC (Allocation Failure) 2020-11-16T00:35:44.492+0800: 0.696: [ParNew: 78651K->8701K(78656K), 0.0121915 secs] 625502K->578625K(649460K), 0.0122729 secs] [Times: user=0.02 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.515+0800: 0.719: [GC (Allocation Failure) 2020-11-16T00:35:44.515+0800: 0.719: [ParNew: 78653K->8699K(78656K), 0.0104495 secs] 648577K->598937K(669720K), 0.0105295 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
2020-11-16T00:35:44.535+0800: 0.741: [GC (Allocation Failure) 2020-11-16T00:35:44.535+0800: 0.741: [ParNew: 78651K->8703K(78656K), 0.0105198 secs] 668889K->616880K(687648K), 0.0106021 secs] [Times: user=0.02 sys=0.03, real=0.01 secs] 
2020-11-16T00:35:44.556+0800: 0.761: [GC (Allocation Failure) 2020-11-16T00:35:44.556+0800: 0.761: [ParNew: 78655K->8695K(78656K), 0.0135903 secs] 686832K->643691K(714636K), 0.0136650 secs] [Times: user=0.01 sys=0.03, real=0.01 secs] 
2020-11-16T00:35:44.580+0800: 0.785: [GC (Allocation Failure) 2020-11-16T00:35:44.580+0800: 0.785: [ParNew: 78623K->8702K(78656K), 0.0128612 secs] 713619K->666500K(737348K), 0.0129269 secs] [Times: user=0.03 sys=0.02, real=0.01 secs] 
2020-11-16T00:35:44.605+0800: 0.810: [GC (Allocation Failure) 2020-11-16T00:35:44.605+0800: 0.810: [ParNew: 78148K->8703K(78656K), 0.0171577 secs] 735946K->690159K(760924K), 0.0172262 secs] [Times: user=0.05 sys=0.00, real=0.02 secs] 
2020-11-16T00:35:44.636+0800: 0.841: [GC (Allocation Failure) 2020-11-16T00:35:44.636+0800: 0.841: [ParNew: 78655K->8696K(78656K), 0.0154689 secs] 760111K->715184K(786028K), 0.0156219 secs] [Times: user=0.03 sys=0.01, real=0.02 secs] 
2020-11-16T00:35:44.652+0800: 0.856: [CMS-concurrent-abortable-preclean: 0.030/0.606 secs] [Times: user=0.94 sys=0.28, real=0.61 secs] 
2020-11-16T00:35:44.652+0800: 0.857: [GC (CMS Final Remark) [YG occupancy: 10301 K (78656 K)]2020-11-16T00:35:44.652+0800: 0.857: [Rescan (parallel) , 0.0004562 secs]2020-11-16T00:35:44.652+0800: 0.857: [weak refs processing, 0.0203387 secs]2020-11-16T00:35:44.673+0800: 0.877: [class unloading, 0.0002213 secs]2020-11-16T00:35:44.673+0800: 0.878: [scrub symbol table, 0.0003153 secs]2020-11-16T00:35:44.673+0800: 0.878: [scrub string table, 0.0000973 secs][1 CMS-remark: 706487K(707372K)] 716789K(786028K), 0.0215226 secs] [Times: user=0.00 sys=0.00, real=0.02 secs] 
2020-11-16T00:35:44.673+0800: 0.878: [CMS-concurrent-sweep-start]
2020-11-16T00:35:44.674+0800: 0.879: [CMS-concurrent-sweep: 0.000/0.000 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:35:44.674+0800: 0.879: [CMS-concurrent-reset-start]
2020-11-16T00:35:44.675+0800: 0.880: [CMS-concurrent-reset: 0.001/0.001 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2020-11-16T00:35:44.685+0800: 0.890: [GC (Allocation Failure) 2020-11-16T00:35:44.685+0800: 0.890: [ParNew: 78440K->78440K(78656K), 0.0000145 secs]2020-11-16T00:35:44.685+0800: 0.890: [CMS: 694099K->325973K(707840K), 0.0673913 secs] 772539K->325973K(786496K), [Metaspace: 3348K->3348K(1056768K)], 0.0680315 secs] [Times: user=0.06 sys=0.00, real=0.07 secs] 
2020-11-16T00:35:44.855+0800: 1.059: [GC (Allocation Failure) 2020-11-16T00:35:44.855+0800: 1.059: [ParNew: 272640K->34046K(306688K), 0.0230957 secs] 598613K->408590K(1014528K), 0.0231472 secs] [Times: user=0.06 sys=0.02, real=0.02 secs] 
2020-11-16T00:35:44.920+0800: 1.125: [GC (Allocation Failure) 2020-11-16T00:35:44.920+0800: 1.125: [ParNew: 306686K->34048K(306688K), 0.0308591 secs] 681230K->490193K(1014528K), 0.0309346 secs] [Times: user=0.02 sys=0.02, real=0.03 secs] 
2020-11-16T00:35:44.951+0800: 1.157: [GC (CMS Initial Mark) [1 CMS-initial-mark: 456145K(707840K)] 490334K(1014528K), 0.0002878 secs] [Times: user=0.02 sys=0.00, real=0.00 secs] 
2020-11-16T00:35:44.952+0800: 1.157: [CMS-concurrent-mark-start]
Heap
 par new generation   total 306688K, used 42259K [0x00000000c0000000, 0x00000000d4cc0000, 0x00000000d4cc0000)
  eden space 272640K,   3% used [0x00000000c0000000, 0x00000000c0804c30, 0x00000000d0a40000)
  from space 34048K, 100% used [0x00000000d0a40000, 0x00000000d2b80000, 0x00000000d2b80000)
  to   space 34048K,   0% used [0x00000000d2b80000, 0x00000000d2b80000, 0x00000000d4cc0000)
 concurrent mark-sweep generation total 707840K, used 456145K [0x00000000d4cc0000, 0x0000000100000000, 0x0000000100000000)
 Metaspace       used 3860K, capacity 4540K, committed 4864K, reserved 1056768K
  class space    used 426K, capacity 428K, committed 512K, reserved 1048576K

```
</details>

## G1 GC

* -XX:+UseG1GC
* -XX:G1HeapRegionSize，設定每個Region大小，預設為記憶體的1/2000
* -XX:G1MaxNewSizePercent，最大年輕代占用Heap百分比
* -XX:G1NewSizePercent，初始年輕代占用Heap百分比
* -XX:ConcGCThreads，執行GC的Threads，預設為CPU內核的1/4
* -XX:+InitiatingHeapOccupancyPercent，設定老年代占用多少要執行GC，預設為45%
* -XX:G1HeapWastePercent，設定G1停止回收的最小內存大小，預設為5%
* -XX:+GCTimeRatio，百分比=100(1+GCTimeRatio)，表示花在GC線程上的時間比率
* -XX:MaxGCPauseMills，預期每次執行GC最大的暫停時間，預設為200
* G1 GC 步驟
  * Evacuation Pause (young) - 年輕代模式轉移暫停
  * Concurrent Marking
    * initial-mark
    * concurrent-root-region-scan
    * concurrent-mark
    * remark
    * cleanup
  * Evacuation Pause (mixed) - 转移暂停: 混合模式


<details>
<summary>gc log use xmx128m, OOM</summary>

temp

</details>

<details>
<summary>gc log use xmx256m, OOM</summary>

temp

</details>

<summary>gc log use xmx1g, 17978 times</summary>

temp

</details>

<summary>gc log use xmx2g, 15093 times</summary>

temp

</details>