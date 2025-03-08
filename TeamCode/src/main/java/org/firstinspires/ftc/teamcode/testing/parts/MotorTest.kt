package org.firstinspires.ftc.teamcode.testing.parts

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
            for (motor in r.motors) {
                motor.power = gamepad1.left_stick_y.toDouble()
            }

            var voltage = hardwareMap.voltageSensor.iterator().next()

            r.dashboardTelemetry.addData("lf", r.lf.currentPosition)
            r.dashboardTelemetry.addData("lb", r.lb.currentPosition)
            r.dashboardTelemetry.addData("rf", r.rf.currentPosition)
            r.dashboardTelemetry.addData("rb", r.rb.currentPosition)
            r.dashboardTelemetry.addData("lf-volt", voltage.voltage)
            r.dashboardTelemetry.update()
        }
    }
}