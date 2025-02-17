package org.firstinspires.ftc.teamcode.parts

import org.firstinspires.ftc.teamcode.util.clampf

class LightArray(val lights: MutableList<Light>) {
    var on: Boolean = false
        set(value) {
            field = value

            for (light in lights) {
                light.on = on
            }
        }

    var color: Double = 0.0
        set(value) {
            field = value

            for (light in lights) {
                light.color = color
            }
        }

    var blinkInterval: Double = 0.0
    private var lastBlink: Int = 0

    private val maxPWM: Double = 0.723
    private val minPWM: Double = 0.279

    fun scaleColors(color: Double): Double {
        return (clampf(color, minPWM, maxPWM) - minPWM) / (maxPWM - minPWM)
    }

    // Needs to be run for blinking
    fun update() {
        if (System.currentTimeMillis() <= lastBlink + (blinkInterval/2 * 1000).toInt())  {
            for (light in lights) {
                light.on = on
            }
        }
    }
}