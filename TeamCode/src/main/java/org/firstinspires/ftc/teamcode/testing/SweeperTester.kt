package org.firstinspires.ftc.teamcode.testing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.Servo

@TeleOp(name = "Sweeper Tester")
class SweeperTester: LinearOpMode() {
    override fun runOpMode() {
        val servo: CRServo = hardwareMap.get(CRServo::class.java, "sweeper")

        waitForStart()

//        while (opModeIsActive()) {
//            if (gamepad1.a) {
//                light += 0.001
//            } else if (gamepad1.b) {
//                light += -0.001
//            } else {
//                //light = 0.0
//            }
//        }
    }
}