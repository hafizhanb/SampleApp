package com.example.sampleapp.cam

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import com.example.sampleapp.R
import com.example.sampleapp.databinding.ActivityCamBinding


class CamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCamBinding

    private var widgetDX: Float = 0F
    private var widgetDY: Float = 0F

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.setNavigationOnClickListener { finish() }
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.fragment_container_main,
                CameraFragment().also { x -> x.arguments = bundleOf("isBack" to true) },
                CameraFragment::class.java.simpleName
            )
        }.commit()
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
                    if (event.rawX >= parentWidth / 2)
                        v.animate().x((parentWidth) - (v.width).toFloat()).setDuration(250).start()
                    else
                        v.animate().x(0F).setDuration(250).start()

                    if (event.rawY >= parentHeight / 2)
                        v.animate().y((parentHeight) - (v.height).toFloat()).setDuration(250)
                            .start()
                    else
                        v.animate().y(0F).setDuration(250).start()
                }
                else -> {
                    return@setOnTouchListener false
                }
            }
            true
        }

        val params = binding.fragmentContainer.layoutParams
        params.height = 4 * 72
        params.width = 3 * 72
        binding.fragmentContainer.layoutParams = params
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.fragment_container,
                CameraFragment(),
                CameraFragment::class.java.simpleName
            )
        }.commit()
    }

}