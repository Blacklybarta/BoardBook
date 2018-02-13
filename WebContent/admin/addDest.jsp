<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="../style.css">
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
			<!-- Formulaire de cr�ation d'une destination -->
			<div class="contenu">
				<mark>L'utilisateur Arthur est connect� en tant qu'administrateur</mark>
				<br>
				<h3>Cr�ation d'une destination</h3>
					<form class="addDest" action="/BoardBook/admin/addDest" method="post">
						<label for="nom">Nouvelle destination :</label>
						<input name="nom"></br>
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
