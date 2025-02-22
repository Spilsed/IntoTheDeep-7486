package org.firstinspires.ftc.teamcode.testing.auto

import com.acmerobotics.roadrunner.SequentialAction
import com.acmerobotics.roadrunner.SleepAction
import com.acmerobotics.roadrunner.ftc.runBlocking
import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.supers.Robot

@Autonomous(name = "Action Test")
class ActionTest: LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this, true)

        waitForStart()

        runBlocking(
            SequentialAction(
                r.GoodLights(),
                r.ArmToSecondBar(),
                r.WorkingLights(),
                SleepAction(3.0),
                r.BadLights(),
                r.ArmToHome()
            )
        )

        telemetry.addData("Arm-b", r.rotationalArm.motor1.isBusy)
        telemetry.addData("Arm", r.rotationalArm.motor1.targetPosition)
        telemetry.update()
    }
}