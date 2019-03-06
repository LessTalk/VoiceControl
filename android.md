## Android

### 工具

* 目前仅支持Android Studio

### 引用

* root.build.gradle

```java
       maven {
            url "http://123.126.114.194:8083/repository/maven-3rd/"
            credentials {
                username 'xxxx'
                password 'xxxx'
            }
        }
 ```

* moudle.build.gradle
```java
implementation "com.bftv.fui:android_device:1.0.3"
```


#### 寻址
```java
SSDPManager.start()
```

#### 鉴权 type = 0

• 需要鉴权验证之后 在能和大耳朵进行通信

```java
XClientManager.send(mXClient,0,"联系商务")
```
• 联系方式:yulingyan@bftv.com

#### 唤起 type = 1(非必须操作)

• 移动端设备 喊了唤醒词 例如 小米音响 "小爱小爱" 这个时候可以通过如下方法 通知电视端 进行响应的动画显示

```java
XClientManager.send(mXClient,1)
```

#### 录音音量 type = 2 (非必须操作)

• 移动设备可以将 当前设备收集到的音量阀值传给大耳朵 大耳朵内部最低值为 1 最高值为25 可以进行响应比例的换算

```java
XClientManager.send(mXClient,2,10)
```

#### 录音结束请求asr结果 type = 3 (非必须操作)

```java
XClientManager.send(mXClient,3)
```

#### 语音转文字结果 type = 4

```java
XClientManager.send(mXClient,4,"","推荐好看的电影")
```
