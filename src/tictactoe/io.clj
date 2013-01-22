(ns tictactoe.io)

(defn get-input [] (read-line))

(defn print-output [output-str] (println output-str))

(defn prompt [question] (print-output question) (get-input))