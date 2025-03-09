package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.MotorArray
import org.firstinspires.ftc.teamcode.util.clampi
import kotlin.math.abs

class RotationalArm(val motor1: DcMotor, val motor2: DcMotor, var min: Int, var max: Int, auto: Boolean, private val acceptableRange: Int = 20, up: Boolean = false) {
    val motors = MotorArray(arrayListOf(motor1, motor2))

    val zeroModifier: Int

    var limited: Boolean = true

    init {
        motor1.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        motor2.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT

        if (auto) {
            motors.targetPosition = motor1.currentPosition
            motors.mode = DcMotor.RunMode.RUN_TO_POSITION
        } else {
            motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
            motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        }

        zeroModifier = if (up) { 1 } else { -1 }
    }

    var targetPosition: Int = motor1.currentPosition
        set(value) {
            field = clampi(value, min, max)
            motors.targetPosition = field
            motors.mode = DcMotor.RunMode.RUN_TO_POSITION
            motors.power = 0.5
        }

    val currentPosition: Int
        get() = motor1.currentPosition

    val isAtTarget: Boolean
        get() = abs(currentPosition - targetPosition) < acceptableRange

    var power: Double = 0.0
        set(value) {
            field = if (limited) limitPower(value, motor1.currentPosition) else value
            motors.power = field
        }

    private fun limitPower(power: Double, position: Int): Double {
        return if (position >= max && power * zeroModifier <= 0.0) {
            0.0
        } else if (position <= min && power * zeroModifier >= 0.0) {
            0.0
        } else {
            power

            // As value approaches a maximum decrease the maximum speed of the motor
            // clampf(abs(power), 0.1, log((min(abs(position - min), abs(position - max)).toDouble() + 50) / 50, 10.0)) * sign(power)
        }
    }

    fun update() {
        power = limitPower(power, motor1.currentPosition)

        if (isAtTarget) {
            motors.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }
    }
}