package com.example.sampleapp.cam

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sampleapp.databinding.ActivityCamBinding


class CamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamBinding

    private var widgetDX: Float = 0F
    private var widgetDY: Float = 0F
    private var widgetXOrigin: Float = 0F
    private var widgetYOrigin: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
        setupStickyDraggable()
    }

    @SuppressLint("ClickableViewAccessibility")

    fun setupStickyDraggable() {
        binding.cvCam.setOnTouchListener { v, event ->
            val viewParent: View = (v.parent as View)
            val parentHeight = viewParent.height
            val parentWidth = viewParent.width

            when (event.actionMasked) {
                MotionEvent.ACTION_DOWN -> {
                    widgetDX = v.x - event.rawX
                    widgetDY = v.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    var newX = event.rawX + this.widgetDX
                    newX = 0F.coerceAtLeast(newX)
                    newX = (parentWidth - v.width).toFloat().coerceAtMost(newX)
                    v.x = newX

                    var newY = event.rawY + this.widgetDY
                    newY = 0F.coerceAtLeast(newY)
                    newY = (parentHeight - v.height).toFloat().coerceAtMost(newY)
                    v.y = newY
                }
                MotionEvent.ACTION_UP -> {
                    // Stick to Left or Right screen
                    if (event.rawX >= parentWidth / 2)
                        v.animate().x((parentWidth) - (v.width).toFloat()).setDuration(250).start()
                    else
                        v.animate().x(0F).setDuration(250).start()

                    // Stick to Top or Bottom screen
                    if (event.rawY >= parentHeight / 2)
                        v.animate().y((parentHeight) - (v.height).toFloat()).setDuration(250)
                            .start()
                    else
                        v.animate().y(0F).setDuration(250).start()

                    // IF BOTH X & Y set to stick, the view will only stick to corner
                }
                else -> {
                    return@setOnTouchListener false
                }
            }
            true
        }
    }

}