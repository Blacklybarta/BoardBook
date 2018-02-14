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
			<% Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur"); %>
			<% List<Utilisateur> listeUtilisateurs = (ArrayList<Utilisateur>)request.getAttribute("listeUtilisateurs"); %>
			<mark>L'utilisateur <%= session.getAttribute("nomUtilisateur") %> est connecté</mark>
			<br>
			<h3>Modification d'un employé</h3>

			<!-- Choix de l'employé à modifier -->
			<% if (utilisateur == null) { %>
				<form class="updateUser"action="/BoardBook/admin/updateUser" method="post">
				<select name="idUser">
					<% for (Utilisateur u:listeUtilisateurs) {%>

					<option value="<%= u.getId() %>"><%= u.getPrenom() + " " + u.getNom()%></option>

					<% } %>
				</select>
				<input type="hidden" name="select" value="true"/>
				<button type="submit">CHOISIR</button>
				</form>

			<!-- Modification de l'employé sélectionné -->
			<% } else { %>
			<form class="updateUser" action="/BoardBook/admin/updateUser" method="post">
				<label for="nom">Nom : </label>
				<input type="text" name="nom" value="<%= utilisateur.getNom() %>"/></br>
				<label for="prenom">Prenom : </label>
				<input type="text" name="prenom" value="<%= utilisateur.getPrenom() %>"/></br>
				<label for="identifiant">Identifiant : </label>
				<input type="text" name="idenftifiant" value="<%= utilisateur.getIdentifiant() %>"/></br>
				<label for="mdp">Prenom : </label>
				<input type="text" name="mdp" value="<%= utilisateur.getMdp() %>"/></br>

				<% // Test de la condition conducteur de l'employé %>
				<% if (utilisateur.isConducteur()) { %>
					<label for="conducteur">Conducteur</label>
					<input type="checkbox" name="conducteur" value="true" checked>
				<% } else { %>
					<label for="conducteur">Conducteur</label>
					<input type="checkbox" name="conducteur" value="true">
				<% } %>
				<% if (utilisateur.isAdministrateur()) { %>
					<label for="administrateur">Administrateur</label>
					<input type="checkbox" name="administrateur" value="true" checked></br>
				<% } else { %>
					<label for="administrateur">Administrateur</label>
					<input type="checkbox" name="administrateur" value="true"></br>
				<% } %>
				<input type="hidden" name="update" value="true"/>
				<button type="submit">MODIFIER</button>
			</form>
			<% } %>
		</div>
	</div>
	<div class="col-xs-12 col-sm-4">

		<!-- Menu -->
		<%@include file="../fragments/menu.jsp" %>
	</div>
</body>
</html>
