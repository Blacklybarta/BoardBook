<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="fr.eni.ecole.boardbook.bo.Vehicule" %>
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
			<!-- Formulaire de suppression d'un véhicule -->
			<h3>Suppression d'un vehicule</h3>
			<% List<Vehicule> listeVehicules = (ArrayList<Vehicule>)request.getAttribute("listeVehicules"); %>
			<form class="removeCar"action="/BoardBook/admin/removeCar" method="post">
				<select name="idVehicule" required>
					<option selected disabled hidden>Choisir un véhicule</option>
					<% for (Vehicule v:listeVehicules) {%>
						<option value="<%= v.getId() %>"><%= v.getMarque() + " " + v.getImmatriculation()%></option>
					<% } %>
				</select>
				<button type="submit">SUPPRIMER</button>
			</form>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4">

		<!-- Menu -->
		<%@include file="../fragments/menu.jsp" %>
	</div>
</body>
</html>
