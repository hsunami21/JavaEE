package binary.search.game.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import binary.search.game.exceptions.ExcessGuessesException;

@Named (value = "WendallStephenGameBean")
@SessionScoped
public class BinarySearch implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// constants for all games
	private static final int maxGuesses = 10;
	private static final int lowBound =0;
	// upper limit is 1001 to allow for rounding in calculating next guess
	private static final int highBound = 1001;
	
	// state of a game in progress
	private int guess;
	private int count;
	private int lowNumber;
	private int highNumber;
	private int bet = 0;
	private String nextPage;
	private List<Integer> guessList;
	
	// start a new game
	public BinarySearch() {
		count = 0;
		lowNumber = lowBound;
		highNumber = highBound;
		guessList = new ArrayList<Integer>();
	}
	
	// calculate the next guess made by computer
	public int nextGuess() throws ExcessGuessesException {	
		incrementCount();
		setGuess((lowNumber + highNumber) / 2);
		return guess;
	}

	// adjust range of number if last guess was too low
	public int tooLow() throws ExcessGuessesException {
		lowNumber = getGuess();
		guessList.add(guess);
		return nextGuess();
	}

	// adjust range of number if last guess was too high
	public int tooHigh() throws ExcessGuessesException {
		highNumber = getGuess();
		guessList.add(guess);
		return nextGuess();
	}

	// increment count and check number of guesses
	private void incrementCount() throws ExcessGuessesException {
		try {
			setCount( getCount()+1);
			setNextPage("game");
			if ( count > maxGuesses ){
				throw new ExcessGuessesException("Did you change your number mid-game or click the wrong button on a move?");
			}
		} catch (ExcessGuessesException ege) {
			FacesMessage facesMessage = new FacesMessage(ege.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, facesMessage);
			FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
			setNextPage("error");
		}
		
	}
	
	public void invalidate() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}
	
	// standard setters and guesser for JavaBean
	public int getGuess() {
		return guess;
	}

	private void setGuess(int guess) {
		this.guess = guess;
	}

	public int getCount() {
		return count;
	}
	
	private void setCount( int count) {
		this.count = count;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}
	
	public String getNextPage() {
		return nextPage;
	}

	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	public List<Integer> getGuessList() {
		return guessList;
	}

	public void setGuessList(List<Integer> guessList) {
		this.guessList = guessList;
	}
}
