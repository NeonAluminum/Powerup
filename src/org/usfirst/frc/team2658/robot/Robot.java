package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */

public class Robot extends IterativeRobot {
	/* Author --> Gokul Swaminathan */
	final int FRONT_LEFT_PORT = 0;   								//port number for front left motor
	final int BACK_LEFT_PORT = 1;						   				//port number for back left motor
	final int FRONT_RIGHT_PORT = 3;					    			//port number for front right motor
	final int BACK_RIGHT_PORT = 4;					   			    //port number for back right motor
	final int XBOX_PORT = 0;								    			//xbox remote port
	final int JOY1_PORT = 1;									            //joystick 1 port
	final int JOY2_PORT = 2;									            //joystick 2 port
	final int CHOOSE_XBOX = 0, CHOOSE_DUAL = 1;		    //chooser id's
	final double encDist = (Math.PI * 6)/12; //  How many feet is one encoder unit;
	final int SIDE_POS = 0, MID_POS = 1; // autonomous chooser id's
	//strings for chooser
    final String power = "Drive Power";
    final String sensitivity = "Sensitivity";
		
	//motors
	Talon fLeft;
	Talon fRight;
	Talon bLeft;
	Talon bRight;
	
	//groups of motors
	SpeedControllerGroup spLeft;
	SpeedControllerGroup spRight;

	//drivetrain
	static DifferentialDrive driveTrain;
	
	//controllers
	Joystick xbox;
	Joystick joyLeft;
	Joystick joyRight;
	/* Author --> Gokul Swaminathan */
	
	SendableChooser<Integer> controllerChooser = new SendableChooser<>();
	
	/* Author --> Neal Chokshi */
	// Autonomous Variables
	DriverStation driverStation;
	String fmsMessage;
	
	Encoder rEncoder, lEncoder;
	int encoderAvg;
	char switchSide, scaleSide;
	static double distanceTraveled;

	SendableChooser<Integer> autoChooser = new SendableChooser<>();

	/* Author --> Neal Chokshi */

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		/* Author --> Gokul Swaminathan */
		controllerChooser.addDefault("Xbox Controller (Tank Drive)", CHOOSE_XBOX);
		controllerChooser.addObject("Dual Joysticks", CHOOSE_DUAL);
		SmartDashboard.putData("Drive choices", controllerChooser);
		SmartDashboard.putNumber(power, 1);
		SmartDashboard.putNumber(sensitivity, 2);
		
		//initialize all motors
		fLeft = new Talon(FRONT_LEFT_PORT);
		fRight = new Talon(FRONT_RIGHT_PORT);
		bLeft = new Talon(BACK_LEFT_PORT);
		bRight = new Talon(BACK_RIGHT_PORT);
		
		//initialize speed controller groups
		spLeft = new SpeedControllerGroup(fLeft, bLeft); 
		spRight = new SpeedControllerGroup(fRight, bRight);
		
		//initialize drivetrain
		driveTrain = new DifferentialDrive(spLeft, spRight);
		
		//intialize all controllers
		xbox = new Joystick(XBOX_PORT);
		joyLeft = new Joystick(JOY1_PORT);
		joyRight = new Joystick(JOY2_PORT);
		/* Author --> Gokul Swaminathan */
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		/* Author --> Neal Chokshi */
		//init vars
		fmsMessage = driverStation.getGameSpecificMessage();
		switchSide = fmsMessage.charAt(0);
		scaleSide = fmsMessage.charAt(1);
		distanceTraveled = 0;


		autoChooser.addDefault("SIDE POSITION", SIDE_POS);
		autoChooser.addObject("MID POSITION", MID_POS);

		/* Author --> Neal Chokshi */
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		/* Author --> Neal Chokshi */
		encoderAvg = (rEncoder.get() + lEncoder.get())/2;
		distanceTraveled = encDist*encoderAvg;
		/* Author --> Neal Chokshi */
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		/* Author --> Gokul Swaminathan */
		//get inputs from user
		double exponent = SmartDashboard.getNumber(sensitivity, 2);
		double constant = SmartDashboard.getNumber(power, 1);

		//controller axis
		final int xboxRightAxis = 5;
		final int xboxLeftAxis = 1;	
		final int joystickAxis = 1;
		
		int mode = controllerChooser.getSelected();
		
		switch(mode)
		{
		case CHOOSE_XBOX: 
			//run tank drive method for xbox
			DriveTankDrive(xbox.getRawAxis(xboxLeftAxis), xbox.getRawAxis(xboxRightAxis) ,exponent, constant, driveTrain);
			break;
			
		case CHOOSE_DUAL:
			//run tank drive method for joysticks
			DriveTankDrive(joyLeft.getRawAxis(joystickAxis), joyRight.getRawAxis(joystickAxis), exponent, constant, driveTrain);
			break;
			
		default:
			break;
		}
		/* Author --> Gokul Swaminathan */
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
	
	/* Author --> Gokul Swaminathan */
	public void DriveTankDrive(double leftAxis, double rightAxis, double exp, double cons, DifferentialDrive drive )
	{
		
		//exp = sensitivity
		//cons = power
		
		int negR = 0, negL = 0;
		
		if(rightAxis < 0)
		{
			negR = -1;			
		}
		else if(rightAxis > 0)
		{
			negR = 1;
		}
		if(leftAxis < 0)
		{
			negL = -1;
		}
		else if(leftAxis > 0)
		{
			negL = 1;
		}
				
		double leftSpeed = - negR * cons * Math.pow(Math.abs(rightAxis), exp);
		double rightSpeed = - negL *  cons * Math.pow(Math.abs(leftAxis), exp);
		
		drive.tankDrive(leftSpeed, rightSpeed);
	}
	/* Author --> Gokul Swaminathan */
}

