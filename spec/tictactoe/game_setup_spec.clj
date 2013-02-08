(ns tictactoe.game-setup-spec
  (:require [speclj.core :refer :all]
			[tictactoe.game-setup :refer :all]
            [tictactoe.game :refer :all]))

(describe "game_setup"
	; (around [nothing] (with-redefs [println (constantly "")])) ; why does this make all tests always pass?

	(context "build-new-game"
		(it "should return a board and a list with two computer players"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.UltimateComputer. "x") (tictactoe.player.UltimateComputer. "x")]] 
				(with-redefs [get-computer-difficulty (constantly (tictactoe.player.UltimateComputer. "x"))] 
					(with-in-str "0" (build-new-game)))))
		(it "should return a board and a list of one human and one computer player"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.Human. "x") (tictactoe.player.UltimateComputer. "x")]] 
				(with-redefs [get-computer-difficulty (constantly (tictactoe.player.UltimateComputer. "x"))] 
					(with-in-str "1" (build-new-game)))))
		(it "should return a board and a list with two human players"
			(should= [[nil nil nil nil nil nil nil nil nil] [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")]] 
				(with-redefs [get-computer-difficulty (constantly (tictactoe.player.UltimateComputer. "x"))] 
					(with-in-str "2" (build-new-game))))))
	
	(context "get-computer-difficulty"
		(it "should set the computer player difficulty to easy" 
			(should= (tictactoe.player.EasyComputer. "o") (with-in-str "1" (get-computer-difficulty "o"))))
		(it "should set the computer player difficulty to easy" 
			(should= (tictactoe.player.UltimateComputer. "o") (with-in-str "2" (get-computer-difficulty "o"))))))

(run-specs)