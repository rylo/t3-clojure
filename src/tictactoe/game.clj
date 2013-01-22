(ns tictactoe.game
	(:require [tictactoe.player :refer :all]
			  [tictactoe.board :refer [printable-board make-board get-marker generate-winning-combinations set-marker]]
			  [tictactoe.io :refer [prompt print-output]]
			  [tictactoe.game_rules :refer [get-winner game-won? game-over-with-tie? valid-move?]]))
			
(defn alternate-players [player-list current-player] 
	(if (= (nth player-list 0) current-player)
		(nth player-list 1)
		(nth player-list 0)))

(defn get-ending-message [board] 
	(if (game-over-with-tie? board)
		"It's a tie!"
		(format "Player %s wins!" (get-winner board))))

(defn start [board player-list]
	(loop [current-player (first player-list) board board]	
		(print-output (printable-board board))
		(let [altered-board (set-marker board (:marker current-player) (get-move current-player board))]
			(if (or (game-won? altered-board) (game-over-with-tie? altered-board))
				(do (print-output (get-ending-message altered-board)) (print-output (printable-board altered-board)))
				(recur (alternate-players player-list current-player) altered-board)))))

(defn set-up-new-game []
	(let [board (make-board 9 nil) player-list [(tictactoe.player.Human. "x") (tictactoe.player.EasyComputer. "o")]]
		(print-output "Welcome to Tic Tac Toe!")
		(start board player-list)))