package org.firstinspires.ftc.teamcode.testing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "MotorTest")
class MotorTest : LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            if (gamepad1.a) {
                for (motor in r.motors) {
                    motor.power = 1.0
                }
            } else if (gamepad1.b) {
                for (motor in r.motors) {
                    motor.power = -1.0
                }
            } else {
                for (motor in r.motors) {
                    motor.power = 0.0
                }
            }

            r.dashboardTelemetry.addData("lf", r.lf.currentPosition)
            r.dashboardTelemetry.addData("lb", r.lb.currentPosition)
            r.dashboardTelemetry.addData("rf", r.rf.currentPosition)
            r.dashboardTelemetry.addData("rb", r.lb.currentPosition)
            r.dashboardTelemetry.update()
        }
    }
}