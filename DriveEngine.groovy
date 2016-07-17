//Your code here
import Jama.Matrix;

return new com.neuronrobotics.sdk.addons.kinematics.IDriveEngine (){
	public ArrayList<DHParameterKinematics> getAllDHChains(MobileBase source) {
		ArrayList<DHParameterKinematics> copy = new ArrayList<DHParameterKinematics>();

		for (DHParameterKinematics l : source.getSteerable()) {
			copy.add(l);
		}
		for (DHParameterKinematics l : source.getDrivable()) {
			copy.add(l);
		}
		return copy;
	}
	@Override
	public void DriveArc(MobileBase source, TransformNR newPose, double seconds) {
		//newPose = newPose.inverse()
		ArrayList<DHParameterKinematics> wheels = getAllDHChains( source)
		ArrayList<DHParameterKinematics> steerable = source.getSteerable();
		
		for(int i=0;i<wheels.size();i++){
			// Get the current pose of the robots base
			TransformNR global= source.getFiducialToGlobalTransform();
			// set a new one if null
			if(global==null){
				global=new TransformNR()
				source.setGlobalToFiducialTransform(global)
			}
			global=global.times(newPose);// new global pose
			DHParameterKinematics thisWheel =wheels.get(i)
			// get the pose of this wheel
			TransformNR wheelStarting = thisWheel.getRobotToFiducialTransform();
			Matrix btt =  wheelStarting.getMatrixTransform();
			Matrix ftb = global.getMatrixTransform();// our new target
			Matrix mForward = ftb.times(btt)
			TransformNR inc =new TransformNR( mForward);// this wheels new increment
			TransformNR vect =new TransformNR(btt.inverse().times(mForward));// this wheels new increment
			double xyplaneDistance = Math.sqrt(
										Math.pow(vect.getX(),2)+
										Math.pow(vect.getY(),2)
									)
			double steer =90-Math.toDegrees( Math.atan2(vect.getX(),vect.getY()))
			boolean reverseWheel = false
			if(steer>90){
				steer=steer-180
				reverseWheel=true;
			}
			if(steer<-90){
				steer=steer+180
				reverseWheel=true;
			}
			if(steerable.contains(thisWheel)){
				println "\n\n"+i+" XY plane distance "+xyplaneDistance
				println "Steer angle "+steer
				thisWheel.setDesiredJointAxisValue(0,steer,0)
			}
			
		}
	}
	@Override
	public void DriveVelocityStraight(MobileBase source, double cmPerSecond) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DriveVelocityArc(MobileBase source, double degreesPerSecond,
			double cmRadius) {
		// TODO Auto-generated method stub
		
	}
}
