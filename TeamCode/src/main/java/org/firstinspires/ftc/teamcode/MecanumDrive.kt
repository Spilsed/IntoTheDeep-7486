package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor

@TeleOp(name = "MecanumDrive")
@Disabled
class MecanumDrive : LinearOpMode() {

    private lateinit var leftFrontMotor: DcMotor
    private lateinit var rightFrontMotor: DcMotor
    private lateinit var leftBackMotor : DcMotor
    private lateinit var rightBackMotor: DcMotor

    override fun runOpMode() {
        leftFrontMotor = hardwareMap.get(DcMotor::class.java, "leftFrontMotor")
        rightFrontMotor = hardwareMap.get(DcMotor::class.java, "rightFrontMotor")
        leftBackMotor = hardwareMap.get(DcMotor::class.java, "leftBackMotor")
        rightBackMotor = hardwareMap.get(DcMotor::class.java, "rightBackMotor")

        waitForStart()

        while (opModeIsActive()) {

            val y : Double = -gamepad1.left_stick_y.toDouble()
            val x : Double = gamepad1.left_stick_x.toDouble()
            val rx : Double = gamepad1.right_stick_x.toDouble()

            var denominator : Double = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0)

            leftFrontMotor.setPower((y + x + rx)/denominator)
            leftBackMotor.setPower((y - x + rx)/denominator)
            rightFrontMotor.setPower((y - x - rx)/denominator)
            rightBackMotor.setPower((y + x - rx)/denominator)

        }
    }
}