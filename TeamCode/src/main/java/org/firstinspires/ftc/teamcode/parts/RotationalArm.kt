package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.clampi

class RotationalArm(val motor1: DcMotor, val motor2: DcMotor, val min: Int, val max: Int) {
    init {
        motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
    }

    val motors: Array<DcMotor> = arrayOf(motor1, motor2)

    var power: Double = 0.0
        set(value) {
            field = value

            for (motor in motors) {
                motor.power = power
            }
        }
}