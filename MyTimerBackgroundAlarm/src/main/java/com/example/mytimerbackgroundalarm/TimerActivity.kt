package com.example.mytimerbackgroundalarm

import android.os.Bundle
import android.os.CountDownTimer
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;

import kotlinx.android.synthetic.main.activity_timer.*

class TimerActivity : AppCompatActivity() {

    enum class TimerState {
        Stopped, Paused, Running
    }

    private lateinit var timer : CountDownTimer
    private var timerLengthSeconds = 0
    private var timerState = TimerState.Stopped

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)
        setSupportActionBar(toolbar)
        supportActionBar?.setIcon(R.drawable.ic_timer)
        supportActionBar?.title = "     Timer"

        fab_start.setOnClickListener { v ->
            startTimer()
            timerState = TimerState.Running
            updateButtons()
        }
        fab_pause.setOnClickListener { v ->
            timer.cancel()
            timerState = TimerState.Paused
            updateButtons()
        }
        fab_stop.setOnClickListener { v ->
            timer.cancel()
            onTimerFinished()
        }
        }

    override fun onResume() {
        super.onResume()

        initTimer()

        //TODO: remove background timer, hide notification
    }

    override fun onPause() {
        super.onPause()

        if(timerState == TimerState.Running){
            timer.cancel()
            //TODO:start background timer and show notification
        }else if()
    }
}

