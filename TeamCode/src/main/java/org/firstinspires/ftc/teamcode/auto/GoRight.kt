package org.firstinspires.ftc.teamcode.auto

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import org.firstinspires.ftc.teamcode.supers.Robot
import kotlin.math.abs
import kotlin.math.max

@Autonomous(name = "GoRight")
class GoRight : LinearOpMode() {
    lateinit var r: Robot
    private var speedFactor: Double = 0.8
    private var motorPowers: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        mecanumDrive(0.0, 0.25, 0.0)

        telemetry.addData("REAL POW", r.motors[0].power)
        telemetry.addData("POW", motorPowers[0])
        telemetry.update()

        sleep(1000)

        for (motor in r.motors) {
            motor.power = 0.0
        }
    }

    private fun mecanumDrive(ly: Double, lx: Double, rx: Double) {
        val denominator: Double = max(abs(ly) + abs(lx) + abs(rx), 1.0)

        motorPowers[0] = (-ly + -lx + rx) / denominator * speedFactor
        motorPowers[1] = (-ly - -lx + rx) / denominator * speedFactor
        motorPowers[2] = (-ly - -lx - rx) / denominator * speedFactor
        motorPowers[3] = (-ly + -lx - rx) / denominator * speedFactor

        for (i in 0..3 step 1) {
            r.motors[i].power = motorPowers[i]
        }
    }
}