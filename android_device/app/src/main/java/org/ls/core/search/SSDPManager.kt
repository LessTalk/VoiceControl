package org.ls.ssdpclient

import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOException
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.nio.charset.Charset
import org.ls.core.type.SSDPServiceType



/**
 * Author by Less on 2019/3/1.
 */
object SSDPManager {

    private var mDatagramSocket: DatagramSocket? = null

    private var listening: Boolean = false

    private var packetLength = 2048

    private var mScanDeviceListener: OnScanListener? = null

    private var mInetAddress: InetAddress = InetAddress.getByName("239.255.255.250")

    private const val mMulticastPort = 1900

    private val targetType = SSDPServiceType.UPnP_AVTransport1

    fun start() {
        Log.e("Less","点击了...")
        Thread(Runnable {
            Log.e("Less","start...")
            stopListen()
            startListen()
            Thread(Runnable {
                fireSearchRequest()
            }).start()
        }).start()

    }

    private fun stopListen() {
        listening = false
        mDatagramSocket?.close()
    }

    fun setScanDeviceListener(listener: OnScanListener) {
        mScanDeviceListener = listener
    }

    private fun startListen() {
        Thread(Runnable {
            Log.e("Less","执行-startListen")
            listening = true
            mDatagramSocket = DatagramSocket()
            mDatagramSocket!!.broadcast = true
            val data = ByteArray(packetLength)
            while (listening) {
                val datagramPacket = DatagramPacket(data, data.size)
                try {
                    mDatagramSocket!!.receive(datagramPacket)
                    mScanDeviceListener?.findDevice(datagramPacket.address, String(datagramPacket.data,
                            0, datagramPacket.length, Charset.forName("UTF-8")))
                } catch (e: IOException) {
                    e.printStackTrace()
                    Log.e("Less", "接受地址:${e.message}")
                }
            }
        }).start()
    }

    private fun fireSearchRequest() {
        val message = "M-SEARCH * HTTP/1.1\r\n" +
                "HOST: 239.255.255.250:1900\r\n" +
                "MAN: \"ssdp:discover\"\r\n" +
                "ST: " + "${targetType.decs}" + "\r\n" +
                "MX: 3\r\n" +
                "USER-AGENT: UPnP/1.0 VoiceCore/1.0\r\n\r\n\r\n\n"
        send(mInetAddress, mMulticastPort, message)
    }

    private fun send(address: InetAddress, port: Int, msg: String) {
        try {
            val messageByte = msg.toByteArray()
            val p = DatagramPacket(messageByte, messageByte.size)
            p.address = address
            p.port = port
            mDatagramSocket?.send(p)
        } catch (e: Throwable) {
            e.printStackTrace()
            Log.e("Less", "寻找地址:${e.message}")
        }
    }

}