package org.firstinspires.ftc.teamcode.decode.example;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PostLobsterCup.example.layers.coordinators.ShooterCoord;
import org.firstinspires.ftc.teamcode.decode.example.layers.coordinators.IntakeCoord;
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

    private  TransferCoord transferCoord;

    private static int SHOOTER_MAX_RPM = 312;
    private static int INTAKE_MAX_RPM = 1150;

    configBank config;

    Telemetry mytele;

    DcMotor shooter;
    DcMotor intake;

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



        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive())
        {
            shooterCoord.fire(6); // distance in Feet
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
                logMessage("Running Intake");
            }

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
    private void logMessage(String message)
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());

        telemetry.addData(timeStamp, message);
        telemetry.update();
    }
}
