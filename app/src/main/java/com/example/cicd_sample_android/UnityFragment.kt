package com.example.cicd_sample_android

import android.app.Fragment
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import com.example.cicd_sample_android.R
import com.unity3d.player.UnityPlayer
import java.lang.Exception

class UnityFragment : Fragment() {
    protected var mUnityPlayer: UnityPlayer? = null
    private var unityView: View? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mUnityPlayer = UnityPlayer(activity)
        unityView = inflater.inflate(R.layout.fragment_unity, container, false)
        try {
            val frameLayoutForUnity = (unityView as View).findViewById<View>(R.id.game_container) as FrameLayout
            try {
                if (mUnityPlayer!=null && mUnityPlayer?.view != null && mUnityPlayer?.view?.parent != null) {
                    (mUnityPlayer?.view?.parent as ViewGroup).removeView(
                        mUnityPlayer?.view)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            frameLayoutForUnity.addView(mUnityPlayer?.view,
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            mUnityPlayer?.requestFocus()
            mUnityPlayer?.windowFocusChanged(true)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return unityView
    }

    override fun onDestroy() {
        try {
            if (mUnityPlayer != null) {
                mUnityPlayer?.quit()
            }
            if (unityView != null) {
                val parentViewGroup = unityView?.parent as ViewGroup
                parentViewGroup.removeAllViews()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        if (mUnityPlayer != null) {
            mUnityPlayer?.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            if (mUnityPlayer != null) {
                mUnityPlayer?.resume()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}