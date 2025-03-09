package org.firstinspires.ftc.teamcode.auto

import com.acmerobotics.roadrunner.ParallelAction
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

        runBlocking(
            r.drive.actionBuilder(r.startPose)
                .splineTo(Vector2d(55.00, 55.00), Math.toRadians(45.00))
                .afterDisp(1.0, r.ArmToHighBasket())
                .waitSeconds(3.5)
//                .afterTime(3.5, r.SlideExtend())
//                .afterTime(2.5, r.WristDown())
//                .afterTime(1.0, SequentialAction(r.WristStop(), r.SweeperOut()))
//                .afterTime(0.5, SequentialAction(r.SweeperOff(), r.WristUp()))
//                .afterTime(1.0, SequentialAction(r.WristStop(), r.SlideToHome()))
                .build()
        )

        runBlocking(r.SlideExtend())
        runBlocking(r.WristDown())
        sleep(1000)
        runBlocking(r.WristStop())
        runBlocking(r.SweeperOut())
        sleep(500)
        runBlocking(r.SweeperOff())
        runBlocking(r.WristUp())
        sleep(1000)
        runBlocking(r.WristStop())
        runBlocking(r.SlideToHome())

        runBlocking(
            r.drive.actionBuilder(r.drive.localizer.pose)
                .setReversed(true)
                .splineTo(Vector2d(36.00, 36.00), Math.toRadians(-90.00))
                .afterDisp(5.0, ParallelAction(r.SweeperOff(), r.ArmToHome(), r.SlideToHome()))
                .splineTo(Vector2d(25.00, -14.00), Math.toRadians(180.00))
                .build()
        )
    }
}