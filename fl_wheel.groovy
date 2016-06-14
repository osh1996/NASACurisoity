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
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {
		ArrayList<DHLink> dhLinks = d.getChain().getLinks()
		ArrayList<CSG> allCad=new ArrayList<CSG>()
		int i=linkIndex;
		DHLink dh = dhLinks.get(linkIndex)
		// Hardware to engineering units configuration
		LinkConfiguration conf = d.getLinkConfiguration(i);
		// Engineering units to kinematics link (limits and hardware type abstraction)
		AbstractLink abstractLink = d.getAbstractLink(i);// Transform used by the UI to render the location of the object
		// Transform used by the UI to render the location of the object
		Affine manipulator = dh.getListener();

		if (i==0){
			
			File wheel_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/steering-bracket.STL");
			CSG wheel = Vitamins.get(wheel_file)
			wheel=wheel			
					.movex(-wheel.getMaxX()/2)
					.movey(-wheel.getMaxY()/2)
					.movez(-wheel.getMaxZ()/2)
			wheel.setManipulator(manipulator)
			allCad.add(wheel)
		}
		if (i==1){
			File wheel_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/tire.STL");
			CSG wheel = Vitamins.get(wheel_file)
			wheel=wheel			
					.movex(-wheel.getMaxX()/2)
					.movey(-wheel.getMaxY()/2)
					.movez(-wheel.getMaxZ()/2)
			wheel.setManipulator(manipulator)
			allCad.add(wheel)
		}
		return allCad;
	}
	@Override 
	public ArrayList<CSG> generateBody(MobileBase b ) {return new ArrayList<>();}
};
