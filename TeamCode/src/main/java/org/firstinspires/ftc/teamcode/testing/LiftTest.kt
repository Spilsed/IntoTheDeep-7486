package org.firstinspires.ftc.teamcode.testing

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.eventloop.opmode.TeleOp
import com.qualcomm.robotcore.hardware.DcMotor
import org.firstinspires.ftc.teamcode.supers.Robot

@TeleOp(name = "Lift Test")
class LiftTest: LinearOpMode() {
    lateinit var r: Robot

    override fun runOpMode() {
        r = Robot(this)

        waitForStart()

        r.lift.motor.mode = DcMotor.RunMode.RUN_USING_ENCODER
        while (opModeIsActive()) {
//            if (gamepad1.a) {
//                r.lift.expand(0.1)
//            } else if (gamepad1.b) {
//                r.lift.expand(-0.2)
//            }


            r.lift.servo.power = gamepad1.left_stick_y.toDouble()
            if (gamepad1.y){
                r.lift.servo.power = 1.0
            }
            if (gamepad1.x){
                r.lift.servo.power = -1.0
            }
            else{
                r.lift.servo.power = 0.0
            }
        }
    }
}