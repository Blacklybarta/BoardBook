<div class="menu">
<h3>Menu</h3>
<% if (String.valueOf(session.getAttribute("conducteur")).equals("true")) { %>
<%@include file="../fragments/menuUser.html" %>
<% } %>
<% if (String.valueOf(session.getAttribute("administrateur")).equals("true")) { %>
<%@include file="../fragments/menuAdmin.html" %>
<% } %>
<%@include file="../fragments/menuStatistiques.html" %>
</br>
<a href="/BoardBook/logout">Déconnexion</a>
</div>