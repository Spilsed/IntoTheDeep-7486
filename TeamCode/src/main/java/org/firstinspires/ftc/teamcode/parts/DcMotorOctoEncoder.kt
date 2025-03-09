package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorEx
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction

class DcMotorOctoEncoder(val motor: DcMotorEx, private val octoQuad: OctoQuad, private val octoId: Int) : DcMotorEx by motor {
    override fun getCurrentPosition(): Int {
        return octoQuad.readSinglePosition(octoId)
    }

    override fun setDirection(direction: Direction) {
        motor.direction = direction

        if (direction == Direction.FORWARD) {
            octoQuad.setSingleEncoderDirection(octoId, OctoQuadBase.EncoderDirection.FORWARD)
        } else {
            octoQuad.setSingleEncoderDirection(octoId, OctoQuadBase.EncoderDirection.REVERSE)
        }
    }

    override fun setMode(mode: DcMotor.RunMode) {
        if (mode == DcMotor.RunMode.STOP_AND_RESET_ENCODER) {
            octoQuad.resetSinglePosition(octoId)
        } else {
            motor.mode = mode
        }
    }
}