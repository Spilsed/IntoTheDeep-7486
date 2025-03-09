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
        r = Robot(this, auto = true)

        waitForStart()

//        runBlocking(
//            r.ArmToHighBasket()
//        )

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

        telemetry.addData("Arm-b", r.rotationalArm.motor1.isBusy)
        telemetry.addData("Arm", r.rotationalArm.motor1.targetPosition)
        telemetry.update()
    }
}