package org.firstinspires.ftc.teamcode.decode.example.layers.physicaldevices;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import androidx.annotation.NonNull;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorImpl;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;


/**
 * This class is responsible for sending the commands to physical motor
 * This class extends from DcMotorImpl, so any additional decorative things or
 * operations can be added in addition when the user want to use the DcMotorImpl's API
 */
public class ContinuousMotorImpl extends DcMotorImpl
{
    private long lastPosition = 0;
    private long lastTimeNanos = 0;

    public ContinuousMotorImpl(DcMotorController controller, int portNumber) {
        super(controller, portNumber, Direction.FORWARD);

    }

    public ContinuousMotorImpl(DcMotorController controller, int portNumber, Direction direction) {
        super(controller, portNumber, direction, MotorConfigurationType.getUnspecifiedMotorType());
    }

    public ContinuousMotorImpl(DcMotorController controller, int portNumber, Direction direction, @NonNull MotorConfigurationType motorType)
    {
        super(controller, portNumber, direction, motorType);
    }

    public static ContinuousMotorImpl create(DcMotor dcMotor, Telemetry telemetry)
    {
        //telemetry.addData("Motor Type", dcMotor.getMotorType());

        return new ContinuousMotorImpl(dcMotor.getController(), dcMotor.getPortNumber(), dcMotor.getDirection(), dcMotor.getMotorType());
    }

    public synchronized double getCurrentRPM(){
        final double TICKS_PER_REV = this.getMotorType().getTicksPerRev();
        // Cannot calculate RPM if ticks per revolution is unknown or zero
        if (TICKS_PER_REV <= 0) {
            return 0.0;
        }
        final long newPosition = this.getCurrentPosition();
        final long newTimeNanos = System.nanoTime();
        // Handle the very first call to prevent division by zero or large initial noise
        if (lastTimeNanos == 0) {
            lastPosition = newPosition;
            lastTimeNanos = newTimeNanos;
            return 0.0;
        }
        final long deltaPosition = newPosition - lastPosition;
        final long deltaTimeNanos = newTimeNanos - lastTimeNanos;
        // Guard against division by zero if time hasn't advanced
        if (deltaTimeNanos == 0) {
            return 0.0;
        }
        // Calculation:
        // (deltaPosition / deltaTimeNanos) -> Ticks per Nanosecond
        // Ticks per Nanosecond * 60,000,000,000 -> Ticks per Minute
        // Ticks per Minute / Ticks per Revolution -> RPM
        final double ticksPerNano = (double) deltaPosition / deltaTimeNanos;
        final double rpm = (ticksPerNano * 60_000_000_000.0) / TICKS_PER_REV;
        // Update tracking variables for the next iteration
        lastPosition = newPosition;
        lastTimeNanos = newTimeNanos;
        return rpm;
    }

    public synchronized void setRPM(double rpm){
        try{
            if (rpm > this.motorType.getMaxRPM()){
                throw new Exception("RPM is greater than max RPM. Max RPM: " + this.motorType.getMaxRPM());
            }else if (rpm < this.motorType.getMaxRPM()){
                setPower(rpm/this.motorType.getMaxRPM());
            }

        }catch (Exception e){
            telemetry.addData("Error", e.getMessage());
            setPower(1);
        }
    }

    public synchronized void setRPMv2(double rpm){
        setPower((Math.max(0,Math.min(rpm,this.motorType.getMaxRPM())))/this.motorType.getMaxRPM());

    }



    public synchronized void setPower(double power) {
        // Adding logging message
        super.setPower(power);
    }

}
