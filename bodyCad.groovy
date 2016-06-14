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
		
		double UpperOffset = -76.3	
		// Load the .CSG from the disk and cache it in memory
		CSG body  = Vitamins.get(mainBodyFile)
		CSG USP1R  = Vitamins.get(USP1R_file)
					.toYMin()
					.movex(UpperOffset)
					.movez(-0.2)
		CSG LSP1R  = Vitamins.get(LSP1R_file)
					.toYMin()
					.movez(-5)
					.movex(-5)
					.movey(10)
		CSG USP2R  = Vitamins.get(USP2R_file)
					.rotz(180)
					.toYMin()
					.toXMin()
					.movex(UpperOffset-8)
					.movez(26.75)
					.movey(-1)
		CSG LSP2R  = Vitamins.get(LSP2R_file)
					.toYMin()

		CSG USP1L  = Vitamins.get(USP1L_file)
		CSG LSP1L  = Vitamins.get(LSP1L_file)
					.movez(-5)
					.movex(-5)
					.movey(-10)
		CSG USP2L  = Vitamins.get(USP2L_file)
		CSG LSP2L  = Vitamins.get(LSP2L_file)

		def left =[USP1L,LSP1L,USP2L]
		def right = [USP1R,LSP1R,USP2R]


		body.setManipulator(b.getRootListener());
		left.addAll(right)
		//left.add(body)

		return left;
	}
};