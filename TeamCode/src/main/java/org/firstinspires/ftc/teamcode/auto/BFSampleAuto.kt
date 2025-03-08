package org.firstinspires.ftc.teamcode.auto

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.supers.Robot

@Autonomous(name = "BF Sample")
class BFSampleAuto : LinearOpMode() {
    lateinit var r: Robot
    override fun runOpMode() {
        r = Robot(this, auto = true, Pose2d(Vector2d(36.00, 65.00), Math.toRadians(-90.00)))

        waitForStart()

        val fullAction = r.drive.actionBuilder(r.startPose)
            .strafeTo(Vector2d(54.00, 53.00))
            .turn(-45.00)
            .strafeTo(Vector2d(35.55, 40.58))
            .splineTo(Vector2d(25.00, -14.00), Math.toRadians(180.00))
            .build()

        runBlocking(fullAction)
    }
}