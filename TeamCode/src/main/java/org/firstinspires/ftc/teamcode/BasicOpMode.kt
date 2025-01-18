package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.supers.Robot
import org.firstinspires.ftc.teamcode.util.lerp
import kotlin.math.abs
import kotlin.math.max

@TeleOp(name = "Basic")
class BasicOpMode : LinearOpMode() {
    lateinit var r: Robot
    private var speedFactor: Double = 0.8
    private var motorPowers: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            gamepad1Logic()
            gamepad2Logic()

            // Update gamepad states
            r.gamepadState1.updateGamepadState(gamepad1)
            r.gamepadState2.updateGamepadState(gamepad2)

            // Dashboard update
            r.dashboardTelemetry.update()
        }
    }

    private fun gamepad1Logic() {
        mecanumDrive()

        // Slow mode
        if (gamepad1.back || !r.gamepadState1.back) {
            speedFactor = 0.3
        } else if (gamepad1.start || !r.gamepadState1.start) {
            speedFactor = 0.8
        }
    }

    private fun gamepad2Logic() {
        // Rotational Arm
        r.dashboardTelemetry.addData("ARM1", r.rotationalArm.power)
        r.dashboardTelemetry.addData("ARMMOVE", gamepad2.left_stick_y.toDouble() / 2.0)

        // Linear Actuator
        if (gamepad2.a) {
            r.linearActuator.motor.power = -0.4
        } else if (gamepad2.b) {
            r.linearActuator.motor.power = 0.4
        } else {
            r.linearActuator.motor.power = 0.0
        }

        // Rotational Arm
        r.rotationalArm.power = gamepad2.left_stick_y.toDouble() / 2.0


        // Hand
        if (gamepad2.left_bumper) {
            r.hand.power = 1.0
        } else if (gamepad2.right_bumper) {
            r.hand.power = 0.0
        }

        // Wrist
        if (gamepad2.dpad_up) {
            r.wrist.lServo.power = 0.25
        } else if (gamepad2.dpad_down) {
            r.wrist.lServo.power = -0.25
        } else {
            r.wrist.lServo.power = 0.0
        }

        if (gamepad2.dpad_left) {
            r.wrist.rServo.power = 0.25
        } else if (gamepad2.dpad_right) {
            r.wrist.rServo.power = -0.25
        } else {
            r.wrist.rServo.power = 0.0
        }

        // r.wrist.turn(gamepad2.right_stick_y.toDouble() / 5.0)
        // r.wrist.twist(gamepad2.right_stick_x.toDouble() / 5.0)
    }

    private fun mecanumDrive() {
        val denominator: Double = max(abs(gamepad1.left_stick_y) + abs(gamepad1.left_stick_x) + abs(gamepad1.right_stick_x), 1.0f).toDouble()

        motorPowers[0] = (-gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        motorPowers[1] = (-gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        motorPowers[2] = (-gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x).toDouble() / denominator * speedFactor
        motorPowers[3] = (-gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x).toDouble() / denominator * speedFactor

        for (i in 0..3 step 1) {
            r.motors[i].power = lerp(r.motors[i].power, motorPowers[i], 0.3)
        }
    }
}