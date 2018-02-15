<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.ecole.boardbook.bo.Utilisateur" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Carnet de bord - Administrateur</title>
</head>
<body>
	<!-- Header -->
	<header>
		<p class="titre1">Carnet de bord</p>
		<p class="titre2">Administrateur</p>
	</header>
	
	<div class="col-xs-12 col-sm-8">
		<div class="contenu">
			<br>
			<% if (request.getAttribute("listeUtilisateurs") != null) { %>
				<!-- Formulaire de sélection de l'employé -->
				<% List<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>)request.getAttribute("listeUtilisateurs"); %>
	
				<form action="/BoardBook/admin/kmUtilisateurMois" method="post">
					<select name="utilisateur" required>
						<option selected disabled hidden>Choisir un employé</option>
						<% for (Utilisateur u:listeUtilisateurs) {%>
							<option value="<%= u.getId() %>"><%= u.getPrenom() + " " + u.getNom() %></option>
						<% } %>
					</select><br>
					<label for="annee">année :</label>
					<input type="number" name ="annee"/><br>
					
					<label for="moisDebut">A partir du mois de</label>
					<select name="moisDebut" required>
						<option value="1">Janvier</option>						
						<option value="2">Février</option>
						<option value="3">Mars</option>
						<option value="4">Avril</option>
						<option value="5">Mai</option>
						<option value="6">Juin</option>
						<option value="7">Juillet</option>
						<option value="8">Août</option>
						<option value="9">Septembre</option>
						<option value="10">Octobre</option>
						<option value="11">Novembre</option>
						<option value="12">Décembre</option>					
					</select><br>
					<label for="moisFin">Jusqu'au mois de</label>
					<select name="moisFin" required>
						<option value="1">Janvier</option>						
						<option value="2">Février</option>
						<option value="3">Mars</option>
						<option value="4">Avril</option>
						<option value="5">Mai</option>
						<option value="6">Juin</option>
						<option value="7">Juillet</option>
						<option value="8">Août</option>
						<option value="9">Septembre</option>
						<option value="10">Octobre</option>
						<option value="11">Novembre</option>
						<option value="12">Décembre</option>					
					</select><br>
					
					<button action="submit">AFFICHER</button>
				</form>
			<% } else {%>
				<!-- Graphique -->
				<img src="http://localhost:8080/BoardBook/admin/graphique">
			<% } %>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4">

		<!-- Menu -->
		<%@include file="../fragments/menu.jsp" %>
	</div>
</body>
</html>
