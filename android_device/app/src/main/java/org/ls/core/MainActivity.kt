package org.ls.core

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.ls.android_device.XClient
import org.ls.core.socket.XClientManager
import org.ls.ssdpclient.OnScanListener
import org.ls.ssdpclient.SSDPManager
import java.net.InetAddress
import java.net.URI

class MainActivity : AppCompatActivity() {

    lateinit var mXClient: XClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SSDPManager.setScanDeviceListener(object : OnScanListener{
            override fun findDevice(address: InetAddress, msg: String) {
                Log.i("Less","findDevice:$msg")
                if(msg.contains("VoiceCore")){
                    Log.e("Less","找到大耳朵设备:$msg")
                    val ip = address.toString().replace("/","")
                    runOnUiThread {
                        Toast.makeText(this@MainActivity,"搜索到大耳朵设备IP:$ip", Toast.LENGTH_SHORT).show()
                    }
                    XClientManager.connect(ip)
                }
            }
        })

        btn_search.setOnClickListener {
            SSDPManager.start()
        }

        btn_auth.setOnClickListener {
            //type = 0 授权
            XClientManager.send(mXClient,0,"联系商务")
        }

        btn_wake.setOnClickListener {
            //type = 1 唤起
            XClientManager.send(mXClient,1)
        }

        btn_volume.setOnClickListener {
            //type = 2 录入音量
            XClientManager.send(mXClient,2,10)
        }

        btn_deal.setOnClickListener {
            //type = 3 录音结束请求ASR
            XClientManager.send(mXClient,3)
        }

        btn_asr.setOnClickListener {
            //type = 3 录音结束请求ASR
            XClientManager.send(mXClient,4,"","推荐好看的电影")
        }
    }
}
