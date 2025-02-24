package org.firstinspires.ftc.teamcode.util

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "[!] ResetRotationalEncoders [!]")
class ResetRotationalEncoders : LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        r.rotationalArm.motor1.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        r.rotationalArm.motor2.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
        r.rotationalArm.motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
        r.rotationalArm.motor2.mode = DcMotor.RunMode.RUN_USING_ENCODER

        telemetry.addData("Motor1", r.rotationalArm.motor1.currentPosition)
        telemetry.addData("Motor2", r.rotationalArm.motor2.currentPosition)
        if (r.rotationalArm.motor1.currentPosition == 0 && r.rotationalArm.motor2.currentPosition == 0) {
            telemetry.addLine("Encoders successfully reset!")
        } else {
            telemetry.addLine("Encoder reset failed?")
        }
        telemetry.update()

        waitForStart()
    }
}