package org.firstinspires.ftc.teamcode.testing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "IndividualMotorTest")
class IndividualMotorTest : LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            if (gamepad1.a) {
                r.linearActuator.power = 0.7
            } else if (gamepad1.b) {
                r.linearActuator.power = -0.7
            } else {
                r.linearActuator.power = 0.0
            }

            r.dashboardTelemetry.addData("Position", r.linearActuator.motor.currentPosition)
            r.dashboardTelemetry.addData("Target", r.linearActuator.motor.targetPosition)
            r.dashboardTelemetry.addData("Power", r.linearActuator.motor.power)
            r.dashboardTelemetry.addData("LPower", r.linearActuator.power)
            r.dashboardTelemetry.update()
        }
    }
}