package org.firstinspires.ftc.teamcode.supers

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.DigitalChannel
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.parts.Lift
import org.firstinspires.ftc.teamcode.parts.ContLinearSlide
import org.firstinspires.ftc.teamcode.parts.DcMotorOctoEncoder
import org.firstinspires.ftc.teamcode.parts.Light
import org.firstinspires.ftc.teamcode.parts.LightArray
import org.firstinspires.ftc.teamcode.parts.ManualWristCont
import org.firstinspires.ftc.teamcode.parts.RotationalArm
import org.firstinspires.ftc.teamcode.roadRunner.MecanumDrive
import org.firstinspires.ftc.teamcode.util.GamepadState


// Kotlin is a stupid language made by stupid people, used by stupid people and i hate it and it's not as compatible with java as the feds want you to think.
class Robot(opMode: OpMode, val startPose: Pose2d = Pose2d(0.0, 0.0, 0.0)) {
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

    // Sensors
    var armHomingTouch: DigitalChannel

    // Lights
    var frontLight: Light
    var backLight: Light
    var allLights: LightArray

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
        octoQuad.saveParametersToFlash()

        // Initialize the robot parts
        hand = hardwareMap.get(CRServo::class.java, "hand")
        wrist = ManualWristCont(hardwareMap.get(CRServo::class.java, "wrist1"), hardwareMap.get(CRServo::class.java, "wrist2"))
        // wrist = Wrist(AxonCRServo(hardwareMap.get(CRServo::class.java, "wrist1"), hardwareMap.get(AnalogInput::class.java, "analog1")), AxonServo(hardwareMap.get(CRServo::class.java, "wrist2"), hardwareMap.get(AnalogInput::class.java, "analog1")))
        linearActuator = ContLinearSlide(DcMotorOctoEncoder(
            hardwareMap.get(DcMotor::class.java, "la"), octoQuad, 2),
            537.7,
            5.2
        )

        rotationalArm = RotationalArm(
            DcMotorOctoEncoder(hardwareMap.get(DcMotor::class.java, "arm1"), octoQuad, 0),
            DcMotorOctoEncoder(hardwareMap.get(DcMotor::class.java, "arm2"), octoQuad, 1),
            1000,
            6335
        )
        rotationalArm.motor1.direction = DcMotorSimple.Direction.REVERSE
        rotationalArm.motor2.direction = DcMotorSimple.Direction.REVERSE

        lift = Lift(
            hardwareMap.get(DcMotor::class.java, "liftm"),
            hardwareMap.get(CRServo::class.java, "lifts"),
            537.7,
            7.33,
            100,
            0,
            0.5,
            0.0
        )

        armHomingTouch = hardwareMap.get(DigitalChannel::class.java, "armtouch")
        armHomingTouch.mode = DigitalChannel.Mode.INPUT

        frontLight = Light(hardwareMap.get(Servo::class.java, "frontlight"))
        backLight = Light(hardwareMap.get(Servo::class.java, "backlight"))
        allLights = LightArray(mutableListOf(frontLight, backLight))

        // Initialize lights
        allLights.on = true
        allLights.color = 0.666

        drive = MecanumDrive(hardwareMap, startPose)
    }

    fun update() {
        frontLight.update()
        backLight.update()

        // If the arm touches the sensor set that as the minimum
//        if (armHomingTouch.state) {
//            rotationalArm.min = rotationalArm.motor1.currentPosition
//        }

        rotationalArm.update()
    }

    // Actions
    // Arm actions
    inner class ArmToHome : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    inner class armAndWristToPickup : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    inner class ArmToSecondBar : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    // Wrist actions
    inner class wristToHome : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    inner class wristToSecondBar : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    // Light actions
    inner class turnOffLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.on = false
            return true
        }
    }

    inner class turnOnLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.on = true
            return true
        }
    }

    inner class goodLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.color = 0.5
            return true
        }
    }

    inner class doingLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.color = 0.722
            return true
        }
    }

    inner class badLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.color = 0.279
            return true
        }
    }
}

// noooow ieeee knowl dendude nahenune nAaah melatha