package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.clampi

class RotationalArm(val motor1: DcMotor, val motor2: DcMotor, var min: Int, val max: Int) {
    init {
        motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
        motor1.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor2.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT
    }

    val motors: Array<DcMotor> = arrayOf(motor1, motor2)

    var power: Double = 0.0
        set(value) {
            field = value

            for (motor in motors) {
                motor.power = power
            }
        }

    fun update() {
        if (motor1.currentPosition >= max && power > 0.0) {
            power = 0.0
        }

        if (motor1.currentPosition <= min && power < 0.0) {
            power = 0.0
        }
    }
}