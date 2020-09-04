### 【友盟+】统计、推送、分享三合一组件化SDK集成Demo

---
## 集成升级必读(Android)

#### 1. 抢先版本SDK不能和正式版本SDK混用。混用可能导致不可预知后果(例如：崩溃，无数据等)。

#### 2.  公测版基础组件库 9.2.0仅支持如下表格中特定版本业务SDK。


| 推送SDK | 分享SDK | 游戏统计SDK| 智能登录SDK| 
| -------- | -------- | --------| ---------|
| v6.2.0    | v7.1.0 |  v9.2.0+G | v1.4.0 |

#### 3. 抢先版本推送、分享、游戏统计、智能登录SDK都必须配合基础组件库(umeng-common-9.2.0.jar)一起使用。

#### 4. 集成本版本SDK 请务必在混淆时增加：-keep class com.uc.** {*;}

#### 5. 集成移动统计时，如果App不能保证在Appcalition.onCreate函数中调用UMConfigure.init初始化函数 请查看https://developer.umeng.com/docs/119267/detail/118588#h3-u9884u521Du59CBu5316

#### 6. 集成移动统计时，如果开发者调用kill或者exit之类的方法杀死进程，请查看，https://developer.umeng.com/docs/119267/detail/118637#h1-u5176u4ED6u529Fu80FD6

#### 7. 如集成移动统计后无数据，请查看https://developer.umeng.com/docs/119267/cate/121449 


