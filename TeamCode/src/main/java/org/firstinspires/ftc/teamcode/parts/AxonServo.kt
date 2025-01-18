package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.AnalogInput
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.util.clampf

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
    var targetPosition: Double = 0.0
        set(value) {
            // Make sure the value is in range
            field = clampf(value, 0.0, 1.0)
            // Make the servo go there
            servo.position = field
        }

    fun updatePosition {
        if (abs(position - targetPosition) >= 0.01) {
            servo.power = sign(position - targetPosition) * 0.5
        } else {
            servo.power = 0.0
        }
    }
}
