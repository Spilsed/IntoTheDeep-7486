package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.MotorArray
import org.firstinspires.ftc.teamcode.util.clampf
import org.firstinspires.ftc.teamcode.util.clampi
import kotlin.math.abs
import kotlin.math.log
import kotlin.math.min
import kotlin.math.sign

class RotationalArm(val motor1: DcMotor, val motor2: DcMotor, var min: Int, val max: Int, auto: Boolean, up: Boolean = true) {
    val motors = MotorArray(arrayListOf(motor1, motor2))

    val zeroModifier: Int

    init {
        motor1.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        motor2.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT

        if (auto) {
            motors.targetPosition = motor1.currentPosition
            motors.mode = DcMotor.RunMode.RUN_TO_POSITION
            motors.power = 0.5
        } else {
            motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
            motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        }

        zeroModifier = if (up) { 1 } else { -1 }
    }

    var power: Double = 0.0
        set(value) {
            field = limitPower(value, motor1.currentPosition)

            motors.power = field
        }

    fun limitPower(power: Double, position: Int): Double {
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
    }
}