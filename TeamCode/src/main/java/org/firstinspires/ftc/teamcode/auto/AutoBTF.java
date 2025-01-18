package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.supers.Robot;

@Autonomous(name = "BTF")
public class AutoBTF extends LinearOpMode {
    @Override
    public void runOpMode() {
        Robot r = new Robot(this);

        Action trajectory0 = r.getDrive().actionBuilder(new Pose2d(36.00, 60.00, Math.toRadians(270.00)))
                .splineToLinearHeading(new Pose2d(37.00, 39.00, Math.toRadians(90.00)), Math.toRadians(-90.00))
                .splineToConstantHeading(new Vector2d(0.0, 36.00), Math.toRadians(180.00))
                .build();

        r.getDrive().pose = new Pose2d(36.00, 60.00, Math.toRadians(270.00));

        waitForStart();

        while (opModeIsActive()) {
            Actions.runBlocking(trajectory0);
        }
    }
}
