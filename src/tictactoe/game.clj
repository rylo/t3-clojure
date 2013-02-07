(ns tictactoe.game
	(:require [tictactoe.player :refer [get-move]]
			  [tictactoe.board :refer [set-marker]]
			  [tictactoe.io :refer [print-output printable-board]]
			  [tictactoe.game-rules :refer [get-winner winning-row-present? game-over-with-tie? valid-move?]]
			  [tictactoe.game-setup :refer [build-new-game]]))
			
(defn alternate-players [player-list current-player] 
	(if (= (nth player-list 0) current-player)
		(nth player-list 1)
		(nth player-list 0)))

(defn get-ending-message [board] 
	(if (game-over-with-tie? board)
		"It's a tie!"
		(format "Player %s wins!" (get-winner board))))

(defn start [new-game]
	(let [board (first new-game) player-list (last new-game)]
		(loop [current-player (first player-list) board board]	
			(print-output (printable-board board))
			(let [altered-board (set-marker board (:marker current-player) (get-move current-player board))]
				(if (or (winning-row-present? altered-board) (game-over-with-tie? altered-board))
					(do (print-output (get-ending-message altered-board)) (print-output (printable-board altered-board)))
					(recur (alternate-players player-list current-player) altered-board))))))

(defn set-up-new-game []
	(print-output "Welcome to Tic Tac Toe!")
		(start (build-new-game)))