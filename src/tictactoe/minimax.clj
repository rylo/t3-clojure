(ns tictactoe.minimax
	(:require [tictactoe.board :refer [get-marker get-markers row-taken? winning-row-present? generate-rows set-marker full?]]
			  [tictactoe.game_rules :refer :all]
			  [tictactoe.game :refer :all]))

(defn get-score [player-marker board]
	(if (or (game-over-with-tie? board) (not (game-won? board)))
		0
		(if (= player-marker (get-winner board))
			 100
			-100)))
			
(defn get-best-moves [player-marker board]
	(let [highest-score 0]
		(for [empty-space (empty-spaces board)
			  :let [altered-board (set-marker board player-marker empty-space) score (get-score player-marker altered-board)]
			  :when (>= score highest-score)
			] (get-best-moves (alternate-players player-marker) altered-board ))))
		
(defn end-of-tree? [board]
	(or (game-won? board) (game-over-with-tie? board)))