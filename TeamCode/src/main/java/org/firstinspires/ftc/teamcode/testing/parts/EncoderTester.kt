package org.firstinspires.ftc.teamcode.testing.parts

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "EncoderTester")
class EncoderTester : LinearOpMode() {

    private lateinit var armMotor1 : DcMotor
    private lateinit var armMotor2 : DcMotor

    lateinit var r: Robot

    override fun runOpMode() {
        var r = Robot(this)
        armMotor1 = hardwareMap.get(DcMotor::class.java, "arm1")
        armMotor2 = hardwareMap.get(DcMotor::class.java, "arm2")

        armMotor1.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER
        armMotor2.mode = DcMotor.RunMode.RUN_WITHOUT_ENCODER

        waitForStart()

        while (opModeIsActive()) {
            val armPower = -gamepad1.left_stick_y.toDouble()
            armMotor1.power = armPower
            armMotor2.power = armPower

            val encoderPosition1 = armMotor1.currentPosition
            val encoderPosition2 = armMotor2.currentPosition

            r.dashboardTelemetry.addData("Encoder Position1", encoderPosition1)
            r.dashboardTelemetry.addData("Encoder Position2", encoderPosition2)
            r.dashboardTelemetry.update()
        }
    }

}