package org.firstinspires.ftc.teamcode.parts
import com.qualcomm.robotcore.hardware.DcMotor


class SweeperSlider(motor: DcMotor, ppr: Double, ratio: Double) : LinearSlide(motor, ppr, ratio) {
    var isOut: Boolean = false

    fun extendOut() {
        extend(8.0)
        isOut = true
    }

    fun retract() {
        returnToZero()
        isOut = false
    }

    fun toggle(value: Boolean) {
        if (isOut == true) {
            retract()
        } else {
            extendOut()
        }
    }
}