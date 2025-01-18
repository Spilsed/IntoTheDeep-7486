package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.util.clampi

class Lift(val motor: DcMotor, val servo: Servo, val ppr: Double, val motorRatio: Double, val motorMin: Int, val motorMax: Int, val servoMin: Double, val servoMax: Double) {
    init {
        motor.targetPosition = motorMin
    }

    var length: Double = 0.0
        set(value) {
            field = value

            motor.targetPosition = clampi(inchesToTicks(length), motorMin, motorMax)
        }

    var isUp: Boolean = false

    fun expand(delta: Double) {
        length += inchesToTicks(delta)
    }

    fun servoUp() {
        servo.position = servoMax
        isUp = true
    }

    fun servoDown() {
        if (length == 0.0) {
            servo.position = servoMin
            isUp = false
        }
    }

    private fun inchesToTicks(inches: Double): Int {
        return (inches / motorRatio * ppr).toInt()
    }

    private fun ticksToInches(ticks: Double): Double {
        return ticks / ppr * motorRatio
    }
}