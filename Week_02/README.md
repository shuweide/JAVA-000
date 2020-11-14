# 學習筆記

## 參考資料
* [如何选择合适的 GC 时间 —— USER, SYS, REAL？](https://my.oschina.net/dabird/blog/714569)
* [What do 'real', 'user' and 'sys' mean in the output of time(1)?](https://stackoverflow.com/questions/556405/what-do-real-user-and-sys-mean-in-the-output-of-time1?lq=1)

## GC觀念

### VMOptions
* Print GC：-XX:+PrintGC
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

## SerialGC

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