(ns tictactoe.negamax
	(:require [tictactoe.game_rules :refer :all]
			  [tictactoe.board :refer :all]
			  [tictactoe.io :refer :all]))

(defn alternate-player-marker [player-marker]
	(if (= "x" player-marker)
		"o"
		"x"))

(defn get-score [player-marker board]
	(if (game-over-with-tie? board)
		0
		(if (= player-marker (get-winner board))
			 100
			-100)))

(defn negamax [board player-marker depth]
	(for [empty-space (empty-spaces board)
		:let[altered-board (set-marker board player-marker empty-space)]]
			(if (or (game-over? altered-board) (= depth 5))
				(int (/ (get-score player-marker altered-board) depth))
				(* -1 (apply max (flatten (negamax altered-board (alternate-player-marker player-marker) (inc depth))))))))

(defn score-empty-spaces [player-marker board]
	(for [empty-space (empty-spaces board)
		  :let [altered-board (set-marker board player-marker empty-space)]]
				(* -1 (apply max (flatten (negamax altered-board (alternate-player-marker player-marker) 1))))))
		
(defn get-best-move [player-marker board] 
	(key (apply max-key val (apply hash-map (interleave (empty-spaces board) (score-empty-spaces player-marker board))))))