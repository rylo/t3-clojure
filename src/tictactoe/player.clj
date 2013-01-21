(ns tictactoe.player
	(:require [tictactoe.io :refer [prompt]]
			  [tictactoe.game_rules :refer [empty-spaces valid-move? get-winner game-over-with-tie? game-over?]]))

(defprotocol Player
	(marker [this])
	(get-move [this board]))

(defmulti get-move (fn [this board] (str (type this))))

(defmethod get-move "class tictactoe.player.Human" [this board] 
	(loop [prompt-message "Please make a move:"]
		(let [prompt-return-value (prompt prompt-message) valid (valid-move? board prompt-return-value)]
				(if valid
					(Integer. prompt-return-value)
					(recur "Invalid move, please try again.")))))

(defmethod get-move "class tictactoe.player.EasyComputer" [this board] 
	(rand-nth (empty-spaces board)))
	
(defmethod get-move "class tictactoe.player.UltimateComputer" [this board] 
	(rand-nth (empty-spaces board)))

(defrecord Human [marker]
	Player
	(marker [this] (:marker this))
	(get-move [this board]))

(defrecord EasyComputer [marker]
	Player
	(marker [this] (:marker this))
	(get-move [this board]))

(defrecord UltimateComputer [marker]
	Player
	(marker [this] (:marker this))
	(get-move [this board]))
	
(defn get-score [player-marker board]
	(if (or (game-over-with-tie? board) (not (game-over? board)))
		0
		(if (= player-marker (get-winner board))
			 100
			-100)))