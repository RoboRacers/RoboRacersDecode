package org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices;

import com.qualcomm.robotcore.hardware.CRServoImpl;
import com.qualcomm.robotcore.hardware.ServoImpl;

/**
 * This class is responsible for computing the RPM/Power needed to shoot the
 * Ball for the given distance.
 */
public class TransferLogical {

    private CRServoImpl CRLeft;
    private CRServoImpl CRRight;
    private ServoImpl LeftLatch;
    private ServoImpl RightLatch;

    private int angle = 0;
    private int angle2 = 0;


    public TransferLogical(CRServoImpl CRLeft, CRServoImpl CRRight, ServoImpl LeftLatch, ServoImpl RightLatch)
    {
        super();
        this.CRLeft = CRLeft;
        this.CRRight = CRRight;
        this.LeftLatch = LeftLatch;
        this.RightLatch = RightLatch;
    }

    public void initialize()
    {
        // If needed Initialize the motors used in this class;

    }

    public void roll(boolean starttransfer)
    {
        // SERVO ANGLE FOR LATCH

        CRLeft.setPower(1);
        CRLeft.setPower(1);
        LeftLatch.setPosition(angle);
        RightLatch.setPosition(angle2);


        // TODO: Log that the motor has been given the power to shoot
    }
}
