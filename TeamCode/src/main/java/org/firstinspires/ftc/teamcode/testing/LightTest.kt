package org.firstinspires.ftc.teamcode.testing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo

@TeleOp(name = "Light Test")
class LightTest : LinearOpMode() {
    override fun runOpMode() {
        val servo: CRServo = hardwareMap.get(CRServo::class.java, "sweeper")

        waitForStart()

        var light: Double = 0.0

        while (opModeIsActive()) {
            if (gamepad1.a) {
                light += 0.001
            } else if (gamepad1.b) {
                light += -0.001
            }

            servo.power = light
        }
    }
}