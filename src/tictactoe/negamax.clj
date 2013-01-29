(ns tictactoe.negamax
	(:require [tictactoe.game_rules :refer :all]
			  [tictactoe.board :refer :all]
			  [tictactoe.io :refer :all]))

(defn alternate-player-marker [player-marker]
	(if (= "x" player-marker)
		"o"
		"x"))

(defn get-score [player-marker board]
	(cond
		(= player-marker (get-winner board)) 100
		(= (alternate-player-marker player-marker) (get-winner board)) -100
		(winning-move-available? player-marker board (generate-winning-combinations)) 50
		(or (not (game-over? board)) (game-over-with-tie? board)) 0))

(defn negamax [board player-marker depth]
	(for [empty-space (empty-spaces board)
		:let[altered-board (set-marker board player-marker empty-space)]]
			(if (or (game-over? altered-board) (= depth 4))
				(int (/ (get-score player-marker altered-board) depth))
				(* -1 (apply max (flatten (negamax altered-board (alternate-player-marker player-marker) (inc depth))))))))

(defn score-empty-spaces [player-marker board]
	(for [empty-space (empty-spaces board)
		  :let [altered-board (set-marker board player-marker empty-space)]]
				(if (winning-move-available? player-marker board (generate-winning-combinations)) 
					(get-score player-marker altered-board)
					(* -1 (apply max (flatten (negamax altered-board (alternate-player-marker player-marker) 1)))))))
		
(defn get-best-move [player-marker board] 
	(key (apply max-key val (apply hash-map (interleave (empty-spaces board) (score-empty-spaces player-marker board))))))