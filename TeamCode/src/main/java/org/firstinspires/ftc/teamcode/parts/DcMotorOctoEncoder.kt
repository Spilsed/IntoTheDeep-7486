package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.hardware.digitalchickenlabs.OctoQuadBase
import com.qualcomm.robotcore.hardware.DcMotor
import com.qualcomm.robotcore.hardware.DcMotorSimple.Direction

class DcMotorOctoEncoder(val motor: DcMotor, val octoQuad: OctoQuad, val octoId: Int) : DcMotor by motor {
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
}