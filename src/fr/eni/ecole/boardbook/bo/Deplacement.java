package fr.eni.ecole.boardbook.bo;

public class Deplacement {
	private int id;
	private String nature;
	
	public Deplacement (){
		
	}
	
	public Deplacement(String nature) {
		this.setNature(nature);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	@Override
	public String toString() {
		return "Deplacement [id=" + id + ", nature=" + nature + "]";
	}
	
	
	
	
}
