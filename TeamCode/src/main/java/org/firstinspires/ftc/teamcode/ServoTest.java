package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "Servo Test", group = "Examples")
public class ServoTest extends OpMode {

    private Servo servo;



    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "servo");

    }

    @Override
    public void loop() {

     if (gamepad1.dpad_up){
         servo.setPosition(1);
     }
        if (gamepad1.dpad_right){
            servo.setPosition(0.5);
        }
        if (gamepad1.dpad_down){
            servo.setPosition(0);
        }

telemetry.addData("Servo Pos", servo.getPosition());




        telemetry.update();



    }
}
