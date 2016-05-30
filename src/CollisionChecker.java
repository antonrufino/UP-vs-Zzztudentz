import java.awt.image.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import avs.models.*;

public class CollisionChecker{

	public static boolean isColliding(Zombie z, ArrayList<Plant> plantList){
		
		for(int i = 0; i < plantList.size(); i++){
			if(z.getBounds().intersects(plantList.get(i).getBounds())){
				return true;
			}
		}
		
		return false;
	}
	public static boolean isColliding(Zombie z, Plant pl){
		if(z.getBounds().intersects(pl.getBounds())){
			return true;
		}
		
		return false;
	}
}