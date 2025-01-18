package org.firstinspires.ftc.teamcode.roadRunner;

import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;

import org.firstinspires.ftc.teamcode.parts.GoBildaPinpointDriver;

public interface Localizer {
    Twist2dDual<Time> update();
}
