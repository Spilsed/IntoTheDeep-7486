package org.firstinspires.ftc.teamcode.tuning

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D
import org.firstinspires.ftc.teamcode.supers.Robot
import java.util.Locale

@TeleOp(name="Odo Tuner")
class OdoTuner : LinearOpMode() {
    override fun runOpMode() {
        val r = Robot(this)
        var pos: Pose2D

        waitForStart()
        resetRuntime()

        while (opModeIsActive()) {
            r.odo.update()

            pos = r.odo.position
            r.dashboardTelemetry.addData("X", pos.getX(DistanceUnit.INCH))
            r.dashboardTelemetry.addData("Y", pos.getY(DistanceUnit.INCH))
            r.dashboardTelemetry.addData("H", pos.getHeading(AngleUnit.DEGREES))
            r.dashboardTelemetry.addData("Status", r.odo.deviceStatus)

            r.dashboardTelemetry.update()
        }
    }
}