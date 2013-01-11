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
		(Integer. (loop [] (let [prompt (prompt "Please make a move:") valid (valid-move? board prompt)]
				(if valid
					prompt
					(recur)))))))