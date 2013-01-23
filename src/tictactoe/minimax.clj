(ns tictactoe.minimax
	(:require [tictactoe.game_rules :refer :all]
			  [tictactoe.board :refer :all]))

;write test for this!
(defn alternate-player-marker [player-marker]
	(if (= "x" player-marker)
		"o"
		"x"))

(defn get-score [player-marker board]
	(if (or (game-over-with-tie? board) (game-not-over? board))
		0
		(if (= player-marker (get-winner board))
			 100
			-100)))

(defn minimax [board player-marker score]
	(if (game-over? board)
		(get-score player-marker board)
			(loop [other-player-marker (alternate-player-marker player-marker)
				   empty-spaces (empty-spaces board)
				   altered-board (set-marker board other-player-marker (first empty-spaces))
				   current-score (get-score other-player-marker altered-board)]
				(if (= 0 (count empty-spaces))
				current-score
				(do (minimax altered-board other-player-marker current-score) 
					(recur other-player-marker (rest empty-spaces) altered-board current-score))))))

(defn score-empty-spots [player-marker board]
	(for [empty-space (empty-spaces board)
		  :let [altered-board (set-marker board player-marker empty-space) 
				score (get-score player-marker altered-board)]] 
				(* -1 (minimax altered-board (alternate-player-marker player-marker) score))))
		
(defn get-best-move [player-marker board] 
	(key (apply max-key val (apply hash-map (interleave (empty-spaces board) (score-empty-spots player-marker board))))))

	; score-empty-spots 
		; loop through empty spaces
		   ; make a move on empty space with computer marker
		   ; delegate to minimax with human player marker to get score for game's end state
	
	; minimax
		; return current player score if the game is over with current board
	    ; loop through empty spaces
	       ; set other player marker on each empty space
	       ; recur on altered board