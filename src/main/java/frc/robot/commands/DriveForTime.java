package frc.robot.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.drive.DriveSubsystem;
/** 展示命令四个阶段：开始、反复执行、判定结束、清理。不是精确距离自动。 */
public class DriveForTime extends Command {
  private final DriveSubsystem drive;
  private final double output, seconds;
  private final Timer timer=new Timer();
  public DriveForTime(DriveSubsystem drive,double output,double seconds) {
    this.drive=drive; this.output=output; this.seconds=seconds;
    addRequirements(drive); // 告诉调度器：此命令执行期间占用底盘。
  }
  @Override public void initialize() { timer.restart(); }
  @Override public void execute() { drive.setOutputs(output,output); }
  @Override public boolean isFinished() { return timer.hasElapsed(seconds); }
  @Override public void end(boolean interrupted) { drive.stop(); timer.stop(); }
}
