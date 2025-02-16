package org.firstinspires.ftc.teamcode.testing

import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import org.firstinspires.ftc.teamcode.supers.Robot

@Autonomous(name = "Square Test")
class SquareTest : LinearOpMode() {

    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while(opModeIsActive()) {
            runBlocking(
                r.drive.actionBuilder(r.startPose)
                    .strafeTo(Vector2d(0.00, 18.00))
                    .strafeTo(Vector2d(18.00, 18.00))
                    .strafeTo(Vector2d(18.00, 0.00))
                    .strafeTo(Vector2d(0.00, 0.00))
                    .build()
            )
        }
    }
}