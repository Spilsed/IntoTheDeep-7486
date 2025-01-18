package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.AnalogInput
import com.qualcomm.robotcore.hardware.CRServo
import org.firstinspires.ftc.teamcode.util.clampf
import kotlin.math.abs
import kotlin.math.sign

class AxonServo(val servo: CRServo, val analog: AnalogInput) {
    // Holds the current position of the servo
    var position: Double
        get() {
            // Return position [0-1]
            return analog.voltage / 3.3
        }
        set(value) {
            // Set target position because this stores current
            targetPosition = value
        }

    // Target position of the servo
    var targetPosition: Double = 0.35
        set(value) {
            // Make sure the value is in range
            field = clampf(value, 0.0, 1.0)
        }

    fun updatePosition() {
        if (abs(position - targetPosition) >= 0.01) {
            servo.power = sign(targetPosition - position) * 0.5
        } else {
            servo.power = 0.0
        }
    }
}
