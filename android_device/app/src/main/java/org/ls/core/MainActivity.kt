package org.ls.core

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.ls.android_device.XClient
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
                        Toast.makeText(this@MainActivity,"搜索到大耳朵设备IP:$ip",Toast.LENGTH_SHORT).show()
                    }
                    try{
                        mXClient = XClient(URI("ws://$ip:7777"))
                        mXClient.connect()
                    }catch (t : Throwable){
                        runOnUiThread {
                            Toast.makeText(this@MainActivity,"连接失败:${t.message}",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        })

        btn_search.setOnClickListener {
            SSDPManager.start()
        }

        btn_auth.setOnClickListener {
            //type = 0 (auth)
            mXClient.send("{\n" +
                    "\t\"type\": \"0\",\n" +
                    "\t\"key\": \"fcd338605d90fe21\"\n" +
                    "}")
        }


    }
}
