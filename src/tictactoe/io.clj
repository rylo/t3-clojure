(ns tictactoe.io)

(defn get-input [] (read-line))

(defn print-output [output-str] (println output-str))

(defn prompt [question] (print-output question) (get-input))

(defn replace-nil [collection replacement-value]
	(for [item collection] 
		(if (nil? item) replacement-value item)))

(defn printable-row [row]
	(format "|%s|\n" (clojure.string/join "|" (replace-nil row " "))))

(defn printable-board [board]
	(loop [rows (partition 3 board) full-string ""]
		(if (not= 0 (count rows))
			(let [row (first rows) updated-full-string (str full-string (printable-row row))]
				(recur (rest rows) updated-full-string))
			(format "_______\n%s_______" full-string))))