package org.firstinspires.ftc.teamcode.testing.parts

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "FullArmTest")
class FullArmTest : LinearOpMode() {
    override fun runOpMode() {
        var r = Robot(this)

        waitForStart()

        while (isStarted && !isStopRequested) {

        }
    }
}