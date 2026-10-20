package frc.robot.subsystems.drive;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
/** 底盘能力层。Command 说要做什么，Subsystem 决定怎样调用 IO。 */
public class DriveSubsystem extends SubsystemBase {
  private final DriveIO io;
  private final DriveIO.Inputs inputs = new DriveIO.Inputs();
  public DriveSubsystem(DriveIO io) { this.io=io; }
  public void arcade(double forward, double turn) {
    double l=forward+turn, r=forward-turn; // 本例 turn 正值表示向右转
    double scale=Math.max(1.0, Math.max(Math.abs(l),Math.abs(r)));
    setOutputs(l/scale*Constants.DRIVE_LIMIT, r/scale*Constants.DRIVE_LIMIT);
  }
  public void setOutputs(double left, double right) {
    io.setOutputs(MathUtil.clamp(left,-Constants.DRIVE_LIMIT,Constants.DRIVE_LIMIT),
                  MathUtil.clamp(right,-Constants.DRIVE_LIMIT,Constants.DRIVE_LIMIT));
  }
  public void stop() { io.stop(); }
  @Override public void periodic() {
    if (!DriverStation.isEnabled()) stop();
    io.updateInputs(inputs);
    SmartDashboard.putNumber("Drive/LeftOutput",inputs.leftOutput);
    SmartDashboard.putNumber("Drive/RightOutput",inputs.rightOutput);
    SmartDashboard.putNumber("Drive/LeftMotorRps",inputs.leftMotorRps);
    SmartDashboard.putNumber("Drive/RightMotorRps",inputs.rightMotorRps);
    SmartDashboard.putBoolean("Robot/Enabled",DriverStation.isEnabled());
  }
}
