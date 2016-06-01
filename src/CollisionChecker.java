package avs.utils; 

import java.awt.image.*;
import java.awt.*;
import java.util.*;
import javax.swing.*;
import avs.models.*;

/************************************************************************
	This class will be responsible for checking if there is a collosion
	between a zombie and other entities.
*************************************************************************/

public class CollisionChecker{
	public static boolean isColliding(Entity z, ArrayList<Entity> entityList){

		for(int i = 0; i < entityList.size(); i++){
			if(z.getBounds().intersects(entityList.get(i).getBounds())){
				return true;
			}
		}

		return false;
	}
	public static boolean isColliding(Entity z, Entity ent){
		if(z.getBounds().intersects(ent.getBounds())){
			return true;
		}

		return false;
	}
}
