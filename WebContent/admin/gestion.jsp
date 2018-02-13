<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.ecole.boardbook.bo.Utilisateur" %>
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
		
			<!-- Présentation des possibilités de l'administrateur -->
			<div class="contenu">
			<% Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur"); %>
				<mark>L'utilisateur <% utilisateur.getNom(); %> est connecté en tant qu'administrateur</mark>
				<br>
				<br>
				En tant qu'admnistrateur vous pouvez :<br>
					<ul>
						<li>Ajouter ou supprimer un conducteur</li>
						<li>Ajouter ou supprimer une destination</li>
						<li>Ajouter ou supprimer un véhicule</li>
						<li>Ajouter ou supprimer un type de déplacement</li>
					</ul>
			</div>
		</div>
		<div class="col-xs-12 col-sm-4">

			<!-- Menu -->
			<div class="menu">
				<h3>Menu</h3>
				<%@include file="../fragments/menuUser.html" %>
				<%@include file="../fragments/menuAdmin.html" %>
				<br>
				<a href="/BoardBook/accueil">Déconnexion</a>
			</div>
		</div>
</body>
</html>