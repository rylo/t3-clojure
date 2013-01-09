(ns tictactoe.board)

(defn board-size [board] 
	(count board))

(defn make-board [size blank] 
	(take size (repeat blank)))

(defn get-marker [board index]
	(nth board index))

(defn get-markers [board index-vector]
	(into [] (for [index index-vector] (get-marker board (- index 1)))))

(defn set-marker [board marker index] 
	(concat (rest (take index board)) (conj (nthrest board index) marker)))

(defn get-rows [board row-guides]
	(let [board (into [] board)]
		(into [] (for [index-vector row-guides] (get-markers board index-vector)))))

(defn row-taken? [board row]
	(let [row-markers (get-markers board row) first-marker (first row-markers)]
		(= (count (take-while (and #(= first-marker %) #(not= nil %)) row-markers)) (count row))))
		
(defn taken-row-present? [board row-vector]
	(let [number-of-rows (count row-vector)]
		(= (count (take-while #(= true (row-taken? board %)) row-vector)) number-of-rows)))

(defn row-taken-by? [marker board row]
	(= (count (filter #(= marker %) (flatten row))) 3))