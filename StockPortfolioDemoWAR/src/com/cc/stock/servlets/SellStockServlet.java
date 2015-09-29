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

/**
 * Servlet implementation class SellStockServlet
 */
@WebServlet("/sell")
public class SellStockServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellStockServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MarketManager marketManager = new MarketManagerImpl();
		request.setAttribute("Market", marketManager.getMarket());
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/SellStock.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		MarketManager marketManager = new MarketManagerImpl();
		String command = request.getParameter("command");
		if (command != null && command.equals("cancel")) {
			request.setAttribute("Market", marketManager.getMarket());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/showMarket.jsp");
			rd.forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		PortfolioManager portfolioManager = (PortfolioManager) session
				.getAttribute("portfolioManager");
		try {
			String symbol = request.getParameter("symbol").trim().toUpperCase();
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			portfolioManager.sellStock(symbol, quantity);
			request.setAttribute("Market", marketManager.getMarket());
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/showMarket.jsp");
			rd.forward(request, response);
		} catch (Exception e) {
			request.setAttribute("exception", e);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/StockError.jsp");
			rd.forward(request, response);
		}
	}
}
