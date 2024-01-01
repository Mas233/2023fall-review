## 2 NAPI

1. 以下关于NAPI的描述，哪些是正确的？

   A. NAPI允许JavaScript代码调用本地C/C++函数。

   B. NAPI主要用于处理前端用户界面的操作。

   C. NAPI只在浏览器环境中使用，不适用于其他JavaScript运行时。

   D. OpenHArmony NAPI机制是基于Node.js的NAPI规范进行扩展开发的框架。

   答案：AD（高级篇41、42页）

2. 在Node.js的N-API模块中，如果模块名为hello，那么动态链接库（.so文件）的名字应该是什么？

   A. libHello.so
   B. lib_hello.so
   C. hello_lib.so
   D. libhello.so

   答案：D（见https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/napi-guidelines-0000001493903956-V2中的so命名规则）

3. 在Ark引擎中，处理N-API接口和线程使用的保护时，以下哪项描述是准确的？

   A. 可以在不同线程中共享同一个env对象，确保在并发环境下正确同步，以避免线程保护问题。

   B. N-API接口在执行过程中会自动锁定线程，确保在多线程环境中不会发生竞态条件。

   C. env对象在创建时与线程绑定，不可跨线程使用，确保在相应线程上执行N-API接口调用。

   D. 在使用N-API接口时，开发者可以随时将env对象从一个线程转移到另一个线程，以简化线程管理。

   答案：C（https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/napi-guidelines-0000001493903956-V2js对象线程限制）

   

4. 以下哪个模块是用来管理NativeValue的生命周期？

   A. NativeEngine

   B. ModuleManager

   C. ScopeManager

   D. ReferenceManager

   答案：C（https://github.com/openharmony/ace_napi Introduction ）

5. 为了防止与其他动态链接库（so）中的符号冲突，对于`nm_register_func`对应的函数，应该：

   A. 加上extern关键字

   B. 加上static关键字

   C. 加上inline关键字

   D. 不做任何修改

   答案：B（见https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/napi-guidelines-0000001493903956-V2中的注册建议）

6. 在JS应用中注册了某个sensor的监听，该sensor的数据由一个SA服务上报，当SA通过IPC调用到客户端时，此时的执行线程是一个IPC通信线程，与应用的JS线程是两个不同的线程。为了避免崩溃，下列哪个选项描述了正确的处理方式？

   A. 将执行JS回调的任务继续在IPC通信线程中执行。

   B. 在IPC通信线程中添加逻辑将执行JS回调的任务切换到JS线程中执行。

   C. 在IPC通信线程和JS线程之间建立一个新的线程用于执行JS回调任务。

   D. 不需要进行额外处理，IPC通信线程和JS线程可以直接执行JS回调任务。

   答案：B（https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/napi-guidelines-0000001493903956-V2在非JS线程中回调JS接口的模块简介）

7. 在OpenHarmony NAPI中，通过什么方式将C语言数据类型转换为JavaScript数据类型？

   A. 使用napi_convert函数 

   B. 使用napi_cast函数 

   C. 使用napi_value_from_c函数 

   D. 使用napi_create_double等类型专用函数

   答案： D（高级篇57页）

8. 在OpenHarmony NAPI中，如何获取JavaScript函数的参数？

   A. 使用napi_get_arguments函数 

   B. 使用napi_value_to_c函数 

   C. 使用napi_get_parameter函数

    D. 使用napi_get_cb_info函数

   答案： D（高级篇57页）

9. 请写出C侧调用JS侧方法的步骤。

   答案：（高级篇66页）

   步骤一：ArkTS中定义一个类

   步骤二：把类的实例保存在全局变量globalthis

   步骤三：C侧获取类和对应的方法

10. 请简述一下NAPI的开发流程

   答案：（高级篇45页）

   1. NAPI开发Native模块
      1. 设置模块注册信息
      2. 配置模块初始化信息
      3. 基于NAPI开发业务功能
   2. C++编译配置（CMakeList）
   3. module构建配置中增加Native构建信息
   4. TS声明Native库支持的接口
   5. 修改模块配置，增加对TS接口依赖
   6. ETS文件中使用NAPI

   

11. 请简述一下NAPI代码集成的过程。

    答案：（高级篇67页）

    1. 按照实际开发过程，可分为以下2个部分:
       - 系统编译出so库
         - IDE编译
       - 系统集成so库
         - 方式一 :Native侧直接集成
         - 方式二 :Native侧通过dlopen方式集成 
         - 方式三 :应用侧直接集成
       

    

   
