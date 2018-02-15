<div class="menu">
<h3>Menu</h3>
<% if (String.valueOf(session.getAttribute("conducteur")).equals("true")) { %>
<%@include file="../fragments/menuUser.html" %>
<hr>
<% } %>
<% if (String.valueOf(session.getAttribute("administrateur")).equals("true")) { %>
<%@include file="../fragments/menuAdmin.html" %>
<hr>
<% } %>
<%@include file="../fragments/menuStatistiques.html" %>
</br>
<a href="/BoardBook/logout">Déconnexion</a></br>
<% if (session.getAttribute("nomUtilisateur") != null) { %>
	<p class="connecte">Connecté en tant que <%= session.getAttribute("nomUtilisateur") %></p>
<% } %>
</div>