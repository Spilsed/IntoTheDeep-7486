package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.clampi

class RotationalArm(val motor1: DcMotor, val motor2: DcMotor, val min: Int, val max: Int) {
    var position: Int = 0
        set(value) {
            field = value

            if (motor1.mode == DcMotor.RunMode.RUN_TO_POSITION || motor2.mode == DcMotor.RunMode.RUN_TO_POSITION) {
                motor1.targetPosition = clampi(position, min, max)
                motor2.targetPosition = clampi(position, min, max)
            }
        }

    var power: Double = 0.0
        set(value) {
            field = value

            motor1.power = power
            motor2.power = power
        }
}