package org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices;

import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.SmartServo;

/**
 * This class is responsible for computing the RPM/Power needed to shoot the
 * Ball for the given distance.
 */
public class DrivetrainLiftLogical {

    private ContinuousMotorImpl FRMotor;
    private ContinuousMotorImpl FLMotor;
    private ContinuousMotorImpl BRMotor;
    private ContinuousMotorImpl BLMotor;
    private SmartServo Servo1;
    private SmartServo Servo2;

    private final double Servo1PosRetract = 0.5;
    private final double Servo2PosRetract = 1;
    private final double Servo1PosExtend = 0.5;
    private final double Servo2PosExtend = 1;


    public DrivetrainLiftLogical(ContinuousMotorImpl FRMotorParam, ContinuousMotorImpl FLMotorParam, ContinuousMotorImpl BRMotorParam, ContinuousMotorImpl BLMotorParam, SmartServo Servo1Param, SmartServo Servo2Param)
    {
        super();
        this.FRMotor = FRMotorParam;
        this.FLMotor = FLMotorParam;
        this.BRMotor = BRMotorParam;
        this.BLMotor = BLMotorParam;
        this.Servo1 = Servo1Param;
        this.Servo2 = Servo2Param;
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

        FRMotor.setPower(frontRightPower);
        FLMotor.setPower(frontLeftPower);
        BRMotor.setPower(backRightPower);
        BLMotor.setPower(backLeftPower);
    }
    
    public void liftToggle(){
        if (Servo1.getPosition() == Servo1PosRetract){
            Servo1.setPosition(Servo1PosExtend);
            Servo2.setPosition(Servo2PosExtend);
        }else if (Servo1.getPosition() == Servo1PosExtend){
            Servo1.setPosition(Servo1PosRetract);
            Servo2.setPosition(Servo2PosRetract);
        }
    }
}
