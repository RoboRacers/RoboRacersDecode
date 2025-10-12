package org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices;

import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;

/**
 * This class is responsible for computing the RPM/Power needed to shoot the
 * Ball for the given distance.
 */
public class DrivetrainLiftLogical {

    private ContinuousMotorImpl FRMotor;
    private ContinuousMotorImpl FLMotor;
    private ContinuousMotorImpl BRMotor;
    private ContinuousMotorImpl BLMotor;

    public DrivetrainLiftLogical(ContinuousMotorImpl FRMotorParam, ContinuousMotorImpl FLMotorParam, ContinuousMotorImpl BRMotorParam, ContinuousMotorImpl BLMotorParam)
    {
        super();
        this.FRMotor = FRMotorParam;
        this.FLMotor = FLMotorParam;
        this.BRMotor = BRMotorParam;
        this.BLMotor = BLMotorParam;
    }

    public void initialize()
    {
        // If needed Initialize the motors used in this class;
    }

    public void drive(double lx, double ly, double rx)
    {
        lx = lx * 1.1;
        double denominator = Math.max(Math.abs(ly) + Math.abs(lx) + Math.abs(rx), 1);
        double frontLeftPower = (ly + lx + rx) / denominator;
        double backLeftPower = (ly - lx + rx) / denominator;
        double frontRightPower = (ly - lx - rx) / denominator;
        double backRightPower = (ly + lx - rx) / denominator;
    }
}
