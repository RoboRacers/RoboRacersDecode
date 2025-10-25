package org.firstinspires.ftc.teamcode.decode.example;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ServoImpl;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.decode.example.layers.coordinators.DrivetrainLiftCoord;
import org.firstinspires.ftc.teamcode.decode.example.layers.coordinators.IntakeCoord;
import org.firstinspires.ftc.teamcode.decode.example.layers.coordinators.ShooterCoord;
import org.firstinspires.ftc.teamcode.decode.example.layers.coordinators.TransferCoord;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * This class is the main class for the robot.
 * This class orchestrates all the subsystems from here.
 *
 */

@TeleOp(name="RobotWorker", group="Linear Opmode")
public class RobotWorker extends LinearOpMode {


    private ShooterCoord shooterCoord;
    private IntakeCoord intakeCoord;
    private DrivetrainLiftCoord drivetrainLiftCoord;
    private  TransferCoord transferCoord;


    private static int SHOOTER_MAX_RPM = 312;
    private static int INTAKE_MAX_RPM = 1150;
    private static int DRIVE_MAX_RPM = 450;

    configBank config;

    Telemetry mytele;

    DcMotor shooter;
    DcMotor intake;
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
        shooter = getShooterMotor();
        shooterCoord = new ShooterCoord(shooter, mytele);
        shooterCoord.initialize();

        intake = getIntakeMotor();
        intakeCoord = new IntakeCoord(intake, mytele);
        intakeCoord.initialize();

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
            shooterCoord.shoot(6); // distance in Feet
            logMessage("Running Shooter");
            
            if (gamepad1.right_trigger != 0){
                intakeCoord.intake(gamepad1.right_trigger);
                logMessage("Running Intake");
            }else{
                intakeCoord.intake(0);
                logMessage("Stopping Intake");
            }

            if (gamepad1.a){
                transferCoord.transfer(true);
                logMessage("Running Transfer");
            }

            drivetrainLiftCoord.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        }
    }


    private DcMotor getShooterMotor() {
        DcMotor shooterMotor =  hardwareMap.get(DcMotor.class, "GecoWheelMotor");
        MotorConfigurationType motorType = shooterMotor.getMotorType();
        motorType.setMaxRPM(SHOOTER_MAX_RPM);
        shooterMotor.setMotorType(motorType);
        return shooterMotor;
    }

    private DcMotor getIntakeMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "IntakeWheelMotor");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(INTAKE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getFRMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "FrontRightMotor");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getFLMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "FrontLeftMotor");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getBRMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "BackRightMotor");
        MotorConfigurationType motorType = intakeMotor.getMotorType();
        motorType.setMaxRPM(DRIVE_MAX_RPM);
        intakeMotor.setMotorType(motorType);
        return intakeMotor;
    }

    private DcMotor getBLMotor() {
        DcMotor intakeMotor =  hardwareMap.get(DcMotor.class, "BackLeftMotor");
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
