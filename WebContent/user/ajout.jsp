<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../style.css">
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
			<!-- Formulaire d'ajout d'un type de d�placement -->
			<div class="contenu">
				<mark>L'utilisateur Arthur est connect� en tant qu'utilisateur</mark>
				<br>
				<h3>Cr�ation d'un d�placement</h3>
					<form class="ajout" action="/BoardBook/user/ajout" method="post">
						<label for="destination">Destination : </label>
						<select name="destination">
							<option>option 1
							<option>option 2
							<option>option 3
						</select></br>
						<label for="lieuReception">Lieu de r�ception du v�hicule : </label>
						<select name="lieuReception">
							<option>option 1
							<option>option 2
							<option>option 3
						</select></br>
						<label for="nature">Nature du d�placement : </label>
						<select name="nature">
							<option>option 1
							<option>option 2
							<option>option 3
						</select></br>
						<label for="vehicule">V�hicule : </label>
						<select name="vehicule">
							<option>option 1
							<option>option 2
							<option>option 3
						</select></br>
						<button type="submit">VALIDER LA CREATION</button>
					</form>
			</div>
		</div>
		<div class="col-xs-12 col-sm-4">

			<!-- Menu -->
			<div class="menu">
				<h3>Menu</h3>
				<%@include file="../fragments/menuUser.html" %>
				<%@include file="../fragments/menuAdmin.html" %>
				<br>
				<a href="/BoardBook/accueil">D�connexion</a>
			</div>
		</div>
</body>
</html>
