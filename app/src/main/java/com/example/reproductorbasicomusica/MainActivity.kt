package com.example.reproductorbasicomusica

import android.content.Context
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var buttonPlay: Button
    private lateinit var buttonPause: Button
    private lateinit var buttonStop: Button

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enlazamos los botones del XML
        buttonPlay = findViewById(R.id.buttonPlay)
        buttonPause = findViewById(R.id.buttonPause)
        buttonStop = findViewById(R.id.buttonStop)

        // Usamos el audio renombrado en res/raw
        mediaPlayer = MediaPlayer.create(this, R.raw.not_like_us)

        setOnClickListeners(this)
    }

    private fun setOnClickListeners(context: Context) {
        buttonPlay.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
                Toast.makeText(context, "Reproduciendo...", Toast.LENGTH_SHORT).show()
            }
        }

        buttonPause.setOnClickListener {
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                Toast.makeText(context, "En pausa...", Toast.LENGTH_SHORT).show()
            }
        }

        buttonStop.setOnClickListener {
            if (mediaPlayer.isPlaying || mediaPlayer.currentPosition > 0) {
                mediaPlayer.stop()
                mediaPlayer = MediaPlayer.create(context, R.raw.not_like_us) // Reiniciamos
                Toast.makeText(context, "Detenido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.release()
        }
    }
}