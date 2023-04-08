package com.android.academy.fundamentals.workshop_1

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.android.academy.fundamentals.R

class WS01ThreadsTaskFragment: Fragment(R.layout.fragment_ws_01) {

    private var threadButton : Button? = null
    private var threadTextView : TextView? = null

    val handler = MyHandler()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        findViews(view)
        threadButton?.setOnClickListener {
            startThread()
            startRunnable()
        }
    }

    override fun onDestroyView() {
        threadButton = null
        threadTextView = null
        super.onDestroyView()
    }

    private fun findViews(view: View) {
        threadButton = view.findViewById(R.id.thread_button)
        threadTextView = view.findViewById(R.id.thread_text_view)
    }

    private fun startThread() {
        val thread = MyThread()
        thread.start()
        printMessage(getString(R.string.wait))
        //TODO(WS1:5) create your thread and start it
    }

    private fun startRunnable() {
        val thread = MyThread()
        thread.start()
        printMessage(getString(R.string.wait))
        //TODO(WS1:8) create your runnable and start it
    }

    private fun printMessage(mes: String){
        threadTextView?.text =  mes
    }

    //TODO(WS1:1) Create inner class Handler
    // example: MyHandler : Handler(Looper.getMainLooper())

    @SuppressLint("HandlerLeak")
    inner class MyHandler: Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            printMessage(msg.data.getString(MESSAGE_KEY, ""))
        }

    }

    //TODO(WS1:2) override function handleMessage(mes: Message)
    // at the function run printMessage
    // Get string from mes.data.getString(MESSAGE_KEY, "")

    //TODO(WS1:3) Create inner class Thread
    // example: MyThread : Thread()
    // override function run

    //TODO(WS1:4) at the run function emulate long work with sleep(6000)
    // create Message, put string to the message.data with MESSAGE_KEY
    // send message to the handler handler.sendMessage(mes)

    inner class MyThread: Thread() {
        override fun run() {
            sleep(6000)
            val msg = Message()
            msg.data.putString(MESSAGE_KEY, getString(R.string.thread_worked))
            handler.sendMessage(msg)
        }
    }

    //TODO(WS1:6) Create inner class Runnable
    // example:  MyRunnable : Runnable
    // override function run

    //TODO(WS1:7) at the run function emulate long work with Thread.sleep(4000)
    // create Message, put string to the message.data with MESSAGE_KEY
    // send message to the handler handler.sendMessage(mes)

    inner class MyRunnable: Runnable {
        override fun run() {
            Thread.sleep(4000)
            val msg = Message()
            msg.data.putString(MESSAGE_KEY, getString(R.string.runnable_worked))
            handler.sendMessage(msg)
        }

    }

    companion object {
        private const val MESSAGE_KEY = "key"
    }
}