## Description
该库的目的是结合详细的Demo来全面解析Android相关的知识点, 帮助读者能够更快的掌握与理解所阐述的要点。

不定时更新，与预期接下的要做的事，希望点进来的你能够喜欢😍😍

温馨提示：点击右上角的💞可以防走失哦~

> 使用前请先切换到对应的分支

### App Startup
Branch: `feat_app_setup`

> 进阶版[android-startup](https://github.com/idisfkj/android-startup)支持同步与异步初始化，异步支持主线程等待，内部使用拓扑排序优化组件初始化顺序。

### Flutter
Flutter Github客户端，同时支持Android与IOS，支持账户密码与认证登陆。使用dart语言进行开发，项目架构是基于Model/State/ViewModel的MSVM；使用Navigator进行页面的跳转；网络框架使用了dio。项目持续更新中...

[Flutter Github](https://github.com/idisfkj/flutter_github)

### Android源码分析

[Android init 启动](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484889&idx=1&sn=b39c8bc0a9925464022b77a341263cf0&chksm=e8e0fc49df97755f598ef0082409c573d7715ce33bb27b1c98194410d487ae69adbeb2293d84&token=1353481402&lang=zh_CN#rd)

[Android Linux Zygote启动](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484903&idx=1&sn=b55b5bd01ad292256364ecd965ca2288&chksm=e8e0fc77df9775611d5d537f7388daaf7e1a8176667d23e3a6883d448d3a264c761dfc639ba9&token=757375240&lang=zh_CN#rd)

[Android Java Zygote启动](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484921&idx=1&sn=bf7251253d23c17c9148423f6f1e386e&chksm=e8e0fc69df97757f2aec459954c07d0fc44be4bd6c6963dab2b5c5b01d8e8176c9069ddab12c&token=757375240&lang=zh_CN#rd)

[Android SystemServer启动(一)](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484938&idx=1&sn=027a7eb6902d8668d15008db0e2ac29a&chksm=e8e0ff9adf97768cb5f77f6b1ffbb2e306a1ac6dea66a4aeeed0a7aed3c3ccb531db634df991&token=757375240&lang=zh_CN#rd)

[Android SystemServer启动(二)](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484991&idx=1&sn=7a4701c30ade3569c75591a12644c4f3&chksm=e8e0ffafdf9776b9a00e9b912a47578622379e667e45a16f5d814b638e8ab04786e38403542b&token=694410729&lang=zh_CN#rd)

### 组件化
Android Github客户端，基于组件化开发，支持账户密码与认证登陆。使用Kotlin语言进行开发，项目架构是基于JetPack&DataBinding的MVVM；同时支持组件开发，使用Arouter进行组件间的跳转；网络框架使用了Retrofit&Coroutine。项目持续更新中...

[AwesomeGithub](https://github.com/idisfkj/AwesomeGithub)

[AwesomeGithub组件化探索之旅](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484214&idx=1&sn=d37e21fd82fb77ccbccd292c3d52ccbe&chksm=e8e0faa6df9773b061e973c070dd72a7830e0afc8b21ba26d07718709b635e50f86a24719fb3&token=1496474879&lang=zh_CN#rd)

### Databinding Plugin

[只需三步实现Databinding插件化](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247483993&idx=1&sn=1c6d7a776a6f8bdc0ce9786fb0087c46&chksm=e8e0fbc9df9772dfdeef5f174e9325010445ac117d5042ea06a81fb3d38d2c597667047c9e46&token=1514380520&lang=zh_CN#rd)

> 由于这是一个小工具，所以我将它独立于另一个[仓库](https://github.com/idisfkj/databinding_autorun)

### 自动化代码注入
Branch: `feat_transform_dev`

[为了能够摸鱼，我走上了歧路](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247486137&idx=1&sn=5ede65905439fdee133eeba86e4a9f73&chksm=e8e0f329df977a3f4aef71cb0c4d3c5c772dfc707153e2298f2060e227e833542cfe55da1066&token=1480633461&lang=zh_CN#rd)

### Retrofit
Branch: `feat_proxy_dev`

[重温Retrofit源码，笑看协程实现](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484584&idx=1&sn=14deb55b443975eec648602a8807862e&chksm=e8e0fd38df97742e45daa2cba014bbbde2216212452c72a00e94e100c13d90d2af5d3fa9ed61&token=981517486&lang=zh_CN#rd)

[动态代理分析与仿Retrofit实践](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484600&idx=1&sn=d0b0546d81aa28ba82d65f3451a01595&chksm=e8e0fd28df97743ef4e998d2ceda9d4358360c398c4d090827cfda165dadf8369ed20580e473&token=1120954802&lang=zh_CN#rd)

### Kotlin Coroutine
Branch: `feat_coroutine_dev`

[What? 你还不知道Kotlin Coroutine?](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247483860&idx=1&sn=d8a4441912d0d1eee189d97506bb4689&chksm=e8e0f844df977152652d69a3b4cc3cd1d1a148609f4295b6142e6d577156b76905e1cb6b95be&token=1091218095&lang=zh_CN#rd)

[Kotlin协程实现原理:Suspend&CoroutineContext](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484630&idx=1&sn=967175b88ce6d57e25917b4763601776&chksm=e8e0fd46df9774500d1446410e6e04cde88fc40f27a95e9d059ca60cbdf5dc4099dc86ed4dc0&token=1520540464&lang=zh_CN&scene=21#wechat_redirect)

[Kotlin协程实现原理:CoroutineScope&Job](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484642&idx=1&sn=3d628b5094d05f0ea6fcb9559dcf73e4&chksm=e8e0fd72df9774642d35efac6dac966ec1c7b25ea561fa686bdc8a08e672bea701e868d27d15&token=1604035346&lang=zh_CN&scene=21#wechat_redirect)

[Kotlin协程实现原理:ContinuationInterceptor&CoroutineDispatcher](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484655&idx=1&sn=36fe3d4a0018b9b633e7aab0f00236a6&chksm=e8e0fd7fdf977469c04c5624acbecc561f167aeaf434ca29d47beb69d9737a771ceb81424c8d&token=1580936770&lang=zh_CN&scene=21#wechat_redirect)

[Kotlin协程实现原理:挂起与恢复](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484696&idx=1&sn=069edb2b12c621cde1a36decc4491e5a&chksm=e8e0fc88df97759ef3d729a9a575a14411bb4062945552692263b37550bc7d51799fa7721029&token=1855541850&lang=zh_CN#rd)

### Bitmap的图片压缩相关
Branch: `feat_bitmap_dev`

[Bitmap的图片压缩汇总](https://www.rousetime.com/2018/03/21/Bitmap%E7%9A%84%E5%9B%BE%E7%89%87%E5%8E%8B%E7%BC%A9%E6%B1%87%E6%80%BB/)

### ConstraintLayout相关
Branch: `feat_constraintlayout_dev`

[ConstraintLayout使用汇总](https://www.rousetime.com/2018/05/03/ConstraintLayout%E4%BD%BF%E7%94%A8%E6%B1%87%E6%80%BB/)

### Android Architecture Components
Branch: `feat_architecture_components`

[Android Architecture Components Part1:Room](https://www.rousetime.com/2018/06/07/Android-Architecture-Components-Part1-Room/)

[Android Architecture Components Part2:LiveData](https://www.rousetime.com/2018/06/10/Android-Architecture-Components-Part2-LiveData/)

[Android Architecture Components Part3:Lifecycle](https://www.rousetime.com/2018/06/14/Android-Architecture-Components-Part3-Lifecycle/)

[Android Architecture Components Part4:ViewModel](https://www.rousetime.com/2018/06/22/Android-Architecture-Components-Part4-ViewModel/)

Branch: `feat_paging_dev`

[Paging在RecyclerView中的应用，有这一篇就够了](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484096&idx=1&sn=c87f8a38fe75108ccdc1883116efc78e&chksm=e8e0fb50df9772460b5ee546b4474c46b58f0433eb75a9029d7ec41bc9d50bea225f880183d6&token=1605536465&lang=zh_CN#rd)

Branch: `feat_work_manager_dev`

[WorkManager从入门到实践，有这一篇就够了](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484164&idx=1&sn=8e1cf325a281340d5664a1826b870e34&chksm=e8e0fa94df977382ec38fba3440723cc12d76a44d2f6e3b4ef59d0228aa21a98a25a6b0d2ac9&token=1929195340&lang=zh_CN#rd)

Branch: `feat_navigation_dev`

[Android Navigation的四大要点你都知道吗？](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247484199&idx=1&sn=5fbe55f36aaeee4e098b85ec694a4ce5&chksm=e8e0fab7df9773a19b73a53c190b1d213f24521a2db20b551040c314c4e2298fa2936c7bd2f2&token=407594993&lang=zh_CN#rd)

Branch: [AwesomeGithub feat_hilt](https://github.com/idisfkj/AwesomeGithub/tree/feat_hilt)

[Android Hilt实战初体验: Dagger替换成Hilt](https://juejin.im/post/6850418118289227783)

### Android Annotation Processing
Branch: `feat_annotation_processing`

[自定义Android注解Part1:注解变量](https://www.rousetime.com/2018/07/01/%E8%87%AA%E5%AE%9A%E4%B9%89Android%E6%B3%A8%E8%A7%A3Part1-%E6%B3%A8%E8%A7%A3%E5%8F%98%E9%87%8F/)

[自定义Android注解Part2:代码自动生成](https://www.rousetime.com/2018/07/04/%E8%87%AA%E5%AE%9A%E4%B9%89Android%E6%B3%A8%E8%A7%A3Part2-%E4%BB%A3%E7%A0%81%E8%87%AA%E5%8A%A8%E7%94%9F%E6%88%90/)

[自定义Android注解Part3绑定](https://www.rousetime.com/2018/07/11/%E8%87%AA%E5%AE%9A%E4%B9%89Android%E6%B3%A8%E8%A7%A3Part3-%E7%BB%91%E5%AE%9A/)

### ViewDragHelper
Branch: `feat_viewdraghelper_dev`

[ViewDragHelper之手势操作神器](https://www.rousetime.com/2018/08/19/ViewDragHelper%E4%B9%8B%E6%89%8B%E5%8A%BF%E6%93%8D%E4%BD%9C%E7%A5%9E%E5%99%A8/)

### Activity启动模式

[你不该忽略的LaunchMode](https://www.rousetime.com/2019/01/03/%E4%BD%A0%E4%B8%8D%E8%AF%A5%E5%BF%BD%E7%95%A5%E7%9A%84LaunchMode/)

### Gson解析

[Gson与List<T>对象间的相亲之旅](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&tempkey=MTAwOF9wNzQrRVk1Q1RNT2xNT0VpQjJqM3V4aTZCaDl0SHhFUWJLTFJaVmhzZURPM2JSdkcxcGo1ajQtMHYyOWdEQUI5N18xaUVnRVh4VmRCbVNQbkVGbnBRV1Rjbm5yNl9zVC1uQTM3eE9HSzlLTy00QU40UXJYTWNubzlxRE5nQkc2dDZCZGM0SE1uR1ZRamJKOU1zeVk4c0tUYjV1WUFET3QtX29oVHJ3fn4%3D&chksm=68e0f8255f977133736be6ac7950dac37b591539d381a6f281e4c0d38f885209f8c81068b4ee#rd)

### Gradle
Branch: `feat_gradle_dev`

[Android Gradle系列-入门篇](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247483821&idx=1&sn=dce064a98e8b3ba6ddf217db34bec7d7&chksm=e8e0f83ddf97712b21e615f128ea3b94bdc217c931e2d343974899c62662fdedbf2248e9cdfd&xtrack=1&scene=90&subscene=93&sessionid=1557203855&clicktime=1557203857&ascene=56&devicetype=android-26&version=27000439&nettype=WIFI&abtest_cookie=BAABAAoACwASABMABQBWmR4AvpkeANyZHgDimR4A8ZkeAAAA&lang=zh_CN&pass_ticket=wt8OMJkRRD0NTILVozc2eSNJAFDcycfjiw9mPbqW9dI6pFNc%2FrE3CTRiDR%2Bfx%2BLf&wx_header=1)

[Android Gradle系列-原理篇](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247483834&idx=1&sn=55264aaad1f018b55280beec93ed4cac&chksm=e8e0f82adf97713c5a43c67b67fbabd659578328a22a406c5a01bd69ccf550e88bf645b15457&token=2079168237&lang=zh_CN#rd)

[Android Gradle系列-运用篇](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247483840&idx=1&sn=f4392bba9a85d79d84e823f2b83aa668&chksm=e8e0f850df9771462447aec9c7275b70e576bd17f20c7eeb703116eb1c23fe534ad796996515&token=1041803379&lang=zh_CN#rd)

[Android Gradle系列-进阶篇](https://mp.weixin.qq.com/s?__biz=MzIzNTc5NDY4Nw==&mid=2247483845&idx=1&sn=6208df8f9a0394e11e86e180288b2048&chksm=e8e0f855df9771439ded464c26501d23ebf450fe90f0390cc0698e3fe431217b8b9e91927d27&token=330677494&lang=zh_CN#rd)


## 加入我们
如需了解更多可以扫描下方二维码，加入我们：Android补给站。让我们与志同道合的你一起成长。

![关注](https://github.com/idisfkj/android-api-analysis/raw/master/image/wx.jpg)
