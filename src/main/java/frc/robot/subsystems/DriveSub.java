// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//  leftBackInt = 6, 
//   leftFrontInt = 5, 
//   rightBackInt = 8, 
//   rightFrontInt = 7, 
public class DriveSub extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private final CANSparkMax
    leftFront = new CANSparkMax(5, CANSparkMaxLowLevel.MotorType.kBrushless),
    rightFront = new CANSparkMax(7, CANSparkMaxLowLevel.MotorType.kBrushless),
    leftBack = new CANSparkMax(6, CANSparkMaxLowLevel.MotorType.kBrushless),
    rightBack = new CANSparkMax(8, CANSparkMaxLowLevel.MotorType.kBrushless);
  
  public DifferentialDrive drive = new DifferentialDrive(leftFront, rightFront);

  public DriveSub() { //In the constructor, when we create a  Drivesub object, wej'll set the left front to be inverted. 
    leftFront.setInverted(true);
  }
  public void driveTank(double left, double right){
    // Acount for joystick drifting. 
    if (Math.abs(left) < 0.07) {
      left = 0;
    }
    if (Math.abs(right) < 0.07) {
      right = 0;  
    }
    // Use the built in tankDrive() with the new motified left and right speeds. 
    drive.tankDrive(left, right);
    // Have the back motors follow the front motors. 
    leftBack.set(leftFront.get());
    rightBack.set(rightFront.get());
  }


  public void arcadeDrive(double speed, double rotation){
  // Use the built-in arcade drive
  drive.arcadeDrive(speed, rotation);
  // have back motors follow the front motors. 
  leftBack.set(leftFront.get());
  rightBack.set(rightFront.get());

}
public void stopDrive(){
  // Stop the Arcade Drive. 
  arcadeDrive(0, 0);
}
public double getLeft(){
  // Return left encoder value. 
  return leftFront.get();
}
public double getRight(){
  // Return right encoder value. 
  return rightFront.get();
}

public double getForward(){
  // Return the average of the two encoders
  return ((getRight() + getLeft()) /2 );
}

public void enableMotors(boolean on){
  // Basically, set the enable motors to either kBrake (doesn't move at all), or kCoast (you can push the robot)
  IdleMode mode;
  if(on) {
    mode = IdleMode.kBrake;
  } else {
    mode = IdleMode.kCoast;
  }
  // Set the motors to the mode. 
  leftFront.setIdleMode(mode);
  leftBack.setIdleMode(mode);
  rightFront.setIdleMode(mode);
  rightBack.setIdleMode(mode);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // Put appropiate values for the numbers on Smartdashboard. 
    SmartDashboard.putNumber("Encoder Forward", getForward());
    SmartDashboard.putNumber("Encoder Left", getLeft());
    SmartDashboard.putNumber("Encoder Right", getRight());
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
