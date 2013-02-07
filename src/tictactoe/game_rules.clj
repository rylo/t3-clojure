(ns tictactoe.game-rules
	(:require [tictactoe.board :refer [get-marker get-markers row-taken? generate-winning-combinations set-marker full?]]))

(defn winning-row-present? [board]
	(< 0 (count (for [row (generate-winning-combinations)
		  :let [row-markers (get-markers board row)]
		  :when (row-taken? board row)
		]
		row-markers))))

(defn valid-move? [board move-destination]
	(nil? (try (get-marker board (Integer. move-destination)) (catch Exception e false) (finally true))))
	
(defn game-over-with-tie? [board] 
	(and (full? board) (not (winning-row-present? board))))
	
(defn empty-spaces [board]
	(map first (filter #(= nil (second %)) (map-indexed vector board))))
	
(defn get-winner [board] 
	(first (for [row (generate-winning-combinations)
		:let [row-markers (get-markers board row)]
		:when (row-taken? board row)]
		(first row-markers))))
		
(defn game-over? [board]
	(or (winning-row-present? board) (game-over-with-tie? board)))
	
(defn game-not-over? [board]
	(not (game-over? board)))

(defn winning-move-available-in-row?  [player-marker board row]
	(let [row-markers (get-markers board row)]
		(and (= (count (filter #(= player-marker %) row-markers)) 2)
			 (= (count (filter #(= nil %) row-markers)) 1))))

(defn winning-move-available? [player-marker board row-vector] 
	(< 0 (count 
		(for [row row-vector
		:let [outcome (winning-move-available-in-row? player-marker board row)]
		:when (= true outcome)]
		row))))