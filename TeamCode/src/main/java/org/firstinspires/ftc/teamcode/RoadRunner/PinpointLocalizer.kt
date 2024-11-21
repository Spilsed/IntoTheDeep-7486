package org.firstinspires.ftc.teamcode.RoadRunner

import com.acmerobotics.roadrunner.DualNum
import com.acmerobotics.roadrunner.Time
import com.acmerobotics.roadrunner.Twist2dDual
import com.acmerobotics.roadrunner.Vector2dDual
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit
import org.firstinspires.ftc.teamcode.GoBildaPinpointDriver

class PinpointLocalizer(val odo: GoBildaPinpointDriver) : Localizer {
    var pose = odo.position
    var lastPose = odo.position
    var velocity = odo.velocity
    var headingVelocity = velocity.getHeading(AngleUnit.RADIANS)

    override fun update(): Twist2dDual<Time> {
        pose = odo.position
        velocity = odo.velocity
        headingVelocity = velocity.getHeading(AngleUnit.RADIANS)

        var twist: Twist2dDual<Time> = Twist2dDual(
            Vector2dDual(
                    DualNum<Time>(doubleArrayOf(
                        // parPosDelta - PARAMS.parYTicks * headingDelta,
                        pose.getY(DistanceUnit.INCH) - lastPose.getY(DistanceUnit.INCH),
                        // parPosVel.velocity - PARAMS.parYTicks * headingVel,
                        velocity.getY(DistanceUnit.INCH)
                    )), //.times(inPerTick),
            DualNum<Time>(doubleArrayOf(
                // perpPosDelta - PARAMS.perpXTicks * headingDelta,
                pose.getX(DistanceUnit.INCH) - lastPose.getX(DistanceUnit.INCH),
                // perpPosVel.velocity - PARAMS.perpXTicks * headingVel,
                velocity.getX(DistanceUnit.INCH)
            )) //.times(inPerTick)
        ),
            DualNum(doubleArrayOf(
                // headingDelta,
                pose.getHeading(AngleUnit.RADIANS) - lastPose.getHeading(AngleUnit.RADIANS),
                // headingVel,
                velocity.getHeading(AngleUnit.RADIANS)
            ))
        )

        lastPose = pose

        return twist
    }
}