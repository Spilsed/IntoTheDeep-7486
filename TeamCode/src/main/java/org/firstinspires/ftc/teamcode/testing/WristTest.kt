package org.firstinspires.ftc.teamcode.testing

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.parts.Wrist
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name="WristTest")
class WristTest: LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            r.wrist.turn(gamepad1.left_stick_y.toDouble() / 100)
            r.wrist.twist(gamepad1.left_stick_x.toDouble() / 100)
            // This is what makes the servos actually move this needs to be where ever the wrist is used
            r.wrist.updatePositions()
        }
    }
}
