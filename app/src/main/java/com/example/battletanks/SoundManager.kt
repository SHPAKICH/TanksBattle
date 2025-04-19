package com.example.battletanks

import android.annotation.SuppressLint
import android.content.Context
import android.media.MediaPlayer

@SuppressLint("StaticFieldLeak")
object SoundManager {
    private lateinit var bulletBurstPlayer: MediaPlayer
    private lateinit var bulletShotPlayer: MediaPlayer
    private lateinit var introMusicPlayer: MediaPlayer
    private lateinit var tankMovePlayerFirst: MediaPlayer
    private lateinit var tankMovePlayerSecond: MediaPlayer
    private var isIntroFinished = false


    var context: Context? = null
        set(value) {
            bulletBurstPlayer = MediaPlayer.create(value, R.raw.bullet_burst)
            bulletShotPlayer = MediaPlayer.create(value, R.raw.bullet_shot)
            introMusicPlayer = MediaPlayer.create(value, R.raw.tanks_pre_music)
            prepareGaplessTankMoveSound(value!!)
        }

    private fun prepareGaplessTankMoveSound(context: Context) {
        tankMovePlayerSecond = MediaPlayer.create(context, R.raw.tank_move_long)
        tankMovePlayerFirst = MediaPlayer.create(context, R.raw.tank_move_long)
        tankMovePlayerFirst.isLooping = true
        tankMovePlayerSecond.isLooping = true
        tankMovePlayerSecond.setNextMediaPlayer(tankMovePlayerSecond)
        tankMovePlayerFirst.setNextMediaPlayer(tankMovePlayerFirst)
    }

    fun playIntroMusic() {
        if (isIntroFinished) {
            return
        }

        introMusicPlayer.setOnCompletionListener {
            isIntroFinished = true
        }

        introMusicPlayer.start()
    }

    fun pauseSounds() {
        bulletBurstPlayer.pause()
        bulletShotPlayer.pause()
        introMusicPlayer.pause()
        tankMovePlayerFirst.pause()
        tankMovePlayerSecond.pause()
    }

    fun bulletShot() {
        bulletShotPlayer.start()
    }

    fun bulletBurst() {
        bulletBurstPlayer.start()
    }

    fun tankMove() {
        tankMovePlayerFirst.start()
    }

    fun tankStop() {
        if (tankMovePlayerFirst.isPlaying) {
            tankMovePlayerFirst.pause()
        }
        if (tankMovePlayerSecond.isPlaying) {
            tankMovePlayerSecond.pause()
        }
    }
}
