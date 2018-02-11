	/*
	 * To change this license header, choose License Headers in Project Properties.
	 * To change this template file, choose Tools | Templates
	 * and open the template in the editor.
	 */

package org.usfirst.frc5803.RobotTestBench.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

	/**
	 *
	 * @author mwtidd
	 */
public class TriggerButton extends Button{
    private final XboxController xbox;
    private final int axis;

    public TriggerButton(XboxController xbox, int axis){
        this.xbox = xbox;
        this.axis = axis;

    }

public boolean get() {
       return xbox.getRawAxis(axis) >.25;
    }
}
