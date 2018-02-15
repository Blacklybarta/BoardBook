<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.ecole.boardbook.bo.Lieu" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Deplacement" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Vehicule" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Utilisateur" %>
<%@ page import="fr.eni.ecole.boardbook.bo.Fiche" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
	
	<div class="col-xs-12 col-sm-8">
			<!-- Affichage des fiches de l'utilisateur -->
			<div class="contenu">
				<br>
				<% List<Fiche> listeFiches = (ArrayList<Fiche>)request.getAttribute("listeFiches"); %>
				<% for (Fiche f:listeFiches) { %>
				<div class="recap">
					<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>
					<h3>Deplacement du <%= df.format(f.getDateDepart().getTime()) %> au <%= df.format(f.getDateCloture().getTime()) %></h3>
						<label for="depart">Depart</label>
						<input name="depart" type="text" value="<%= f.getLieuDepart().getNom() %>" disabled/></br>
						<label for="destination">Destination</label>
						<input name="destination" type="text" value="<%= f.getLieuArrivee().getNom() %>" disabled/></br>
						<label for="nature">Nature du déplacement</label>
						<input name="nature" type="text" value="<%= f.getNatureDeplacement().getNature() %>" disabled/></br>
						<label for="vehicule">Véhicule</label>
						<input name="vehicule" type="text" value="<%= f.getVehiculeLoue().getMarque() + " " +  f.getVehiculeLoue().getImmatriculation() %>" disabled/></br>
						<label for="nbKmEntree">Kilometrage au départ</label>
						<input name="nbKmEntree" type="text" value="<%= f.getNbKmEntree() %>" disabled/></br>
						<label for="nbKmSortie">Kilometrage à l'arrivée</label>
						<input type="number" name="nbKmSortie" value="<%= f.getNbKmSortie()%>"disabled/></br>
						<label for="commentaire">Commentaire</label>
						<textarea name="commentaire" rows="1" cols="40"><%= f.getCommentaire() %></textarea></br>
					</div>	
				<% } %>
				
			</div>
		</div>
		<div class="col-xs-12 col-sm-4">

			<!-- Menu -->
			<%@include file="../fragments/menu.jsp" %>
		</div>
</body>
</html>
