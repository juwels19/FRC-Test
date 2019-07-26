package org.usfirst.frc.team6513.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;


public class PowerSubsystem extends Subsystem {

	PowerDistributionPanel pdp = new PowerDistributionPanel();
	
	public double getTotalCurrent() {
		return pdp.getTotalCurrent();
	}
	
	public double getTotalEnergy() {
		return pdp.getTotalEnergy();
	}
	
	public double getTotalPower() {
		return pdp.getTotalPower();
	}
	
	public double getChannelCurrent(int channel) {
		return pdp.getCurrent(channel);
	}
	
	public double getPdpTemp() {
		return pdp.getTemperature();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

