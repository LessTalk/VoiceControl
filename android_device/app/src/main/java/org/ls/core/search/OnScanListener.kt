package org.ls.ssdpclient
import java.net.InetAddress
/**
 * Author by Less on 2019/2/27.
 */
interface OnScanListener{

    fun findDevice(address: InetAddress, msg : String)
}