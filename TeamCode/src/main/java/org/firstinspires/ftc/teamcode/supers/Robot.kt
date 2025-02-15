package org.firstinspires.ftc.teamcode.supers

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.HardwareMap
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.parts.Lift
import org.firstinspires.ftc.teamcode.parts.ContLinearSlide
import org.firstinspires.ftc.teamcode.parts.ManualWristCont
import org.firstinspires.ftc.teamcode.parts.RotationalArm
import org.firstinspires.ftc.teamcode.roadRunner.MecanumDrive
import org.firstinspires.ftc.teamcode.util.GamepadState


// Kotlin is a stupid language made by stupid people, used by stupid people and i hate it and it's not as compatible with java as the feds want you to think.
class Robot (opMode: OpMode) {
    // Declare all the hardware
    var lf: DcMotor
    var lb: DcMotor
    var rf: DcMotor
    var rb: DcMotor
    var motors: Array<DcMotor>

    // Robot Parts
    var hand: CRServo
    var wrist: ManualWristCont
    var linearActuator: ContLinearSlide
    var rotationalArm: RotationalArm
    var lift: Lift

    // Misc
    var octoQuad: OctoQuad
    val drive: MecanumDrive

    // Declare game pads
    var gamepadState1: GamepadState = GamepadState()
    var gamepadState2: GamepadState = GamepadState()

    // FTC Dashboard
    var dashboard: FtcDashboard? = FtcDashboard.getInstance()
    var dashboardTelemetry: Telemetry = dashboard!!.telemetry

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
            motor.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }

        lf.direction = DcMotorSimple.Direction.REVERSE
        lb.direction = DcMotorSimple.Direction.REVERSE

        octoQuad = hardwareMap.get(OctoQuad::class.java, "octo")
        octoQuad.setSingleEncoderDirection(0, OctoQuadBase.EncoderDirection.FORWARD)
        octoQuad.setSingleEncoderDirection(1, OctoQuadBase.EncoderDirection.REVERSE)
        octoQuad.setSingleEncoderDirection(2, OctoQuadBase.EncoderDirection.FORWARD)
        octoQuad.saveParametersToFlash()

        // Initialize the robot parts
        hand = hardwareMap.get(CRServo::class.java, "hand")
        wrist = ManualWristCont(hardwareMap.get(CRServo::class.java, "wrist1"), hardwareMap.get(CRServo::class.java, "wrist2"))
        // wrist = Wrist(AxonCRServo(hardwareMap.get(CRServo::class.java, "wrist1"), hardwareMap.get(AnalogInput::class.java, "analog1")), AxonServo(hardwareMap.get(CRServo::class.java, "wrist2"), hardwareMap.get(AnalogInput::class.java, "analog1")))
        linearActuator = ContLinearSlide(hardwareMap.get(DcMotor::class.java, "la"), 537.7, 5.2)
        rotationalArm = RotationalArm(hardwareMap.get(DcMotor::class.java, "arm1"), hardwareMap.get(DcMotor::class.java, "arm2"), -10000, 6335)
        lift = Lift(hardwareMap.get(DcMotor::class.java, "liftm"), hardwareMap.get(CRServo::class.java, "lifts"), 537.7, 7.33, 100, 0, 0.5, 0.0)

        drive = MecanumDrive(hardwareMap, Pose2d(0.0, 0.0, 0.0))
    }
}

// noooow ieeee knowl dendude nahenune nAaah melatha