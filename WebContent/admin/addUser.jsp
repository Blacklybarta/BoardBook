<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
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
		<div class="contenu">
			<% Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur"); %>
			<mark>L'utilisateur <%= utilisateur.getNom() %> est connecté en tant qu'administrateur</mark>
			<br>
				
			<!-- Formulaire de création d'un conducteur -->
			<h3>Création d'un compte conducteur</h3>
			<form class="addUser" action="/BoardBook/admin/addUser" method="post">
				<label for="nom">Nom :</label>
				<input name="nom"></br>
				<label for="prenom">Prénom :</label>
				<input name="prenom"></br>
				<label for="identifiant">Identifiant :</label>
				<input name="identifiant"></br>
				<label for="mdp">Mot de passe :</label>
				<input name="mdp" type="password"></br>
				<label for="conducteur">Conducteur</label>
				<input type="checkbox" name="conducteur" value="true">
				<label for="administrateur">Administrateur</label>
				<input type="checkbox" name="administrateur" value="true"></br>
				<button type="submit">VALIDER LA CREATION</button>
			</form>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4">
	
		<!-- Menu -->
		<%@include file="../fragments/menu.jsp" %>
	</div>
</body>
</html>
