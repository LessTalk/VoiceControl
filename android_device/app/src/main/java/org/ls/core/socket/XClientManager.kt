package org.ls.core.socket

import android.content.Context
import android.util.Log
import android.widget.Toast
import org.ls.android_device.XClient
import java.net.URI

/**
 * Author by Less on 2019/3/6.
 */
object XClientManager{

    fun send(xClient : XClient, type : Int){
        send(xClient,type,"",0,"")
    }

    fun send(xClient : XClient, type : Int,v : Int){
        send(xClient,type,"",v,"")
    }

    fun send(xClient : XClient, type : Int,key : String){
        send(xClient,type,key,0,"")
    }

    fun send(xClient : XClient, type : Int,key : String,result : String){
        send(xClient,type,key,0,result)
    }

    fun send(xClient : XClient, type : Int,key : String,v : Int,result : String){
        xClient.send("{\n" +
                "\t\"type\": $type,\n" +
                "\t\"key\": \"$key\",\n" +
                "\t\"val\": $v,\n" +
                "\t\"result\": \"$result\"\n" +
                "}")
    }

    fun connect(ip : String) : XClient? {
        try{
            val xClient = XClient(URI("ws://$ip:7777"))
            xClient.connect()
            return xClient
        }catch (t : Throwable){
            Log.e("Less","connect-t:${t.message}")
        }
        return null
    }

}