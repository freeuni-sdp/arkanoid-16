package ge.edu.freeuni.sdp.arkanoid;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
import ge.edu.freeuni.sdp.arkanoid.model.*;
import ge.edu.freeuni.sdp.arkanoid.presenter.PresenterFactory;
import ge.edu.freeuni.sdp.arkanoid.view.ViewController;
import ge.edu.freeuni.sdp.arkanoid.view.terminal.TerminalViewFactory;

import java.nio.charset.Charset;
import java.util.List;

public class App {

	public static void main(String[] args) {

		Terminal terminal = getTerminal();
		Size size = getSize(terminal);

		List<Level> levels = LevelRegistry.getLevels();

		Configuration.init(size, levels);
		TerminalViewFactory viewFactory = new TerminalViewFactory(terminal);
		GameFacade gameFacade = new Game(size);
		PresenterFactory presenterFactory = new PresenterFactory(gameFacade, size);
		ViewController controller = new ViewController(viewFactory, presenterFactory);
		controller.run();
		terminal.exitPrivateMode();
	}


	private static Terminal getTerminal() {
		Terminal terminal = TerminalFacade.createTerminal(System.in,
				System.out, Charset.forName("UTF8"));
		terminal.enterPrivateMode();
		terminal.clearScreen();
		terminal.setCursorVisible(false);
		return terminal;
	}

	private static Size getSize(Terminal terminal) {
		TerminalSize terminalSize = terminal.getTerminalSize();
		int width = terminalSize.getColumns();
		int height = terminalSize.getRows();

		Size size = new Size(width, height);
		return size;
	}
}