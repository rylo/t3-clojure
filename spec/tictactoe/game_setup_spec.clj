(ns tictactoe.game-setup-spec
  (:require [speclj.core :refer :all]
			[tictactoe.game-setup :refer :all]
            [tictactoe.game :refer :all]))

(describe "game_setup"
	(context "build-new-game"
		(before-all (with-redefs [println (constantly "")]))
		(it "should return a board and a list with two computer players"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.UltimateComputer. "x") (tictactoe.player.UltimateComputer. "o")]] 
				(with-in-str "0" (build-new-game))))
		(it "should return a board and a list of one human and one computer player"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.Human. "x") (tictactoe.player.UltimateComputer. "o")]] 
				(with-in-str "1" (build-new-game))))
		(it "should return a board and a list with two human players"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")]] 
				(with-in-str "2" (build-new-game)))))
	
	(context "set-computer-difficulty"
		(it "should" (should= (tictactoe.player.UltimateComputer. "o") (with-in-str "1" (set-computer-difficulty "o"))))
	)
)

(run-specs)