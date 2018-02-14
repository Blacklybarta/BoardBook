<div class="menu">
<h3>Menu</h3>
<% if ((boolean)session.getAttribute("conducteur")) { %>
<%@include file="../fragments/menuUser.html" %>
<% } %>
<% if ((boolean)session.getAttribute("administrateur")) { %>
<%@include file="../fragments/menuAdmin.html" %>
<% } %>
<%@include file="../fragments/statistiques.html" %>
</br>
<a href="/BoardBook/logout">Déconnexion</a>
</div>