package org.usfirst.frc.team6513.robot.commands;

import org.usfirst.frc.team6513.robot.Robot;
import org.usfirst.frc.team6513.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorMoveToTop extends Command {

    public ElevatorMoveToTop() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.elevatorSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.elevatorSubsystem.autoMode = true;
    	Robot.elevatorSubsystem.moveToTarget(RobotMap.TOP, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
