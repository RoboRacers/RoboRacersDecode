package org.firstinspires.ftc.teamcode.decode.example.layers.logicaldevices;

import org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices.ContinuousMotorImpl;

/**
 * This class is responsible for computing the RPM/Power needed to shoot the
 * Ball for the given distance.
 */
public class ShooterLogical {

    public ContinuousMotorImpl gecoWheelMotor;

    public ShooterLogical(ContinuousMotorImpl gecoWheelMotorParam)
    {
        super();
        this.gecoWheelMotor = gecoWheelMotorParam;
    }

    public void initialize()
    {
        // If needed Initialize the motors used in this class;

    }

    public double calcSpeed(double distanceInches){

        return 3000;
    }

    public double getSpeed(){
        return gecoWheelMotor.getCurrentRPM();
    }

    public boolean shoot(double distanceInches)
    {
        double rpm = calcSpeed(distanceInches);
        // TODO: Math to compute RPM/Power needed to be set in motor to shoot the Ball to target distance

        gecoWheelMotor.setRPM(rpm);
        return (Math.abs(calcSpeed(distanceInches) - gecoWheelMotor.getCurrentRPM()) <= 5);
        // TODO: Log that the motor has been given the power to shoot
    }
}
