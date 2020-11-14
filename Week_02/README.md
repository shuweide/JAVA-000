# 學習筆記

## 參考資料
* [如何选择合适的 GC 时间 —— USER, SYS, REAL？](https://my.oschina.net/dabird/blog/714569)
* [What do 'real', 'user' and 'sys' mean in the output of time(1)?](https://stackoverflow.com/questions/556405/what-do-real-user-and-sys-mean-in-the-output-of-time1?lq=1)

## GC 

### VMOptions
* Print GC：-XX:+PrintGC
* Print GC timestamp：-XX:PrintGCDateStamps
* Export GC log：-Xloggc:**filepath**

### 觀念
* user - User is the amount of CPU time spent in user-mode code (outside the kernel) within the process. This is only actual CPU time used in executing the process. Other processes and time the process spends blocked do not count towards this figure.
* sys - Sys is the amount of CPU time spent in the kernel within the process. This means executing CPU time spent in system calls within the kernel, as opposed to library code, which is still running in user-space. Like ‘user’, this is only CPU time used by the process.
* real - Real is wall clock time – time from start to finish of the call. This is all elapsed time including time slices used by other processes and time the process spends blocked (for example if it is waiting for I/O to complete).

## SerialGC

* Use SerialGC：-XX:+UseSerialGC
* SerialGC使用單線程處理GC，因此user + sys = real。

<details>
<summary>gc log</summary>

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