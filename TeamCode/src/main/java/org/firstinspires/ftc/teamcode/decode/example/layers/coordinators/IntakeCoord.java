package org.firstinspires.ftc.teamcode.decode.example.layers.coordinators;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.IntakeLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;

/** This class is responsible for coordinating the shooring.
 * The client have to simply call the API shoot(inches) to shoot
 */
public class IntakeCoord {

    private ContinuousMotorImpl IntakeWheelMotor;
    private IntakeLogical intakeLogical;

    public IntakeCoord(DcMotor dcMotor, Telemetry tele)
    {
        IntakeWheelMotor = ContinuousMotorImpl.create(dcMotor, tele); // Get the motor from hardware map
        intakeLogical = new IntakeLogical(IntakeWheelMotor);
    }

    public void initialize()
    {
        // Make sure the motors used by this class is initialized

    }

    public void intake(double power)
    {
        // TODO: Add logging of the distance to shoot given to logical layer

        // TODO:
        //  The GeckoWheel must be started
        //  The coordinator must wait for the wheels to be up to target speed.
        //  Then the servo to push the ball to the shooter
        //  After the ball is shoot, the GeckoWheel should stop.
        intakeLogical.intake(power);
    }
}
