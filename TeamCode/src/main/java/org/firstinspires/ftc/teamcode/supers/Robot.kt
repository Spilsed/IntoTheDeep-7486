package org.firstinspires.ftc.teamcode.supers

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.IMU
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.LinearSlide
import org.firstinspires.ftc.teamcode.ServoHand
import org.firstinspires.ftc.teamcode.Wrist
import org.firstinspires.ftc.teamcode.util.GamepadState


// Kotlin is a stupid language made by stupid people, used by stupid people and i hate it and it's not as compatible with java as the feds want you to think.
class Robot (opMode: OpMode, resetEncoders: Boolean = true) {
    // Declare all the hardware
    var lt: DcMotor
    var lb: DcMotor
    var rt: DcMotor
    var rb: DcMotor
    var motors: Array<DcMotor>

    var hand: ServoHand
    var wrist: Wrist
    var linearActuator: LinearSlide

    var odo: GoBildaPinpointDriver
    var octoQuad: OctoQuad

    // Declare gamepads
    var gamepadState1: GamepadState = GamepadState()
    var gamepadState2: GamepadState = GamepadState()

    // FTC Dashboard
    var dashboard: FtcDashboard? = FtcDashboard.getInstance()
    var dashboardTelemetry = dashboard!!.telemetry

    // Set opMode and hardwareMap
    init {
        val hardwareMap: HardwareMap = opMode.hardwareMap

        // Get all the motors
        lt = hardwareMap.get(DcMotor::class.java, "lf")
        lb = hardwareMap.get(DcMotor::class.java, "lb")
        rt = hardwareMap.get(DcMotor::class.java, "rf")
        rb = hardwareMap.get(DcMotor::class.java, "rb")

        motors = arrayOf(lt, lb, rt, rb)

        // Make sure all the motors are in what should be the default
        for (motor in motors) {
            motor.power = 0.0
            motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
            motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }

        // IMU (Used until odometry)
        imu = hardwareMap.get(IMU::class.java, "imu")
        val parameters: IMU.Parameters = IMU.Parameters(RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.FORWARD, RevHubOrientationOnRobot.UsbFacingDirection.UP))
        hand = ServoHand(hardwareMap.get(Servo::class.java, "hand"), 0.0, 1.0)
        wrist = Wrist(hardwareMap.get(Servo::class.java, "wrist1"), hardwareMap.get(Servo::class.java, "wrist2"))
        linearActuator = LinearSlide(hardwareMap.get(DcMotor::class.java, "la"), 537.7, 5.2)

        imu.initialize(parameters)
    }
}

// noooow ieeee knowl dendude nahenune nAaah melatha