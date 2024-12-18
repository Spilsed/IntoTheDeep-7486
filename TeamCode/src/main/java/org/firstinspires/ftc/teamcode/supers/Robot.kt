package org.firstinspires.ftc.teamcode.supers

import com.acmerobotics.dashboard.FtcDashboard
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.teamcode.parts.GoBildaPinpointDriver
import org.firstinspires.ftc.teamcode.parts.Lift
import org.firstinspires.ftc.teamcode.parts.LinearSlide
import org.firstinspires.ftc.teamcode.parts.RotationalArm
import org.firstinspires.ftc.teamcode.parts.ServoHand
import org.firstinspires.ftc.teamcode.parts.Wrist
import org.firstinspires.ftc.teamcode.util.GamepadState


// Kotlin is a stupid language made by stupid people, used by stupid people and i hate it and it's not as compatible with java as the feds want you to think.
class Robot (opMode: OpMode, resetEncoders: Boolean = true) {
    // Declare all the hardware
    var lf: DcMotor
    var lb: DcMotor
    var rf: DcMotor
    var rb: DcMotor
    var motors: Array<DcMotor>

    // Robot Parts
    var hand: ServoHand
    var wrist: Wrist
    var linearActuator: LinearSlide
    var rotationalArm: RotationalArm
    var lift: Lift

    // Misc
    var odo: GoBildaPinpointDriver
    var octoQuad: OctoQuad

    // Declare game pads
    var gamepadState1: GamepadState = GamepadState()
    var gamepadState2: GamepadState = GamepadState()

    // FTC Dashboard
    var dashboard: FtcDashboard? = FtcDashboard.getInstance()
    var dashboardTelemetry = dashboard!!.telemetry

    // Set opMode and hardwareMap
    init {
        val hardwareMap: HardwareMap = opMode.hardwareMap

        // Get all the motors
        lf = hardwareMap.get(DcMotor::class.java, "lf")
        lb = hardwareMap.get(DcMotor::class.java, "lb")
        rf = hardwareMap.get(DcMotor::class.java, "rf")
        rb = hardwareMap.get(DcMotor::class.java, "rb")

        motors = arrayOf(lf, lb, rf, rb)

        // Make sure all the motors are in what should be the default
        for (motor in motors) {
            motor.power = 0.0
            motor.zeroPowerBehavior = DcMotor.ZeroPowerBehavior.BRAKE
            motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }

        // Initialize the robot parts
        hand = ServoHand(hardwareMap.get(Servo::class.java, "hand"), 0.0, 1.0)
        wrist = Wrist(hardwareMap.get(Servo::class.java, "wrist1"), hardwareMap.get(Servo::class.java, "wrist2"))
        linearActuator = LinearSlide(hardwareMap.get(DcMotor::class.java, "la"), 537.7, 5.2)
        rotationalArm = RotationalArm(hardwareMap.get(DcMotor::class.java, "arm1"), hardwareMap.get(DcMotor::class.java, "arm2"), 0, 10)
        lift = Lift(hardwareMap.get(DcMotor::class.java, "liftm"), hardwareMap.get(Servo::class.java, "lifts"), 537.7, 1.0, 100, 0, 0.5, 0.0)

        // Odometry
        odo = hardwareMap.get(GoBildaPinpointDriver::class.java, "odo")
        odo.setOffsets(-174.625, 142.38478)
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)

        /*
        Set the direction that each of the two odometry pods count. The X (forward) pod should
        increase when you move the robot forward. And the Y (strafe) pod should increase when
        you move the robot to the left.
        */

        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();

        octoQuad = hardwareMap.get(OctoQuad::class.java, "octo")
    }
}

// noooow ieeee knowl dendude nahenune nAaah melatha