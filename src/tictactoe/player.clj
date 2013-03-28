(ns tictactoe.player
	(:require [tictactoe.io :refer [prompt]]
			  [tictactoe.game-rules :refer [empty-spaces valid-move?]]
			  [tictactoe.negamax :refer [get-best-move]]))

(defn is-human? [player]
  (re-find #"Human" (str (class player))))

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
	(get-best-move (:marker this) board))

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