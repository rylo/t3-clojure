(ns tictactoe.game-setup
	(:require [tictactoe.player :refer :all]
			  [tictactoe.board :refer [make-board]]
			  [tictactoe.io :refer :all]))
	
(defn get-computer-difficulty [marker] 
	(case (read-string (prompt (str "Please select computer difficulty level for computer \"" marker "\":\n  1 - Easy Computer\n  2 - Ultimate Computer")))
		1 (tictactoe.player.EasyComputer. marker)
		2 (tictactoe.player.UltimateComputer. marker)
		(get-computer-difficulty marker)))

(defn get-player-config []	
	(case (prompt "How many human players?")
			"0" [(get-computer-difficulty "x") (get-computer-difficulty "o")]
			"1" [(tictactoe.player.Human. "x") (get-computer-difficulty "o")]
			"2" [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")]
			(get-player-config)))

(defn build-new-game []
		[(make-board 9 nil)
		(get-player-config)])