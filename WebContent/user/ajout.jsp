<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.ecole.boardbook.bo.Lieu" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Deplacement" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Vehicule" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Utilisateur" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="<%=request.getContextPath()%>/style.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Carnet de bord - Utilisateur</title>
</head>
<body>
	<!-- Header -->
	<header>
		<p class="titre1">Carnet de bord</p>
		<p class="titre2">Utilisateur</p>
	</header>
	
	<div class="col-xs-12 col-sm-9">
			<!-- Formulaire d'ajout d'un déplacement -->
			<div class="contenu">
				<br>
				<h3>Création d'un déplacement</h3>
					<form class="ajout" action="/BoardBook/user/ajout" method="post">
						
						<% List<Lieu> listeLieux = (ArrayList<Lieu>)request.getAttribute("listeLieux"); %>
						<label for="idDestination">Destination</label>
						<select name="idDestination" required>
							<option selected disabled hidden>Choisir une destination</option>
							<% for (Lieu l:listeLieux) {%>
								<% if (l.isActif()) {%>
									<option value="<%= l.getId() %>"><%= l.getNom() %></option>
								<% } %>
							<% } %>
						</select></br>
						<label for="idReception">Lieu de réception du véhicule</label>
						<select name="idReception" required>
							<option selected disabled hidden>Choisir un lieu de réception</option>
							<% for (Lieu l:listeLieux) {%>
								<% if (l.isActif()) {%>
									<option value="<%= l.getId() %>"><%= l.getNom() %></option>
								<% } %>
							<% } %>
						</select></br>
						<% List<Deplacement> listeTypes = (ArrayList<Deplacement>)request.getAttribute("listeTypes"); %>
						<label for="idType">Deplacement</label>
						<select name="idType" required>
							<option selected disabled hidden>Choisir une nature de déplacement</option>
							<% for (Deplacement d:listeTypes) {%>
								<% if (d.isActif()) { %>
									<option value="<%= d.getId() %>"><%= d.getNature() %></option>
								<% } %>
							<% } %>
						</select></br>
						<% List<Vehicule> listeVehicules = (ArrayList<Vehicule>)request.getAttribute("listeVehicules"); %>
						<label for="idVehicule">Vehicule</label>
						<select name="idVehicule" required>
							<option selected disabled hidden>Choisir un véhicule</option>
							<% for (Vehicule v:listeVehicules) {%>
								<% if (v.isDisponible()) { %>
									<option value="<%= v.getId() %>"><%= v.getMarque() + " " + v.getImmatriculation()%></option>
								<% } %>
							<% } %>
						</select></br>
						<label for="nbKmVehicule">Kilometrage du véhicule</label>
						<input type="number" name="nbKmVehicule" required/>
						<% List<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>)request.getAttribute("listeUtilisateurs"); %>
						<label for="listeConducteurs">Conducteurs supplémentaires (9 max)</label>
						<select name="listeConducteurs" size="5" multiple>
							<option value="aucun">Aucun</option>
							<% for (Utilisateur u:listeUtilisateurs) {%>
								<% if (u.getNom() != session.getAttribute("nomUtilisateur")) {%>
									<option value="<%= u.getId() %>"><%= u.getPrenom() + " " + u.getNom() %></option>
								<% } %>
							<% } %>
						</select></br>
						<label for="commentaire">Commentaire</label>
						<textarea name="commentaire" rows="1" cols="40"></textarea></br>
						<button type="submit">VALIDER LA CREATION</button>
					</form>
			</div>
		</div>
		<div class="col-xs-12 col-sm-3">

			<!-- Menu -->
			<%@include file="../fragments/menu.jsp" %>
		</div>
</body>
</html>
