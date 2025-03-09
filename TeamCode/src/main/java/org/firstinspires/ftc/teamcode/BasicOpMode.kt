package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import org.firstinspires.ftc.teamcode.supers.Robot
import org.firstinspires.ftc.teamcode.util.lerp
import kotlin.math.abs
import kotlin.math.max
import com.qualcomm.robotcore.hardware.DcMotor


@TeleOp(name = "Basic")
class BasicOpMode : LinearOpMode() {
    lateinit var r: Robot
    private var speedFactor: Double = 0.8
    private var motorPowers: DoubleArray = doubleArrayOf(0.0, 0.0, 0.0, 0.0)

    private var gamepad1Shutdown: Boolean = false

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        while (opModeIsActive()) {
            if (!gamepad1Shutdown) {
                gamepad1Logic()
            }
            gamepad2Logic()

            // Update gamepad states
            r.gamepadState1.updateGamepadState(gamepad1)
            r.gamepadState2.updateGamepadState(gamepad2)

            // Dashboard update
            r.dashboardTelemetry.update()
            // Robot update
            r.update()
        }
    }

    private fun gamepad1Logic() {
        mecanumDrive()
        // Slow mode
        if (gamepad1.back) {
            speedFactor = 0.5
        } else if (gamepad1.start) {
            speedFactor = 0.8
        }
    }

    private fun gamepad2Logic() {
        // Linear Actuator
        if (gamepad2.a) {
            r.linearActuator.power = -0.4
        } else if (gamepad2.b) {
            r.linearActuator.power = 0.4
        } else {
            r.linearActuator.power = 0.0
        }

        r.dashboardTelemetry.addData("LAct", r.linearActuator.motor.currentPosition)

        // Rotational Arm
        r.dashboardTelemetry.addData("Homing", r.armHomingTouch.state)
        r.dashboardTelemetry.addData("ROT-Pos", r.rotationalArm.motor1.currentPosition)
        r.dashboardTelemetry.addData("ROT-Pos2", r.rotationalArm.motor2.currentPosition)
        r.rotationalArm.power = gamepad2.left_stick_y.toDouble()
        r.dashboardTelemetry.addData("ROT-Pow", r.rotationalArm.power)
        r.dashboardTelemetry.addData("ROT-MPow", r.rotationalArm.motor1.power)
        r.dashboardTelemetry.addData("ROT-Mode", r.rotationalArm.motor1.mode)
        r.dashboardTelemetry.addData("ROT-Min", r.rotationalArm.min)
        r.dashboardTelemetry.addData("ROT-Max", r.rotationalArm.max)
        r.dashboardTelemetry.addData("ROT-MinStop", r.rotationalArm.motor1.currentPosition <= r.rotationalArm.min && r.rotationalArm.power * r.rotationalArm.zeroModifier < 0.0)

        if ((!r.gamepadState2.start && gamepad2.start) && (!r.gamepadState2.back && gamepad2.back)) {
            r.rotationalArm.limited = !r.rotationalArm.limited
        }

        // Hand
        // in
        if (gamepad2.left_bumper) {
            r.hand.power = 1.0
            // out
        } else if (gamepad2.right_bumper) {
            r.hand.power = -1.0
        } else {
            r.hand.power = 0.0
        }

        r.dashboardTelemetry.addData("Hand power", r.hand.power)
        r.dashboardTelemetry.addData("Right Bumper", gamepad2.right_bumper)

        // Wrist
        if (gamepad2.dpad_right) {
            r.wrist.turn(1.0)
        } else if (gamepad2.dpad_left) {
            r.wrist.turn(-1.0)
        } else {
            r.wrist.turn(0.0)
        }

        if (gamepad2.dpad_up) {
            r.wrist.twist(1.0)
        } else if (gamepad2.dpad_down) {
            r.wrist.twist(-1.0)
        } else {
            r.wrist.twist(0.0)
        }

//        if (gamepad2.dpad_right) {
//            r.wrist.lServo.power = 1.0
//        } else if (gamepad2.dpad_left) {
//            r.wrist.lServo.power = -1.0
//        } else {
//            r.wrist.lServo.power = 0.0
//        }
//        if (gamepad2.dpad_up) {
//            r.wrist.rServo.power = 1.0
//        } else if (gamepad2.dpad_down) {
//            r.wrist.rServo.power = -1.0
//        } else {
//            r.wrist.rServo.power = 0.0
//        }

        r.lift.motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        r.lift.motor.power = gamepad2.right_stick_y.toDouble()
        r.lift.servo.power = gamepad2.right_stick_x.toDouble()

        // Gamepad1 shutdown
        if (gamepad2.y && !r.gamepadState2.y) {
            gamepad1Shutdown = !gamepad1Shutdown
        }
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