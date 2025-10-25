package org.firstinspires.ftc.teamcode.decode.example.layers.coordinators;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.DrivetrainLiftLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.IntakeLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.SmartServo;

/** This class is responsible for coordinating the shooring.
 * The client have to simply call the API shoot(inches) to shoot
 */
public class DrivetrainLiftCoord {
    private ContinuousMotorImpl FRMotor;
    private ContinuousMotorImpl FLMotor;
    private ContinuousMotorImpl BRMotor;
    private ContinuousMotorImpl BLMotor;
    private SmartServo Servo1;
    private SmartServo Servo2;
    private DrivetrainLiftLogical drivetrainLiftLogical;

    public DrivetrainLiftCoord(DcMotor FRMotor, DcMotor FLMotor, DcMotor BRMotor, DcMotor BLMotor, ServoImpl Servo1, ServoImpl Servo2, Telemetry tele)
    {
        this.FRMotor = ContinuousMotorImpl.create(FRMotor, tele); // Get the motor from hardware map
        this.FLMotor = ContinuousMotorImpl.create(FLMotor, tele); // Get the motor from hardware map
        this.BRMotor = ContinuousMotorImpl.create(BRMotor, tele); // Get the motor from hardware map
        this.BLMotor = ContinuousMotorImpl.create(BLMotor, tele); // Get the motor from hardware map
        this.Servo1 = SmartServo.create(Servo1, tele);
        this.Servo2 = SmartServo.create(Servo2, tele);
        drivetrainLiftLogical = new DrivetrainLiftLogical(this.FRMotor, this.FLMotor, this.BRMotor, this.BLMotor, this.Servo1, this.Servo2);
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
