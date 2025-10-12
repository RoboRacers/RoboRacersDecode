package org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices;

import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;

/**
 * This class is responsible for handling the intaking
 * Ball at a given power.
 */
public class IntakeLogical {

    private ContinuousMotorImpl IntakeWheelMotor;

    public IntakeLogical(ContinuousMotorImpl IntakeWheelMotorParam)
    {
        super();
        this.IntakeWheelMotor = IntakeWheelMotorParam;
    }

    public void initialize()
    {
        // If needed Initialize the motors used in this class;
    }

    public void intake(double power)
    {
        IntakeWheelMotor.setPower(power);
    }
}
