package com.cc.stock.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cc.stock.model.MarketManager;
import com.cc.stock.model.MarketManagerImpl;
import com.cc.stock.model.PortfolioManager;
import com.cc.stock.model.PortfolioManagerImpl;

/**
 * Servlet implementation class MarketServlet
 */
@WebServlet("/stockMarket")
public class MarketServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init() {
		MarketManager marketManager = new MarketManagerImpl();
		marketManager.generateMarket();
	}

	// call to display market and current portfolio
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MarketManager marketManager = new MarketManagerImpl();
		HttpSession session = request.getSession();
		PortfolioManager portfolioManager = (PortfolioManager) session
				.getAttribute("portfolioManager");
		if (portfolioManager == null) {
			portfolioManager = new PortfolioManagerImpl();
			session.setAttribute("portfolioManager", portfolioManager);
		}
		request.setAttribute("Market", marketManager.getMarket());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/showMarket.jsp");
		rd.forward(request, response);
	}

	// called to end game or start over --> invalidate HTTP session 
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
			HttpSession session = request.getSession();
			PortfolioManager portfolioManager = (PortfolioManager) session
					.getAttribute("portfolioManager");
			portfolioManager.endGame();
			session.invalidate();
		doGet(request, response);
	}

}
