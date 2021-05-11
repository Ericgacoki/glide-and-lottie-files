package com.ericg.glide

import android.animation.Animator
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.ericg.glide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Glide & Lottie Anim"
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadImage(
            "https://www.pandasecurity.com/en/mediacenter/src/uploads/2013/11/download-facebook-photo.jpg",
            binding.image1
        )
    }

    private fun loadImage(url: String, view: ImageView) {
        Glide.with(this)
            .load(url)
            .centerCrop()
            .placeholder(R.drawable.ic_placeholder)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Toast.makeText(this@MainActivity, "Failed to fetch image", Toast.LENGTH_SHORT)
                        .show()
                    Log.e("MainActivity", e.toString())
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    activateLottie()
                    return false
                }
            })
            .into(view)
    }

    private fun activateLottie() {

        binding.lottieAnim.apply {
            visibility = VISIBLE
            playAnimation()
            addAnimatorPauseListener(object : Animator.AnimatorPauseListener {
                override fun onAnimationPause(animation: Animator?) {
                    binding.lottieAnim.visibility = GONE
                }

                override fun onAnimationResume(animation: Animator?) {

                }

            })
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}