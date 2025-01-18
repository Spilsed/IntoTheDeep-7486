package org.firstinspires.ftc.teamcode.testing

import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.roadRunner.MecanumDrive
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "LocalizationTesting")
class LocalizationTesting : LinearOpMode() {
    override fun runOpMode() {
        val r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            r.dashboardTelemetry.addData("Status", r.odo.deviceStatus)
            r.dashboardTelemetry.update()
        }
    }
}