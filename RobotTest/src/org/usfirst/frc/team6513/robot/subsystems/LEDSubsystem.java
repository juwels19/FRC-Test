package org.usfirst.frc.team6513.robot.subsystems;

import org.usfirst.frc.team6513.robot.RobotMap;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LEDSubsystem extends Subsystem {
	
	Spark blinkin = new Spark(RobotMap.BLINKIN_DRIVER);
	
	// patterns only hold for the 5V addressable LED strip
	
	public void robotDisabled() {
		blinkin.set(0.61); // led pattern: solid red
	}

	public void autoRunning() {
		blinkin.set(-0.09); // led pattern: blue strobe
	}
	
	public void teleopStarted() {
		blinkin.set(-0.29); // led pattern: blue light chase
		Timer.delay(1.5); // display pattern for 1.5 seconds
		blinkin.set(0.0); // turn leds off
	}
	
	public void endGameWarning() {
		blinkin.set(-0.05); // led pattern: white strobe
		Timer.delay(3.0); // display the pattern for 3 seconds
		blinkin.set(0.0); // turn leds off
	}
	
	public void hasGamePiece() {
		blinkin.set(0.15); // led pattern: "colour 1" strobe (change colour 1 on the blinkin driver to green)
		Timer.delay(1.0); // display pattern for 1 second
		blinkin.set(0.77); // led pattern: solid green 
		
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

