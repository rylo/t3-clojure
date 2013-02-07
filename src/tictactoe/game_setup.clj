(ns tictactoe.game-setup
	(:require [tictactoe.player :refer :all]
			  [tictactoe.board :refer [make-board]]
			  [tictactoe.io :refer :all]))
			
(defn build-new-game []
		[(make-board 9 nil)
		(case (prompt "How many human players?")
			"0" [(tictactoe.player.UltimateComputer. "x") (tictactoe.player.UltimateComputer. "o")]
			"1" [(tictactoe.player.Human. "x") (tictactoe.player.UltimateComputer. "o")]
			"2" [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")])])

(defn set-computer-difficulty [marker] 
	(case (prompt "Please select computer difficulty level:\n 1 - Easy Computer\n 2 - Ultimate Computer")
		"1" (tictactoe.player.UltimateComputer. marker)))