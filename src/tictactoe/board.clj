(ns tictactoe.board)

(defn board-size [board] 
	(count board))

(defn generate-winning-combinations [] [[0 1 2][3 4 5][6 7 8][0 3 6][1 4 7][2 5 8][0 4 8][2 4 6]])

(defn make-board [size blank] 
	(take size (repeat blank)))

(defn get-marker [board index]
	(nth board index))

(defn get-markers [board row-index-vector]
	(for [index row-index-vector] (get-marker board index)))

(defn set-marker [board marker index]
	(if (nil? index)
		board
		(assoc (into [] board) index marker)))

(defn get-rows [board row-guides]
	(let [board (into [] board)]
		(for [index-vector row-guides] (get-markers board index-vector))))

(defn row-taken? [board row]
	(let [row-markers (get-markers board row) first-marker (first row-markers)]
		(if (not= nil first-marker)
			(= (count (take-while #(= first-marker %) row-markers)) (count row))
			false)))

(defn row-taken-by? [marker board row]
	(= (count (filter #(= marker %) (flatten row))) 3))
	
(defn full? [board] (= (board-size board) (count (filter #(not (nil? %)) board))))