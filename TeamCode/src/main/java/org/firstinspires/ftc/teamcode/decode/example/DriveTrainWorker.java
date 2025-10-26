package org.firstinspires.ftc.teamcode.decode.example;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.decode.example.layers.coordinators.DrivetrainLiftCoord;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class is the main class for the robot.
 * This class orchestrates all the subsystems from here.
 *
 */

@TeleOp(name="DriveTrainWorker", group="Linear Opmode")
public class DriveTrainWorker extends LinearOpMode {


    private DrivetrainLiftCoord drivetrainLiftCoord;

    private static int DRIVE_MAX_RPM = 450;

    configBank config;

    Telemetry mytele;


    DcMotor FRMotor;
    DcMotor FLMotor;
    DcMotor BRMotor;
    DcMotor BLMotor;
    ServoImpl Servo1;
    ServoImpl Servo2;


    @Override
    public void runOpMode() throws InterruptedException
    {

        logMessage("Started the Robot Worker");

        mytele = telemetry;

        FRMotor = getFRMotor();
        FLMotor = getFLMotor();
        BRMotor = getBRMotor();
        BLMotor = getBLMotor();

        Servo1 = getServo1();
        Servo2 = getServo2();

        drivetrainLiftCoord = new DrivetrainLiftCoord(FRMotor, FLMotor, BRMotor, BLMotor, Servo1, Servo2, mytele);
        drivetrainLiftCoord.initialize();


        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive())
        {
            drivetrainLiftCoord.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        }
    }

    private DcMotor getFRMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "rf");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getFLMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "rr");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getBRMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "lr");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getBLMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "lf");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private ServoImpl getServo1() {
        ServoImpl servo =  hardwareMap.get(ServoImpl.class, "Servo1");
        return servo;
    }

    private ServoImpl getServo2() {
        ServoImpl servo =  hardwareMap.get(ServoImpl.class, "Servo2");
        return servo;
    }

    private void logMessage(String message)
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        telemetry.addData(timeStamp, message);
        telemetry.update();
    }
}
