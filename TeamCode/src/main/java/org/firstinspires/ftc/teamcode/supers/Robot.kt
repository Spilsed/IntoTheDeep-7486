package org.firstinspires.ftc.teamcode.supers

import com.acmerobotics.dashboard.FtcDashboard
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.acmerobotics.roadrunner.Action
import com.acmerobotics.roadrunner.Pose2d
import com.qualcomm.hardware.bosch.BNO055IMU
import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.hardware.limelightvision.Limelight3A
import com.qualcomm.robotcore.eventloop.opmode.OpMode
import com.qualcomm.robotcore.hardware.CRServo
import com.qualcomm.robotcore.hardware.ColorSensor
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple
import com.qualcomm.robotcore.hardware.DigitalChannel
import com.qualcomm.robotcore.hardware.DistanceSensor
import com.qualcomm.robotcore.hardware.HardwareMap
import com.qualcomm.robotcore.hardware.Servo
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.parts.Lift
import org.firstinspires.ftc.teamcode.parts.ContLinearSlide
import org.firstinspires.ftc.teamcode.parts.DcMotorOctoEncoder
import org.firstinspires.ftc.teamcode.parts.Light
import org.firstinspires.ftc.teamcode.parts.LightArray
import org.firstinspires.ftc.teamcode.parts.LinearSlide
import org.firstinspires.ftc.teamcode.parts.ManualWristCont
import org.firstinspires.ftc.teamcode.parts.RotationalArm
import org.firstinspires.ftc.teamcode.roadRunner.MecanumDrive
import org.firstinspires.ftc.teamcode.util.GamepadState


// Kotlin is a stupid language made by stupid people, used by stupid people and i hate it and it's not as compatible with java as the feds want you to think.
class Robot(opMode: OpMode, auto: Boolean = false, val startPose: Pose2d = Pose2d(0.0, 0.0, 0.0)) {
    // Declare all the hardware
    var lf: DcMotor
    var lb: DcMotor
    var rf: DcMotor
    var rb: DcMotor
    var motors: Array<DcMotor>

    // Robot Parts
    var hand: CRServo
    var wrist: ManualWristCont
    var linearActuator: LinearSlide
    var rotationalArm: RotationalArm
    var lift: Lift

    // Sensors
    var armHomingTouch: DigitalChannel
    var imu: BNO055IMU
    // var limelight: Limelight3A
//    var distanceSensorRF: DistanceSensor
//    var distanceSensorLF: DistanceSensor
//    var distanceSensorLB: DistanceSensor
//    var distanceSensorLL: DistanceSensor
//    var distanceSensorLR: DistanceSensor
//    var distanceSensorRB: DistanceSensor
//    var distanceSensorRL: DistanceSensor
//    var distanceSensorRR: DistanceSensor
//   // var distanceSensorLB: DistanceSensor
    var colorSensor: ColorSensor

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
    var dashboard: FtcDashboard = FtcDashboard.getInstance()
    var dashboardTelemetry: Telemetry = dashboard.telemetry

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
        linearActuator = LinearSlide(
            hardwareMap.get(DcMotor::class.java, "la"),
            537.7,
            0.2105263,
            auto
        ) //0.2105263 rot/inch

        rotationalArm = RotationalArm(
            hardwareMap.get(DcMotor::class.java, "arm1"),
            hardwareMap.get(DcMotor::class.java, "arm2"),
            -4000,
            0,
            auto
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
        allLights.color = 0.640

        drive = MecanumDrive(hardwareMap, startPose)

        imu = hardwareMap.get(BNO055IMU::class.java, "imu")
        // limelight = hardwareMap.get(Limelight3A::class.java, "limelight")
        // distanceSensorRF = hardwareMap.get(DistanceSensor::class.java, "rf-distance")
        // distanceSensorLF = hardwareMap.get(DistanceSensor::class.java, "lf-distance")
        // distanceSensorRR = hardwareMap.get(DistanceSensor::class.java, "rr-distance")
        // distanceSensorRL = hardwareMap.get(DistanceSensor::class.java, "rl-distance")
        // distanceSensorLR = hardwareMap.get(DistanceSensor::class.java, "lr-distance")
        // distanceSensorLL = hardwareMap.get(DistanceSensor::class.java, "ll-distance")
        // distanceSensorRB = hardwareMap.get(DistanceSensor::class.java, "rb-distance")
        // distanceSensorLB = hardwareMap.get(DistanceSensor::class.java, "lb-distance")
        colorSensor = hardwareMap.get(ColorSensor::class.java, "wrist-color")
    }

    fun update() {
        frontLight.update()
        backLight.update()

        // If the arm touches the sensor set that as the minimum
        if (armHomingTouch.state && rotationalArm.motor1.currentPosition != 0) {
            rotationalArm.motor1.mode = DcMotor.RunMode.STOP_AND_RESET_ENCODER
            rotationalArm.motor1.mode = DcMotor.RunMode.RUN_USING_ENCODER
        }

        rotationalArm.update()
        linearActuator.update()
    }

    // Actions
    // Arm actions
    inner class ArmToHome: Action {
        override fun run(p: TelemetryPacket): Boolean {
            rotationalArm.targetPosition = 0
            return !rotationalArm.isAtTarget
        }
    }

    inner class SlideToHome: Action {
        override fun run(p: TelemetryPacket): Boolean {
            linearActuator.targetPosition = 0
            return false
            // return !linearActuator.isAtTarget
        }
    }

    inner class ArmAndWristToPickup : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    inner class ArmToSecondBar : Action {
        override fun run(p: TelemetryPacket): Boolean {
            rotationalArm.targetPosition = -1700
            return !rotationalArm.isAtTarget
        }
    }

    inner class ArmToHighBasket: Action {
        override fun run(p: TelemetryPacket): Boolean {
            rotationalArm.targetPosition = -3500
            dashboardTelemetry.addData("Arm-Position", rotationalArm.currentPosition)
            dashboardTelemetry.addData("Arm-Target-Position", rotationalArm.targetPosition)
            dashboardTelemetry.update()
            return false
            // return !rotationalArm.isAtTarget
        }
    }

    inner class SlideExtend: Action {
        override fun run(p: TelemetryPacket): Boolean {
            linearActuator.targetPosition = -1200
            dashboardTelemetry.addData("Position", linearActuator.currentPosition)
            dashboardTelemetry.addData("Target-Position", linearActuator.targetPosition)
            dashboardTelemetry.update()
            return !linearActuator.isAtTarget
        }
    }

    // Wrist actions
    inner class WristToHome : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    inner class WristToSecondBar : Action {
        override fun run(p: TelemetryPacket): Boolean {
            TODO("Not yet implemented")
        }
    }

    inner class WristDown: Action {
        override fun run(p: TelemetryPacket): Boolean {
            wrist.lServo.power = -0.25
            wrist.rServo.power = -0.25
            return false
        }
    }

    inner class WristUp: Action {
        override fun run(p: TelemetryPacket): Boolean {
            wrist.lServo.power = 0.25
            wrist.rServo.power = 0.25
            return false
        }
    }

    inner class WristStop: Action {
        override fun run(p: TelemetryPacket): Boolean {
            wrist.lServo.power = 0.0
            wrist.rServo.power = 0.0
            return false
        }
    }

    inner class SweeperOff: Action {
        override fun run(p: TelemetryPacket): Boolean {
            hand.power = 0.0
            return false
        }
    }

    inner class SweeperIn: Action {
        override fun run(p: TelemetryPacket): Boolean {
            hand.power = -1.0
            return false
        }
    }

    inner class SweeperOut: Action {
        override fun run(p: TelemetryPacket): Boolean {
            hand.power = 1.0
            return false
        }
    }

    // Light actions
    inner class TurnOffLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.on = false
            return false
        }
    }

    inner class TurnOnLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.on = false
            return false
        }
    }

    inner class GoodLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.color = 0.5
            return false
        }
    }

    inner class WorkingLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.color = 0.722
            return false
        }
    }

    inner class BadLights : Action {
        override fun run(p: TelemetryPacket): Boolean {
            allLights.color = 0.279
            return false
        }
    }
}

// noooow ieeee knowl dendude nahenune nAaah melatha