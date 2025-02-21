package org.firstinspires.ftc.teamcode.util

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

class MotorArray(var motors: ArrayList<DcMotor>) {
    fun setZeroPowerBehavior(zeroPowerBehavior: DcMotor.ZeroPowerBehavior?) {
        for (motor in motors) {
            motor.zeroPowerBehavior = zeroPowerBehavior
        }
    }

    fun getZeroPowerBehavior(): DcMotor.ZeroPowerBehavior? {
        return motors[0].zeroPowerBehavior
    }

    fun setTargetPosition(position: Int) {
        for (motor in motors) {
            motor.targetPosition = position
        }
    }

    fun getTargetPosition(): Int {
        return motors[0].targetPosition
    }

    fun setMode(mode: DcMotor.RunMode?) {
        for (motor in motors) {
            motor.mode = mode
        }
    }

    fun getMode(): DcMotor.RunMode? {
        return motors[0].mode
    }

    fun setDirection(direction: DcMotorSimple.Direction?) {
        for (motor in motors) {
            motor.direction = direction
        }
    }

    fun getDirection(): DcMotorSimple.Direction? {
        return motors[0].direction
    }

    fun setPower(power: Double) {
        for (motor in motors) {
            motor.power = power
        }
    }

    fun getPower(): Double {
        return motors[0].power
    }
}