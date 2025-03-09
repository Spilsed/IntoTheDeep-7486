package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.clampi
import kotlin.math.abs

class LinearSlide(val motor: DcMotor, private val ppr: Double, private val ratio: Double, auto: Boolean) {
    // Ratio is rotations/inch
    var min: Int = -1200
    var max: Int = 40

    var limited: Boolean = true
    private val zeroModifier: Int = -1

    init {
        if (auto) {
            motor.targetPosition = motor.currentPosition
            motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            motor.power = 0.4
        } else {
            motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    var targetPosition: Int = motor.currentPosition
        set(value) {
            field = value // clampi(value, min, max)
            motor.targetPosition = field
            motor.mode = DcMotor.RunMode.RUN_TO_POSITION
            motor.power = 0.5
        }

    val currentPosition: Int
        get() = motor.currentPosition

    val isAtTarget: Boolean
        get() = abs(currentPosition - targetPosition) < 20

    var power: Double = 0.0
        set(value) {
            field = if (limited) limitPower(value, motor.currentPosition) else value
            motor.power = field
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
        power = limitPower(power, motor.currentPosition)

        if (isAtTarget) {
            motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }
    }

    private fun inchesToTicks(inches: Double): Int {
        return (inches / ratio * ppr).toInt()
    }

    private fun ticksToInches(ticks: Double): Double {
        return ticks / ppr * ratio
    }
}

class ContLinearSlide(val motor: DcMotor, private val ppr: Double, private val ratio: Double) {
    var zeroPosition: Int = motor.currentPosition - 1000
    private val maxPosition: Int = motor.currentPosition + inchesToTicks(12.0)

    init {
        motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
    }

    var power: Double = 0.0
        set(value) {
            if (motor.currentPosition >= maxPosition) {
                if (motor.power > 0) {
                    motor.power = 0.0
                } else {
                    motor.power = value
                }
            } else if (motor.currentPosition <= zeroPosition) {
                if (motor.power < 0) {
                    motor.power = 0.0
                } else {
                    motor.power = value
                }
            }

            power = motor.power
        }

    private fun inchesToTicks(inches: Double): Int {
        return (inches / ratio * ppr).toInt()
    }
}