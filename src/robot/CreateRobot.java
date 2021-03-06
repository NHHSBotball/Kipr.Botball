package robot;

import cbc.create.Create;
import cbc.create.CreateConnectException;
import cbc.movement.DriveTrain;
import cbc.movement.efficiency.IEfficiencyCalibrator;
import cbc.movement.plugins.create.CreateMovementPlugin;

public abstract class CreateRobot extends Robot {

	protected CreateRobot() throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create())) {
			@Override
			protected void moveWheelCm(double leftCm, double rightCm, //positions
	                    double leftCmps, double rightCmps) { 
					if(leftCm<0 && rightCm<0 || leftCm>0 && rightCm>0){
						leftCm = -leftCm;
						rightCm = -rightCm;
					}
					super.moveWheelCm(leftCm, rightCm, leftCmps, rightCmps);
				}
			}
		);
	}
	protected CreateRobot(double leftEfficiency, double rightEfficiency) throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create(),leftEfficiency, rightEfficiency)){
			@Override
			protected void moveWheelCm(double leftCm, double rightCm, //positions
	                    double leftCmps, double rightCmps) { 
				if(leftCm<0 && rightCm<0 || leftCm>0 && rightCm>0){
					leftCm = -leftCm;
					rightCm = -rightCm;
				}
				}
			}
		);
	}
	protected CreateRobot(IEfficiencyCalibrator leftEfficiency, IEfficiencyCalibrator rightEfficiency) throws CreateConnectException {
		this(leftEfficiency, rightEfficiency, false);
	}
	
	protected CreateRobot(IEfficiencyCalibrator leftEfficiency, IEfficiencyCalibrator rightEfficiency, boolean fullMode) throws CreateConnectException {
		super(new DriveTrain(new CreateMovementPlugin(new Create(), leftEfficiency, rightEfficiency, fullMode)){
			@Override
			protected void moveWheelCm(double leftCm, double rightCm, //positions
	                    double leftCmps, double rightCmps) { 
				if(leftCm<0 && rightCm<0 || leftCm>0 && rightCm>0){
					leftCm = -leftCm;
					rightCm = -rightCm;
				}
				}
			}
		);
	}
	
	
}
