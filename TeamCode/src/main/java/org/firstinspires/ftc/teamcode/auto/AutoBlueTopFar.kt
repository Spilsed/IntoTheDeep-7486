package org.firstinspires.ftc.teamcode.auto

import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Pose2d
import com.acmerobotics.roadrunner.Vector2d
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.roadRunner.MecanumDrive
import org.firstinspires.ftc.teamcode.supers.Robot

@Autonomous(name = "BTF2")
class AutoBlueTopFar : LinearOpMode() {
    val r = Robot(this)
    val drive = MecanumDrive(this.hardwareMap, Pose2d(0.0, 0.0, 0.0))

    override fun runOpMode() {

        val trajectory0: Action = drive.actionBuilder(Pose2d(36.00, 60.00, Math.toRadians(270.00)))
            .splineToLinearHeading(Pose2d(37.00, 39.00, Math.toRadians(90.00)), Math.toRadians(-90.00))
            .splineToConstantHeading(Vector2d(0.0, 36.00), Math.toRadians(180.00))
            .build();

        drive.pose = Pose2d(36.00, 60.00, Math.toRadians(270.00))

        while (opModeIsActive()) {

        }
    }
}