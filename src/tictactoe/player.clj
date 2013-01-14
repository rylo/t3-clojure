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
		(loop [prompt-message "Please make a move:"]
			(let [prompt-return-value (prompt prompt-message) valid (valid-move? board prompt-return-value)]
					(if valid
						(Integer. prompt-return-value)
						(recur "Invalid move, please try again."))))))
						
(defrecord EasyComputer [marker]
	Player
	(marker [this] (:marker this))
	(get-move [this board]
		(rand-nth (empty-spaces board))))