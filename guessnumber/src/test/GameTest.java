package test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import guessnumber.Game;

public class GameTest {
	Game game;

	@Before
	public void setUp() throws Exception {
		game = new Game();
		game.startNewGame(50);
	}

	@Test
	public void testStartNewGame() {
		// fail("Not yet implemented");
	}

	@Test
	public void testStartNewGameInt() {
		Assert.assertEquals("spatne nastaveno pocatecni guessCount", 0, game.getGuessCount());
		Assert.assertFalse("spatne nastaveno pocatecni gameOver", game.isGameOver());
	}

	@Test
	public void testGuess() {
		Assert.assertTrue("spatna hlaska - hadane cislo mensi nez hledane", game.guess(25).equals(game.MSG_LOWER));
		Assert.assertTrue("spatna hlaska - hadane cislo mensi nez hledane", game.guess(0).equals(game.MSG_LOWER));
		Assert.assertTrue("spatna hlaska - hadane cislo mimo povoleny rozsah", game.guess(-1).equals(game.MSG_OUT));
		Assert.assertTrue("spatna hlaska - hadane cislo mimo povoleny rozsah", game.guess(100).equals(game.MSG_OUT));
		Assert.assertTrue("spatna hlaska - hadane cislo vetsi nez hledane", game.guess(70).equals(game.MSG_GREATER));
		Assert.assertTrue("spatna hlaska - hadane cislo je spravne", game.guess(50).equals(game.MSG_CORRECT));
		Assert.assertTrue("spatna hlaska - nelze pokracovat v hadani", game.guess(51).equals(game.MSG_OVER));
	}

	@Test
	public void testGetGuessCount() {
		Assert.assertEquals("spatne pocet tipu - ma byt inicialni 0", 0, game.getGuessCount());
		game.guess(0);
		Assert.assertEquals("spatne pocet tipu - ma byt navyseno na 1", 1, game.getGuessCount());
		game.guess(1);
		Assert.assertEquals("spatne pocet tipu - ma byt navyseno na 2", 2, game.getGuessCount());
		game.guess(-1);
		Assert.assertEquals("spatne pocet tipu - tip mimo rozsah, mela zustat 2", 2, game.getGuessCount());
		game.guess(100);
		Assert.assertEquals("spatne pocet tipu - tip mimo rozsah, mela zustat 2", 2, game.getGuessCount());
		game.guess(50);
		Assert.assertEquals("spatne pocet tipu - ma byt navyseno na 3", 3, game.getGuessCount());
		game.guess(80);
		Assert.assertEquals("spatne pocet tipu - hadani bylo ukonceno, mela zustat 3", 3, game.getGuessCount());
	}

	@Test
	public void testIsGameOver() {
		Assert.assertFalse("spatne nastaveno pocatecni gameOver", game.isGameOver());
		game.guess(0);
		Assert.assertFalse("spatne nastaveno pocatecni gameOver", game.isGameOver());
		game.guess(1);
		Assert.assertFalse("spatne nastaveno pocatecni gameOver", game.isGameOver());
		game.guess(-1);
		Assert.assertFalse("spatne nastaveno pocatecni gameOver", game.isGameOver());
		game.guess(100);
		Assert.assertFalse("spatne nastaveno pocatecni gameOver", game.isGameOver());
		game.guess(50);
		Assert.assertTrue("spatne nastaveno pocatecni gameOver", game.isGameOver());
		game.guess(80);
		Assert.assertTrue("spatne nastaveno pocatecni gameOver", game.isGameOver());
	}

}
