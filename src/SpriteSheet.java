package avs.utils; 

import java.awt.image.BufferedImage;

/************************************************************************
	This class will be responsible for loading the subimages from 
	the spritesheet.
*************************************************************************/

public class SpriteSheet{
	private BufferedImage image;
	private BufferedImage img;

	public final int H_OFFSET = 126;
	public final int V_OFFSET = 105;

	public SpriteSheet(BufferedImage image){
		this.image = image;
	}

	public BufferedImage grabImage(int col, int row, int width, int height){ //Grabs images from sprite sheet
		if(row == 1){ //DLTB Sprites
			this.img = image.getSubimage((H_OFFSET*col)+(width*(col-1)), V_OFFSET, width, height);
		}
		else if(row == 2){ //BANGA Sprites
			if(col < 10){
				this.img = image.getSubimage((H_OFFSET*col)+(width*(col-1)), (V_OFFSET*2)+Textures.BUS_HEIGHT, width, height);
			}
			else if(col >= 10 && col < 13){ //TC7 Sprites
				this.img = image.getSubimage((123*col)+(Textures.BANGA_WIDTH*9)+(width*(col%10)), 1059, width, height);

			}
			else if(col >= 13 && col < 17){ //TOWER Sprites
				this.img = image.getSubimage((123*col)+(Textures.BANGA_WIDTH*9)+(Textures.TC7_WIDTH*3)+(width*(col%13)), (V_OFFSET*2)+Textures.BUS_HEIGHT+(Textures.BANGA_HEIGHT-Textures.TOWER_HEIGHT), width, height);
			}

			else if(col == 17){ //KWEK KWEK SPRITES
				this.img = image.getSubimage((H_OFFSET*col)+(Textures.BANGA_WIDTH*9)+(Textures.TC7_WIDTH*3)+(Textures.TOWER_WIDTH*4),(V_OFFSET*2)+Textures.BUS_HEIGHT+(Textures.BANGA_HEIGHT-Textures.TOWER_HEIGHT), width, height);
			}
			else if(col >= 18 && col < 23){ //ZOMBIE SPRITES
				this.img = image.getSubimage((H_OFFSET*col)+(Textures.BANGA_WIDTH*9)+(Textures.TC7_WIDTH*3)+(Textures.TOWER_WIDTH*5)+(Textures.ZOMBIE_WIDTH*(col%18))-40,(V_OFFSET*2)+Textures.BUS_HEIGHT, width, height);
			}
		}
			else if(row == 3){
			if(col != 7){ //KOPIKO SPRITES
				this.img = image.getSubimage(((H_OFFSET)*col)+((Textures.KOPIKO_WIDTH-1)*(col-1)), (V_OFFSET*row)+Textures.BUS_HEIGHT+Textures.BANGA_HEIGHT, width, height);
			}
			else{ //ENERGY SPRITES
				this.img = image.getSubimage((H_OFFSET*col)+(Textures.KOPIKO_WIDTH*(col-1)), (V_OFFSET*row) + Textures.BUS_HEIGHT + Textures.BANGA_HEIGHT + (Textures.KOPIKO_HEIGHT - Textures.ENERGY_HEIGHT), width, height);
			}
		}
        return this.img;
    }
}
