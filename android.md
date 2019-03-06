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


### DLNA连接

* 寻址
```java
SSDPManager.start()
```

* 连接
```java
SSDPManager.setScanDeviceListener(object : OnScanListener{
            override fun findDevice(address: InetAddress, msg: String) {
                Log.i("Less","findDevice:$msg")
                if(msg.contains("VoiceCore")){
                    Log.e("Less","找到大耳朵设备:$msg")
                    val ip = address.toString().replace("/","")
                    runOnUiThread {
                        Toast.makeText(this@MainActivity,"搜索到大耳朵设备IP:$ip", Toast.LENGTH_SHORT).show()
                    }
                    mXClient = XClientManager.connect(ip)!!
                }
            }
})
```

### 通信

• 鉴权<br>

| 类型Type        | 联系方式           | 
| ------------- |:-------------:| 
| 0     | yulingyan@bftv.com | 

```java
XClientManager.send(mXClient,0,"联系商务")
```


• 唤起<br>

| 类型Type        | 描述           | 
| ------------- |:-------------:| 
| 1     | 移动端设备 喊了唤醒词 通知电视端 进行响应的动画显示 | 
  
```java
XClientManager.send(mXClient,1)
```

