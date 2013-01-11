(ns tictactoe.board)

(defn board-size [board] 
	(count board))

(defn printable-row [row] 
	(format "|%s|\n" (clojure.string/join "|" row)))

(defn printable-board [board]
	(loop [rows (partition 3 board) full-string ""]
		(if (not= 0 (count rows))
			(let [row (first rows) updated-full-string (str full-string (printable-row row))]
				(recur (rest rows) updated-full-string))
				(format "_______\n%s_______" full-string))))

(defn generate-rows [] [[1 2 3][4 5 6][7 8 9][1 4 7][2 5 8][3 6 9][1 5 9][3 5 7]])

(defn make-board [size blank] (take size (repeat blank)))

(defn get-marker [board index] (nth board index))

(defn get-markers [board index-vector]
	(for [index index-vector] (get-marker board (- index 1))))

(defn set-marker [board marker index] 
	(concat (rest (take index board)) (conj (nthrest board index) marker)))

(defn get-rows [board row-guides]
	(let [board (into [] board)]
		(for [index-vector row-guides] (get-markers board index-vector))))

(defn row-taken? [board row]
	(let [row-markers (get-markers board row) first-marker (first row-markers)]
		(= (count (take-while (and #(= first-marker %) #(not= nil %)) row-markers)) (count row))))
		
(defn taken-row-present? [board row-vector]
	(let [number-of-rows (count row-vector)]
		(= (count (take-while #(= true (row-taken? board %)) row-vector)) number-of-rows)))

(defn row-taken-by? [marker board row]
	(= (count (filter #(= marker %) (flatten row))) 3))