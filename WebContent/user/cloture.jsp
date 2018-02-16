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
			<!-- Formulaire de cloture d'un déplacement -->
			<div class="contenu">
				<br>
				<% if(request.getAttribute("redirection") != null){%>
				<p class="error"><%= request.getAttribute("redirection") %></p>
				<%} %>
					
				
				<h3>Clôture d'un déplacement</h3>
				<% DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); %>
				<% Fiche fiche = (Fiche)request.getAttribute("fiche"); %>
					<form class="cloture" action="/BoardBook/user/cloture" method="post">
						<label for="dateDepart">Date de départ</label>
						<input name="dateDepart" type="text" value="<%= df.format(fiche.getDateDepart().getTime()) %>" disabled/></br>
						<label for="depart">Depart</label>
						<input name="depart" type="text" value="<%= fiche.getLieuDepart().getNom() %>" disabled/></br>
						<label for="destination">Destination</label>
						<input name="destination" type="text" value="<%= fiche.getLieuArrivee().getNom() %>" disabled/></br>
						<label for="nature">Nature du déplacement</label>
						<input name="nature" type="text" value="<%= fiche.getNatureDeplacement().getNature() %>" disabled/></br>
						<label for="vehicule">Véhicule</label>
						<input name="vehicule" type="text" value="<%= fiche.getVehiculeLoue().getMarque() + " " +  fiche.getVehiculeLoue().getImmatriculation() %>" disabled/></br>
						<label for="commentaire">Commentaire</label>
						<textarea name="commentaire" rows="1" cols="40" disabled><%= fiche.getCommentaire() %></textarea></br>
						<label for="nbKmEntree">Kilometrage au départ</label>						
						<input name="nbKmEntree" type="text" value="<%= fiche.getNbKmEntree() %>" disabled/></br>
						<label for="nbKmSortie">Kilometrage à l'arrivée</label>
						<input type="number" name="nbKmSortie" required/></br>
						<label for="carburantNbLitre">Nombre de litres mis au réservoir </label>
						<input type="number" name="carburantNbLitre" required/></br>
						<label for="carburantMontant">Montant du plein </label>
						<input type="number" name="carburantMontant" required/></br>
						<input type="hidden" name="idFiche" value="<%= fiche.getId() %>"/>
						<input type="hidden" name="dateDepart" value="<%= df.format(fiche.getDateDepart().getTime())%>"/>
						<button type="submit">CLOTURER</button>
					</form>
			</div>
		</div>
		<div class="col-xs-12 col-sm-4">

			<!-- Menu -->
			<%@include file="../fragments/menu.jsp" %>
		</div>
</body>
</html>
