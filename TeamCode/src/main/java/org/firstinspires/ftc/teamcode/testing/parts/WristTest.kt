package org.firstinspires.ftc.teamcode.testing.parts

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name="WristTest")
class WristTest: LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            // r.wrist.turn(gamepad1.left_stick_y.toDouble() / 4)
            // r.wrist.twist(gamepad1.left_stick_x.toDouble() / 4)
            r.wrist.lServo.power = gamepad1.left_stick_x.toDouble()
            r.wrist.rServo.power = gamepad1.right_stick_x.toDouble()

            if (gamepad1.a) {
                r.wrist.lServo.power = -0.5
                r.wrist.rServo.power = 0.5
            } else if (gamepad1.b) {
                r.wrist.lServo.power = 0.5
                r.wrist.rServo.power = -0.5
            }

            telemetry.addData("LWrist", r.wrist.lServo.power)
            telemetry.addData("RWrist", r.wrist.rServo.power)
            telemetry.update()
            // This is what makes the servos actually move this needs to be where ever the wrist is used
            //r.wrist.updatePositions()
        }
    }
}
