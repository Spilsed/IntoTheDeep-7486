package org.firstinspires.ftc.teamcode.auto

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.supers.Robot
import kotlin.math.abs
import kotlin.math.max

@Autonomous(name = "GoRight", group = "autonomous")
class GoRight : LinearOpMode() {
    lateinit var r: Robot

    private var speedFactor: Double = 0.8
    private var motorPowers: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        mecanumDrive(0.0, 1.0, 0.0)

        sleep(1000)

        for (motor in r.motors) {
            motor.power = 0.0
        }
    }

    private fun mecanumDrive(ly: Double, lx: Double, rx: Double) {
        val denominator: Double = max(abs(ly) + abs(lx) + abs(rx), 1.0)

        motorPowers[0] = (-gamepad1.left_stick_y + -gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        motorPowers[1] = (-gamepad1.left_stick_y - -gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        motorPowers[2] = (-gamepad1.left_stick_y - -gamepad1.left_stick_x - gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        motorPowers[3] = (-gamepad1.left_stick_y + -gamepad1.left_stick_x - gamepad1.right_stick_x).toDouble() / denominator * speedFactor

        for (i in 0..3 step 1) {
            r.motors[i].power = motorPowers[i]
        }
    }
}