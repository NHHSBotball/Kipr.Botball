package test;


import cbc.motors.Servo;
import cbc.sensors.buttons.*;
import cbc.create.Create;
import cbc.create.CreateConnectException;
import cbc.movement.DriveTrain;
import cbc.movement.plugins.MovementPlugin;
import cbc.movement.plugins.create.CreateMovementPlugin;

class Main {
	public static void main(String[] args) {
		MovementPlugin plugin = null;
		try {
			plugin = new CreateMovementPlugin(new Create());
		} catch(CreateConnectException e) {
			System.out.println("Create failed to connect.");
			System.exit(1);
		}
		DriveTrain dt= new DriveTrain(plugin);
		Servo servoL = new Servo(0);
		Servo servoR = new Servo(3);
		Servo servoClaw = new Servo(1);
		UpButton upButton = new UpButton();
		DownButton downButton = new DownButton(); 
		LeftButton leftButton = new LeftButton();
		RightButton rightButton = new RightButton();
		BlackButton blackButton = new BlackButton();
		servoL.enable();
		servoR.enable();
		servoClaw.enable();
		while(blackButton.getValue() == true){
			while(upButton.getValue() == true){
				System.out.println(servoL.getPosition() + " " + servoR.getPosition());
				servoL.setPosition(servoL.getPosition() + 30);
				servoR.setPosition(servoR.getPosition() - 30);
				try { Thread.sleep(150); } catch (Exception e) {}
			}
			while(downButton.getValue() == true){
				System.out.println(servoL.getPosition() + " " + servoR.getPosition());
				servoL.setPosition(servoL.getPosition() - 30);
				servoR.setPosition(servoR.getPosition() + 30);
				try { Thread.sleep(100); } catch (Exception e) {}
			}
			while(leftButton.getValue() == true){
				System.out.println(servoClaw.getPosition());
				servoClaw.setPosition(servoClaw.getPosition() + 30);
				try { Thread.sleep(100); } catch (Exception e) {}
			}
			while(rightButton.getValue() == true){
				System.out.println(servoClaw.getPosition());
				servoClaw.setPosition(servoClaw.getPosition() - 30);
				try { Thread.sleep(100); } catch (Exception e) {}
			}
		}
	}
}