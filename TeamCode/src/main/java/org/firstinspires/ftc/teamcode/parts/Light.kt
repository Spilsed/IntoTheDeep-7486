package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.util.clampf

class Light(val servo: Servo) {
    var on: Boolean = false
        set(value) {
            field = value

            servo.position = if (on) scaleColors(color) else 0.0
        }

    var color: Double = 0.0
        set(value) {
            field = value
            servo.position = scaleColors(color)
        }

    var blinkInterval: Double = 0.0
    private var lastBlink: Int = 0

    fun scaleColors(color: Double): Double {
        return (clampf(color, 0.279, 0.722) - 0.279) / 0.443
    }

    fun update() {
        if (System.currentTimeMillis() <= lastBlink + (blinkInterval/2 * 1000).toInt())  {
            on = !on
        }
    }
}