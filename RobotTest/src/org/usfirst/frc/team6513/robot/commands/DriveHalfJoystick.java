package org.usfirst.frc.team6513.robot.commands;

import org.usfirst.frc.team6513.robot.Robot;
import org.usfirst.frc.team6513.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class DriveHalfJoystick extends Command {

    public DriveHalfJoystick() {
    	requires(Robot.driveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveSubsystem.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double throttle = -Robot.oi.driverStick.getRawAxis(RobotMap.LEFT_STICK_Y); // invert signal for y-axis 
    	double yaw = Robot.oi.driverStick.getRawAxis(RobotMap.RIGHT_STICK_X);
    	double damper = 0.5;
    	
    	Robot.driveSubsystem.driveSlowerTeleop(throttle, yaw, damper);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveSubsystem.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
