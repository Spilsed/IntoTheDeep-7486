package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.clampi
import kotlin.math.max

open class LinearSlide(open val motor: DcMotor, private val ppr: Double, private val ratio: Double) {
    // Ratio is rotations/inch
    var zeroPosition: Int = -20
    private val maxPosition: Int = motor.currentPosition + inchesToTicks(12.0)

    init {
        motor.targetPosition = motor.currentPosition
        motor.mode = DcMotor.RunMode.RUN_TO_POSITION
        motor.power = 0.4
    }

    fun extend(delta: Double) {
        motor.targetPosition = clampi(inchesToTicks(delta) + motor.currentPosition, zeroPosition, maxPosition)
    }

    fun extendTicks(delta: Int) {
        motor.targetPosition = motor.currentPosition + delta
    }

    fun returnToZero() {
        motor.targetPosition = zeroPosition
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