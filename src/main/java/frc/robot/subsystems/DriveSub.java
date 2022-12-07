// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.*;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

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
  public DriveSub() {
    leftFront.setInverted(true);
  }
  public void driveTank(double left, double right){
    if (Math.abs(left) < 0.07) {
      left = 0;
    }
    if (Math.abs(right) < 0.07) {
      right = 0;  
    }
    drive.tankDrive(left, right);
    leftBack.set(leftFront.get());
    rightBack.set(rightFront.get());
  }
  public void arcadeDrive(double speed, double rotation){
  drive.arcadeDrive(speed, rotation);
  leftBack.set(leftFront.get());
  rightBack.set(rightFront.get());
}
public void stopDrive(){
  arcadeDrive(0, 0);
}
public double getLeft(){
  return leftFront.get();
}
public double getRight(){

  return rightFront.get();
}
public void enableMotors(boolean on){
  IdleMode mode;
  if(on) {
    mode = IdleMode.kBrake;
  } else {
    mode = IdleMode.kCoast;
  }
  leftFront.setIdleMode(mode);
  leftBack.setIdleMode(mode);
  rightFront.setIdleMode(mode);
  rightBack.setIdleMode(mode);
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
