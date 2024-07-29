package com.bwin.bridge

import android.util.Log
import com.unity3d.player.UnityPlayer
import org.json.JSONObject


object NotificationMessageCenter {

    //this method is invoking from Unity..
    // it acts like receive messages from unity
    @JvmStatic
    fun messageFromUnity(message: String): String {

        Log.d("testPOC","messageFromUnity"+  message)
        handleUnityMessage(JSONObject(message))
        return message
    }

     fun handleUnityMessage(message: JSONObject) {
         Log.d("testPOC","messageToUnity"+  message)
         if(message.getString("type")==("initialised")) {
             UnityPlayer.UnitySendMessage("NativeMessageHandler", "MessageFromNative", "{\"type\":\"gameAvailable\",\"args\":{\"gameName\":\"ivymhpremiumblackjackes\",\"status\":false}}")
         }
         else if (message.getString("type")==("unloadGame"))
         {
             UnityPlayer.UnitySendMessage("NativeMessageHandler", "MessageFromNative","{\"args\":{\"accountBalance\":0,\"defaultBetIndex\":0,\"gameName\":\"ivysliderblackjack\",\"hasUnfinishedGame\":false,\"maxBetAmount\":0,\"maxSideBetAmount\":0,\"minBetAmount\":0,\"minSideBetAmount\":0,\"status\":false},\"type\":\"unloadGame\"}")
         }
         else{
             UnityPlayer.UnitySendMessage("NativeMessageHandler", "MessageFromNative","{\"type\":\""+message.getString("type") +"\"  }");
         }


     }
}





