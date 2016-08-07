
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

ICadGenerator cadGen =new ICadGenerator(){
	@Override 
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {
		ArrayList<CSG> parts = new ArrayList<>();
		if(linkIndex==0){
			File mount = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/swivel-bracket.STL");
			CSG mountCSG  = Vitamins.get(mount)
			mountCSG.setManipulator(d.getRootListener());
			
			parts.add(mountCSG)
		}
		return parts
	}
	@Override 
	public ArrayList<CSG> generateBody(MobileBase b ) {return new ArrayList<>();}
}

return cadGen