(ns tictactoe.game_rules
	(:require [tictactoe.board :refer [get-marker get-markers row-taken? winning-row-present? generate-rows set-marker full?]]))
	
(defn game-over? [board]
	(winning-row-present? board))

(defn valid-move? [board move-destination]
	(nil? (try (get-marker board (Integer. move-destination)) (catch Exception e false) (finally true))))
	
(defn game-over-with-tie? [board] 
	(and (full? board) (not (winning-row-present? board))))
	
(defn empty-spaces [board]
	(map first (filter #(= nil (second %)) (map-indexed vector board))))
	
(defn get-winner [board] 
	(first (for [row (generate-rows)
		  :let [row-markers (get-markers board row)]
		  :when (row-taken? board row)
		]
		(first row-markers))))