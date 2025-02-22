package org.firstinspires.ftc.teamcode.util

import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple

class MotorArray(var motors: ArrayList<DcMotor>) {
    var zeroPowerBehavior: DcMotor.ZeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
        set(value) {
            field = value
            for (motor in motors) {
                motor.zeroPowerBehavior = zeroPowerBehavior
            }
        }
        get() {
            return motors[0].zeroPowerBehavior
        }

    var targetPosition: Int = 0
        set(value) {
            field = value
            for (motor in motors) {
                motor.targetPosition = targetPosition
            }
        }
        get() {
            return motors[0].targetPosition
        }

    var mode: DcMotor.RunMode = motors[0].mode
        set(value) {
            field = value
            for (motor in motors) {
                motor.mode = mode
            }
        }
        get() {
            return motors[0].mode
        }

    var direction: DcMotorSimple.Direction = DcMotorSimple.Direction.FORWARD
        set(value) {
            field = value
            for (motor in motors) {
                motor.direction = direction
            }
        }
        get() {
            return motors[0].direction
        }

    var power: Double = 0.0
        set(value) {
            field = value
            for (motor in motors) {
                motor.power = power
            }
        }
        get() {
            return motors[0].power
        }
}