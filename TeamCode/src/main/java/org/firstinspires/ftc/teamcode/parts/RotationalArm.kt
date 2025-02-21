package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.util.clampi

class RotationalArm(val motor1: DcMotor, val motor2: DcMotor, var min: Int, val max: Int, up: Boolean = false) {
    val motors: Array<DcMotor> = arrayOf(motor1, motor2)

    val zeroModifier: Int

    init {
        motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
        motor1.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE

        motor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        motor2.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.FLOAT

        zeroModifier = if (up) { 1 } else { -1 }
    }

    var power: Double = 0.0
        set(value) {
            field = if (motor1.currentPosition >= max && power * zeroModifier < 0.0) {
                0.0
            } else if (motor1.currentPosition <= min && power * zeroModifier > 0.0) {
                0.0
            } else {
                value
            }

            for (motor in motors) {
                motor.power = field
            }
        }

    fun update() {
        if (motor1.currentPosition >= max && power * zeroModifier > 0.0) {
            power = 0.0
        }

        if (motor1.currentPosition <= min && power * zeroModifier < 0.0) {
            power = 0.0
        }
    }
}