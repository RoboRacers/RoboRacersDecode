package org.firstinspires.ftc.teamcode.decode.example.layers.coordinators;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.ShooterLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices.TransferLogical;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.SmartCRServo;
import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.SmartServo;

/** This class is responsible for coordinating the shooring.
 * The client have to simply call the API shoot(inches) to shoot
 */
public class TransferCoord {

    private CRServoImpl CRLeft;
    private CRServoImpl CRRight;
    private ServoImpl LeftLatch;
    private ServoImpl RightLatch;

    private TransferLogical transferLogical;

    public TransferCoord(SmartCRServo crServo, SmartServo servo, Telemetry tele)
    {
        CRLeft = SmartCRServo.create(crServo, tele); // Get the motor from hardware map
        CRRight = SmartCRServo.create(crServo, tele); // Get the motor from hardware map
        LeftLatch = SmartServo.create(servo, tele); // Get the motor from hardware map
        RightLatch = SmartServo.create(servo, tele); // Get the motor from hardware map

        transferLogical = new TransferLogical(CRLeft, CRRight, LeftLatch, RightLatch);
    }

    public void initialize()
    {
        // Make sure the motors used by this class is initialized

    }

    public void transfer(boolean start)
    {
        // TODO: Add logging of the distance to shoot given to logical layer

        // TODO:
        //  The GeckoWheel must be started
        //  The coordinator must wait for the wheels to be up to target speed.
        //  Then the servo to push the ball to the shooter
        //  After the ball is shoot, the GeckoWheel should stop.

        transferLogical.roll(true);


    }
}
