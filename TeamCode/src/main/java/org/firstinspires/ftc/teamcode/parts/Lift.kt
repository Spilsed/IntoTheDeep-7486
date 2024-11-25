package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

class Lift(val motor: DcMotor, val servo: Servo, val ppr: Double, val motorRatio: Double, val motorMin: Int, val motorMax: Int, val servoMin: Double, val servoMax: Double) {
    var length: Double = 0.0
        set(value) {
            field = value

            motor.targetPosition = inchesToTicks(length)
        }

    fun expand(delta: Double) {
        length += inchesToTicks(delta)
    }

    fun servoUp() {
        servo.position = servoMax
    }

    fun servoDown() {
        servo.position = servoMin
    }

    private fun inchesToTicks(inches: Double): Int {
        return (inches / motorRatio * ppr).toInt()
    }

    private fun ticksToInches(ticks: Double): Double {
        return ticks / ppr * motorRatio
    }
}