## Stage模型出题

1.Stage模型从API第几版开始支持？

A. API6

B. API7

C. API8

D. API9

##### 答案：D

解释：Stage 模型：是为了解决FA模型⽆法解决的开发场景问题，⽅便开发者更加⽅便地开发出分布式环境下的复杂应⽤。⾃ API9 新增的模型。是⽇后⻓期演进的模型。





2.Stage模型中的ExtensionAbility被扩展为的元能力包括哪一些？

A. ServiceExtensionAbility

B. FormExtensionAbility

C. DataShareExtensionAbility

D. PageAbility

##### 答案：ABC

解释：Stage模型将Ability分为PageAbility和ExtensionAbility两⼤类，其中ExtensionAbility⼜被扩展为ServiceExtensionAbility、FormExtensionAbility、DataShareExtensionAbility等⼀系列 ExtensionAbility，以便满⾜更多的使⽤场景。



3.公共事件服务为应用程序提供哪些能力

A. 订阅公共事件

B. 发布公共事件

C. 删除公共事件

D. 退订公共事件

##### 答案：ABD

解释：HarmonyOS通过CES（Common Event Service，公共事件服务）为应⽤程序提供订阅、发布、退订公共事件的能⼒。



4.鸿蒙的UI Ability包括哪些生命周期接口？

A. onCreate

B. onDestroy

C. onPageShow

D. onBackground

##### 答案：ABD

解释：

UIAbility的生命周期包括Create、Foreground、Background、Destroy四个状态。

onPageShow是@Entry标记的组件具有的回调接口。



5.UIAbility的启动模式有哪些

A. singleton

B. specified

C. multition

D. builder

##### 答案：ABC

解释：

UIAbility的启动模式是指UIAbility实例在启动时的不同呈现状态。针对不同的业务场景，系统提供了三种启动模式：

- [singleton（单实例模式）](https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/uiability-launch-type-0000001428061476-V2#ZH-CN_TOPIC_0000001523489150__singleton启动模式)
- [standard（标准实例模式）](https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/uiability-launch-type-0000001428061476-V2#ZH-CN_TOPIC_0000001523489150__standard启动模式)
- [specified（指定实例模式）](https://developer.huawei.com/consumer/cn/doc/harmonyos-guides-V2/uiability-launch-type-0000001428061476-V2#ZH-CN_TOPIC_0000001523489150__specified启动模式)





6.元服务包名必须以哪个字段结尾？

A. .hmservice

B. .coreservice

C. .hwservice

D. .metaservice

##### 答案：A

解释：

元服务包名必须以哪个字段结尾？（hmservice），选自HarmonyOS应用开发者高级认证。



7.被@Entry装饰的组件生命周期中，①aboutToApear, ②build, ③onPageShow的执行顺序是。

A. ①②③

B. ③①②

C. ②③①

D. ③②①

##### 答案：A

解释：

自定义组件声明周期：aboutToAppear、build、onPageShow。

![](https://alliance-communityfile-drcn.dbankcdn.com/FileServer/getFile/cmtyPub/011/111/111/0000000000011111111.20231016160223.99199945095842886884636368090803:50001231000000:2800:019E4D38024DDC3B25A19EE8BEF753B3FB089891518C310555FB21C5117F6BF7.png?needInitFileName=true?needInitFileName=true)



8.worker线程最大同时激活数。

A. 7

B. 8

C. 9

D. 10

##### 答案：B

解释：HarmonyOS应用开发者高级认证考试题目。



9.下列说法正确的是。

A. Stage 模型中多个应⽤组件共享同⼀个虚拟机

B. FA 模型中，每个应⽤组件独享⼀个虚拟机

C. Stage 模型中，每个应⽤组件独享⼀个虚拟机

D. FA 模型中多个应⽤组件共享同⼀个虚拟机

##### 答案：AB

解释：

Stage 模型与 FA 模型最⼤的区别在于：Stage 模型中多个应⽤组件共享同⼀个虚拟机， ⽽ FA 模型中，每个应⽤组件独享⼀个虚拟机。Stage 模型作为鸿蒙系统的主要应⽤编程模型， 开发者通过它能够更加便 利地开发出分布式场景下的复杂应⽤。



10.Stage模型具有哪些特点？

A. 采⽤⾯向对象的开发⽅式

B. 窗⼝部分可单独销毁和重建

C. 窗⼝与 Ability 可跨设备运⾏

D. Ability 可在不启动界⾯的情况下响应请求

##### 答案：ABCD

解释：

采⽤⾯向对象的开发⽅式，使得复杂应⽤代码可读性⾼、易维护、扩展性强。程序逻辑与⽤户界⾯解耦：窗⼝部分可单独销毁和重建，窗⼝与 Ability 可跨设备运⾏， Ability 可在不启动界⾯的情况下响应请求。（ppt上Stage模型的优势）



11 下面哪个文件可以设置页面路由配置信息？

A. EntryAbility.ets

B. app.json5

C. main_pages.json

D. index.ets

##### 答案：C

解释：参考ppt，Stage模型“应⽤配置⽂件”页面



12 引用ohpm三方库的包依赖是在哪个配置文件中？

A. oh-package.json5

B. ohpm-package.json

C. build-profile.json5

D. 以上配置皆可

##### 答案：A

解释：参考ppt，Stage模型“应⽤配置⽂件”页面



13 module.json5配置文件包含以下哪些信息。

A. Ability的配置信息

B. 应用包名和版本号信息

C. 设备类型信息

D. 应用权限申请列表

##### 答案：ACD

解释：参考ppt，Stage模型“应⽤级、模块级”。应用包名和版本号信息是app.json5中的内容。



14 使用Stage模型构建的项目中，有两个层级的应用配置文件，包括。

A. 项目级

B. 应用级

C. 模块级

D. 单元级

##### 答案：BC

解释：参考ppt，Stage模型“应⽤级、模块级”。



15 关于短时任务，下列说法错误的是。

A. 应⽤需要在前台或退⾄后台30秒内，可以申请短时任务

B.⼀个应⽤同⼀时刻最多申请3个短时任务 

C. ⼀个应⽤的短时任务配额是可以随系统状态调整的

D. 系统提供获取对应短时任务剩余时间的查询接口

##### 答案：A

解释：应⽤需要在前台或退⾄后台5秒内，申请短时任务，否则会申请失败。





16 关于Stage的进程模型，以下说法正确的是。

A. ⼀个应⽤所有的webview都运⾏在Render进程中。

B. 每个应⽤⾄多并存两类进程。

C. Stage模型允许应⽤配置多进程

D. 主进程运⾏应⽤中的UIAbility组件和⻚⾯，不处理业务逻辑

##### 答案：A

解释：

B，每个应⽤⾄多并存三类进程：主进程、Extension类进程和Render进程

C，不允许应⽤配置多进程，应⽤中所有的进程都是由 系统创建和管理的

D，主进程：运⾏应⽤中所有的UIAbility组件、⻚⾯和业务逻辑



17 公共事件按发送⽅式可分为。

A. ⽆序公共事件

B. 有序公共事件

C. 复制公共事件

D. 粘性公共事件

##### 答案：ABD

解释：公共事件按发送⽅式可分为：⽆序公共事件、有序公共事件和粘性公共事件。





18 关于ArkTS的并发处理，下列说法正确的是。

A. Promise和async/await提供异步并发能⼒，适⽤于单次I/O任务的开发场景。

B. TaskPool和Worker提供多线程并发能⼒，适⽤于CPU密集型任务、I/O密集型任务和同步任务等并发场景。

C. ArkTS⽀持异步并发

D. ArkTS不⽀持多线程并发

##### 答案：ABC

解释：

A， Promise和async/await提供异步并发能⼒，适⽤于单次I/O任务的开发场景。

B， TaskPool和Worker提供多线程并发能⼒，适⽤于CPU密集型任务、I/O密集型任务和同步任务等并发场景

D，ArkTS⽀持异步并发和多线程并发。



19 Promise是⼀种⽤于处理异步操作的对象，在ArkTS中，Promise对象具有哪些状态？

A. wating(等待中)

B. pending(进行中)

C. fulfilled(已完成)

D. rejected(已拒绝)

##### 答案：BCD

解释：Promise有三种状态：pending（进⾏中）、fulfilled（已完成）和 rejected（已拒绝）。Promise对象创建后处于pending状态，并在异步操作完成 后转换为fulfilled或rejected状态



20 关于Worker线程的说法，正确的是。

A. 主线程无法销毁Worker线程，需要由Worker线程主动销毁。

B. Worker线程中可以调用close()方法主动销毁Worker线程，终止Worker接收消息。

C. 同步任务之间相对独⽴时，推荐使用Worker而不是TaskPool。

D. 宿主机可以通过onexit()方法销毁Worker线程

##### 答案：B

解释：

A，销毁线程的⽅式主要有两种：根据需要可以在宿主线程中对Worker线程进⾏销毁；也可以在Worker线程中主动销毁Worker线程。

C，TaskPool和Worker提供多线程并发能⼒，适⽤于CPU密集型任务、I/O密集型任务和同步任务等并发场景

D，在宿主线程中通过调⽤terminate()⽅法销毁Worker线程，并终⽌Worker接收消息，而不是onexit。