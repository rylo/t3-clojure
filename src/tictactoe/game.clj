(ns tictactoe.game
	(:require [tictactoe.player :refer :all]
			  [tictactoe.board :refer [printable-board make-board get-marker winning-row-present? generate-rows set-marker]]
			  [tictactoe.io :refer [prompt set-output]]
			  [tictactoe.game_rules :refer [game-over? valid-move?]]))
			
(defn alternate-players [player-list current-player] (if (= "x" (marker current-player)) (nth player-list 1) (nth player-list 0)))

(defn start [board player-list]
	(loop [current-player (first player-list) board board]	
		(set-output (printable-board board))
		(let [altered-board (set-marker board (:marker current-player) (get-move current-player board))]
			(if (game-over? altered-board)
				"game over"
				(recur (alternate-players player-list current-player) altered-board)))))

(defn set-up-new-game []
	(let [board (make-board 9 nil) player-list [(tictactoe.player.Human. "x") (tictactoe.player.Human. "o")]]
		(set-output "Welcome to Tic Tac Toe!")
		(start board player-list)))