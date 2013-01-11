(ns tictactoe.game_rules
	(:require [tictactoe.board :refer [get-marker taken-row-present? generate-rows set-marker]]))
	
(defn game-over? [board]
	(taken-row-present? board (generate-rows)))

(defn valid-move? [board move-destination]
	(nil? (try (get-marker board (Integer. move-destination)) (catch Exception e false) (finally true))))