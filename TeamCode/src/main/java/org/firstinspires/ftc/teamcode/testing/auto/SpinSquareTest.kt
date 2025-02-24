package org.firstinspires.ftc.teamcode.testing.auto

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.supers.Robot

@Autonomous(name = "Spin Square Test")
class SpinSquareTest : LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this, startPose = Pose2d(Vector2d(0.0, 0.0), 0.0))

        waitForStart()

        while (opModeIsActive()) {
            runBlocking(
                r.drive.actionBuilder(r.startPose)
                    .strafeToLinearHeading(Vector2d(0.00, 18.00), Math.toRadians(180.00))
                    .strafeToLinearHeading(Vector2d(18.00, 18.00), Math.toRadians(90.00))
                    .strafeToLinearHeading(Vector2d(18.00, 0.00), Math.toRadians(0.00))
                    .strafeToLinearHeading(Vector2d(0.00, 0.00), Math.toRadians(-90.00))
                    .build()
            )
        }
    }
}