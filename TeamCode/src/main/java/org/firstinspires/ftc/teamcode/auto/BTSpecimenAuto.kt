package org.firstinspires.ftc.teamcode.auto

import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.Vector2d
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.supers.Robot

@Autonomous(name = "BT Specimen")
class BBSpecimenAuto : LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this, auto = true, Pose2d(Vector2d(-12.00, -65.00), Math.toRadians(90.00)))

        waitForStart()

        val fullAction = SequentialAction(
            r.drive.actionBuilder(r.startPose)
                .strafeTo(Vector2d(0.00, -40.00))
                // .afterDisp(0.0, r.ArmAndWristToPickup())

                .strafeTo(Vector2d(-49.00, -38.00))
                //  .afterDisp(0.0, r.ArmToSecondBar())

                .strafeTo(Vector2d(0.00, -40.00))
                //  .afterDisp(0.0, r.ArmAndWristToPickup())

                .strafeTo(Vector2d(-59.00, -38.00))
                //  .afterDisp(0.0, r.ArmToSecondBar())

                .strafeTo(Vector2d(0.00,- 40.00))
                //  .afterDisp(0.0, r.ArmAndWristToPickup())

                .strafeTo(Vector2d(36.00, -40.00))
                .splineTo(Vector2d(26.00, -12.00), Math.toRadians(0.00))
                .build()
        )

        runBlocking(fullAction)
    }
}