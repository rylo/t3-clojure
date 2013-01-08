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
	(let [board (into [] board)] (into [] (for [index-vector row-guides] (get-markers board index-vector)))))
		
(defn row-won? [board row marker]
	(= (count (filter #(= marker %) row)) 3))