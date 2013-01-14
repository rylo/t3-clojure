(ns tictactoe.game_rules
	(:require [tictactoe.board :refer [get-marker winning-row-present? generate-rows set-marker full?]]))
	
(defn game-over? [board]
	(winning-row-present? board (generate-rows)))

(defn valid-move? [board move-destination]
	(nil? (try (get-marker board (Integer. move-destination)) (catch Exception e false) (finally true))))
	
(defn game-over-with-tie? [board] 
	(and (full? board) (not (winning-row-present? board (generate-rows)))))