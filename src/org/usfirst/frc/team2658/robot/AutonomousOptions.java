package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.Timer;
//TODO: Implement Timers(?) Implement Autonomous Methods
public class AutonomousOptions {
    Timer timer = new Timer();

    private static void moveForward(double distance, double power){
        double drivePower = 0;
        while(Robot.distanceTraveled < distance){
            drivePower = power * ((distance - Robot.distanceTraveled)/distance);
            Robot.driveTrain.arcadeDrive(power, 0);

        }
        Robot.distanceTraveled = 0;
        resetEncoders();
    }
    //  negative angle to turn left, positive to turn right. Speed to turn at, tolerance of turn (how off can it be)
    private static void turn(double angle, double power, int tolerance){
        double turnPower = power * (angle > 0 ? 1:-1);
        double targetAngle = Robot.gyro.getAngle() + angle;

        while(Math.abs(targetAngle - Robot.gyro.getAngle()) > tolerance){
            Robot.driveTrain.arcadeDrive(0, turnPower);
        }


    }
    private static void resetEncoders(){
        Robot.rEncoder.reset();
        Robot.lEncoder.reset();
    }

    public static void straightDiagL(){

    }
    public static void straightDiagR(){

    }
    public static void straight(){

    }
    public static void crossLine(){

    }
    public static void nintendoSwitch(char joycon){
        if(joycon == 'r'){
            // go to switch
        }
        else if (joycon == 'l'){
            // go to switch
        }
    }

}
