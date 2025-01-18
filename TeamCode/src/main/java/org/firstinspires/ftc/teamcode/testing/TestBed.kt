package org.firstinspires.ftc.teamcode.testing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.Servo

@TeleOp(name = "TestBed")
class TestBed: LinearOpMode() {
    override fun runOpMode() {
        val motor0 = hardwareMap.get(DcMotor::class.java, "motor0")
        val motor1 = hardwareMap.get(DcMotor::class.java, "motor1")
        val servo0 = hardwareMap.get(CRServo::class.java, "servo0")
        val servo1 = hardwareMap.get(Servo::class.java, "servo1")
        //manipulate these variable so that pressing button does things to them
        while (isStarted && !isStopRequested) {
            while (isStarted && !isStopRequested){
                if (gamepad1.dpad_left) {
                    servo0.power += 0.01
                }
                else if (gamepad1.dpad_right) {
                    servo0.power += 0.01
                }
                else if (gamepad1.x) {
                    servo1.position += 0.01
                }
                else if (gamepad1.b) {
                    servo1.position += 0.01
                }
                motor0.power = gamepad1.left_stick_x.toDouble()
                motor1.power = gamepad1.right_stick_x.toDouble()
            }
        }
    }
}