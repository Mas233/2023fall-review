# ArkCompiler客观题

arkCompiler相关客观题参考以下两处相关文档

https://developer.harmonyos.com/cn/develop/arkCompiler/

https://blog.csdn.net/harmonyosdev/article/details/121510706

因为都是文本层面的理解，不做多余解释。

**单选：**

1. ArkCompiler使用（）技术提供更高强度的混淆与保护，使得HarmonyOS应用包中装载的是多重混淆后的字节码，有效提高了应用代码安全的强度。

   A. 复杂编译技术

   B. 防范式保护技术

   C. 深层混淆技术

   D. 多种混淆技术

   **答案：D**

2. ArkCompiler运行时在HarmonyOS上提供了Worker API支持（）。

   A. 并发编程

   B. UI测试框架

   C. 单元测试框架

   D. 各类开发插件

   **答案：A**

3. AOT(Ahead-of-Time)  Compiler利用（）信息直接将字节码编译生成优化机器码，让应用启动即可运行高性能代码，提升应用启动和运行性能。

   A. 所有类型

   B. 动态类型

   C. 静态类型

   D. 混合类型

   **答案：C**

4. 下列说法中，正确的是：

   A. ArkCompiler包含编译器、工具链、运行时等关键部件。

   B. ArkCompiler工具链实现对应语言的前端编译器，将前端开发框架的高级语言编译成统一的JS文件。

   C. ArkCompiler运行时和普通JS运行时的过程完全一样。

   D. ArkCompiler中，前端编译器不会在运行前做更多更复杂的算法优化。

   **答案：A**

5. 下列说法中，正确的是：

   A. ArkCompiler字节码不可以屏蔽设备的差异。

   B. 前端编译器的主要作用是在Client侧把源码生成字节码文件。

   C. ArkCompiler采用的是基于内存的字节码格式

   D. 相比常见的JavaScript运行时，可以把端侧的编译解析过程提前到发布前，提升程序的启动性能。

   **答案：D**

6. 下列说法中，正确的是：

   A. ArkCompiler寄存器要求能够放置对象引用和基本类型，宽度采用32位。

   B. 寄存器的作用域是以字节长度为范围。

   C. 在字节码指令编码中，寄存器索引支持4位、8位以及32位的变长编码

   D. 累加寄存器，俗称累加器，是一个特殊的寄存器，被指令隐含使用。

   **答案：D**

7. 下列说法中，正确的是：

   A. ArkCompiler字节码提供对32位（i32）和64位（i64）整型数值的寄存器操作支持，8位和16位数值通过扩展到64位来模拟。

   B. 在ArkCompiler字节码中，上一条指令利用累加器作为结果输出，下一条指令将此累加器作为输入，可以有效改善指令密度，减小字节码的尺寸。

   C. ArkCompiler根据其执行的语言支持递进式的类型系统。

   D. ArkCompiler运行时，被分为了核心运行时和外部运行时。

   **答案：B**

8. 下列说法中，正确的是：

   A. ArkCompiler运行时执行引擎有多种组件，包括解释器、JIT编译器和AOT编译器。

   B. JIT编译器在运行前根据静态信息直接编译生成高质量的目标机器码在设备上运行。

   C. AOT编译器一般需要运行时执行代码一段时间。

   D. 只有AOT编译器生成的优化代码，通常都是在一定优化假设或者优化推断的前提下生成的。JIT编译器不是

   **答案：A**

9. 下列说法中，正确的是：

   A. 解释器，启动慢，执行性能一般，内存占用小

   B. JIT编译器，启动需要预热，执行性能高，内存占用较高

   C. AOT编译器，启动块，执行性能高，内存占用高

   D. AOT编译器，启动需要预热，执行性能高，内存占用高

   **答案：C**

10. 关于ArkCompiler中的并发，下列说法中，正确的是：

    A. ArkCompiler运行时通过共享Actor实例中的不可变或者不易变的对象、内建代码块、方法字节码等，提升Actor的启动性能，达到实现轻量级Actor并发模型的目标。但占用内存可能会变高。

    B. 当前，业界的一些Actor并发模型，例如传统JS引擎的web-worker实现，有启动速度慢的缺陷，但占用内存不高。

    C. ArkCompiler运行时除了提供标准的“Java多线程编程”和“运行支持”之外，也提供响应式的Actor并发编程模型支持。

    D.Actor并发编程模型下执行体之间不通过消息机制进行通信，而是直接共享数据。

    **答案：C**

11. 下列说法中，错误的是：

    A. 方舟编译器在运行性能上无明显优势

    B. 方舟编译器在编译优化上有明显优势

    C. 方舟编译器具有超级文件系统，安全又高效

    D. 方舟编译器的只读设计保护系统分区更加安全

    **答案：A**

12. 下列说法中，错误的是：

    A. 方舟编译器可针对不同应用灵活编译优化

    B. 方舟编译器没有提供更高效的内存回收机制

    C. 开发者学习和使用成本低，打包时编译

    D. 方舟编译器的专利压缩算法确保性能提升

    **答案：B**

    

**多选：**

1. ArkCompiler可支撑应用和服务运行在（）设备上的需求。

   A. 手机

   B. 个人电脑与平板

   C. 电视与汽车

   D. 智能穿戴

   **答案： ABCD**

2. ArkCompiler是华为自研的统一编程平台，包含（）等关键部位。

   A. 编译器

   B. 工具链

   C. 全自动化测试

   D. 运行时

   **答案：ABD**

3. ArkCompiler可以把（）语言编译为方舟字节码。

   A. ArkTS

   B. Java

   C. TS

   D. JS

   **答案：ACD**

4. ArkCompiler通过共享运行实例中的不可变或者不易变的对象、内建代码块、方法字节码等技术手段，优化了并发运行实例的（）（）。

   A. 应用稳定性

   B. 内存开销

   C. 启动性能

   D. 错误率

   **答案：BC**

5. ArkCompiler诞生的原因是（）

   A. 需要定制开发

   B. 需要相应的编译器和运行时来支撑这些高级应用编程语言的高效开发、部署和运行

   C. 需要更高效率的开发

   D.使应用开发者能使用同一套开发框架实现一次开发多端部署运行，并且让使用HarmonyOS设备的用户，能获得统一的用户体验。

   **答案：BD**

6. ArkCompiler设计目标为

   A. 语言可插拔

   B. 高效率

   C. 低成本

   D. 组件可配置

   **答案：AD**

7. 下列说法正确的有：

   A.  ArkCompiler运行时通过不同执行模式的按需组合，支持多种设备不同的定制化需求。

   B. 在低端IOT设备上，ArkCompiler执行引擎支持纯解释器的执行模式，以满足小设备的内存限制条件；

   C. 在高端设备上，ArkCompiler执行引擎支持解释器配合AOT编译器以及JIT编译器的模式运行，对相当部分代码使用AOT编译器编译，使得程序一开始就可以运行在高质量的优化代码上，获得最好的执行性能；

   D. 在其它设备上，则根据设备的硬件条件限制来选择策略，设定高频使用需要AOT编译的代码范围，其它代码则依靠解释器配合JIT Compiler运行，使得应用执行性能能够得到最大化。

   **答案：ABCD**

8. 关于ArkCompiler的跨语言优化，下列说法中，正确的是：

   A. HarmonyOS JS/TS应用，有一些系统库、框架和应用依赖的部分能力的实现使用了C/C++和Java语言。

   B. HarmonyOS开发框架也提供了JS/TS与C/C++交互的JS NAPI以及JS/TS与Java交互的Channel机制。

   C. ArkCompiler利用同时支持多语言的优势，运行时具备不同语言的数据表示、对象布局、函数调用约定等信息，这使得跨语言之间的直接数据访问、对象操作和方法调用成为可能，同时Java代码提供的更多确定的类型信息也成为JS/TS类型推导的额外输入，利于对JS/TS的编译优化。

   D. 考虑不同语言之间的交互场景的开发和运行效率需求，ArkCompiler和开发框架联合设计，提供了对应的优化机制。

   **答案：ABCD**