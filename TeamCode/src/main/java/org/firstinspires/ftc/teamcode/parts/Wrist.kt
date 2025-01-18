package org.firstinspires.ftc.teamcode.parts

import com.qualcomm.hardware.digitalchickenlabs.OctoQuad
import com.qualcomm.robotcore.hardware.CRServo
import kotlin.math.abs

class Wrist(val lServo: AxonServo, val rServo: AxonServo) {
    var lServoTarget: Double = 0.0
    var rServoTarget: Double = 0.0

    fun twist(delta: Double) {
        lServoTarget = lServo.position + delta
        rServoTarget = rServo.position + delta

        lServo.position = lServoTarget % 1.0
        rServo.position = rServoTarget % 1.0
        
        updatePositions()
    }

    fun turn(delta: Double) {
        lServoTarget = lServo.position - delta
        rServoTarget = rServo.position + delta

        lServo.position = lServoTarget % 1.0
        rServo.position = rServoTarget % 1.0

        updatePositions()
    }

    fun updatePositions() {
        lServo.updatePosition()
        rServo.updatePosition()
    }

    fun getTurn(): Double {
        return (lServo.position + rServo.position) / 2
    }

    fun getTwist(): Double {
        return abs(lServo.position - rServo.position)
    }
}

class ManualWristCont(private val lServo: CRServo, private val rServo: CRServo, private val octoQuad: OctoQuad, private val lIdx: Int, private val rIdx: Int, private val servoPower: Double, private val mins: Array<Int>, private val maxes: Array<Int>) {
    var turnableDirection: Int = 0
    var twistableDirection: Int = 0

    var wantedTurn: Int = 0
    var wantedTwist: Int = 0

    fun twist(delta: Int) {
        wantedTwist = getTwist() + delta

        lServo.power = servoPower
        rServo.power = -servoPower
    }

    fun turn(delta: Int) {
        wantedTurn = getTurn() + delta

        lServo.power = -servoPower
        rServo.power = servoPower
    }

    fun setPower(power: Double) {
        lServo.power = power
        rServo.power = power
    }

    fun getTurn(): Int {
        return (octoQuad.readSinglePosition(lIdx) + octoQuad.readSinglePosition(rIdx)) / 2
    }

    fun getTwist(): Int {
        return abs(octoQuad.readSinglePosition(lIdx) - octoQuad.readSinglePosition(rIdx))
    }

    fun update() {

    }
}

class WristCont(private val lServo: CRServo, private val rServo: CRServo, private val octoQuad: OctoQuad, private val lIdx: Int, private val rIdx: Int, private val servoPower: Double, private val mins: Array<Int>, private val maxes: Array<Int>) {
    var wantedTurn: Int = 0
    var wantedTwist: Int = 0
    val margin: Int = 50

    fun twist(delta: Int) {
        wantedTwist = getTwist() + delta

        lServo.power = servoPower
        rServo.power = -servoPower
    }

    fun turn(delta: Int) {
        wantedTurn = getTurn() + delta

        lServo.power = -servoPower
        rServo.power = servoPower
    }

    fun setPower(power: Double) {
        lServo.power = power
        rServo.power = power
    }

    fun getTurn(): Int {
        return (octoQuad.readSinglePosition(lIdx) + octoQuad.readSinglePosition(rIdx)) / 2
    }

    fun getTwist(): Int {
        return abs(octoQuad.readSinglePosition(lIdx) - octoQuad.readSinglePosition(rIdx))
    }

    fun update() {
        if (getTurn() >= maxes[0] || getTurn() <= mins[0]) {
            setPower(0.0)
        } else if (getTurn() in wantedTurn - margin..wantedTurn + margin) {
            setPower(0.0)
            wantedTurn = getTurn()
        }

        if (getTwist() >= maxes[1] || getTwist() <= mins[1]) {
            setPower(0.0)
        } else if (getTwist() in wantedTwist - margin..wantedTwist + margin) {
            setPower(0.0)
            wantedTwist = getTwist()
        }
    }
}
