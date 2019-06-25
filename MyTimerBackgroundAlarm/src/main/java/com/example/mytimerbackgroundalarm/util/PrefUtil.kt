@file:Suppress("DEPRECATION")

package com.example.mytimerbackgroundalarm.util

import android.content.Context
import android.preference.PreferenceManager
import com.example.mytimerbackgroundalarm.TimerActivity

@Suppress("DEPRECATION")
class PreUtil {
    companion object{
        fun getTimerLength(context: Context): Int{
            //placeholder
            return 1
        }

        private const val PREVIOIS_TIMER_LENGTH_SECOND_ID = "com.example.timer.previous_timer_length"

        //시작 타이머 시간 초기화 설정
        fun getPreviousTimerLengthSeconds(context: Context):Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(PREVIOIS_TIMER_LENGTH_SECOND_ID,0)
        }

        //시작 타이머 시간 지정하기
        fun setPreviousTimerLengthSeconds(seconds: Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(PREVIOIS_TIMER_LENGTH_SECOND_ID,seconds)
            editor.apply()
        }

        private const val TIMER_STATE_ID = "com.example.timer.timer_state"

        //타이머의 현재 상태 가져오기
        fun getTimerState(context: Context) : TimerActivity.TimerState{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val ordinal = preferences.getInt(TIMER_STATE_ID,0)
            return TimerActivity.TimerState.values()[ordinal]
        }

        //타이머의 현재 상태 지정하기
        fun setTimerState(state: TimerActivity.TimerState, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            val ordinal = state.ordinal
            editor.putInt(TIMER_STATE_ID,ordinal)
            editor.apply()

        }

        private const val SECONDS_REMAINING_ID = "com.example.timer.seconds_remaining"

        //남은 타이머 시간 초기화 설정
        fun getSecondsRemaining(context: Context):Long{
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getLong(SECONDS_REMAINING_ID,0)
        }

        //남은 타이머 시간 지정하기
        fun setSecondsRemaining(seconds: Long, context: Context){
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putLong(SECONDS_REMAINING_ID,seconds)
            editor.apply()
        }
    }
}