package org.ls.android_device

import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.java_websocket.client.WebSocketClient
import org.java_websocket.handshake.ServerHandshake
import java.lang.Exception
import java.net.URI

/**
 * Author by Less on 2018/12/5.
 */
class XClient(serverUri: URI?) : WebSocketClient(serverUri) {
    override fun onOpen(handshakedata: ServerHandshake?) {
        Log.e("Less","XClient-onOpen")
    }

    override fun onClose(code: Int, reason: String?, remote: Boolean) {
        Log.e("Less","XClient-onClose-reason:$reason")
    }

    override fun onMessage(message: String?) {
        Log.e("Less","XClient-onMessage")
    }

    override fun onError(ex: Exception?) {
        Log.e("Less","XClient-onError:" + ex.toString())
    }


}