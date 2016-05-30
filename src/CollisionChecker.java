import java.awt.image.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;

public class CollisionChecker{

	public static boolean isColliding(Zombie z, ArrayList<AllyEntity> allyList){
		
		for(int i = 0; i < allyList.size(); i++){
			if(z.getBounds().intersects(allyList.get(i).getBounds())){
				return true;
			}
		}
		
		return false;
	}
	public static boolean isColliding(Zombie z, AllyEntity all){
		if(z.getBounds().intersects(all.getBounds())){
			return true;
		}
		
		return false;
	}
}