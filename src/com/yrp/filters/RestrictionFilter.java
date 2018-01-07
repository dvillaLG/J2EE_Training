package com.yrp.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RestrictionFilter implements Filter {
	private static final String ACCES_PUBLIC     = "/accesPublic.jsp";
    private static final String ATT_SESSION_USER = "sessionUtilisateur";
	
	public void init(FilterConfig fConfig) throws ServletException {
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		/* Cast des objets request et response */
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        
        /* Non-filtrage des ressources statiques */
        String chemin = req.getRequestURI().substring( req.getContextPath().length() );
        if ( chemin.startsWith( "/inc" ) ) {
            chain.doFilter( request, response );
            return;
        }
        
        /* Récupération de la session depuis la requête */
        HttpSession session = req.getSession();

        /**
         * Si l'objet utilisateur n'existe pas dans la session en cours, alors
         * l'utilisateur n'est pas connecté.
         */
        if ( session.getAttribute( ATT_SESSION_USER ) == null ) {
            /* Redirection vers la page publique */
            resp.sendRedirect( req.getContextPath() + ACCES_PUBLIC );
        } else {
            /* Affichage de la page restreinte */
            chain.doFilter( req, resp );
        }
	}
	
	public void destroy() {
	
	}
}
