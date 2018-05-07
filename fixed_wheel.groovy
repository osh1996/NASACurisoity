import com.neuronrobotics.bowlerstudio.creature.ICadGenerator;
import com.neuronrobotics.bowlerstudio.creature.CreatureLab;
import org.apache.commons.io.IOUtils;
import com.neuronrobotics.bowlerstudio.vitamins.*;
import java.nio.file.Paths;
import eu.mihosoft.vrl.v3d.FileUtil;
import com.neuronrobotics.bowlerstudio.vitamins.*;
import javafx.scene.transform.*;
println "Loading STL file"
// Load an STL file from a git repo
// Loading a local file also works here

return new ICadGenerator(){
	@Override 
	public ArrayList<CSG> generateCad(DHParameterKinematics d, int linkIndex) {
		ArrayList<DHLink> dhLinks = d.getChain().getLinks()
		ArrayList<CSG> allCad=new ArrayList<CSG>()
	
		DHLink dh = dhLinks.get(linkIndex)
		// Hardware to engineering units configuration
		LinkConfiguration conf = d.getLinkConfiguration(linkIndex);
		// Engineering units to kinematics link (limits and hardware type abstraction)
		AbstractLink abstractLink = d.getAbstractLink(linkIndex);// Transform used by the UI to render the location of the object
		// Transform used by the UI to render the location of the object
		Affine manipulator = dh.getListener();


		if (linkIndex==0){
			File wheel_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/wheel.STL");
			File tire_file = ScriptingEngine.fileFromGit(
			"https://github.com/NeuronRobotics/NASACurisoity.git",
			"STL/tire.STL");
  
			CSG wheel = Vitamins.get(wheel_file)
			wheel=wheel			
					.movex(-2*dh.getR()+2.37920)
					.movey(-dh.getR()+0.5- 2.10190)
					.movez(-6.5-3.95063)
					.rotx(90)
			print "FIXED WHEEL CENTER "
			println wheel.getCenter() 		
			//println wheel.metaClass.methods*.name.sort().unique() 		
			wheel.setManipulator(manipulator)
			
			allCad.add(wheel)
			
			CSG tire = Vitamins.get(tire_file)
					.movex(-dh.getR())
					.movez(-dh.getD())
			print "FIXED TIRE CENTER "
			println tire.getCenter()
			tire.setManipulator(manipulator)

			allCad.add(tire)
		}
		for(int i=0;i<allCad.size();i++){
			allCad.get(i).setColor(javafx.scene.paint.Color.GRAY)
		}
		return allCad;
	}
	@Override 
	public ArrayList<CSG> generateBody(MobileBase b ) {return new ArrayList<>();}
};
