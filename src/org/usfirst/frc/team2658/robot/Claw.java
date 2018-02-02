package org.usfirst.frc.team2658.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//place holder class for claw mechanism -- will implement actual claw class once created
public class Claw {

	/* Author --> Gokul Swaminathan */
	// port numbers
	private final int grabbyPortOne = 0;
	private final int grabbyPortTwo = 1;

	DoubleSolenoid grabby = new DoubleSolenoid(grabbyPortOne, grabbyPortTwo);

	public void openClaw() {
		forward();
	}

	public void closeClaw() {
		reverse();
	}

	//show the status of the claw on the smartdashboard
	public void clawStatus() {
		if (grabby.get() == DoubleSolenoid.Value.kForward) {
			SmartDashboard.putString("Grabby Arm", "OPEN");
		}

		else if (grabby.get() == DoubleSolenoid.Value.kReverse) {
			SmartDashboard.putString("Grabby Arm", "CLOSE");
		}

		else {
			SmartDashboard.putString("Grabby Arm", "OFF");
		}
	}

	public void forward() {
		grabby.set(DoubleSolenoid.Value.kForward);
	}

	public void reverse() {
		grabby.set(DoubleSolenoid.Value.kReverse);
	}

	public void off() {
		grabby.set(DoubleSolenoid.Value.kOff);
	}
	/* Author --> Gokul Swaminathan */
}
