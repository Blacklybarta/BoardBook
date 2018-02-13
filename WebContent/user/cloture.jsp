<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<!-- Formulaire d'ajout d'un type de déplacement -->
			<div class="contenu">
				<mark>L'utilisateur Arthur est connecté en tant qu'utilisateur</mark>
				<br>
				<h3>Clôture d'un déplacement</h3>
					<div class="col-xs-12">
						<div class="deplacementEnCours">
							Déplacement en cours
							<form class="clotureDeplacement" action="/BoardBook/user/cloture" method="post">
								<label for="destination">Destination : </label>
								<input name="destination" type="text" value="" disabled="disabled"></br>
								<label for="dateDepart">Date de départ : </label>
								<input name="dateDepart" type="text" value="" disabled="disabled"></br>
								<label for="conducteurs">Conducteur(s) : </label>
								<input name="conducteurs" type="text" value="" disabled="disabled"></br>
							</form>
						</div>
					</div>
			</div>
		</div>
		<div class="col-xs-12 col-sm-4">

			<!-- Menu -->
			<div class="menu">
				<h3>Menu</h3>
				<!-- Menu admin -->
				<%@include file="../fragments/menuUser.html" %>
				<!-- Menu user -->
				<%@include file="../fragments/menuAdmin.html" %>
				<br>
				<a href="/BoardBook/accueil">Déconnexion</a>
			</div>
		</div>
</body>
</html>
