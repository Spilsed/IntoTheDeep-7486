package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import kotlin.math.abs
import kotlin.math.max

@TeleOp(name = "Solo Drive")
class SoloDrive : LinearOpMode() {
    lateinit var lf : DcMotor
    lateinit var lb : DcMotor
    lateinit var rf : DcMotor
    lateinit var rb : DcMotor

    var speedFactor: Double = 0.5

    override fun runOpMode() {
        lf = hardwareMap.get(DcMotor::class.java, "lf")
        lb = hardwareMap.get(DcMotor::class.java, "lb")
        rf = hardwareMap.get(DcMotor::class.java, "rf")
        rb = hardwareMap.get(DcMotor::class.java, "rb")

        lf.direction = DcMotorSimple.Direction.REVERSE
        lb.direction = DcMotorSimple.Direction.REVERSE

        waitForStart()

        while (opModeIsActive()) {
            mecanumDrive()
        }
    }

    private fun mecanumDrive() {
        val denominator: Double = max(abs(gamepad1.left_stick_y) + abs(gamepad1.left_stick_x) + abs(gamepad1.right_stick_x), 1.0f).toDouble()

        lf.power = (-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        lb.power = (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        rf.power = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        rb.power = (-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x).toDouble() / denominator * speedFactor
    }
}