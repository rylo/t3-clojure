(ns tictactoe.game
	(:require [tictactoe.board :refer [get-marker]]
			  [tictactoe.io :refer [prompt]]))

(defn game-over? [function board row-vector]
	(function board row-vector))

(defn valid-move? [board move-destination]
	(nil? (try (get-marker board (Integer. move-destination)) (catch Exception e false) (finally true))))

(defn get-human-move [board]
	(loop []
		(let [prompt (prompt "Please make a move:") valid (valid-move? board prompt)]
		(if (true? valid)
			prompt
			(recur)))))