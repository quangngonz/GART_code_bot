// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotContainer;
import frc.robot.Constants.JOYSTICK;

public class Drivebase extends SubsystemBase {
  public WPI_TalonSRX leftFront = new WPI_TalonSRX(1);
  public WPI_TalonSRX leftBack = new WPI_TalonSRX(2);
  public WPI_TalonSRX rightFront = new WPI_TalonSRX(3);
  public WPI_TalonSRX rightBack = new WPI_TalonSRX(4);

  private double speedV1;
  private double speedV2;
  private double speedV3;
  private double speedV4;

  private double angleFromOrigin;
  private double x_cord;
  private double y_cord;

  /** Creates a new Drivebase. */
  public Drivebase() {}

  private double getAngles(double x, double y) {
    if (x == 0 && y > 0) {
      angleFromOrigin = Math.PI/2;
    } else if (x == 0 && y < 0) {
      angleFromOrigin = Math.PI*1.5;
    }else if (x > 0 && y == 0) {
      angleFromOrigin = 0;
    }else if (x < 0 && y == 0) {
      angleFromOrigin = Math.PI;
    }else if (x > 0 && y > 0) {
      angleFromOrigin = Math.atan(x/y);
    }else if (x < 0 && y > 0) {
      angleFromOrigin =  Math.PI + Math.atan(x/y);
    }else if (x < 0 && y < 0) {
      angleFromOrigin = Math.PI*1.5 - Math.atan(x/y);
    }else if (x > 0 && y < 0) {
      angleFromOrigin = Math.PI*2 + Math.atan(x/y);
    };
    return angleFromOrigin;
  }

  public void drive(double v1, double v2, double v3, double v4) {
    leftFront.set(v1);
    leftBack.set(v2);
    rightFront.set(v3);
    rightBack.set(v4);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    x_cord = RobotContainer.taycam.getRawAxis(JOYSTICK.LEFT_X);
    y_cord = RobotContainer.taycam.getRawAxis(JOYSTICK.LEFT_Y);
    if(x_cord != 0 || y_cord != 0 ){
      double angleGoal = getAngles(x_cord, y_cord);
      speedV1 = Math.sin(angleGoal + Math.PI / 4);
      speedV2 = Math.cos(angleGoal + Math.PI / 4);
      speedV3 = Math.sin(angleGoal + Math.PI / 4);
      speedV4 = Math.cos(angleGoal + Math.PI / 4);
      drive(speedV1, speedV2, speedV3, speedV4);
    };
  }
}
