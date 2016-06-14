import com.neuronrobotics.bowlerstudio.creature.ICadGenerator;
import com.neuronrobotics.bowlerstudio.creature.CreatureLab;
import org.apache.commons.io.IOUtils;
import com.neuronrobotics.bowlerstudio.vitamins.*;
import java.nio.file.Paths;
import eu.mihosoft.vrl.v3d.FileUtil;
import com.neuronrobotics.bowlerstudio.vitamins.*;
println "Loading STL file"
// Load an STL file from a git repo
// Loading a local file also works here

return new ICadGenerator(){
	@Override 
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {return new ArrayList<>()}
	@Override 
	public ArrayList<CSG> generateBody(MobileBase b ) {
		ArrayList<CSG> allCad=new ArrayList<>();
		double size =40;

		File mainBodyFile = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/body.STL");
		File USP1R_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/upper-suspension-p1-right.STL");
		File LSP1R_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/lower-suspension-p1-right.STL");
		File USP2R_file= ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/upper-suspension-p2-right.STL");
		
		File USP1L_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/upper-suspension-p1-left.STL");
		File LSP1L_file= ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/lower-suspension-p1-left.STL");
		File USP2L_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/upper-suspension-p2-left.STL");
			
		//changed
		File LSP2R_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/lower-suspension-p2-right.STL");		
		File LSP2L_file= ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/lower-suspension-p2-left.STL");	
			
		double upperHeightAdjust = 2.175
		double lowerAdjustAngle = 4.6
		double UpperOffset = -78.5
		double heightOfUpperHinge = 27+upperHeightAdjust
		double offsetOfLowerBracket = 8.5
		double LowerOffset  = 65.25
		double mainWheelAlignemnt=-5
		double lowerHingeOffset = 1
	
		// Load the .CSG from the disk and cache it in memory
		CSG body  = Vitamins.get(mainBodyFile)
		CSG USP1R  = Vitamins.get(USP1R_file)
					.toYMin()
					.movex(UpperOffset)
					.movez(upperHeightAdjust)
		CSG LSP1R  = Vitamins.get(LSP1R_file)
					.toYMin()
					.movez(mainWheelAlignemnt)
					.movex(mainWheelAlignemnt)
					.roty(lowerAdjustAngle)
					.movey(offsetOfLowerBracket)
		CSG USP2R  = Vitamins.get(USP2R_file)
					.rotz(180)
					.toYMin()
					.toXMin()
					.movex(UpperOffset-8)
					.movez(heightOfUpperHinge)
					.movey(-1)
		CSG LSP2R  = Vitamins.get(LSP2R_file)
					.rotx(-90)
					.rotz(180)
					.toYMin()
					.toXMin()
					.toYMin()
					.movey(offsetOfLowerBracket-lowerHingeOffset)
					.movez(heightOfUpperHinge)
					.movex(LowerOffset)
					
		CSG USP1L  = Vitamins.get(USP1L_file)
					.movex(UpperOffset)
					.movez(upperHeightAdjust)
		CSG LSP1L  = Vitamins.get(LSP1L_file)
					.movez(mainWheelAlignemnt)
					.movex(mainWheelAlignemnt+0.25)
					.roty(lowerAdjustAngle)
					.movey(-offsetOfLowerBracket+0.75)
					.movez(0.25)
		CSG USP2L  = Vitamins.get(USP2L_file)
					.movex(UpperOffset-8)
					.movez(heightOfUpperHinge)
					.movey(1)
		CSG LSP2L  = Vitamins.get(LSP2L_file)
					.rotx(-90)
					.movey(-offsetOfLowerBracket+lowerHingeOffset)
					.movez(heightOfUpperHinge)
					.movex(LowerOffset)

		def left =[USP1L,LSP1L,USP2L,LSP2L]
		def right = [USP1R,LSP1R,USP2R,LSP2R]


		body.setManipulator(b.getRootListener());
		left.addAll(right)
		//left.add(body)

		return left;
	}
};