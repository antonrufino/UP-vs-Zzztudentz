package avs.models;

import avs.utils.Textures;

public class PlantFactory {
    public static final String KOPIKO = "KOPIKO";
    public static final String TOWER = "TOWER";
    public static final String BANGA = "BANGA";
    public static final String TC7 = "TC7";

    private String request;
    private Textures tex;

    public PlantFactory(String request, Textures tex) {
        this.request = request;
        this.tex = tex;
    }

    public Plant makePlant() {
        Plant plant;

        switch (this.request) {
            case KOPIKO:
                plant = new Kopiko(tex);
                break;

            case TOWER:
                plant = new Tower(tex);
                break;

            case BANGA:
                plant = new Banga(tex);
                break;

            case TC7:
                plant = new Tc7(tex);
                break;

            default:
                plant = null;
        }

        return plant;
    }
}
