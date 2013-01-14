(ns tictactoe.player
	(:require [tictactoe.io :refer :all]
			  [tictactoe.game_rules :refer :all]))

(defprotocol Player
	(marker [this])
	(get-move [this board]))

(defrecord Human [marker]
	Player
	(marker [this] (:marker this))
	(get-move [this board]
		(loop [prompt-return-value (prompt "Please make a move:")] 
			(let [valid (valid-move? board prompt-return-value)]
					(if valid
						(println valid)
						(recur (prompt "Invalid move, please try again.")))))))