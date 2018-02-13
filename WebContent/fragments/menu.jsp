<!-- Menu -->
<div class="menu">
<h3>Menu</h3>
<% if (utilisateur.isConducteur()) { %>
<%@include file="../fragments/menuUser.html" %>
<% } %>
<% if (utilisateur.isAdministrateur()) { %>
<%@include file="../fragments/menuAdmin.html" %>
<% } %>
<%@include file="../fragments/statistiques.html" %>
<br>
<a href="/BoardBook/logout">Déconnexion</a>
</div>