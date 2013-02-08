(ns tictactoe.game-setup
	(:require [tictactoe.player :refer :all]
			  [tictactoe.board :refer [make-board]]
			  [tictactoe.io :refer :all]))
	
(defn get-computer-difficulty [marker] 
	(case (prompt (str "Please select a difficulty level for AI \"" marker "\":\n  1 - Easy Computer\n  2 - Ultimate Computer"))
		"1" (tictactoe.player.EasyComputer. marker)
		"2" (tictactoe.player.UltimateComputer. marker)
		(get-computer-difficulty marker)))

(defn get-player-config []	
	(case (prompt "How many human players?")
			"0" [(get-computer-difficulty "x") (get-computer-difficulty "o")]
			"1" [(tictactoe.player.Human. "x") (get-computer-difficulty "o")]
			"2" [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")]
			(get-player-config)))

(defn set-first-player [player-list] 
	(case (prompt (str "Who goes first?\n  1 - " (class (first player-list)) " \"" (marker (first player-list)) "\"\n  2 - " (class (last player-list)) " \"" (marker (last player-list)) "\""))
		"1" player-list
		"2" [(last player-list) (first player-list)]
		(set-first-player player-list)))

(defn build-new-game []
	[(make-board 9 nil) (set-first-player (get-player-config))])