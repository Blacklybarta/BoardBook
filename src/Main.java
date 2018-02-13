import java.sql.SQLException;

import fr.eni.ecole.boardbook.bo.Deplacement;
import fr.eni.ecole.boardbook.dal.DAOFactory;

public class Main {

	public static void main(String[] args) {
		
		Deplacement deplacement = new Deplacement();
		deplacement.setNature("Cours");
		
		try {
			DAOFactory.getDeplacementDAO().insert(deplacement);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
