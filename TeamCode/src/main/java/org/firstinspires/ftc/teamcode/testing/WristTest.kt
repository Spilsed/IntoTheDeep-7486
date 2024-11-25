package org.firstinspires.ftc.teamcode.testing

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.parts.Wrist

@TeleOp(name="WristTest")
class WristTest: LinearOpMode() {
    override fun runOpMode() {
        val lServo: Servo = hardwareMap.get(Servo::class.java, "wrist1")
        val rServo: Servo = hardwareMap.get(Servo::class.java, "wrist2")
        val wrist = Wrist(lServo, rServo)

        val dashboard: FtcDashboard? = FtcDashboard.getInstance()
        val dashboardTelemetry = dashboard!!.telemetry

        waitForStart()

        while (isStarted && !isStopRequested) {
            wrist.twist(gamepad1.left_stick_y.toDouble() / 50)
            wrist.turn(gamepad1.left_stick_x.toDouble() / 50)

            dashboardTelemetry.addData("Turn", wrist.getTurn())
            dashboardTelemetry.addData("Twist", wrist.getTwist())
            dashboardTelemetry.addData("L", wrist.lServoTarget)
            dashboardTelemetry.addData("R", wrist.rServoTarget)
            dashboardTelemetry.update()
        }
    }

}