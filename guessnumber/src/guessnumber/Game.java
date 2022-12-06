package guessnumber;

import java.util.Random;

public class Game implements IGame {
	private int guessCount;
	private int guessedNumber;
	private boolean gameOver;

	public final String MSG_OUT = "Hádané číslo je mimo povolený rozsah.";
	public final String MSG_LOWER = "Hádané číslo je menší než hledané číslo.";
	public final String MSG_GREATER = "Hádané číslo je větší než hledané číslo.";
	public final String MSG_CORRECT = "Správně! Toto je hledané číslo.";
	public final String MSG_OVER = "Nelze hádat, hra byla ukončena.";

	public final String X = "added now, edited on gh";
	/**
	 * xxx Metoda, která nastartuje novou hru. Bude vygenerováno nové číslo k hádání,
	 * počet pokusů nastaven na 0, atd...
	 *
	 * @return vygenerované číslo k hádání (využívá se při testování)
	 */
	@Override
	public int startNewGame() {
		guessCount = 0;
		gameOver = false;
		Random random = new Random();
		guessedNumber = random.nextInt(100);
		return guessedNumber;
	}

	@Override
	public void startNewGame(int newSecretNumber) {
		guessCount = 0;
		gameOver = false;
		guessedNumber = newSecretNumber;
	}

	/**
	 * Metoda pro předání tipu od hráče. Metoda vrací text, který hráči řekne, zda
	 * jeho tip je menší nebo větší než hádané číslo, případně že uhodl nebo je jeho
	 * tip mimo interval 0 - 99.
	 *
	 * @param guess tipované číslo
	 * @return odpoveď počítače např. "Hádané číslo je menší než tvůj tip"
	 */
	@Override
	public String guess(int guess) {
		if (!isGameOver()) {
			if (guess < 0 || guess >= 100) {
				return MSG_OUT;
			}

			guessCount++;
			if (guess < guessedNumber) {
				return MSG_LOWER;
			} else if (guess > guessedNumber) {
				return MSG_GREATER;
			} else {
				gameOver = true;
				return MSG_CORRECT;
			}
		} else {
			return MSG_OVER;
		}
	}

	/**
	 * Metoda vrací počet kolikrát již hráč zadal svůj tip (pokusy mimo interval se
	 * nepočítají).
	 *
	 * @return počet pokusů
	 */
	@Override
	public int getGuessCount() {
		return guessCount;
	}

	/**
	 * Metoda, která říká zda hra již skončila.
	 *
	 * @return true v případě, že hráč již číslo uhodl; jinak false
	 */
	@Override
	public boolean isGameOver() {
		return gameOver;
	}

}
