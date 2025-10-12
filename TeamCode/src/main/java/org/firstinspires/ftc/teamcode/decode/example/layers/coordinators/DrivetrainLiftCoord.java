package org.firstinspires.ftc.teamcode.decode.example.layers.coordinators;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.DrivetrainLiftLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.IntakeLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;

/** This class is responsible for coordinating the shooring.
 * The client have to simply call the API shoot(inches) to shoot
 */
public class DrivetrainLiftCoord {
    private ContinuousMotorImpl FRMotor;
    private ContinuousMotorImpl FLMotor;
    private ContinuousMotorImpl BRMotor;
    private ContinuousMotorImpl BLMotor;
    private DrivetrainLiftLogical drivetrainLiftLogical;

    public DrivetrainLiftCoord(DcMotor FRMotor, DcMotor FLMotor, DcMotor BRMotor, DcMotor BLMotor, Telemetry tele)
    {
        this.FRMotor = ContinuousMotorImpl.create(FRMotor, tele); // Get the motor from hardware map
        this.FLMotor = ContinuousMotorImpl.create(FLMotor, tele); // Get the motor from hardware map
        this.BRMotor = ContinuousMotorImpl.create(BRMotor, tele); // Get the motor from hardware map
        this.BLMotor = ContinuousMotorImpl.create(BLMotor, tele); // Get the motor from hardware map
        drivetrainLiftLogical = new DrivetrainLiftLogical(this.FRMotor, this.FLMotor, this.BRMotor, this.BLMotor);
    }

    public void initialize()
    {
        FRMotor.setDirection(DcMotor.Direction.REVERSE);
        FLMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    public void drive(double lx, double ly, double rx)
    {
        drivetrainLiftLogical.drive(lx, ly, rx);
    }
}
