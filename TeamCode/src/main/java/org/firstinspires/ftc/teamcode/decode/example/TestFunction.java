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
public class TestFunction extends LinearOpMode {


    private ShooterCoord shooterCoord;

    private static int SHOOTER_MAX_RPM = 3000;

    configBank config;

    Telemetry mytele;

    DcMotor shooter;


    @Override
    public void runOpMode() throws InterruptedException
    {

        logMessage("Started the Robot Worker");

        mytele = telemetry;
        shooter = getShooterMotor();
        shooterCoord = new ShooterCoord(shooter, mytele);
        shooterCoord.initialize();



        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive())
        {
            logMessage("Running Shooter");
            shooterCoord.shoot(6);
            logMessage(String.valueOf(shooterCoord.getSpeed()));
        }
    }


    private DcMotor getShooterMotor() {
        DcMotor shooterMotor =  hardwareMap.get(DcMotor.class, "testmotor");
        MotorConfigurationType motorType = shooterMotor.getMotorType();
        motorType.setMaxRPM(SHOOTER_MAX_RPM);
        shooterMotor.setMotorType(motorType);
        return shooterMotor;
    }

    private void logMessage(String message)
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        telemetry.addData(timeStamp, message);
        telemetry.update();
    }
}
