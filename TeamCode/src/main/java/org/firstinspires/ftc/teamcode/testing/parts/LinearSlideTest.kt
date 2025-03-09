package org.firstinspires.ftc.teamcode.testing.parts

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name="LinearSlideTest")
class LinearSlideTest : LinearOpMode() {
    override fun runOpMode() {
        val r = Robot(this)
        r.linearActuator.motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        // r.linearActuator.zeroPosition = -10000

        waitForStart()

        while (isStarted && !isStopRequested) {
            r.dashboardTelemetry.addData("Current", r.linearActuator.motor.currentPosition)
            r.dashboardTelemetry.addData("Target", r.linearActuator.motor.targetPosition)
            r.dashboardTelemetry.addData("G", (gamepad1.left_stick_y.toDouble() * 10).toInt())
            r.dashboardTelemetry.update()

            if (gamepad1.a) {
                r.linearActuator.motor.power = 0.2
            } else if (gamepad1.b) {
                r.linearActuator.motor.power = -0.2
            } else {
                r.linearActuator.motor.power = 0.0
            }
            // r.linearActuator.extendTicks((gamepad1.left_stick_y.toDouble() * 10).toInt())
        }
    }
}