package frc.robot;
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.DriveForTime;
import frc.robot.subsystems.drive.*;
import frc.robot.subsystems.roller.*;
/** 装配中心：决定用什么硬件、哪些按钮触发哪些动作、自动选什么。 */
public class RobotContainer {
  private final CommandXboxController driver = new CommandXboxController(Constants.DRIVER_PORT);
  private final DriveSubsystem drive;
  private final RollerSubsystem intake;
  private final RollerSubsystem shooter;
  private final SendableChooser<Command> autoChooser = new SendableChooser<>();
  public RobotContainer() {
    if (RobotBase.isReal() && !Constants.ALLOW_REAL_HARDWARE) {
      throw new IllegalStateException("教学模板默认禁止实车输出，请先完成硬件核对");
    }
    // 与旧项目相同：上层逻辑不变，替换底层 IO 即可切换实车和模拟。
    drive = new DriveSubsystem(RobotBase.isReal() ? new DriveIOReal() : new DriveIOSim());
    intake = new RollerSubsystem("Intake", RobotBase.isReal()
        ? new RollerIOReal(Constants.INTAKE_ID) : new RollerIOSim());
    shooter = new RollerSubsystem("Shooter", RobotBase.isReal()
        ? new RollerIOReal(Constants.SHOOTER_ID) : new RollerIOSim());
    configureBindings();
    autoChooser.setDefaultOption("Do nothing", Commands.none());
    autoChooser.addOption("Drive 1 second", new DriveForTime(drive, 0.15, 1.0));
    SmartDashboard.putData("AutoChooser", autoChooser);
  }
  private void configureBindings() {
    drive.setDefaultCommand(drive.run(() -> {
      // 默认命令也可能在 Autonomous 空闲时调度，所以明确限制到 Teleop。
      if (DriverStation.isTeleopEnabled()) {
        drive.arcade(MathUtil.applyDeadband(-driver.getLeftY(), Constants.DEADBAND),
                     MathUtil.applyDeadband(driver.getRightX(), Constants.DEADBAND));
      } else drive.stop();
    }).finallyDo(interrupted -> drive.stop()));
    driver.leftBumper().and(DriverStation::isTeleopEnabled).whileTrue(intake.runAt(0.20));
    driver.a().and(DriverStation::isTeleopEnabled).whileTrue(shooter.runAt(0.20));
    // 按住 X 临时占用底盘并停车；松开后默认驾驶命令恢复。
    driver.x().and(DriverStation::isTeleopEnabled)
        .whileTrue(drive.run(drive::stop).finallyDo(interrupted -> drive.stop()));
  }
  public Command getAutonomousCommand() { return autoChooser.getSelected(); }
  public void stopAll() { drive.stop(); intake.stop(); shooter.stop(); }
}
